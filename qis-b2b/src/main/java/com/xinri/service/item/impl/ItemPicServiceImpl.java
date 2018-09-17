package com.xinri.service.item.impl;

import com.app.api.DataTable;
import com.google.common.base.Strings;

import com.qis.common.persistence.Page;
import com.qis.common.service.CrudService;
import com.qis.common.util.DateUtils;
import com.xinri.dao.item.ItemPicMapper;
import com.xinri.po.item.ItemPic;
import com.xinri.service.item.IItemPicService;
import com.xinri.vo.item.ItemPicVo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service("itemPicService")
public class ItemPicServiceImpl extends CrudService<ItemPicMapper,ItemPic> implements IItemPicService {

    /**
     * 分页列表
     * @param dt
     * @param searchParams
     * @param id
     * @param type
     * @return
     */
    @Override
    public DataTable<ItemPicVo> findListByDt(DataTable<ItemPicVo> dt, Map<String, Object> searchParams, Long id, Integer type) {
        try {
            Page page = new Page(dt.pageNo()+1, dt.getiDisplayLength());
            ItemPicVo vo = new ItemPicVo();
            List<ItemPicVo> configList = new ArrayList<ItemPicVo>();
            if ( searchParams!= null && searchParams.size() != 0) {
                if (searchParams.containsKey("isDeleted")&&!Strings.isNullOrEmpty(searchParams.get("isDeleted").toString().trim())) {
                    String modelName = searchParams.get("isDeleted").toString().trim();
                    vo.setIsDeleted(Integer.valueOf(modelName));
                }
            }

            vo.setPageCode(type);
            vo.setItemId(id);
            vo.setPage(page);
            configList = dao.findListByVo(vo);
            page.setData(configList);
            dt.setiTotalDisplayRecords(page.getTotalSize());
            dt.setAaData(page.getData());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("配置列表出错"+e.getMessage());
        }
        return dt;
    }


    /**
     * 上传并保存图片
     * @param file
     * @param pageCode
     * @param itemId
     */
    @Transactional(readOnly = false)
    @Override
    public Boolean upload(MultipartFile file, Integer pageCode, Long itemId) {
        WebApplicationContext webApplicationContext= ContextLoader.getCurrentWebApplicationContext();
        String originalFileName = file.getOriginalFilename();//原文件名
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();//截取文件扩展名
        //String systemTime = String.valueOf(System.currentTimeMillis());//时间戳作为文件名
        String systemTime = DateUtils.getCurrentTime();
        String fileName = systemTime + extension;//时间戳+后缀名

        StringBuilder targetFilePath=new StringBuilder("/uf/qis");
        targetFilePath.append("/").append(Calendar.getInstance().get(Calendar.YEAR));
        targetFilePath.append("/").append(Calendar.getInstance().get(Calendar.MONTH)+1);
        targetFilePath.append("/").append(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        targetFilePath.append("/").append(fileName);
        File targetFile = new File(webApplicationContext.getServletContext().getRealPath(targetFilePath.toString()));
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(targetFile);

            StringBuilder targetFilePaths=new StringBuilder("/uf/qis");
            targetFilePaths.append("/").append(Calendar.getInstance().get(Calendar.YEAR));
            targetFilePaths.append("/").append(Calendar.getInstance().get(Calendar.MONTH)+1);
            targetFilePaths.append("/").append(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            targetFilePaths.append("/").append(systemTime + "_t"+ extension);
            File targetFiles = new File(webApplicationContext.getServletContext().getRealPath(targetFilePaths.toString()));
            if(isImageFormat(extension)){ //生成缩略图
                 fileName = generateThumbnailImage(targetFile,targetFiles.toString());
            }
            ItemPic pic = new ItemPic();
            pic.setIsDeleted(0);
            pic.setItemId(itemId);
            pic.setPageCode(pageCode);
            pic.setPicLayout(extension);
            pic.setPicSize(file.getSize());
            pic.setPicUrl(targetFilePath.toString().toLowerCase());
            pic.setPicSmall(targetFilePaths.toString().toLowerCase());
            this.saveOrUpdate(pic);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 删除服务器图片
     * @param path
     */
    public void deletePic(String path){
        WebApplicationContext webApplicationContext= ContextLoader.getCurrentWebApplicationContext();
        File file=new File(webApplicationContext.getServletContext().getRealPath(path));
        logger.info(file.getAbsolutePath());
        if(file.exists() && file.isFile()){
            file.delete();
        }
    }


    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Boolean deleteOne(Long id) {
        boolean statu = false;
        try {
            dao.delete(id);
            statu = true;
        }catch (Exception e) {
            logger.error("删除出错了"+e.getMessage());
        }
        return statu;
    }


    /**
     * 设置默认
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Boolean defaultOne(Long id) {
        boolean statu = false;
        try {
            ItemPic pic = new ItemPic();
            pic = dao.get(id);

            ItemPic itemPic = new ItemPic();
            itemPic.setPicSort(0);
            itemPic.setWhereSqlParam(" t.ITEM_ID="+pic.getItemId() + " AND t.PAGE_CODE =" +pic.getPageCode() + " AND t.PIC_SORT =1" );
            dao.updateByCondition(itemPic);

            pic.setPicSort(1);
            dao.update(pic);
            statu = true;
        }catch (Exception e) {
            logger.error("设置默认出错了"+e.getMessage());
        }
        return statu;
    }

    /**
     * 取消默认
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public Boolean cancelOne(Long id) {
        boolean statu = false;
        try {
            ItemPic itemPic = new ItemPic();
            itemPic.setId(id);
            itemPic.setPicSort(0);
            dao.update(itemPic);
            statu = true;
        }catch (Exception e) {
            logger.error("取消默认出错了"+e.getMessage());
        }
        return statu;
    }


    /**
     * 判断上传附件类型，看是否有生成缩略图的权限
     *
     * @param suffix
     *            只有.tff .tiff .png .gif .jpg .jpeg 才会生成缩略图
     *
     * @return true /false
     */
    public Boolean isImageFormat(String suffix) {
        return ".TFF".equalsIgnoreCase(suffix)
                || ".TIFF".equalsIgnoreCase(suffix)
                || ".PNG".equalsIgnoreCase(suffix)
                || ".GIF".equalsIgnoreCase(suffix)
                || ".JPG".equalsIgnoreCase(suffix)
                || ".JPEG".equalsIgnoreCase(suffix);
    }


    /**
     * 生成缩略图
     * @param localFile
     * @param path
     * @return 返回生成的缩略图文件名
     */
    public String generateThumbnailImage(File localFile,String path){
        try{
//            String smallImgPath = path ;//1464947211905_s.png
            String thumbImgPath = path ;//1464947211905_t.png
            //图像处理
            BufferedImage image = ImageIO.read(localFile);
            int imageWidth = image.getWidth();
            int imageHeitht = image.getHeight();
            //生成缩小图，尺寸除以4倍
//            Thumbnails.of(image).size(imageWidth/4, imageHeitht/4).outputQuality(0.5f).toFile(smallImgPath);
            //生成缩略图，尺寸除以2倍
            Thumbnails.of(image).size(imageWidth/2, imageHeitht/2).outputQuality(0.5f).toFile(thumbImgPath);
        }catch(IOException e){
            e.printStackTrace();
        }
        return path;
    }
}
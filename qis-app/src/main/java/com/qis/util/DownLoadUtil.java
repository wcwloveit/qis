package com.qis.util;
import com.app.Setting;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;



/**
 * Created by 善于游勇 on 2017/8/28.
 */
public class DownLoadUtil {
    private static DownLoadUtil downExcel=null;

    private final Logger log= LoggerFactory.getLogger(DownLoadUtil.class);

    private DownLoadUtil(){
    }
    public static DownLoadUtil getInstall(){
        if(null==downExcel){
            synchronized(DownLoadUtil.class){
                if(downExcel==null){
                    downExcel=new DownLoadUtil();
                }
            }
        }
        return downExcel;
    }

    public boolean downLoadFile(String filePath,
                                HttpServletResponse response, String fileName, String fileType)
            throws Exception {
        File file = new File(filePath);  //根据文件路径获得File文件
        //设置文件类型(这样设置就不止是下Excel文件了，一举多得)
        if("pdf".equals(fileType)){
            response.setContentType("application/pdf;charset=GBK");
        }else if("xls".equals(fileType)){
            response.setContentType("application/msexcel;charset=GBK");
        }else if("doc".equals(fileType)){
            response.setContentType("application/msword;charset=GBK");
        }
        //文件名
        response.setHeader("Content-Disposition", "attachment;filename=\""
                + new String(fileName.getBytes(), "ISO8859-1") + "\"");
        response.setContentLength((int) file.length());
        byte[] buffer = new byte[4096];// 缓冲区
        BufferedOutputStream output = null;
        BufferedInputStream input = null;
        try {
            output = new BufferedOutputStream(response.getOutputStream());
            input = new BufferedInputStream(new FileInputStream(file));
            int n = -1;
            //遍历，开始下载
            while ((n = input.read(buffer, 0, 4096)) > -1) {
                output.write(buffer, 0, n);
            }
            output.flush();   //不可少
        } catch (Exception e) {
            //异常自己捕捉
        } finally {
            //关闭流，不可少
            if (input != null)
                input.close();
            if (output != null)
                output.close();
        }
        return false;
    }

    /**
     * 系统导出
     * @param fileName 导出后文件名，需加后缀
     * @param orginName 导出前系统内文件名称，需加后缀
     * @param response
     */
    public void downLoadExcel(String fileName,String orginName,HttpServletResponse response){
        try{
            String targetDirectory = PathUtil.getRootPath(), path="";
            path = targetDirectory + Setting.downloadExcel + "/" + orginName;
            File file = new File(path);// path是指欲下载的文件的路径。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));// 以流的形式下载文件。
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            //解决中文文件 在edge下载时显示乱码
            response.addHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO-8859-1"));
            response.addHeader("Content-Length",""+file.length());
            OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }catch(Exception e){
            e.printStackTrace();
            log.error("下载excel模板文件，错误信息 {}",e);
        }
    }
}


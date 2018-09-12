package com.qis.util;

import com.app.Setting;
import com.google.common.base.Strings;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
*
 * 生成应用的下载文件
 *
*/
public class DownExcel{
    private static DownExcel downExcel=null;
    private final Logger log= LoggerFactory.getLogger(DownExcel.class);
    private DownExcel(){
    }
    public static DownExcel getInstall(){
        if(null==downExcel){
            synchronized(DownExcel.class){
                if(downExcel==null){
                    downExcel=new DownExcel();
                }
            }
        }
        return downExcel;
    }
    /**
     * @Description: (下载excel 模板文件)
     */
    public void downloadExcel(String tip, HttpServletResponse response, LinkedList<String> titleList){
        try{
            String targetDirectory = PathUtil.getRootPath(), path="";
            if(!Strings.isNullOrEmpty(tip)){
                 if(tip.equals("2")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"bilityInf.xls";
                }else if(tip.equals("3")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"companyInfo.xls";
                }else if(tip.equals("4")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"companyPhoneInfo.xls";
                }else if(tip.equals("5")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"userDetail.xls";
                }else if(tip.equals("7")){
                    path=targetDirectory+Setting.downloadExcel+"/"+"crmSignAttAndAdd.xls";
                }else if("8".equals(tip)){
                    path=targetDirectory+Setting.downloadExcel+"/"+"crmAttDeptUser.xls";
                }else if("9".equals(tip)){
                    path=targetDirectory+Setting.downloadExcel+"/"+"orgUser.xlsx";
                }
            }
            File file=new File(path);// path是指欲下载的文件的路径。
            InputStream fis=new BufferedInputStream(new FileInputStream(path));// 以流的形式下载文件。
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
//            response.addHeader("Content-Disposition","attachment;filename="+new String(file.getName().getBytes(),"ISO-8859-1"));
            //解决中文文件 在edge下载时显示乱码
            response.addHeader("Content-Disposition","attachment;filename="+new String(file.getName().getBytes("gb2312"),"ISO-8859-1"));
            response.addHeader("Content-Length",""+file.length());
            OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            //downLoadFile(response,path);
        }catch(Exception e){
            e.printStackTrace();
            log.error("下载excel模板文件，错误信息 {}",e);
        }
    }
    /**
     * @Description: (获取传入参数实际值)
     * @param: @param cc 待传入参数
     * @return: String    retuStr
     */
    private String getValueByCell(Cell cc){
        String retuStr="";
        if(null!=cc){
            try{
               /*CellType 类型 值
                  CELL_TYPE_NUMERIC 数值型 0
				  CELL_TYPE_STRING 字符串型 1
				  CELL_TYPE_FORMULA 公式型 2
				  CELL_TYPE_BLANK 空值 3
				  CELL_TYPE_BOOLEAN 布尔型 4
				  CELL_TYPE_ERROR 错误 5*/
                int cellType=cc.getCellType();
                if(0==cellType){
                   /* Date excelFromDate=HSSFDateUtil.getJavaDate(cc.getNumericCellValue());
                    retuStr=new DateTime(excelFromDate).toString("yyyy-MM-dd HH:mm");*/
                    retuStr=String.valueOf((long)cc.getNumericCellValue());
                }else if(1==cellType){
                    retuStr=cc.getStringCellValue();
                }else if(2==cellType){
                    retuStr=cc.getCachedFormulaResultType()+"";
                }else if(3==cellType){
                    retuStr=cc.getStringCellValue();
                }else if(4==cellType){
                    retuStr=cc.getBooleanCellValue()+"";
                }else if(5==cellType){
                    retuStr=cc.getStringCellValue();
                }
            }catch(Exception e){
                retuStr=cc.getStringCellValue();
                e.printStackTrace();
            }
            if(!com.google.common.base.Strings.isNullOrEmpty(retuStr)){
                Pattern p=Pattern.compile("\\s*|\t|\r|\n");
                Matcher m=p.matcher(retuStr.trim());
                retuStr=m.replaceAll("");
            }
        }
        return retuStr;
    }
    /**
     * 通过响应输出流实现文件下载
     *
     * @param response     响应的请求
     * @param fileLocal    文件的绝对路径 请用/斜杠表示路径
     * @param downloadName 自定义的文件名 ( 不要后缀),如果此值为空则使用时间日期做为默认的文件名
     * @param deleFile     下载完成后是否删除文件（true: 删除 , false：不删除）
     */
    public void downLoadFile(HttpServletResponse response,String fileLocal,String downloadName,boolean deleFile){
        InputStream in=null;
        OutputStream out=null;
        try{
            if(!"".equals(downloadName)){
                downloadName=downloadName+fileLocal.substring(fileLocal.lastIndexOf("."));
            }else{
                downloadName=fileLocal.substring(fileLocal.lastIndexOf("/")+1);
            }
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(downloadName,"UTF-8"));
            in=new FileInputStream(fileLocal);
            int len=0;
            byte buffer[]=new byte[1024];
            out=response.getOutputStream();
            while((len=in.read(buffer))>0){
                out.write(buffer,0,len);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(in!=null){
                try{
                    //
                    in.close();
                    if(deleFile){
                        Thread.sleep(1000l);
                        File file=new File(fileLocal);
                        file.delete();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * excel 2003版本的导出方法 支持多个sheet导出 导出的文件后缀为.xls
     *
     * @param dataMap       要导出的数据
     * @param excelFilePath excel文件的存放位置
     * @param fileName      excel文件名字
     *
     * @return
     */
    public String exportXlsExcel(Map<String,List<String[]>> dataMap,String excelFilePath,String fileName){
        FileOutputStream fout=null;
        String fileLocal="";
        try{
            File file=new File(excelFilePath);
            if(!file.exists()){
                file.mkdirs();
            }
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb=new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet=null;
            List<String[]> dataList=null;
            HSSFCellStyle style=wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            Set<String> keyTitle=dataMap.keySet();
            for(String title : keyTitle){
                sheet=wb.createSheet(title);
                dataList=dataMap.get(title);
                for(int i=0;null!=dataList&&i<dataList.size();i++){
                    // 生成第一行
                    HSSFRow row=sheet.createRow(i);
                    String[] arr=dataList.get(i);
                    for(int j=0;null!=arr&&j<arr.length;j++){
                        // 给这一行的第一列赋值
                        HSSFCell cell=row.createCell(j);
                        String value = arr[j];
                        if(value==null ||"null".equals(value)){
                            value = "";
                        }
                        cell.setCellValue(value);
                        cell.setCellStyle(style);
                        if(i==0){
                            HSSFCellStyle tempStyle=style;
                            tempStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                            tempStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
                            cell.setCellStyle(tempStyle);
                        }
                    }
                }
            }
            // 第六步，将文件存到指定位置
            fileLocal=excelFilePath+"/"+fileName+".xls";
            fout=new FileOutputStream(fileLocal);
            wb.write(fout);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fout != null) {
                    fout.close();
                }
            }catch(IOException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return fileLocal;
    }


    /**
     * excel 2003版本的导出方法 支持多个sheet导出 导出的文件后缀为.xls
     *
     * @param dataMap       要导出的数据
     * @param excelFilePath excel文件的存放位置
     * @param fileName      excel文件名字
     *
     * @return
     */
    public String exportXlsExcel2(Map<String,List<String[]>> dataMap,List<Boolean> isRedList,String excelFilePath,String fileName){
        FileOutputStream fout=null;
        String fileLocal="";
        try{
            File file=new File(excelFilePath);
            if(!file.exists()){
                file.mkdirs();
            }
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb=new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet=null;
            List<String[]> dataList=null;
            HSSFCellStyle style=wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            Set<String> keyTitle=dataMap.keySet();
            for(String title : keyTitle){
                sheet=wb.createSheet(title);
                dataList=dataMap.get(title);
                for(int i=0;null!=dataList&&i<dataList.size();i++){
                    // 生成第一行
                    HSSFRow row=sheet.createRow(i);
                    String[] arr=dataList.get(i);
                    for(int j=0;null!=arr&&j<arr.length;j++){
                        // 给这一行的第一列赋值
                        HSSFCell cell=row.createCell(j);
                        String value = arr[j];
                        if(value==null ||"null".equals(value)){
                            value = "";
                        }

                        if(j==11){
                            boolean redFlag = isRedList.get(i);
                            if(redFlag){
                                HSSFCellStyle tempStyle=wb.createCellStyle();
                                tempStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                                HSSFFont font = wb.createFont();
                                font.setColor(HSSFFont.COLOR_RED); //字体颜色
                                tempStyle.setFont(font);
                                cell.setCellStyle(tempStyle);
                                cell.setCellValue(value);
                            }else{
                                cell.setCellValue(value);
                                cell.setCellStyle(style);
                            }
                        }else{
                            cell.setCellValue(value);
                            cell.setCellStyle(style);
                        }


                        if(i==0){
                            HSSFCellStyle tempStyle=style;
                            tempStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
                            tempStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
                            cell.setCellStyle(tempStyle);
                        }
                    }

                }
            }
            // 第六步，将文件存到指定位置
            fileLocal=excelFilePath+"/"+fileName+".xls";
            fout=new FileOutputStream(fileLocal);
            wb.write(fout);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fout != null) {
                    fout.close();
                }
            }catch(IOException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return fileLocal;
    }


}

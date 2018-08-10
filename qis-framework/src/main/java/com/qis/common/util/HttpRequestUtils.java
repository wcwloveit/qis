package com.qis.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpRequestUtils {
    private static Log logger = LogFactory.getLog(HttpRequestUtils.class);
    // default time out setting , half minute
    private static final int defaultTimeOut = 30 * 1000;

    private static void validateUrl(String url) {
        if (!URLUtils.isUseHttpProtocol(url)) {
            throw new IllegalArgumentException(String.format(
                    "The URL %s is illegal", url));
        }
    }

    public static String doGet(String url, String charSetName, int timeOut)
            throws Exception {
        validateUrl(url);
        try {
            URL ur = new URL(url);
            URLConnection con = ur.openConnection();
            con.setConnectTimeout(timeOut);
            con.setReadTimeout(timeOut);
            BufferedReader rd = new BufferedReader(new InputStreamReader(con
                    .getInputStream(), charSetName));
            StringBuilder sb = new StringBuilder();
            try {
                int k = rd.read();
                while (k != -1) {
                    sb.append((char) k);
                    k = rd.read();
                }
            } catch (Exception ee) {
            } finally {
                if (rd != null) {
                    rd.close();
                }
            }
            return sb.toString();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String doGet(String url, String charSetName) throws Exception {
        return doGet(url, charSetName, defaultTimeOut);
    }

    public static String doGet(String url) throws Exception {
        return doGet(url, "UTF-8", defaultTimeOut);
    }

    public static void doGetFile(String url, int timeOut, String fullFileName)
            throws Exception {
        validateUrl(url);
        InputStream is = null;
        OutputStream os = null;
        try {
            URL ur = new URL(url);
            URLConnection con = ur.openConnection();
            con.setConnectTimeout(timeOut);
            con.setReadTimeout(timeOut);

            is = con.getInputStream();

            // 1K cache
            byte[] bs = new byte[1024];
            // length
            int len;

            os = new FileOutputStream(fullFileName);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static InputStream doGetStream(String url, int timeOut)
            throws Exception {
        validateUrl(url);
        InputStream is = null;
        try {
            URL ur = new URL(url);
            URLConnection con = ur.openConnection();
            con.setConnectTimeout(timeOut);
            con.setReadTimeout(timeOut);
            is = con.getInputStream();
            return is;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception unusede) {
                }
            }
        }
    }

    public static String doPost(String url, Map<String, String> parameters,
                                int timeOut, String charSetName) throws Exception {
        // validate
        validateUrl(url);

        // generate post data form parameters
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> kv : parameters.entrySet()) {
            sb.append(kv.getKey());
            sb.append("=");
            sb.append(URLUtils.decode(kv.getValue()));
            sb.append("&");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        byte[] postData = BytesUtils.toBytes(sb);
        try {
            URL ur = new URL(url);
            URLConnection con = ur.openConnection();

            // setting
            con.setConnectTimeout(timeOut);
            con.setReadTimeout(timeOut);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            con.setRequestProperty("Content-Length", postData.length + "");
            OutputStream os = con.getOutputStream();

            os.write(postData);
            os.flush();
            os.close();
            BufferedReader rd = new BufferedReader(new InputStreamReader(con
                    .getInputStream(), charSetName));
            StringBuilder rsb = new StringBuilder();
            try {
                int k = rd.read();
                while (k != -1) {
                    rsb.append((char) k);
                    k = rd.read();
                }
            } catch (Exception ee) {
            } finally {
                try {
                    rd.close();
                } catch (Exception e) {

                }
            }
            return rsb.toString();
        } catch (Exception e) {
            throw new Exception(url+" 请求异常");
        }
    }

    public static String doPost(String url, Map<String, String> parameters,
                                int timeOut) throws Exception {
        return HttpRequestUtils
                .doPost(url, parameters, timeOut,"UTF-8");
    }

    public static String doPost(String url, Map<String, String> parameters)
            throws Exception {
        return HttpRequestUtils.doPost(url, parameters, defaultTimeOut,
                "UTF-8");
    }

    public static int doHead(String url, int timeOut) throws Exception {
        validateUrl(url);
        try {
            URL ur = new URL(url);
            HttpURLConnection con = (HttpURLConnection) ur.openConnection();
            con.setConnectTimeout(timeOut);
            return con.getResponseCode();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static int doHead(String url) throws Exception {
        return doHead(url, defaultTimeOut);
    }
    public static JSONObject doPostByJson(String httpUrl, JSONObject jsonObject, Integer timeout) throws Exception {
        StringBuffer sb = null;
        HttpURLConnection connection=null;
        OutputStreamWriter out=null;
        BufferedReader reader=null;
        JSONObject jsonObj=null;
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            if (null != timeout) {
                connection.setReadTimeout(timeout);
            } else {
                connection.setReadTimeout(60 * 1000);
            }
//            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            connection.connect();

            //POST请求
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            logger.info("请求url:"+httpUrl+"  请求参数:" + jsonObject.toString());
            out.write(jsonObject.toString());
            out.flush();

            //读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String lines;
            sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            logger.info("响应参数：" + sb);
            if(sb.length()>0){
                //  jsonArray = JSONObject.fromObject(sb+"".replaceAll("\n",""));
                jsonObj = JSONObject.fromObject(sb+"".replaceAll("\n",""));
            }
            // 断开连接
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(httpUrl+" 请求异常");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception(httpUrl+" 请求异常");
        } finally {
            if(out!=null){
                out.flush();
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
            if(connection!=null){
                connection.disconnect();
            }
        }
        return jsonObj;
    }
    public static JSONObject doPostByJson4(String httpUrl, JSONObject jsonObject, Integer timeout) throws IOException {
        StringBuffer sb = null;
        HttpURLConnection connection=null;
        OutputStreamWriter out=null;
        BufferedReader reader=null;
        JSONObject jsonObj=null;
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            if (null != timeout) {
                connection.setReadTimeout(timeout);
            } else {
                connection.setReadTimeout(60 * 1000);
            }
//            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Type", "text/html");
            connection.connect();

            //POST请求
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            logger.info("请求url:"+httpUrl+"  请求参数:" + jsonObject.toString());
            out.write(jsonObject.toString());
            out.flush();

            //读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String lines;
            sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            logger.info("响应参数：" + sb);
            if(sb.length()>0){
                //  jsonArray = JSONObject.fromObject(sb+"".replaceAll("\n",""));
                jsonObj = JSONObject.fromObject(sb+"".replaceAll("\n",""));
            }
            // 断开连接
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(httpUrl+" 请求异常");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(httpUrl+" 请求异常");
        } finally {
            if(out!=null){
                out.flush();
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
            if(connection!=null){
                connection.disconnect();
            }
        }
        return jsonObj;
    }
    public static JSONArray doPostByJson2(String httpUrl, JSONObject jsonObject, Integer timeout) throws IOException {
        StringBuffer sb = null;
        HttpURLConnection connection=null;
        OutputStreamWriter out=null;
        BufferedReader reader=null;
        JSONArray jsonArray=null;
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            if (null != timeout) {
                connection.setReadTimeout(timeout);
            } else {
                connection.setReadTimeout(60 * 1000);
            }
            connection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            connection.connect();

            //POST请求
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            logger.info("请求url:"+httpUrl+"  请求参数:" + jsonObject.toString());
            out.write(jsonObject.toString());
            out.flush();

            //读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String lines;
            sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            logger.info("响应参数：" + sb);
            if("无相应数据".equals(sb.toString())){
               return  null;
            }
            if(sb.length()>0){
                jsonArray = JSONArray.fromObject(sb+"".replaceAll("\n",""));
            }
            // 断开连接
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(httpUrl+" 请求异常");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(httpUrl+" 请求异常");
        } finally {
            if(out!=null){
                out.flush();
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
            if(connection!=null){
                connection.disconnect();
            }
        }
        return jsonArray;
    }

    public static String doPostByJson3(String httpUrl, JSONObject jsonObject, Integer timeout) throws IOException {
        StringBuffer sb = null;
        HttpURLConnection connection=null;
        OutputStreamWriter out=null;
        BufferedReader reader=null;
        try {
            //创建连接
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            if (null != timeout) {
                connection.setReadTimeout(timeout);
            } else {
                connection.setReadTimeout(60 * 1000);
            }
            connection.setRequestProperty("Content-Type", "application/json; charset=utf8");
            connection.connect();

            //POST请求
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            logger.info("请求url:"+httpUrl+"  请求参数:" + jsonObject.toString());
            out.write(jsonObject.toString());
            out.flush();

            //读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String lines;
            sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            logger.info("响应参数：" + sb);
            if("无相应数据".equals(sb.toString())){
                return  null;
            }
            // 断开连接
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(httpUrl+" 请求异常");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(httpUrl+" 请求异常");
        } finally {
            if(out!=null){
                out.flush();
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
            if(connection!=null){
                connection.disconnect();
            }
        }
        return sb.toString();
    }

    public static JSONArray httpRequestGet(String requestUrl,Integer timeout) throws Exception{
        JSONArray jsonArray = null;
        HttpURLConnection httpUrlConn = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        URL url = null;
        try {
            url = new URL(requestUrl);
            // http协议传输
            httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("GET");
            if (null != timeout) {
                httpUrlConn.setReadTimeout(timeout);
            } else {
                httpUrlConn.setReadTimeout(60 * 1000);
            }
            httpUrlConn.connect();
            // 将返回的输入流转换成字符串
            bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(),"utf-8"));

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            httpUrlConn.disconnect();
            if(buffer.length()>0){
                jsonArray = JSONArray.fromObject(buffer+"".replaceAll("\n",""));
            }

        } catch (Exception e) {
            throw new Exception(url+" 请求异常");
        }  finally {

            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(httpUrlConn != null){
                httpUrlConn.disconnect();
            }
        }
        return jsonArray;
    }

    public static JSONObject httpRequestGet2(String requestUrl,Integer timeout) throws IOException{
        JSONObject jsonObject = null;
        HttpURLConnection httpUrlConn = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {

            URL url = new URL(requestUrl);
            // http协议传输
            httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod("GET");
            if (null != timeout) {
                httpUrlConn.setReadTimeout(60 * 1000);
            } else {
                httpUrlConn.setReadTimeout(timeout);
            }
            httpUrlConn.connect();
            // 将返回的输入流转换成字符串
            bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(),"utf-8"));

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            httpUrlConn.disconnect();
            if(buffer.length()>0){
                jsonObject = JSONObject.fromObject(buffer+"".replaceAll("\n",""));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(requestUrl+" 请求异常");
        }  finally {

            if(bufferedReader != null){
                bufferedReader.close();
            }
            if(httpUrlConn != null){
                httpUrlConn.disconnect();
            }
        }
        return jsonObject;
    }

    /**
     * 向指定URL发送POST方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String httpRequestPost5(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setReadTimeout(6000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 向指定URL发送POST方法的请求
     * @param url 发送请求的URL
     * @param map 这里会将map中的key与value转换为name1=value1&name2=value2的形式请求参数，请求参数应该是name1=value1&name2=value2的形式。
     * @return URL所代表远程资源的响应
     */
    public static String httpRequestPost6(String url, Map<String,Object> map) throws Exception{
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setReadTimeout(6000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            // 发送请求参数
            //转换Map
            String param = "";
            Boolean isFirst = true;
            for (Map.Entry<String,Object> entity : map.entrySet()) {
                if(isFirst){
                    param = entity.getKey() + "=" + entity.getValue();
                }else {
                    param = param + "&" + entity.getKey() + "=" + entity.getValue();
                }
                isFirst = false;
            }
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw new Exception(url+" 请求异常");
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static JSONArray doPostByJson7(String url, Map<String,Object> map, Integer timeout) throws IOException {
        StringBuffer sb = null;
        HttpURLConnection connection=null;
        OutputStreamWriter out=null;
        BufferedReader reader=null;
        JSONArray jsonArray=null;

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setReadTimeout(6000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            // 发送请求参数
            //转换Map
            String param = "";
            Boolean isFirst = true;
            for (Map.Entry<String,Object> entity : map.entrySet()) {
                if(isFirst){
                    param = entity.getKey() + "=" + entity.getValue();
                }else {
                    param = param + "&" + entity.getKey() + "=" + entity.getValue();
                }
                isFirst = false;
            }
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            String line;

            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String lines;
            sb = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            logger.info("响应参数：" + sb);
            if("无相应数据".equals(sb.toString())){
                return  null;
            }
            if(sb.length()>0){
                jsonArray = JSONArray.fromObject(sb+"".replaceAll("\n",""));
            }
            // 断开连接
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(url+" 请求异常");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new IOException(url+" 请求异常");
        } finally {
            if(out!=null){
                out.flush();
                out.close();
            }
            if(reader!=null){
                reader.close();
            }
            if(connection!=null){
                connection.disconnect();
            }
        }
        return jsonArray;
    }


    /**
     * post请求，参数
     * @param url
     * @param map
     * @throws IOException
     */
    public static String readContentFromPost(String url,Map<String,Object> map) throws IOException {
        String result = "";
        // Post请求的url，与get不同的是不需要带参数
        URL postUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        // 设置是否向connection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true
        connection.setDoOutput(true);
        // Read from the connection. Default is true.
        connection.setDoInput(true);
        // 默认是 GET方式
        connection.setRequestMethod("POST");
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
        //设置本次连接是否自动重定向
        connection.setInstanceFollowRedirects(false);
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
        // 意思是正文是urlencoded编码过的form参数
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String content = "字段名=" + URLEncoder.encode("字符串值", "UTF-8");
        //转换Map
        String param = "";
        Boolean isFirst = true;
        for (Map.Entry<String,Object> entity : map.entrySet()) {
            if(isFirst){
                param = entity.getKey() + "=" + URLEncoder.encode(entity.getValue().toString(),"UTF-8");
            }else {
                param = param + "&" + entity.getKey() + "=" + URLEncoder.encode(entity.getValue().toString(),"UTF-8");
            }
            isFirst = false;
        }
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
        out.writeBytes(content);
        //流用完记得关
        out.flush();
        out.close();
        //获取响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            result += line;
            System.out.println(line);
        }
        reader.close();
        //该干的都干完了,记得把连接断了
        connection.disconnect();
        return  result;
    }
}

package com.suguiming.myandroid.tool.test;

import android.util.Log;

import com.suguiming.myandroid.tool.MyTool;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suguiming on 15/12/19.
 */
public class HttpTest {

    public static void urlConnectionGET(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.baidu.com");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        builder.append(line);
                    }
                    MyTool.log(builder.toString());

                    if (connection != null){
                        connection.disconnect();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void urlConnectionPOST(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.baidu.com");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    DataOutputStream outputStream = (DataOutputStream)connection.getOutputStream();
                    outputStream.writeBytes("name=andy&password=123");//键值对&分开

                    if (connection != null){
                        connection.disconnect();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void httpClientGET(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://www.baidu.com");

                    HttpResponse response = httpClient.execute(httpGet);//模拟请求
                    int code = response.getStatusLine().getStatusCode();//返回响应码
                    if(code == 200) {
                        InputStream inputStream = response.getEntity().getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null){
                            builder.append(line);
                        }
                        MyTool.log(builder.toString());
                    }

                    }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void httpClientPOST(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://www.baidu.com");

                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("name","admin"));
                    params.add(new BasicNameValuePair("password","123"));
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"utf-8");
                    httpPost.setEntity(entity);

                    HttpResponse response = httpClient.execute(httpPost);//模拟请求
                    int code = response.getStatusLine().getStatusCode();//返回响应码
                    if(code == 200) {
                        HttpEntity httpEntity = response.getEntity();
                        String string = EntityUtils.toString(httpEntity,"utf-8");
                        MyTool.log(string);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


}

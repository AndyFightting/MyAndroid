package com.suguiming.myandroid.base.volley;

import java.io.File;
import java.util.Map;

public interface MultiPartRequest {

    void addFileUpload(String param, File file);
    void addStringUpload(String param, String content);
    
    Map<String,File> getFileUploads();
    Map<String,String> getStringUploads();
}
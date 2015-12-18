package com.suguiming.myandroid.base.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class MultiPartStringRequest extends Request<String> implements MultiPartRequest{

	private final Listener<String> mListener;
	private Map<String,File> fileUploads = new HashMap<>();
	private Map<String,String> stringUploads = new HashMap<>();

    public MultiPartStringRequest(int method, String url, Listener<String> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public void addFileUpload(String param,File file) {
    	fileUploads.put(param,file);
    }
    
    public void addStringUpload(String param,String content) {
    	stringUploads.put(param,content);
    }
    
    public Map<String,File> getFileUploads() {
    	return fileUploads;
    }
    
    public Map<String,String> getStringUploads() {
    	return stringUploads;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

	@Override
	protected void deliverResponse(String response) {
		if(mListener != null) {
			mListener.onResponse(response);
		}
	}
	
    public String getBodyContentType() {
        return null;
    }
}
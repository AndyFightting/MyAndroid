package com.suguiming.myandroid.tool.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by suguiming on 15/12/5.
 */
public class FileUtil {

    public static void saveTextFile(Context context,String fileName,String text){
        FileOutputStream outputStream ;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(text);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static String getTextFile(Context context,String fileName){
        FileInputStream inputStream;
        BufferedReader bufferReader = null;
        StringBuilder contentBuilder = new StringBuilder();
        try {
            inputStream = context.openFileInput(fileName);
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));
            String lineText = "";
            while ((lineText = bufferReader.readLine()) != null){
                contentBuilder.append(lineText);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (bufferReader != null){
                try {
                    bufferReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return contentBuilder.toString();
    }

    public static void saveBitmap(Context context, Bitmap bmp, String bitmapName){
        try {
          File file = new File(context.getFilesDir()+"/"+bitmapName);
          if (file.exists()) {
            file.delete();
          }
           OutputStream os = new FileOutputStream(file);
           bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
           os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmap(Context context,String bitmapName){
        String myJpgPath = context.getFilesDir()+"/"+bitmapName;
        BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeFile(myJpgPath, options);

    }

    public static File createDir(Context context,String directoryName){
        String dirPath = context.getFilesDir()+"/"+directoryName;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile;
    }

    public static File createFile(Context context, String fileName) {
        try {
            String filePath = context.getFilesDir()+"/"+fileName;
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeFile(Context context, String fileName) {
        String filePath = context.getFilesDir()+"/"+fileName;
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }
    }

    public static void removeDir(Context context,String directoryName){
        String dirPath = context.getFilesDir()+"/"+directoryName;
        File dirFile = new File(dirPath);
        removeDir(dirFile);
    }

    public static void removeDir(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                removeDir(childFiles[i]);
            }
            file.delete();
        }
    }

    public static void removeAll(Context context){
        String dirPath = context.getFilesDir()+"/";
        File dirFile = new File(dirPath);
        removeDir(dirFile);
    }
}

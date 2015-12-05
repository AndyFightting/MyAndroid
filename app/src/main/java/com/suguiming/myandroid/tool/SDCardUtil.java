package com.suguiming.myandroid.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by suguiming on 15/12/5.
 */
public class SDCardUtil {

    // 默认都保存在这个文件夹下
    private static final String DEFAULT_DIR = "ProData/";

    public static boolean isEnable() {
        return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static long getTotalMB() {
        if (!isEnable())
            return 0;
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getBlockCountLong();
        return (availableBlocks * blockSize) / 1024 / 1024;
    }

    public static long getAvailableMB() {
        if (!isEnable())
            return 0;
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return (availableBlocks * blockSize) / 1024 / 1024;
    }

    public static long getAvailableByte(){
        if (!isEnable())
            return 0;
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return availableBlocks * blockSize;
    }

    // 路径：/storage/sdcard0/
    public static String getSDCardPath()
    {
        if (!isEnable())
            return "";
        return Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
    }


    public static File createDir(String directoryName){
        //建默认文件夹
        String defaultDir = getSDCardPath() + DEFAULT_DIR;
        File defaultFile = new File(defaultDir);
        if (!defaultFile.exists()){
            defaultFile.mkdir();
        }

        String dirPath = getFilePath(directoryName);
        File dirFile = new File(dirPath);
        if (!dirFile.exists()){
            dirFile.mkdir();
        }
        return dirFile;
    }

    public static File createFile(String fileName){
        try {
            //建默认文件夹
            String dirPath = getSDCardPath() + DEFAULT_DIR;
            File directory = new File(dirPath);
            if (!directory.exists()){
                directory.mkdir();
            }

            //再在默认文件夹里建文件
            File file = new File(getFilePath(fileName));
            if (!file.exists()){
                file.createNewFile();
            }
            return file;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getFilePath(String fileName){
        return getSDCardPath() + DEFAULT_DIR + fileName;
    }

    public static boolean isFileExist(String fileName){
        File file = new File(getFilePath(fileName));
        return file.exists();
    }

    public static boolean saveByte(String fileName,byte[] bytes){
        if(bytes == null ){
            return false;
        }

        OutputStream output = null;
        try {
            if (bytes.length < getAvailableByte()) {
                File file = createFile(fileName);//这里面已经建了默认文件夹
                output = new BufferedOutputStream(new FileOutputStream(file));
                output.write(bytes);
                output.flush();
                return true;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally{
            try{
                if( output != null ){
                    output.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static byte[] getByte(String fileName)
    {
        File file = new File(getFilePath(fileName));
        if( !file.exists() ){
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            return data;
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            try {
                if( inputStream != null ){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean saveBitmap(Bitmap bitmap, String bitmapName){
        if (bitmap == null){
            return false;
        }
        //建默认文件夹
        String dirPath = getSDCardPath() + DEFAULT_DIR;
        File directory = new File(dirPath);
        if (!directory.exists()){
            directory.mkdir();
        }

        File file = new File(getFilePath(bitmapName));
        BufferedOutputStream output = null;
        try {
            output = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, output);
            output.flush();
            output.close();
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally{
            try{
                if( output != null ){
                    output.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Bitmap getBitmap(String bitmapName) {
        String myJpgPath = getFilePath(bitmapName);
        BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeFile(myJpgPath, options);
    }

    public static void removeFile(String fileName){
        String filePath = getFilePath(fileName);
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }
    }

    public static void removeFile(File file) {
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
                removeFile(childFiles[i]);
                }
              file.delete();
        }
      }

    public static void removeAll(){
        String filePath = getSDCardPath() + DEFAULT_DIR;
        File file = new File(filePath);
        if (file.exists()){
            removeFile(file);
        }

        //再建一个空文件夹
        File directory = new File(filePath);
        if (!directory.exists()){
            directory.mkdir();
        }
    }


}

package com.suguiming.myandroid.tool.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.customView.PhotoSheet;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by suguiming on 15/12/12.
 */
public class SelectPhotoUtil {

    public static Activity mActivity;
    public static ResultListener resultListener;
    public static Uri imageUri;
    public static final String imageName = "temp.jpg";

    public static void beginSelect(Activity activity, ResultListener listener) {
        mActivity = activity;
        resultListener = listener;

        PhotoSheet.show(mActivity, PhotoSheet.class, new ItemTapListener() {
            @Override
            public void itemTap(View view) {
                switch (view.getId()) {
                    case R.id.camera_tv:
                        cameraTap();
                        break;
                    case R.id.phone_tv:
                        albumTap();
                        break;
                }
            }
        });
    }

    private static void initData() {
        File outputImage = new File(Environment.getExternalStorageDirectory(), imageName);
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageUri = null;
        imageUri = Uri.fromFile(outputImage);
    }

    private static void cameraTap() {
        initData();

        Intent cameraIntent = new Intent(Task.CAMERA_ACTION);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        mActivity.startActivityForResult(cameraIntent, Task.TAKE_PHOTO_CODE);
    }

    private static void albumTap() {
        initData();

        Intent intent = new Intent(Task.ALBUM_ACTION);
        intent.setType("image/*");
        mActivity.startActivityForResult(intent, Task.ALBUM_CHOOSE_CODE);

    }

    private static void getCropImage() {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(imageUri));
            if (bitmap != null) {
                resultListener.complete(bitmap);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void processPhoto(int requestCode, int resultCode, Intent data) {
        if (resultCode == MyTool.RESULT_OK) {
            switch (requestCode) {
                case Task.TAKE_PHOTO_CODE:
                    startCameraCrop();
                    break;
                case Task.CROP_PHOTO_CODE:
                    getCropImage();
                    break;
                case Task.ALBUM_CHOOSE_CODE:
                    startAlbumCrop(data);

                    // albumChooseFullImage(data);
                    break;
                default:
                    break;
            }
        }
    }

    private static void startCameraCrop() {
        Intent intent = new Intent(Task.CROP_ACTION);
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 450);
        intent.putExtra("outputY", 450);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        mActivity.startActivityForResult(intent, Task.CROP_PHOTO_CODE);
    }

    private static void startAlbumCrop(Intent data) {
        Intent intent = new Intent(Task.CROP_ACTION);
        intent.setDataAndType(data.getData(), "image/*"); //区别 data.getData()
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 450);
        intent.putExtra("outputY", 450);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        mActivity.startActivityForResult(intent, Task.CROP_PHOTO_CODE);
    }

    public interface ResultListener {
        void complete(Bitmap bitmap);
    }

/*
    private static void albumChooseFullImage(Intent data) {
        if (Build.VERSION.SDK_INT >= 19) { //4.4及以上
            handleUpVersion44(data);
        } else {
            handleBelowVersion44(data);
        }
    }

    private static void handleUpVersion44(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(mActivity, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        }
        getAlbumFullImage(imagePath);
    }

    private static void handleBelowVersion44(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        getAlbumFullImage(imagePath);
    }

    private static String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = mActivity.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private static void getAlbumFullImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (bitmap != null) {
                resultListener.complete(bitmap);
            }
        }
    }
*/

}

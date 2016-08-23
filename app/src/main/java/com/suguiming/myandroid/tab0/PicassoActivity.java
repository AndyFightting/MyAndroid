package com.suguiming.myandroid.tab0;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class PicassoActivity extends BaseActivity {

    private ImageView picassoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_picasso);
        showTitleView("Picasso");
        showLeftImg("back_img");

        picassoImage = (ImageView) findViewById(R.id.picasso_image);

//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(picassoImage);
//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").placeholder(R.mipmap.back_img).into(picassoImage);
//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").resize(300, 300).centerCrop().into(picassoImage);
//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").transform(new CropSquareTransformation()).into(picassoImage);
//        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(picassoImage, new Callback() {
//            @Override
//            public void onSuccess() {
//                showToast("成功");
//            }
//
//            @Override
//            public void onError() {
//                showToast("失败");
//            }
//        });

//        Transformation库: https://github.com/wasabeef/picasso-transformations
//        Crop: CropTransformation, CropCircleTransformation, CropSquareTransformation, RoundedCornersTransformation
//        Color: ColorFilterTransformation, GrayscaleTransformation
//        Blur: BlurTransformation
//        Mask: MaskTransformation

//        需要 GPUImage的Transformation库 ：compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.4.1'
//        ToonFilterTransformation, SepiaFilterTransformation, ContrastFilterTransformation InvertFilterTransformation,
//        PixelationFilterTransformation, SketchFilterTransformation SwirlFilterTransformation,
//        BrightnessFilterTransformation, KuwaharaFilterTransformation VignetteFilterTransformation
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").transform(new CropCircleTransformation()).into(picassoImage);

    }

    //自定义Transformation
    public class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "square_picasso()";
        }
    }

}

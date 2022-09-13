package com.yyds.imageloadingframe;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.yyds.imageloadingframe.bitmap.LocalCacheUtils;
import com.yyds.imageloadingframe.bitmap.MemoryCacheUtils;
import com.yyds.imageloadingframe.bitmap.NetCacheUtils;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/13
 * Describe:
 */
public class ImageLoaderUtil {
    private Context mContext;

    private static ImageLoaderUtil instance;

    private ImageLoaderUtil(){

    }

    public static ImageLoaderUtil getInstance() {
        if (instance == null) {
            synchronized (ImageLoaderUtil.class) {
                if (instance == null) {
                    instance = new ImageLoaderUtil();
                }
            }
        }
        return instance;
    }

    public ImageLoaderUtil with(Context context){
        mContext = context;
        return this;
    }

    public void disPlay(ImageView imageView,String url){
        //先去内存中找
        Bitmap bitmapToMemory = MemoryCacheUtils.getInstance().getBitmapFromMemCache(url);
        if (bitmapToMemory != null) {
            imageView.setImageBitmap(bitmapToMemory);
            return;
        }

        //如果内存没有再去内部存储缓存
        Bitmap bitmapToLocal = LocalCacheUtils.getInstance().setPath(mContext).getBitmapToLocal(url);
        if (bitmapToLocal != null) {
            imageView.setImageBitmap(bitmapToLocal);
            MemoryCacheUtils.getInstance().addBitmapToMemoryCache(url,bitmapToLocal);
            return;
        }
        //如果内部存储没有再去网络缓存
        NetCacheUtils.getInstance().setContext(mContext).getBitmapForNet(url,imageView);
    }
}

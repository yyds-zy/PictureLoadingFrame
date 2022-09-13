package com.yyds.imageloadingframe.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yyds.imageloadingframe.util.MD5Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
 * Created by 阿飞の小蝴蝶 on 2022/9/13
 * Describe: 本地缓存工具
 */
public class LocalCacheUtils {
    public String CACHE_PATH;

    private static LocalCacheUtils instance;
    private LocalCacheUtils(){

    }

    public static LocalCacheUtils getInstance() {
        if (instance == null) {
            synchronized (LocalCacheUtils.class) {
                if (instance == null) {
                    instance = new LocalCacheUtils();
                }
            }
        }
        return instance;
    }

    public LocalCacheUtils setPath(Context context) {
        //CACHE_PATH = context.getCacheDir().getAbsolutePath()+"/bitmaps";
        CACHE_PATH = context.getExternalCacheDir().getAbsolutePath()+"/bitmaps";
        return this;
    }

    public void setBitmapToLocal(String name, Bitmap bitmap){
        try {
            String encode = MD5Encoder.encode(name);
            File file = new File(CACHE_PATH,encode);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitmapToLocal(String name){
        try {
            String encode = MD5Encoder.encode(name);
            File file = new File(CACHE_PATH,encode);
            File parentFile = file.getParentFile();
            if (parentFile.exists()) {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

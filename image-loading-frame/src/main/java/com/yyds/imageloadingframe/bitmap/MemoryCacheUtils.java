package com.yyds.imageloadingframe.bitmap;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.yyds.imageloadingframe.util.LogUtil;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/13
 * Describe: 内存缓存工具
 */
public class MemoryCacheUtils {

    public static MemoryCacheUtils instance;
    private LruCache<String, Bitmap> mLruCache;

    private MemoryCacheUtils(){
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
        LogUtil.getInstance().output(cacheSize+"----- cacheSize ");

        mLruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount() / 1024;
                LogUtil.getInstance().output(byteCount+"");
                return byteCount;
            }
        };
    }

    public static MemoryCacheUtils getInstance() {
        if (instance == null) {
            synchronized (MemoryCacheUtils.class) {
                if (instance == null) {
                    instance = new MemoryCacheUtils();
                }
            }
        }
        return instance;
    }




    public void addBitmapToMemoryCache(String name,Bitmap bitmap){
        if (bitmap == null) return;
        if (getBitmapFromMemCache(name) == null) {
            mLruCache.put(name, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        int size = mLruCache.size();
        LogUtil.getInstance().output(size+"");
        return mLruCache.get(key);
    }
}

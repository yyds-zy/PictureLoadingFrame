package com.yyds.imageloadingframe.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 阿飞の小蝴蝶 on 2022/9/13
 * Describe: 网络缓存工具类
 */
public class NetCacheUtils {

    private Context mContext;
    public static NetCacheUtils instance;
    private NetCacheUtils() {}

    public static NetCacheUtils getInstance() {
        if (instance == null) {
            synchronized (NetCacheUtils.class) {
                if (instance == null) {
                    instance = new NetCacheUtils();
                }
            }
        }
        return instance;
    }

    public NetCacheUtils setContext(Context context){
        mContext = context;
        return this;
    }

    public void getBitmapForNet(String pic_url, ImageView imageView) {
        new BitmapTask().execute(imageView,pic_url);
    }

    class BitmapTask extends AsyncTask<Object ,Void , Bitmap> {
        private ImageView iv_image;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            iv_image = (ImageView) objects[0];
            url = (String) objects[1];
            return downLoadBitmap(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            iv_image.setImageBitmap(bitmap);
            LocalCacheUtils.getInstance().setPath(mContext).setBitmapToLocal(url,bitmap);
            //这里不对  需要优化
            MemoryCacheUtils.getInstance().addBitmapToMemoryCache(url,bitmap);
        }
    }

    private Bitmap downLoadBitmap(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //图片压缩
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize=1;//宽高压缩为原来的1/2
                options.inPreferredConfig=Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream(),null,options);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

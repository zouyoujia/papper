package org.uplus.papper.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by youjia.zyj on 2014/5/30.
 */
public class ImageLoaderUtil {
    public static final int FADE_IN_TIME = 300; //毫秒
    private static Handler mHandler = new Handler();
    private static DisplayImageOptions options;

    public static void initImageLoader(Context applicationContext) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(applicationContext)
                .threadPriority(Thread.NORM_PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        //.writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(android.R.color.transparent)
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(FADE_IN_TIME))
                .handler(mHandler)
                .build();
    }

    public static void displayImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }

}

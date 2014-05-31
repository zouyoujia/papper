package org.uplus.papper;

import android.app.Application;

import org.uplus.papper.utils.JobUtil;
import org.uplus.papper.utils.ImageLoaderUtil;

/**
 * Created by youjia.zyj on 2014/5/30.
 */
public class PapperApplication extends Application {
    private static PapperApplication instance;

    public PapperApplication() {
        instance = this;
    }

    public static PapperApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        JobUtil.initManager(this);

        ImageLoaderUtil.initImageLoader(this);
    }
}

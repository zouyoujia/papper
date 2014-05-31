package org.uplus.papper.utils;

import android.content.Context;
import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;

/**
 * Created by u+ on 5-13.
 */
public class JobUtil {
    private static final String TAG = "JobUtil";
    private static JobManager jobManager;

    public static void initManager(Context applicationContext) {
        Configuration configuration = new Configuration.Builder(applicationContext)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(5)//up to 3 consumers at a time
                .loadFactor(3)//3 org.uplus.papper.jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();
        jobManager = new JobManager(applicationContext, configuration);
    }

    public static JobManager getManager() {
        return jobManager;
    }

    public static void addJob(Job job) {
        if (jobManager == null) {
            Log.e(TAG, "JobManager not initialized!");
            return;
        }
        jobManager.addJob(job);
    }

    public static void addJobInBackground(Job job) {
        if (jobManager == null) {
            Log.e(TAG, "JobManager not initialized!");
            return;
        }
        jobManager.addJobInBackground(job);
    }
}

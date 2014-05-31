package org.uplus.papper.jobs;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 * Created by U+ on 14-3-19.
 * 用来承载简单的本地操作。如数据库的一些操作
 */
public abstract class SimpleLocalJob extends Job {

    public SimpleLocalJob() {
        super(new Params(Priority.HIGH).groupBy("SimpleLocalJob"));
    }

    @Override
    public void onAdded() {
        //do noting
    }

    @Override
    protected void onCancel() {
        //do noting
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}

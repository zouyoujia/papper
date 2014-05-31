package org.uplus.papper.jobs;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.uplus.papper.entities.Api;
import org.uplus.papper.entities.Pic;
import org.uplus.papper.gson.GsonUtil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by youjia.zyj on 2014/5/29.
 */
public class FetchPicJob extends Job {
    public static final String[] TITLES = {
        "美女","摄影","壁纸"
    };
    public static final String[] CHANNELS = {
            "http://image.baidu.com/channel/imgs?c=%E7%BE%8E%E5%A5%B3&s=0&pn=0&rn=300",
            "http://image.baidu.com/channel/imgs?c=%E6%91%84%E5%BD%B1&s=0&pn=0&rn=300",
            "http://image.baidu.com/channel/imgs?c=%E5%A3%81%E7%BA%B8&s=0&pn=0&rn=300"
    };

    private int channel = 0;
    public FetchPicJob(int channel) {
        super(new Params(Priority.HIGH).groupBy("fetch_pic_job"));
        this.channel = channel;
    }

    @Override
    public void onAdded() {
        //do nothing
    }

    @Override
    public void onRun() throws Throwable {
        Reader reader = null;
        Api result = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(CHANNELS[channel]));
            HttpResponse response = client.execute(request);
            reader = new InputStreamReader(response.getEntity().getContent());
            result = GsonUtil.getGson().fromJson(reader, Api.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Pic> picList = new ArrayList<Pic>();
        if (result != null && result.getImgs() != null) {
            JsonArray array = result.getImgs().getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonElement element = array.get(i);
                Pic pic = GsonUtil.getGson().fromJson(element, Pic.class);
                if (pic.getId() != 0 && pic.getImageUrl() != null) {
                    picList.add(pic);
                }
            }
        }
        EventBus.getDefault().post(new JobDoneEvent(channel, picList));
    }

    @Override
    protected void onCancel() {
        //do nothing
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }

    public class JobDoneEvent {
        int channel;
        List<Pic> picList;

        public JobDoneEvent(int channel, List<Pic> picList) {
            this.channel = channel;
            this.picList = picList;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public List<Pic> getPicList() {
            return picList;
        }

        public void setPicList(List<Pic> picList) {
            this.picList = picList;
        }
    }
}

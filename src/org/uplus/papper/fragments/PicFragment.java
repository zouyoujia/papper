package org.uplus.papper.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;

import org.uplus.papper.R;
import org.uplus.papper.adapters.PicAdapter;
import org.uplus.papper.entities.Pic;
import org.uplus.papper.jobs.FetchPicJob;
import org.uplus.papper.utils.JobUtil;

import java.io.File;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by youjia.zyj on 2014/5/31.
 */
public class PicFragment extends Fragment {
    public static final String ARG_CHANNEL = "channel";

    private PicAdapter mAdapter;
    private List<Pic> mPicList;
    private int mChannel;
    private boolean dataDirty = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        mChannel = getArguments().getInt(ARG_CHANNEL);
        JobUtil.addJob(new FetchPicJob(mChannel));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        mAdapter = new PicAdapter(inflater);
        initListView(rootView, mAdapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (dataDirty) {
            refreshUi();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    private void initListView(View rootView, ListAdapter adapter) {
        ListView listView = ((ListView) rootView.findViewById(R.id.paper_list));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Pic pic = (Pic) parent.getAdapter().getItem(position);
                File file = DiskCacheUtils.findInCache(pic.getImageUrl(), ImageLoader.getInstance().getDiskCache());
                if (file == null || !file.exists()) {
                    Toast.makeText(getActivity(), "file not exists", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "image/*");
                    startActivity(intent);
                }
            }
        });
    }

    private void refreshUi() {
        dataDirty = false;
        mAdapter.setPics(mPicList);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(FetchPicJob.JobDoneEvent event) {
        if (mChannel == event.getChannel()) {
            mPicList = event.getPicList();
            if (isVisible()) {
                refreshUi();
            } else {
                dataDirty = true;
            }
        }
    }
}

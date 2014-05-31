package org.uplus.papper.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.uplus.papper.R;
import org.uplus.papper.entities.Pic;
import org.uplus.papper.utils.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youjia.zyj on 2014/5/28.
 */
public class PicAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<Pic> picList = new ArrayList<Pic>();

    public PicAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setPics(List<Pic> pics) {
        picList.clear();
        picList.addAll(pics);
        notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public Pic getItem(int position) {
        return picList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.paper_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = ViewHolder.getFromView(convertView);
        }
        viewHolder.render(getItem(position));
        return convertView;
    }

    private static class ViewHolder {
        ImageView image;
        public ViewHolder(View rootView) {
            image = (ImageView) rootView.findViewById(R.id.pic);
        }

        public static ViewHolder getFromView(View view) {
            Object tag = view.getTag();
            if (tag instanceof ViewHolder) {
                return (ViewHolder) tag;
            } else {
                return new ViewHolder(view);
            }
        }

        public void render(Pic pic) {
            ImageLoaderUtil.displayImage(pic.getImageUrl(), image);
        }
    }
}

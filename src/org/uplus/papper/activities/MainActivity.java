package org.uplus.papper.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import org.uplus.papper.R;
import org.uplus.papper.adapters.PicPagerAdapter;

/**
 * Created by youjia.zyj on 2014/5/28.
 */
public class MainActivity extends FragmentActivity {
    boolean isVisible = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initViewPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisible = false;
    }

    private void initViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        PicPagerAdapter adapter = new PicPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

}
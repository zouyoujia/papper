package org.uplus.papper.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import org.uplus.papper.R;
import org.uplus.papper.adapters.PicPagerAdapter;
import org.uplus.papper.views.PagerSlidingTabStrip;

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
        final int colors[] = getResources().getIntArray(R.array.gplus_colors);
        final PagerSlidingTabStrip slidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.pager_title_strip);
        slidingTabStrip.setViewPager(viewPager);
        slidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                //do noting
            }

            @Override
            public void onPageSelected(int i) {
                slidingTabStrip.setIndicatorColor(colors[i % colors.length]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //do nothing
            }
        });
    }

}
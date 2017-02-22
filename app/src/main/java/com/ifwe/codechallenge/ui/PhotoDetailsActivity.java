package com.ifwe.codechallenge.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ifwe.codechallenge.R;
import com.ifwe.codechallenge.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoDetailsActivity extends AppCompatActivity implements ViewPager
        .OnPageChangeListener {

    private static final String LOG_TAG = PhotoDetailsActivity.class.getSimpleName();

    private static final String STATE_CURRENT_POSITION = "state_current_position";
    private static final String STATE_OLD_POSITION = "state_old_position";

    private PhotoDetailsAdapter adapter;
    private ViewPager viewPager;
    private List<Photo> photoList;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.photo_details);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        photoList = new ArrayList<>();
        getDataIntent(getIntent());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new PhotoDetailsAdapter(getSupportFragmentManager());
        adapter.setPhotoList(photoList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(currentPosition, true);

    }

    private void getDataIntent(Intent intent) {
        if (intent.hasExtra(MainActivity.EXTRA_DATA)) {
            photoList = intent.getParcelableArrayListExtra(MainActivity.EXTRA_DATA);
            currentPosition = intent.getExtras().getInt(MainActivity.EXTRA_POSITION);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}



package com.ifwe.codechallenge.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.ifwe.codechallenge.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoDetailsAdapter extends FragmentStatePagerAdapter {

    private List<Photo> photoList;
    private PhotoDetailsFragment currentFragment;

    public PhotoDetailsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        photoList = new ArrayList<>();

    }

    @Override
    public Fragment getItem(int position) {
        return PhotoDetailsFragment.newInstance(photoList.get(position), position);

    }

    public void setPhotoList(List<Photo> list) {
        this.photoList = list;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentFragment = (PhotoDetailsFragment) object;
    }

    public PhotoDetailsFragment getCurrentDetailsFragment() {
        return currentFragment;
    }
}

package com.ifwe.codechallenge.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ifwe.codechallenge.R;
import com.ifwe.codechallenge.models.Photo;
import com.squareup.picasso.Picasso;


public class PhotoDetailsFragment extends Fragment {


    private static final String LOG_TAG = PhotoDetailsFragment.class.getSimpleName();
    private static final String ARG_POSITION = "position";
    private static final String ARG_PHOTO = "photo";
    private Photo photoDetails;
    private ImageView detailImageView;
    private TextView descriptionTextView;
    private int position;


    public static PhotoDetailsFragment newInstance(Photo photo, int position) {
        PhotoDetailsFragment fragment = new PhotoDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PHOTO, photo);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }

    public PhotoDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
                photoDetails = getArguments().getParcelable(ARG_PHOTO);
                position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = (ScrollView) inflater.inflate(R.layout.fragment_photo_details,
                container, false);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.linear_layout);
        detailImageView = (ImageView) linearLayout.findViewById(R.id.detail_imageview);
        descriptionTextView = (TextView) linearLayout.findViewById(R.id
                .details_description_textview);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Picasso.with(getActivity())
                .load(photoDetails.getUrlZ())
                .placeholder(R.drawable.image)
                .into(detailImageView);
        descriptionTextView.setText(Html.fromHtml(photoDetails.getDescription().getContent()));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

package com.ifwe.codechallenge.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.ifwe.codechallenge.FlickrRecentPhotosApiService;
import com.ifwe.codechallenge.R;
import com.ifwe.codechallenge.RecentPhotosSevice;
import com.ifwe.codechallenge.RecyclerItemClickListener;
import com.ifwe.codechallenge.SpacesItemDecoration;
import com.ifwe.codechallenge.models.Details;
import com.ifwe.codechallenge.models.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final int PER_PAGE = 50;
    public static final String EXTRAS = "description,url_z,url_m";
    public static final String EXTRA_DATA = "PHOTO_LIST";
    public static final String EXTRA_POSITION = "position";

    private RecyclerView photosRecyclerView;
    private PhotoAdapter photoAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        photosRecyclerView = (RecyclerView) findViewById(R.id.photos_gallery);
        photosRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
        photosRecyclerView.addItemDecoration(new SpacesItemDecoration(6));
        photoAdapter = new PhotoAdapter(this);
        photosRecyclerView.setAdapter(photoAdapter);
        photosRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        handleClick(view, position);
                    }
                }));
        showSpinner();
        getRecentPhotos();
    }

    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void getRecentPhotos(){
        if(isOnline()) {
            RecentPhotosSevice photosSevice = new RecentPhotosSevice();
            Retrofit retrofit = photosSevice.getRestAdapter();
            FlickrRecentPhotosApiService service = retrofit.create(FlickrRecentPhotosApiService.class);

            Call<Details> callPhotos = service.getRecentPhotos(PER_PAGE, EXTRAS);
            callPhotos.enqueue(new Callback<Details>() {
                @Override
                public void onResponse(Call<Details> call, Response<Details> response) {
                    Details details = response.body();
                    List<Photo> list = details.getPhotos().getPhoto();
                    photoAdapter.setData(list);
                    hideSpinner();
                }

                @Override
                public void onFailure(Call<Details> call, Throwable t) {
                    hideSpinner();
                    showErrorDialog(getString(R.string.error_title), t.getMessage());

                }
            });
        } else {
            hideSpinner();
            showErrorDialog(getString(R.string.network_error_title), getString(R.string.network_error_msg));
        }
    }

    private void handleClick(View view, int position){
        Intent intent = new Intent(this, PhotoDetailsActivity.class);
        List<Photo> list = photoAdapter.getPhotoList();
        intent.putParcelableArrayListExtra(EXTRA_DATA, (ArrayList) list);
        intent.putExtra(EXTRA_POSITION, position);
        startActivity(intent);
    }

    private void showSpinner(){
       progressBar.setVisibility(View.VISIBLE);
       photosRecyclerView.setVisibility(View.GONE);
    }

    private void hideSpinner(){
        progressBar.setVisibility(View.GONE);
        photosRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorDialog(String errorDialogTitle, String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(errorDialogTitle);
        builder.setMessage(errorMessage);
        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

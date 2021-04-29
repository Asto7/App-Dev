package com.project.headlines;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.headlines.adapters.AdapterListNews;
import com.project.headlines.clicklisteners.AdapterItemClickListener;
import com.project.headlines.clicklisteners.NewsDialogClickListeners;
import com.project.headlines.databinding.NewsDialogBinding;
import com.project.headlines.model.News;

import java.util.List;

import butterknife.BindView;

public class SaveActivity extends AppCompatActivity implements LifecycleOwner, AdapterItemClickListener {
    BottomNavigationView bottom_nav;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    AdapterListNews adapterListNews;
    List<News> newsList;
    DatabaseHandler dbSavedItem;
    Context context;
    ImageFilterButton ifg;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_headlines: {
                    Intent intent = new Intent(SaveActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    return true;
                }
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);


        bottom_nav = findViewById(R.id.navigation);
        bottom_nav.setSelectedItemId(R.id.navigation_saved);

        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        dbSavedItem = new DatabaseHandler(this);
        context = this;
        recyclerView = findViewById(R.id.recyclerView);

        refresh();
    }

    private void refresh() {
        newsList = dbSavedItem.getAllNews();

        adapterListNews = new AdapterListNews(newsList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapterListNews);
    }


    @Override
    public void onNewsItemClick(News news) {
        showDialogPolygon(news);
    }

    private void showDialogPolygon(News news) {
        final Dialog dialog = new Dialog(this);
        NewsDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getApplicationContext()), R.layout.dialog_header_polygon, null, false);
        binding.setNews(news);
        binding.setListener(new NewsDialogClickListeners() {
            @Override
            public void onGotoWebSiteClick(String url) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

            @Override
            public void onShareClick(String title, String url) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                String shareText = title + "\n" + url;
                intent.putExtra(Intent.EXTRA_TEXT, shareText);
                intent.setType("text/plain");
                startActivity(intent);
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onSaveClick(News news) {
                if (dbSavedItem.checkIfAlreadyExists(news.getNewsUrl())) {
                    dbSavedItem.removeNews(news);
                    onDismissClick();
                    refresh();
                    Toast.makeText(context, "Removed from Bookmark!", 0).show();
                }
            }

            @Override
            public void onDismissClick() {
                dialog.dismiss();
            }
        });


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(binding.getRoot());

        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);

        View main_root = dialog.findViewById(R.id.dialog_root);
        ViewGroup.LayoutParams layoutParams = main_root.getLayoutParams();
        layoutParams.width = width;
        main_root.setLayoutParams(layoutParams);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        ifg = dialog.findViewById(R.id.saved_item);
        ifg.setBackgroundResource(R.mipmap.ic_remove);

        dialog.show();
    }

}
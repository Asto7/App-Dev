package com.project.headlines.activities;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.headlines.DatabaseHandler;
import com.project.headlines.R;
import com.project.headlines.adapters.AdapterListNews;
import com.project.headlines.clicklisteners.AdapterItemClickListener;
import com.project.headlines.clicklisteners.NewsDialogClickListeners;
import com.project.headlines.databinding.NewsDialogBinding;
import com.project.headlines.model.News;
import com.project.headlines.utils.LocaleHelper;
import com.project.headlines.utils.Util;
import com.project.headlines.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LifecycleOwner, AdapterItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ivToolbarCountry)
    ImageView ivToolbarCountry;


    MainActivity context;
    MainViewModel viewModel;
    AdapterListNews adapterListNews;
    List<News> newsList;

    private String firstControl = "firstControl";
    private String countryPositionPref = "countryPositionPref";
    SharedPreferences pref;
    private String[] countrys;
    private TypedArray countrysIcons;

    TextView main_title;

    DatabaseHandler dbSavedItem;

    BottomNavigationView bottom_nav;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {


                case R.id.navigation_saved: {
                    Intent intent = new Intent(MainActivity.this, SaveActivity.class);
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

        pref = getApplicationContext().getSharedPreferences(Util.APP_NAME, MODE_PRIVATE);
        languageControl();

        setContentView(R.layout.activity_main);

        bottom_nav = findViewById(R.id.navigation);
        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        dbSavedItem = new DatabaseHandler(this);

        context = this;

        ButterKnife.bind(this);

        countrys = getResources().getStringArray(R.array.countrys);
        countrysIcons = getResources().obtainTypedArray(R.array.countrysIcons);

        initToolbar();

        newsList = new ArrayList<>();
        adapterListNews = new AdapterListNews(newsList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapterListNews);

        main_title = findViewById(R.id.main_title);

        if (pref.contains(countryPositionPref))
            ivToolbarCountry.setImageResource(countrysIcons.getResourceId(pref.getInt(countryPositionPref, 0), 0));

        viewModel = ViewModelProviders.of(context).get(MainViewModel.class);
        viewModel.getNewsLiveData().observe(context, newsListUpdateObserver);
        viewModel.setApiKey("b422300c429d4dc29134e788bc4adbdf");
        viewModel.setCountryCode(pref.getString(Util.COUNTRY_PREF, "in"));


    }

    private void languageControl() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N && !pref.getBoolean(firstControl, false)) {
            Locale primaryLocale = getResources().getConfiguration().getLocales().get(0);
            LocaleHelper.setLocale(MainActivity.this, primaryLocale.getLanguage());
            int position = getLanguagePosition(primaryLocale.getLanguage());
            pref.edit().putInt(countryPositionPref, position).apply();
            pref.edit().putBoolean(firstControl, true).apply();
            recreate();
        }
    }

    private int getLanguagePosition(String displayLanguage) {
        String[] codes = getResources().getStringArray(R.array.countrysCodes);
        for (int i = 0; i < codes.length; i++) {
            if (codes[i].equals(displayLanguage)) return i;
        }
        return 0;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Util.setSystemBarColor(this, android.R.color.white);
        Util.setSystemBarLight(this);
    }

    private void showLanguageDialog() {
        new AlertDialog.Builder(this).setCancelable(false)
                .setTitle("Choose Country")
                .setSingleChoiceItems(countrys, pref.getInt(countryPositionPref, 0), null)
                .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(R.string.ok, (dialog, whichButton) -> {
                    int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                    pref.edit().putInt(countryPositionPref, selectedPosition).apply();
                    pref.edit().putString(Util.COUNTRY_PREF, getResources().getStringArray(R.array.countrysCodes)[selectedPosition]).apply();
                    LocaleHelper.setLocale(MainActivity.this, getResources().getStringArray(R.array.countrysCodes)[selectedPosition]);
                    recreate();
                    dialog.dismiss();
                })
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        Util.changeMenuIconColor(menu, Color.BLACK);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        searchView.setQueryHint(getString(R.string.search_in_everything));
        if (searchView != null)
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (viewModel != null) viewModel.searchNews(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        return true;
    }

    public void categoryClicked(View view) {

        String name = String.valueOf(view.getTag());
        String firstLetter = name.substring(0, 1);
        String remainingLetters = name.substring(1);
        firstLetter = firstLetter.toUpperCase();
        name = firstLetter + remainingLetters;

        main_title.setText(name);

        name = name.toLowerCase();
        viewModel.newsCategoryClick(name);
    }

    public void countryClick(View view) {
        showLanguageDialog();
    }

    Observer<List<News>> newsListUpdateObserver = new Observer<List<News>>() {
        @Override
        public void onChanged(List<News> news) {
            newsList.clear();
            if (news != null) {
                newsList.addAll(news);
            }
            adapterListNews.notifyDataSetChanged();
        }
    };


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
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

            @Override
            public void onSaveClick(News news) {
                if (!dbSavedItem.checkIfAlreadyExists(news.getNewsUrl())) {
                    dbSavedItem.addNews(news);
                    Toast.makeText(context, "Added", 0).show();

                } else {
                    Toast.makeText(context, "Already exists", 0).show();
                }

            }

            @Override
            public void onDismissClick() {
                dialog.dismiss();
            }
        });

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);

        dialog.show();
    }

}

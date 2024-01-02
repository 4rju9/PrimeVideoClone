package cf.arjun.dev.primevideoclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.Request;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import cf.arjun.dev.primevideoclone.adapters.BannerMoviesPagerAdapter;
import cf.arjun.dev.primevideoclone.adapters.MainRecyclerAdapter;
import cf.arjun.dev.primevideoclone.models.AllCategory;
import cf.arjun.dev.primevideoclone.models.Movies;

public class MainActivity extends AppCompatActivity {

    Queue volley;
    NestedScrollView nestedScrollView;
    AppBarLayout appBar;
    BannerMoviesPagerAdapter bannerMoviespagerAdapter;
    TabLayout tabLayout, indicatorTabLayout;
    ViewPager bannerMoviesViewPager;
    List<Movies> homeBannerList, tvBannerList, moviesBannerList, kidsBannerList, banners;
    MainRecyclerAdapter mainAdapter;
    RecyclerView mainRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        loadBanner();
        initTab();

    }

    private void setupUIViews () {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        volley = Queue.getInstance(this.getApplicationContext());
        appBar = findViewById(R.id.mainAppBar);
        nestedScrollView = findViewById(R.id.mainNestedScrollView);
        indicatorTabLayout = findViewById(R.id.tabIndicator);
        tabLayout = findViewById(R.id.tabLayout);

    }

    private void resetScrollView () {

        appBar.setExpanded(true);
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0, 0);

    }

    private void setupBannerLists () {

        homeBannerList = new ArrayList<>();
        tvBannerList = new ArrayList<>();
        moviesBannerList = new ArrayList<>();
        kidsBannerList = new ArrayList<>();

        int size = banners.size();

        for (int i=0; i<size; i++) {

            Movies movie = banners.get(i);

            switch (movie.getCategoryId()) {

                case 1: {
                    homeBannerList.add(movie);
                    break;
                }
                case 2: {
                    tvBannerList.add(movie);
                    break;
                }
                case 3: {
                    moviesBannerList.add(movie);
                    break;
                }
                case 4: {
                    kidsBannerList.add(movie);
                    break;
                }

            }

        }

    }

    private Movies initMoviesData(JSONObject ob) {

        try {
            Movies movie = new Movies(
                    ob.getInt("id"),
                    ob.getString("movieName"),
                    ob.getString("imageUrl"),
                    ob.getString("fileUrl")
            );
            if (ob.has("categoryId")) {
                movie.setCategoryId(ob.getInt("categoryId"));
            }
            return movie;
        } catch (JSONException ignore) {}
        return null;
    }

    private void loadBanner () {

        banners = new ArrayList<>();

        volley.makeRequest(
                Request.Method.GET,
                this.getResources().getString(R.string.banner_url),
                response -> {

                    try {

                        JSONObject json = new JSONObject(response);
                        JSONArray bannersJson = json.getJSONArray("get_banners");
                        int size = bannersJson.length();

                        for (int i=0; i<size; i++) {
                            banners.add(initMoviesData(bannersJson.getJSONObject(i)));
                        }

                    } catch (Exception ignore) {}

                    setupBannerLists();
                    setBannerMoviesPagerAdapter(homeBannerList);

                },
                false
        );

    }

    private void loadRecycler (String url) {

        List<AllCategory> allCategories = new ArrayList<>();

        volley.makeRequest(
                Request.Method.GET,
                url,
                response -> {

                    try {

                        JSONObject json = new JSONObject(response);
                        JSONArray allCategoriesJson = json.getJSONArray("get_categories");

                        int size = allCategoriesJson.length();
                        for (int i=0; i<size; i++) {

                            try {

                                JSONObject ob = allCategoriesJson.getJSONObject(i);
                                JSONArray moviesJson = ob.getJSONArray("get_movies");

                                int s = moviesJson.length();
                                List<Movies> mList = new ArrayList<>();

                                for (int j=0; j<s; j++) {
                                    mList.add(initMoviesData(moviesJson.getJSONObject(j)));
                                }

                                AllCategory category = new AllCategory(
                                        ob.getInt("categoryId"),
                                        ob.getString("categoryTitle"),
                                        mList
                                );

                                allCategories.add(category);

                            } catch (JSONException ignore) {}

                        }

                    } catch (Exception ignore) {}

                    setMainRecyclerAdapter(allCategories);

                },
                true
        );

    }

    private void initTab () {

        // default selected tab is home tab.
        loadRecycler(this.getResources().getString(R.string.url_one));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 1: {
                        resetScrollView();
                        setBannerMoviesPagerAdapter(tvBannerList);
                        loadRecycler(MainActivity.this.getResources().getString(R.string.url_two));
                        break;
                    } case 2: {
                        resetScrollView();
                        setBannerMoviesPagerAdapter(moviesBannerList);
                        loadRecycler(MainActivity.this.getResources().getString(R.string.url_three));
                        break;
                    } case 3: {
                        resetScrollView();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        loadRecycler(MainActivity.this.getResources().getString(R.string.url_four));
                        break;
                    } default: {
                        resetScrollView();
                        setBannerMoviesPagerAdapter(homeBannerList);
                        loadRecycler(MainActivity.this.getResources().getString(R.string.url_one));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setBannerMoviesPagerAdapter (List<Movies> moviesList) {

        bannerMoviesViewPager = findViewById(R.id.bannerViewPager);
        bannerMoviespagerAdapter = new BannerMoviesPagerAdapter(this, moviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviespagerAdapter);
        indicatorTabLayout.setupWithViewPager(bannerMoviesViewPager, true);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new BannerAutoSlider(moviesList.size()), 4000, 6000);

    }

    private void setMainRecyclerAdapter (List<AllCategory> categoryList) {

        mainRecycler = findViewById(R.id.mainRecycler);
        mainRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mainAdapter = new MainRecyclerAdapter(this, categoryList);
        mainRecycler.setAdapter(mainAdapter);

    }

    class BannerAutoSlider extends TimerTask {

        int maxSize;

        public BannerAutoSlider(int maxSize) {
            this.maxSize = maxSize;
        }

        @Override
        public void run() {

            MainActivity.this.runOnUiThread( () -> {

                int index = bannerMoviesViewPager.getCurrentItem();
                if (index < maxSize - 1) {
                    bannerMoviesViewPager.setCurrentItem(index + 1);
                } else {
                    bannerMoviesViewPager.setCurrentItem(0);
                }

            });

        }
    }

}
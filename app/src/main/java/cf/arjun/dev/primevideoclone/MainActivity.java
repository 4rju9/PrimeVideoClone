package cf.arjun.dev.primevideoclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.WindowManager;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import cf.arjun.dev.primevideoclone.adapters.BannerMoviesPagerAdapter;
import cf.arjun.dev.primevideoclone.adapters.MainRecyclerAdapter;
import cf.arjun.dev.primevideoclone.models.AllCategory;
import cf.arjun.dev.primevideoclone.models.BannerMovies;

public class MainActivity extends AppCompatActivity {

    BannerMoviesPagerAdapter bannerMoviespagerAdapter;
    TabLayout tabLayout, indicatorTabLayout;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvBannerList;
    List<BannerMovies> moviesBannerList;
    List<BannerMovies> kidsBannerList;
    MainRecyclerAdapter mainAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        setupLists();
        initTab();

    }

    private void setupUIViews () {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        indicatorTabLayout = findViewById(R.id.tabIndicator);
        tabLayout = findViewById(R.id.tabLayout);

    }

    private void setupLists () {

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "AQUA MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7byybfv7B3BlEb7btSnQ2XAQRHDao9q9_Iw&usqp=CAU", ""));
        homeBannerList.add(new BannerMovies(2, "BLACK WIDOW", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNyJ1uXbVY-smPvVtdaK4c8eyAimNjWxz-VA&usqp=CAU", ""));
        homeBannerList.add(new BannerMovies(3, "THE AMAZING SPIDER MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cHeuIyp7dYGjJaq351m97BBj_15zazCESA&usqp=CAU", ""));
        homeBannerList.add(new BannerMovies(4, "JUSTICE LEAGUE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbB5WuyTsBdfgfKZr3YaXbRq0giaHErYkyXA&usqp=CAU", ""));
        homeBannerList.add(new BannerMovies(5, "K. G. F. CHAPTER 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTQGHO4P6zJkavlE_PPD1Whm6aUme9JrX-Dg&usqp=CAU", ""));

        tvBannerList = new ArrayList<>();
        tvBannerList.add(new BannerMovies(1, "AQUA MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7byybfv7B3BlEb7btSnQ2XAQRHDao9q9_Iw&usqp=CAU", ""));
        tvBannerList.add(new BannerMovies(2, "BLACK WIDOW", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNyJ1uXbVY-smPvVtdaK4c8eyAimNjWxz-VA&usqp=CAU", ""));
        tvBannerList.add(new BannerMovies(3, "THE AMAZING SPIDER MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cHeuIyp7dYGjJaq351m97BBj_15zazCESA&usqp=CAU", ""));
        tvBannerList.add(new BannerMovies(4, "JUSTICE LEAGUE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbB5WuyTsBdfgfKZr3YaXbRq0giaHErYkyXA&usqp=CAU", ""));
        tvBannerList.add(new BannerMovies(5, "K. G. F. CHAPTER 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTQGHO4P6zJkavlE_PPD1Whm6aUme9JrX-Dg&usqp=CAU", ""));

        moviesBannerList = new ArrayList<>();
        moviesBannerList.add(new BannerMovies(1, "AQUA MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7byybfv7B3BlEb7btSnQ2XAQRHDao9q9_Iw&usqp=CAU", ""));
        moviesBannerList.add(new BannerMovies(2, "BLACK WIDOW", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNyJ1uXbVY-smPvVtdaK4c8eyAimNjWxz-VA&usqp=CAU", ""));
        moviesBannerList.add(new BannerMovies(3, "THE AMAZING SPIDER MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cHeuIyp7dYGjJaq351m97BBj_15zazCESA&usqp=CAU", ""));
        moviesBannerList.add(new BannerMovies(4, "JUSTICE LEAGUE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbB5WuyTsBdfgfKZr3YaXbRq0giaHErYkyXA&usqp=CAU", ""));
        moviesBannerList.add(new BannerMovies(5, "K. G. F. CHAPTER 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTQGHO4P6zJkavlE_PPD1Whm6aUme9JrX-Dg&usqp=CAU", ""));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "AQUA MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7byybfv7B3BlEb7btSnQ2XAQRHDao9q9_Iw&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(2, "BLACK WIDOW", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNyJ1uXbVY-smPvVtdaK4c8eyAimNjWxz-VA&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(3, "THE AMAZING SPIDER MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cHeuIyp7dYGjJaq351m97BBj_15zazCESA&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(4, "JUSTICE LEAGUE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbB5WuyTsBdfgfKZr3YaXbRq0giaHErYkyXA&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(5, "K. G. F. CHAPTER 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTQGHO4P6zJkavlE_PPD1Whm6aUme9JrX-Dg&usqp=CAU", ""));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Bollywood"));
        allCategoryList.add(new AllCategory(2, "Hollywood"));
        allCategoryList.add(new AllCategory(3, "Kids"));

    }

    private void initTab() {

        // default selected tab.
        setBannerMoviesPagerAdapter(homeBannerList);
        setMainRecyclerAdapter(allCategoryList);
        // on tab data changed.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabId = tab.getId();
                switch (tabId) {
                    case 1: {
                        setBannerMoviesPagerAdapter(tvBannerList);
                        break;
                    } case 2: {
                        setBannerMoviesPagerAdapter(moviesBannerList);
                        break;
                    } case 3: {
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        break;
                    } default: {
                        setBannerMoviesPagerAdapter(homeBannerList);
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

    private void setBannerMoviesPagerAdapter (List<BannerMovies> moviesList) {

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
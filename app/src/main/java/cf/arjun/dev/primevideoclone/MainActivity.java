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
        tvBannerList.add(new BannerMovies(1, "ORIGINAL SIN", "https://film-book.com/wp-content/uploads/2022/08/pretty-little-liars-original-sin-tv-show-poster-banner-01-700x400-1-700x400.jpg", ""));
        tvBannerList.add(new BannerMovies(2, "THEM", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUhiaYJHzZOAdwEAvuliUmdMAxHdkVngzRpw&usqp=CAU", ""));
        tvBannerList.add(new BannerMovies(3, "SQUID GAMES", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRelqOyA2vibMPfWo0rM0hoScofNs9ilG8eRQ&usqp=CAU", ""));

        moviesBannerList = new ArrayList<>();
        moviesBannerList.add(new BannerMovies(1, "GHAYAL", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQghoSQN5eB0WRoR7UC1mDQIBiBU7lqhNtDBiGxe-0OjTvwWT-A9Yc7OFopWY_OggTvm0E&usqp=CAU", ""));
        moviesBannerList.add(new BannerMovies(2, "X-MEN APOCALYPSE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXuWFeXoqXQRG_EYJmDkS_b7AXshaVCBqNR__fsFE6-LgIjcL4uD9FUt3Dtb4U4MTE97k&usqp=CAU", ""));
        moviesBannerList.add(new BannerMovies(3, "LEO", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKaAOPmjTijbgq6B3wfg-E1VuA5S0Fou1FhQ&usqp=CAU", ""));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "MALEFICENT", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoVqC6AP3tmqbSZlmCJ983NqkSlOsVvMHLVQ&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(2, "COCO", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoVqC6AP3tmqbSZlmCJ983NqkSlOsVvMHLVQ&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(3, "ENCANTO", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQs0swMgh1CowDeiRvDmEwQefbRgfXz5IgsBQ&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(4, "RAYA AND THE LAST THE DRAGON", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYjwwntJ1jXF4iufQuNoPyqCJIbb5C9FhVag&usqp=CAU", ""));
        kidsBannerList.add(new BannerMovies(5, "STRANGE WORLD", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv-qjwc5lZ-Xxc0brdgusp7fFkYGWPZ98M-w&usqp=CAU", ""));

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
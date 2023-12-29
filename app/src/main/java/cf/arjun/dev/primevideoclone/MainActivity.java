package cf.arjun.dev.primevideoclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import cf.arjun.dev.primevideoclone.adapters.BannerMoviesPagerAdapter;
import cf.arjun.dev.primevideoclone.adapters.MainRecyclerAdapter;
import cf.arjun.dev.primevideoclone.models.AllCategory;
import cf.arjun.dev.primevideoclone.models.Movies;

public class MainActivity extends AppCompatActivity {

    NestedScrollView nestedScrollView;
    AppBarLayout appBar;
    BannerMoviesPagerAdapter bannerMoviespagerAdapter;
    TabLayout tabLayout, indicatorTabLayout;
    ViewPager bannerMoviesViewPager;
    List<Movies> homeBannerList, tvBannerList, moviesBannerList, kidsBannerList;
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

    private void setupLists () {

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new Movies(1, "AQUA MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7byybfv7B3BlEb7btSnQ2XAQRHDao9q9_Iw&usqp=CAU", ""));
        homeBannerList.add(new Movies(2, "BLACK WIDOW", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNyJ1uXbVY-smPvVtdaK4c8eyAimNjWxz-VA&usqp=CAU", ""));
        homeBannerList.add(new Movies(3, "THE AMAZING SPIDER MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cHeuIyp7dYGjJaq351m97BBj_15zazCESA&usqp=CAU", ""));
        homeBannerList.add(new Movies(4, "JUSTICE LEAGUE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbB5WuyTsBdfgfKZr3YaXbRq0giaHErYkyXA&usqp=CAU", ""));
        homeBannerList.add(new Movies(5, "K. G. F. CHAPTER 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTQGHO4P6zJkavlE_PPD1Whm6aUme9JrX-Dg&usqp=CAU", ""));

        tvBannerList = new ArrayList<>();
        tvBannerList.add(new Movies(1, "ORIGINAL SIN", "https://film-book.com/wp-content/uploads/2022/08/pretty-little-liars-original-sin-tv-show-poster-banner-01-700x400-1-700x400.jpg", ""));
        tvBannerList.add(new Movies(2, "THEM", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUhiaYJHzZOAdwEAvuliUmdMAxHdkVngzRpw&usqp=CAU", ""));
        tvBannerList.add(new Movies(3, "SQUID GAMES", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRelqOyA2vibMPfWo0rM0hoScofNs9ilG8eRQ&usqp=CAU", ""));

        moviesBannerList = new ArrayList<>();
        moviesBannerList.add(new Movies(1, "GHAYAL", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQghoSQN5eB0WRoR7UC1mDQIBiBU7lqhNtDBiGxe-0OjTvwWT-A9Yc7OFopWY_OggTvm0E&usqp=CAU", ""));
        moviesBannerList.add(new Movies(2, "X-MEN APOCALYPSE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXuWFeXoqXQRG_EYJmDkS_b7AXshaVCBqNR__fsFE6-LgIjcL4uD9FUt3Dtb4U4MTE97k&usqp=CAU", ""));
        moviesBannerList.add(new Movies(3, "LEO", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKaAOPmjTijbgq6B3wfg-E1VuA5S0Fou1FhQ&usqp=CAU", ""));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new Movies(1, "MALEFICENT", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoVqC6AP3tmqbSZlmCJ983NqkSlOsVvMHLVQ&usqp=CAU", ""));
        kidsBannerList.add(new Movies(2, "ENCANTO", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQs0swMgh1CowDeiRvDmEwQefbRgfXz5IgsBQ&usqp=CAU", ""));
        kidsBannerList.add(new Movies(3, "RAYA AND THE LAST THE DRAGON", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYjwwntJ1jXF4iufQuNoPyqCJIbb5C9FhVag&usqp=CAU", ""));
        kidsBannerList.add(new Movies(4, "STRANGE WORLD", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv-qjwc5lZ-Xxc0brdgusp7fFkYGWPZ98M-w&usqp=CAU", ""));

        List<Movies> homeItemList1 = new ArrayList<>();
        homeItemList1.add(new Movies(1, "DHAMAL", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGE7Ru849-CouHPXcvEugD5LyVVxgHXGpUMCf4B_Rmoc_3ybcPMf4rkK8ecC4lC6pX8eY&usqp=CAU", ""));
        homeItemList1.add(new Movies(2, "DHOL", "https://img1.hotstarext.com/image/upload/f_auto,t_hcdl/sources/r1/cms/prod/old_images/MOVIE/2067/1000102067/1000102067-h", ""));
        homeItemList1.add(new Movies(3, "JHOOTHA KAHIN KA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSk0Wj8yhOhvd1SlaMM1Rr3RlMNNaJFzGkZv8x8-GkTC3BvnSyDnQb1PtcTyf5ATOsTns4&usqp=CAU", ""));

        List<Movies> homeItemList2 = new ArrayList<>();
        homeItemList2.add(new Movies(1, "PATHAAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZtSzm23_itlm8fPlTs-6mlZKnBqeLoF7YsERQhSkE63KtxyMY_K5xpT0_EqJWgLvUcFY&usqp=CAU", ""));
        homeItemList2.add(new Movies(2, "ACTION JACKSON", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkyxIFV22C72ahiA27hVFRV53uJvEH7EhNKoQ8DPtM_ncQda7V7Eh4Td5U7WXfXt6wMAA&usqp=CAU", ""));
        homeItemList2.add(new Movies(3, "BHOOT POLICE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtUmF51J7aWS4HnH6RKecZpDSilblzIXDcO_9pMdgpJWqXimmCQlc4q_YT4r5nqBNudr8&usqp=CAU", ""));
        homeItemList2.add(new Movies(4, "SHAMSHERA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTFa7Dfu0dmwVW74DR_uf0Fs6oGYMlOGyg528GVzuF7x_ii5ocvuV10U3FSDNIJbvJSqU&usqp=CAU", ""));
        homeItemList2.add(new Movies(5, "D-DAY", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmJvLKnNQlhaBN0egVr9by8230UCvkas3QEbxPgQz6LYQiem8FG-AGQ0tAG4TtAymTPWo&usqp=CAU", ""));

        List<Movies> homeItemList3 = new ArrayList<>();
        homeItemList3.add(new Movies(1, "MAHABHARAT", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7MR1TO6ubpFF0wBxItBZVmXL6gexBCPM9CgFzRdFewR8je1DHULC6heL0dTaHNfekNxI&usqp=CAU", ""));
        homeItemList3.add(new Movies(2, "RAMAYANA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXSwQYzmlgj7Mn1jSQXomKGh_xmgZNYv9sa2g2fYKw9t9YnAmhL4EOWIB1ak-u95wWFcs&usqp=CAU", ""));
        homeItemList3.add(new Movies(3, "TAARAK MEHTA KA OOLTAH CHASHMAH", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7MR1TO6ubpFF0wBxItBZVmXL6gexBCPM9CgFzRdFewR8je1DHULC6heL0dTaHNfekNxI&usqp=CAU", ""));
        homeItemList3.add(new Movies(5, "NAAGIN SEASON 5", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS29R3nU5yArItqGtGvYAQQCEzadIUEe6GYXNomwo_rnO_q_zXTaEv88t9VclYCpqfzNvI&usqp=CAU", ""));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Comedy movies", homeItemList1));
        allCategoryList.add(new AllCategory(2, "Action and adventure movies", homeItemList2));
        allCategoryList.add(new AllCategory(3, "Hindi TV Shows", homeItemList3));

    }

    private void initTab() {

        // default selected tab.
        setBannerMoviesPagerAdapter(homeBannerList);
        setMainRecyclerAdapter(allCategoryList);
        // on tab data changed.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 1: {
                        resetScrollView();
                        setBannerMoviesPagerAdapter(tvBannerList);
                        break;
                    } case 2: {
                        resetScrollView();
                        setBannerMoviesPagerAdapter(moviesBannerList);
                        break;
                    } case 3: {
                        resetScrollView();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        break;
                    } default: {
                        resetScrollView();
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
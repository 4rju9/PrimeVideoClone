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
        homeItemList1.add(new Movies(1, "DHAMAL", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8I2vAjyTzgH7e26wapyw30ZMEjUyO5-kMtYpKMmIwxUpVfKcQaZCNV1lEHaCEPG_OExQ&usqp=CAU", ""));
        homeItemList1.add(new Movies(2, "DHOL", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh1RywiWLMU3cr4BFmJ8Mke_e3PjHpH0AnNWTjnnqHCspAe5QzbXVYMoZRg9bwog61x9c&usqp=CAU", ""));
        homeItemList1.add(new Movies(3, "JHOOTHA KAHIN KA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhMcWfismhf2bOV8qXDk-vcma42W5VLdK2Lu2FeEhD26X9OnzSYyQ1S1amholwwOKyq1s&usqp=CAU", ""));

        List<Movies> homeItemList2 = new ArrayList<>();
        homeItemList2.add(new Movies(1, "PATHAAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtfV689Zt69ZII_yYu3-hT9k0QUSDeGxSo1cTpP28ixWEpRv6Y3LZskE7MvvHhpscdWeU&usqp=CAU", ""));
        homeItemList2.add(new Movies(2, "ACTION JACKSON", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6n2PkNPQ_kv-BxipSAtBRi8hSHICX-g5oYNCCt-D6ryDfc2_2FVEJx4WD2pXNvdO9QMI&usqp=CAU", ""));
        homeItemList2.add(new Movies(3, "BHOOT POLICE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuD59jGuyRLkNAmppIxVs4ebvMeIPFa54Pw707SC_DmL2ixD7IFTO9LdLHdoqN1oXn8Po&usqp=CAU", ""));
        homeItemList2.add(new Movies(4, "SHAMSHERA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8SU45pKefiD-XWe_is8zFuvt3u-m0ysbc275D5AtHN4P1fuJGZKplpQgqRB_GP4c9VEo&usqp=CAU", ""));
        homeItemList2.add(new Movies(5, "D-DAY", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZeU6_LiK6-1kMppMJWf6B452xn0CcncwolLaQLmMdq04oZCArT8sSrMFYGVDXP2Kp3Mk&usqp=CAU", ""));

        List<Movies> homeItemList3 = new ArrayList<>();
        homeItemList3.add(new Movies(1, "MAHABHARAT", "https://m.media-amazon.com/images/M/MV5BMGE1MTlmNmYtMTg4Zi00NWRmLWIxMzktMTViNjRmYTQ5NDI2XkEyXkFqcGdeQXVyODAzNzAwOTU@._V1_UY209_CR3,0,140,209_AL_.jpg", ""));
        homeItemList3.add(new Movies(2, "RAMAYANA", "https://m.media-amazon.com/images/M/MV5BODFjYWEyOTktMTU3OC00YTBhLWE4ZmEtNTJiYWUzYTYwMjIzXkEyXkFqcGdeQXVyNjU1NDgwMDg@._V1_UY209_CR4,0,140,209_AL_.jpg", ""));
        homeItemList3.add(new Movies(3, "Taarak Mehta Ka Ooltah Chashmah", "https://m.media-amazon.com/images/M/MV5BYzkyZjQ0MjAtNmMyYS00ZDQwLWE1YmItYzIwNTUyMDlmYWM5XkEyXkFqcGdeQXVyNDc0MDgzNTE@._V1_UY209_CR0,0,140,209_AL_.jpg", ""));
        homeItemList3.add(new Movies(4, " Kaun Banega Crorepati?", "https://m.media-amazon.com/images/M/MV5BZDE3YTNhNzctZjdiNy00YjZjLWE4MDMtOGJjODE2YjE3NDllXkEyXkFqcGdeQXVyODAzNzAwOTU@._V1_UY209_CR3,0,140,209_AL_.jpg", ""));
        homeItemList3.add(new Movies(5, "SHAKTIMANA", "https://m.media-amazon.com/images/M/MV5BYzQyYmE2NDItZTBlZS00MmEyLWI4MjctMWM1NWVkY2MxNzg3XkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_UY209_CR4,0,140,209_AL_.jpg", ""));

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
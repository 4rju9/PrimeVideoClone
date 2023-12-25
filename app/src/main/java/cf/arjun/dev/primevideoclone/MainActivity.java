package cf.arjun.dev.primevideoclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cf.arjun.dev.primevideoclone.adapters.BannerMoviesPagerAdapter;
import cf.arjun.dev.primevideoclone.models.BannerMovies;

public class MainActivity extends AppCompatActivity {

    BannerMoviesPagerAdapter bannerMoviespagerAdapter;
    TabLayout tabLayout, indicatorTabLayout;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> bannerMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerMoviesList = new ArrayList<>();
        bannerMoviesList.add(new BannerMovies(1, "AQUA MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7byybfv7B3BlEb7btSnQ2XAQRHDao9q9_Iw&usqp=CAU", ""));
        bannerMoviesList.add(new BannerMovies(2, "BLACK WIDOW", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNyJ1uXbVY-smPvVtdaK4c8eyAimNjWxz-VA&usqp=CAU", ""));
        bannerMoviesList.add(new BannerMovies(3, "THE AMAZING SPIDER MAN", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3cHeuIyp7dYGjJaq351m97BBj_15zazCESA&usqp=CAU", ""));
        bannerMoviesList.add(new BannerMovies(4, "JUSTICE LEAGUE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbB5WuyTsBdfgfKZr3YaXbRq0giaHErYkyXA&usqp=CAU", ""));
        bannerMoviesList.add(new BannerMovies(5, "K. G. F. CHAPTER 2", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTQGHO4P6zJkavlE_PPD1Whm6aUme9JrX-Dg&usqp=CAU", ""));

        setBannerMoviesPagerAdapter(bannerMoviesList);

    }

    private void setBannerMoviesPagerAdapter (List<BannerMovies> bannerMoviesList) {

        bannerMoviesViewPager = findViewById(R.id.bannerViewPager);
        bannerMoviespagerAdapter = new BannerMoviesPagerAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviespagerAdapter);
        indicatorTabLayout = findViewById(R.id.tabIndicator);
        indicatorTabLayout.setupWithViewPager(bannerMoviesViewPager, true);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new BannerAutoSlider(), 4000, 6000);

    }

    class BannerAutoSlider extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread( () -> {
                int index = bannerMoviesViewPager.getCurrentItem();
                if (index < bannerMoviesList.size() - 1) {
                    bannerMoviesViewPager.setCurrentItem(index + 1);
                } else {
                    bannerMoviesViewPager.setCurrentItem(0);
                }
            });

        }
    }

}
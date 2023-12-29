package cf.arjun.dev.primevideoclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import cf.arjun.dev.primevideoclone.R;
import cf.arjun.dev.primevideoclone.models.Movies;

public class BannerMoviesPagerAdapter extends PagerAdapter {

    Context context;
    List<Movies> bannerMoviesList;

    public BannerMoviesPagerAdapter(Context context, List<Movies> bannerMoviesList) {
        this.context = context;
        this.bannerMoviesList = bannerMoviesList;
    }

    @Override
    public int getCount() {
        return bannerMoviesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.banner_movie_layout, null);

        ImageView bannerImage = view.findViewById(R.id.bannerImage);
        Glide.with(context).load(bannerMoviesList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);
        return view;

    }
}

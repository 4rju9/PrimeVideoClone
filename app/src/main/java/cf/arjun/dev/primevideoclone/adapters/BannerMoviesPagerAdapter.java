package cf.arjun.dev.primevideoclone.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import java.util.List;
import cf.arjun.dev.primevideoclone.MovieDetails;
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

        // current object on the movie list.
        Movies current = bannerMoviesList.get(position);


        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.banner_movie_layout, null);

        // Finding and setting the image.
        ImageView bannerImage = view.findViewById(R.id.bannerImage);
        Glide.with(context).load(current.getImageUrl()).into(bannerImage);

        // adding the view into the parentView.
        container.addView(view);

        // on click listener on the banner image.
        bannerImage.setOnClickListener( v -> {

            Intent intent = new Intent(context, MovieDetails.class);
            intent.putExtra("id", current.getId());
            intent.putExtra("name", current.getMovieName());
            intent.putExtra("imageUrl", current.getImageUrl());
            intent.putExtra("fileUrl", current.getFileUrl());
            context.startActivity(intent);

        });
        return view;

    }
}

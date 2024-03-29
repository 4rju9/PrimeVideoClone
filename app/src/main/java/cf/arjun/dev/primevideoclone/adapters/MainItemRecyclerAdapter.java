package cf.arjun.dev.primevideoclone.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import cf.arjun.dev.primevideoclone.MovieDetails;
import cf.arjun.dev.primevideoclone.R;
import cf.arjun.dev.primevideoclone.models.Movies;

public class MainItemRecyclerAdapter extends RecyclerView.Adapter<MainItemRecyclerAdapter.MainItemViewHolder> {

    Context context;
    List<Movies> moviesList;

    public MainItemRecyclerAdapter(Context context, List<Movies> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MainItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainItemViewHolder(LayoutInflater.from(context).inflate(R.layout.main_category_single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainItemViewHolder holder, int position) {

        Movies current = moviesList.get(position);

        Glide.with(context).load(current.getImageUrl()).into(holder.itemImage);

        // on click listener on the item image.
        holder.itemImage.setOnClickListener( v -> {

            Intent intent = new Intent(context, MovieDetails.class);
            intent.putExtra("id", current.getId());
            intent.putExtra("name", current.getMovieName());
            intent.putExtra("imageUrl", current.getImageUrl());
            intent.putExtra("fileUrl", current.getFileUrl());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MainItemViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;

        public MainItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }

}

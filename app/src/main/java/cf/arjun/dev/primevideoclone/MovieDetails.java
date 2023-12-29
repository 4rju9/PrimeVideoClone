package cf.arjun.dev.primevideoclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    TextView movieTitle;
    ImageView moviePoster;
    AppCompatButton playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();

        if (intent != null) {

            setupUIViews();
            movieTitle.setText(intent.getStringExtra("name"));
            Glide.with(this).load(intent.getStringExtra("imageUrl")).into(moviePoster);

        } else finish();

    }

    private void setupUIViews () {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        movieTitle = findViewById(R.id.detailsMovieTitle);
        moviePoster = findViewById(R.id.detailsMovieImage);
        playButton = findViewById(R.id.detailsPlayButton);

    }
}
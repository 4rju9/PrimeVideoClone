package cf.arjun.dev.primevideoclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.ui.PlayerView;

public class PlayerViewActivity extends AppCompatActivity {

    PlayerView player;
    ExoPlayer exoPlayer;
    TrackSelectionParameters trackSelectionParameters;
    private boolean PLAYER_STATE = true;
    private boolean isShowingTrackSelectionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view);

        restoreBundle(savedInstanceState);

        setupUIViews();
        initializeExoPlayer();

    }

    private void setupUIViews () {
        player = findViewById(R.id.exoPlayer);
    }

    private void initializeExoPlayer () {

        exoPlayer = new ExoPlayer.Builder(this).build();
        player.setPlayer(exoPlayer);

        MediaItem mediaItem = MediaItem.fromUri(getIntent().getStringExtra("fileUrl"));
        exoPlayer.setMediaItem(mediaItem);

        setTrackSelectionParameters();

        exoPlayer.setPlayWhenReady(PLAYER_STATE);
        exoPlayer.prepare();

        initializeExoUis();

    }

    private void initializeExoUis () {

        ImageView forwardBtn = player.findViewById(R.id.fwd);
        ImageView rewBtn = player.findViewById(R.id.rew);
        ImageView speedBtn = player.findViewById(R.id.exo_playback_speed);
        TextView speedTxt = player.findViewById(R.id.speed);

        speedBtn.setOnClickListener( v -> {

            String[] speed = {"0.25x","0.5x","Normal","1.5x","2x"};
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerViewActivity.this);
                    builder.setTitle("Set Speed");
                    builder.setItems(speed, (dialog, which) -> {

                        PlaybackParameters param = new PlaybackParameters(1f);
                        String text = speed[which];

                        if (which==0){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText(text);
                            param = new PlaybackParameters(0.25f);

                        }  if (which==1){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText(text);
                            param = new PlaybackParameters(0.5f);
                        }
                        if (which==2){

                            speedTxt.setVisibility(View.GONE);
                            param = new PlaybackParameters(1f);
                        }
                        if (which==3){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText(text);
                            param = new PlaybackParameters(1.5f);
                        }
                        if (which==4){

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText(text);
                            param = new PlaybackParameters(2f);
                        }

                        exoPlayer.setPlaybackParameters(param);

                    });

                    builder.show();

        });

        forwardBtn.setOnClickListener(v -> {

            try {

                long currentNum = exoPlayer.getCurrentPosition();
                long num = currentNum + 10000;
                long maxPossibleSkip = exoPlayer.getDuration();
                if (maxPossibleSkip == C.TIME_UNSET) maxPossibleSkip = currentNum;
                if (num <= maxPossibleSkip) exoPlayer.seekTo(num);
                else exoPlayer.seekTo(currentNum);

            } catch (Exception ignore) {}

        });
        rewBtn.setOnClickListener(v -> {

            long num = exoPlayer.getCurrentPosition() - 10000;
            if (num < 0) {
                exoPlayer.seekTo(0);
            } else {
                exoPlayer.seekTo(num);
            }
        });

        ImageView trackSelectionView = findViewById(R.id.exo_track_selection_view);

        trackSelectionView.setOnClickListener( v -> {

            if (!isShowingTrackSelectionDialog && TrackSelectionDialog.willHaveContent(exoPlayer)) {

                isShowingTrackSelectionDialog = true;
                TrackSelectionDialog trackSelectionDialog =
                        TrackSelectionDialog.createForPlayer(
                                exoPlayer,
                                dismissDialog -> isShowingTrackSelectionDialog = false);
                trackSelectionDialog.show(getSupportFragmentManager(), null);
            }

        });

        ImageView fullscreenButton = player.findViewById(R.id.fullscreen);

        fullscreenButton.setOnClickListener(view -> {

            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                // code for portrait mode
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // code for landscape mode
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });

        findViewById(R.id.exo_play).setOnClickListener(v -> {
            if (exoPlayer.getPlayerError() != null) exoPlayer.prepare();
            exoPlayer.play();
            PLAYER_STATE = true;
        });
        findViewById(R.id.exo_pause).setOnClickListener(v -> {
            exoPlayer.pause();
            PLAYER_STATE = false;
        });

    }

    private void setTrackSelectionParameters () {

        if (trackSelectionParameters == null) {
            trackSelectionParameters = new TrackSelectionParameters.Builder(this)
                    .setPreferredAudioLanguage("hi")
                    .setForceLowestBitrate(true)
                    .build();
        }
        exoPlayer.setTrackSelectionParameters(trackSelectionParameters);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("playerState", PLAYER_STATE);
        outState.putBundle("trackSelection", trackSelectionParameters.toBundle());

    }

    private void restoreBundle (Bundle bundle) {

        if (bundle != null) {

            PLAYER_STATE = bundle.getBoolean("playerState");
            trackSelectionParameters = TrackSelectionParameters.fromBundle(bundle.getBundle("trackSelection"));

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        exoPlayer.release();
        player = null;
        exoPlayer = null;
        trackSelectionParameters = null;

    }
}
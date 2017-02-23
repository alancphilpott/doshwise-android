package phil.alan.audio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer MP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MP = MediaPlayer.create(MainActivity.this, R.raw.beep);

        Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MP.setLooping(true);
                MP.start();
            }
        });

        Button stopButton = (Button) findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MP.pause();
            }
        });
    }

    protected void onPause() {
        super.onPause();
        if(MP != null) {
            MP.reset();
            MP.release();
        }
    }

    protected void onResume() {
        super.onResume();
        MP = MediaPlayer.create(MainActivity.this, R.raw.beep);
    }
}

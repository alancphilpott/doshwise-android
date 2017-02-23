package phil.alan.lecture9;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View newGameButton = findViewById(R.id.new_game);
        newGameButton.setOnClickListener(this);

        View loadGameButton = findViewById(R.id.load_game);
        loadGameButton.setOnClickListener(this);

        View highScoresButton = findViewById(R.id.high_scores);
        highScoresButton.setOnClickListener(this);

        View howToPlayButton = findViewById(R.id.how_to_play);
        howToPlayButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.new_game:
                Toast.makeText(this, "New Game", Toast.LENGTH_SHORT).show();
                break;

            case R.id.load_game:
                Toast.makeText(this, "Load Game", Toast.LENGTH_SHORT).show();
                break;

            case R.id.high_scores:
                Toast.makeText(this, "High Scores", Toast.LENGTH_SHORT).show();
                break;

            case R.id.how_to_play:
                Toast.makeText(this, "How To Play", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

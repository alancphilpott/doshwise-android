package phil.alan.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class EnterNameActivity extends AppCompatActivity {

    private TextView welcomeMessage;
    private EditText userName;
    private Button saveUser;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);

        welcomeMessage = (TextView) findViewById(R.id.welcome);
        userName = (EditText) findViewById(R.id.user_name);
        saveUser = (Button) findViewById(R.id.save_user);
        btnContinue = (Button) findViewById(R.id.continue_button);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userNameString = prefs.getString(getString(R.string.pref_user_name),getString(R.string.guest));
        final String welcomeMessageString = getString(R.string.welcome_string,userNameString);
        welcomeMessage.setText(welcomeMessageString);

        saveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameFromUser = userName.getText().toString();
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(getString(R.string.pref_user_name),userNameFromUser);
                String welcomeMessageUpdateString = getString(R.string.welcome_string,userNameFromUser);
                welcomeMessage.setText(welcomeMessageUpdateString);
                editor.apply();
                Toast.makeText(EnterNameActivity.this, (getString(R.string.message_user_saved, userNameFromUser)), Toast.LENGTH_SHORT).show();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterNameActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}

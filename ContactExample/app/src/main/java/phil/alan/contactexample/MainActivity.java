package phil.alan.contactexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                null,null,null);

        List<String> contacts = new ArrayList<>();

        try
        {
            while (c.moveToNext()) {
                contacts.add(c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            }
        } finally
        {
            c.close();
        }

        String toastText = "Contact List: ";
        Toast toast = Toast.makeText(getApplicationContext(),toastText, Toast.LENGTH_SHORT);
        for (String s : contacts)
            toastText += s + " ";
        toast.makeText(getApplicationContext(), toastText + " ", Toast.LENGTH_SHORT);
        toast.show();
    }
}

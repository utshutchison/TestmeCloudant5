package hutchison_teach.ca.testme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CloudantClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        client = ClientBuilder.account("ghutchis")
                .username("ghutchis")
                .password("CHANGEME")
                .build();

        new DownloadWebpageTask().execute();
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // Get a List of all the databases this Cloudant account
            List<String> databases = client.getAllDbs();
            Log.d("Cloudant Test", "My databases");
            for (String db : databases) {
                Log.d("Cloudant Test", db);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(getApplicationContext(), "Got Database Info", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return "";

        }

    }

}

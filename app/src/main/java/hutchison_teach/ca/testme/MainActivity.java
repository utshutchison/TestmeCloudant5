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
import com.cloudant.client.api.Database;
import com.cloudant.http.Http;
import com.cloudant.http.HttpConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CloudantClient client;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        client = ClientBuilder.account("ghutchis")
                .username("ghutchis")
                .password("changemenow")
                .build();

        new DownloadWebpageTask().execute();
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // Get a List of all the databases this Cloudant account
            db = client.database("feud",true);
            Question q = new Question("Which Party?");
            q.addAnswer(new Answer(30,"Liberal"));
            q.addAnswer(new Answer(20,"PC"));
            q.addAnswer(new Answer(10,"NDP"));
            db.save(q);

            Question q2 = db.find(Question.class,
                    "c3e337cddb514dcfa2472ceda887fc63");
            Log.d("HELLO",String.valueOf(q2.numAnswers()));

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

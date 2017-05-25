package hutchison_teach.ca.testme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView tv;
    private CloudantClient client;
    private Database db;
    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        tv = (TextView) findViewById(R.id.tvout);
        client = ClientBuilder.account("ghutchis")
                .username("ghutchis")
                .password("XXXXXX")
                .build();

        new DownloadWebpageTask().execute();
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // Get a List of all the databases this Cloudant account
            db = client.database("feud",true);
         /*   Question q = new Question(5, "Which Party?");
            q.addAnswer(new Answer(30,"Liberal"));
            q.addAnswer(new Answer(20,"PC"));
            q.addAnswer(new Answer(10,"NDP"));
            db.save(q);
*/
            List<Question> allQuestions = new ArrayList<Question>();

            try {
                allQuestions = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Question.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String out = "";

            for (Question quest : allQuestions){
                Log.d("Question", quest.getQuestion());
                for (Answer ans : quest.getAnswers()){
                    total += ans.getNum();
                }
            }


           // Log.d("HELLO", String.valueOf(allQuestions.size()));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   // Toast toast = Toast.makeText(getApplicationContext(), "Updated Database", Toast.LENGTH_SHORT);
                   // toast.show();
                    tv.setText("Survey total was :" + String.valueOf(total));
                }
            });
            return "";

        }

    }

}

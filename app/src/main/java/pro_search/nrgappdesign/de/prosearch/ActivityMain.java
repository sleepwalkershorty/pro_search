package pro_search.nrgappdesign.de.prosearch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.R.attr.type;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button bData = (Button) findViewById(R.id.button2);
        bData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        //"/v2.5/me",
                        //"/search?q=Andreas+Jungmann&type=user",
                        "/search?q=Stephan+greupner&type=user",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                            /* handle the result */
                                try {

                                    JSONObject object = response.getJSONObject();
                                    if (object != null)
                                    {
                                        //String name = object.getString("name");
                                        //Toast.makeText(ActivityMain.this,"Hallo "+name+"! Wie geht's dir?",Toast.LENGTH_LONG).show();
                                        Log.d("Test",object.toString());
                                        JSONArray array = object.getJSONArray("data");

                                        if (array != null)
                                        {
                                            for (int i=0;i<array.length();i++) {
                                                Log.d("Test", array.get(i).toString());
//                                                Log.d("Test", array.getJSONObject(i).getString("name"));
                                            }
                                        }
                                    }
                                    else
                                        Log.d("Test",response.toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ).executeAsync();
            }
        });
    }
}

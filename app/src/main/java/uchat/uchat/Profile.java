package uchat.uchat;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {

    ImageView profile_image;
    TextView profile_name, profile_email, profile_username, profile_year,
             profile_major, profile_word_description;
    StringRequest stringRequest;
    final String url = "http://73.42.47.33/get-profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");
        toolbar.setTitleTextColor(getResources().getColor(R.color.White));

        profile_image = (ImageView) findViewById(R.id.profile_image);

        profile_name = (TextView) findViewById(R.id.profile_name);
        profile_email = (TextView) findViewById(R.id.profile_email);
        profile_username = (TextView) findViewById(R.id.profile_username);
        profile_year = (TextView) findViewById(R.id.profile_year);
        profile_major = (TextView) findViewById(R.id.profile_major);
        profile_word_description = (TextView) findViewById(R.id.profile_word_description);
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    profile_username.setText(jo.getString("username"));
                    profile_name.setText(jo.getString("name"));
                    profile_email.setText(jo.getString("email"));
                    profile_year.setText(jo.getString("year"));
                    profile_major.setText(jo.getString("major"));
                    profile_word_description.setText(jo.getString("mood"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.getMessage());
            }
        });


    }
}



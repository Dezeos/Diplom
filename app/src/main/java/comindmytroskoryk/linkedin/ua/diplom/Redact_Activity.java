package comindmytroskoryk.linkedin.ua.diplom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Redact_Activity extends AppCompatActivity {

    TextView tvTITLE ;
    EditText etDESC ;
    Button bSAVE;
    String description = "";
    String title = "";
    String API_KEY = "";
    String groupId = "";
    private ArrayList<Unswer> getUNSWER = new ArrayList<>();
   // private ArrayList<Unswer> getUNSWER2 = new ArrayList<>();

    private static final String URL = "http://labo-pbei.no-ip.org:10001/";
    private Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    Link link = retrofit.create(Link.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);

        tvTITLE = (TextView) findViewById(R.id.tvTITLE);
        etDESC = (EditText) findViewById(R.id.etDESC);
        bSAVE = (Button) findViewById(R.id.bSAVE);

        Intent intent = getIntent();

        title = intent.getStringExtra("Title");
        description = intent.getStringExtra("Description");
        getUNSWER = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");
        API_KEY = intent.getStringExtra("API_KEY");

        Log.d("LOGO", "IMAGEBUTTON " +title + " " + description );

        etDESC.setText(Html.fromHtml(description));


        Log.d("LOGO", "REDACT_ACTIVITY" + String.valueOf(getUNSWER));


        bSAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    for (int i = 0; i < getUNSWER.size() ; i++) {

                        if (getUNSWER.get(i).getTitle().equals(title)){
                            getUNSWER.get(i).setDescription(String.valueOf( etDESC.getText().toString()));
                            groupId = getUNSWER.get(i).getgroupId();
                        }

                    }


                redactDESCRIPTION(groupId,API_KEY,title,etDESC.getText().toString());

                Intent intent = new Intent(Redact_Activity.this,SecondActivity.class);
                intent.putExtra("Title2",title);
                intent.putExtra("Description2", etDESC.getText().toString());
                intent.putExtra("aboutGROUPS",getUNSWER);
                intent.putExtra("API_KEY", API_KEY);
                startActivity(intent);
                //setResult(RESULT_OK, intent);
                //finish();
            }

        });
    }

    public void redactDESCRIPTION(String beacon_id, String api_key, String title, String description){

        Call<redactSTATUS> call = link.redact( "maintain-api/edit?id=" + beacon_id +"&api_key=" + api_key , title , description);
        call.enqueue(new Callback<redactSTATUS>() {
            @Override
            public void onResponse(Response<redactSTATUS> response3, Retrofit retrofit) {
                Log.d("LOGO", "Get Status " +  String.valueOf(response3.message() + response3.code()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
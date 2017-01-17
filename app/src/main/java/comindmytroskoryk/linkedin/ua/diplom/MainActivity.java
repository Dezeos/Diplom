package comindmytroskoryk.linkedin.ua.diplom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;



public class MainActivity extends AppCompatActivity {


    EditText etEMAIL;
    EditText etPASS;
    Button bLOGIN;

    String AUNT_KEY = "";

    ArrayList<String> groups_list = new ArrayList<String>();;
    ArrayList<Unswer> ui;
    String[] data ;

    private static final String URL = "http://labo-pbei.no-ip.org:10001/";
    private Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    Link link = retrofit.create(Link.class);

    int count = 0;

    DB db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEMAIL = (EditText) findViewById(R.id.etEMAIL);
        etPASS = (EditText) findViewById(R.id.etPASS);
        bLOGIN = (Button)findViewById(R.id.bLOGIN);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Call<ArrayList<Unswer>> call1 = link.getGroups2();
        call1.enqueue(new Callback<ArrayList<Unswer>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<Unswer>> response227, Retrofit retrofit) {

                ui = response227.body();

                for (Unswer s:ui) {

                    groups_list.add(String.valueOf(s.getFirstName()));
                }

                set_group_name(groups_list);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });



            bLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*

                Call<User> call = link.authentication(etEMAIL.getText().toString(),etPASS.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(retrofit.Response<User> response, Retrofit retrofit) {

                        AUNT_KEY = String.valueOf(response.body());
                        Log.d("LOGO",  AUNT_KEY);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

                Call<ArrayList<Unswer>> call1 = link.getGroups();
                call1.enqueue(new Callback<ArrayList<Unswer>>() {
                    @Override
                    public void onResponse(retrofit.Response<ArrayList<Unswer>> response228, Retrofit retrofit) {
                        Log.d("LOGO", response228.body()+ response228.message() + response228.code());
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });*/

            }
        });

    }

    public void set_group_name (ArrayList<String> names){
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,names );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setNotifyOnChange(true);

        Spinner spinner = (Spinner) findViewById(R.id.sGROUPS);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Select the required group");
        // выделяем элемент
        spinner.setSelection(1);
    }






}




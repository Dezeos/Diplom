package comindmytroskoryk.linkedin.ua.diplom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;



public class MainActivity extends AppCompatActivity {


    EditText etEMAIL;
    EditText etPASS;
    Button bLOGIN;
    Spinner sGROUP;
    final int DIALOG_EXIT = 1;


    public static String API_KEY = "";


    ProgressDialog pd;
    ArrayList<String> groups_list = new ArrayList<String>();
    ArrayList<Unswer> ui = new ArrayList<Unswer>();
    ArrayList<Unswer> needs = new ArrayList<>();


    private static final String URL = "http://labo-pbei.no-ip.org:10001/";
    private Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    Link link = retrofit.create(Link.class);


    //DB db;
   // Cursor c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db = new DB(MainActivity.this);
       //db.open();
    }

    @Override
    protected void onResume() {

        etEMAIL = (EditText) findViewById(R.id.etEMAIL);
        etPASS = (EditText) findViewById(R.id.etPASS);
        sGROUP = (Spinner) findViewById(R.id.sGROUPS);
        bLOGIN = (Button)findViewById(R.id.bLOGIN);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        set_spiner_items();
        Log.d("LOGO",  " 1 response.body() = " +  API_KEY);

        bLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((etEMAIL.getText().toString().isEmpty()||etPASS.getText().toString().isEmpty())||(etEMAIL.getText().toString().isEmpty()&&etPASS.getText().toString().isEmpty())){
                    Log.d("LOGO",  " EMPTY  " );
                    Toast toast = Toast.makeText(MainActivity.this, "Заполните пустые поля", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else {
                    get_api_key(etEMAIL.getText().toString(), etPASS.getText().toString());


                }
            }
        });

        Log.d("LOGO",  " 4 response.body() = " +  API_KEY);
        super.onResume();
    }

    public void set_spiner_items(){


        final Call<ArrayList<Unswer>> call1 = link.getGroups2();
        call1.enqueue(new Callback<ArrayList<Unswer>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<Unswer>> response, Retrofit retrofit) {

              //  ui = response.body();

                for (Unswer s : response.body()) {

                    groups_list.add(String.valueOf(s.getFirstName()));
                }

                set_group_name(groups_list);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("LOGO",  " onFailure(Throwable t)  = " +  t.getMessage());
                showDialog(DIALOG_EXIT);
            }
        });
    }


    public void set_group_name (ArrayList<String> names){
        // адаптер для выпадающего списка групп
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,names );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setNotifyOnChange(true);

        Spinner spinner = (Spinner) findViewById(R.id.sGROUPS);
        spinner.setAdapter(adapter);
        // заголовок списка групп
        spinner.setPrompt("Select the required group");
        // выделяем элемент списка групп  по умолчанию
        spinner.setSelection(0);
    }

    public void get_api_key(String email, String password){
        Call<User> call = link.authentication(email,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit.Response<User> response, Retrofit retrofit) {

                try {
                    API_KEY = response.body().getApi_key();
                    get_beacons_description(API_KEY);
                    pd = new ProgressDialog(MainActivity.this);
                    pd.setTitle("Подождите !");
                    pd.setMessage("Идет загрузка данных...");
                    pd.show();
                }
                catch (NullPointerException np){
                    Toast toast = Toast.makeText(MainActivity.this, "Введенные некорректные данные! Пользователь не зарегестрированн!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }
                Log.d("LOGO",  " 2 response.body() = " +  API_KEY);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("LOGO",  " onFailure228(Throwable t)  = " +  t.getMessage());
                showDialog(DIALOG_EXIT);
            }
        });

        Log.d("LOGO",  " 3 response.body() = " +  API_KEY);

    }


    public void get_beacons_description(String api_key){

        Call<ArrayList<Unswer>> call1 = link.getGroups("maintain-api/beacons?api_key=" + api_key);
        call1.enqueue(new Callback<ArrayList<Unswer>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<Unswer>> response228, Retrofit retrofit) {

                    //Log.d("LOGO", "Get unswer " +  String.valueOf(response228.body()));
                for (Unswer s : response228.body()) {
                    if ((sGROUP.getSelectedItem().toString()).equals(s.getGroupName())) {
                        needs.add(s);
                        Log.d("LOGO", "Get unswer " +  String.valueOf(s));
                    }

                }

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("aboutGROUPS", needs);
                intent.putExtra("API_KEY", API_KEY);
                startActivity(intent);

                pd.dismiss();
               // db.write_DB(needs);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

       // c.close();
      //  db.close();
    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            // заголовок
            adb.setTitle("Ошибка");
            // сообщение
            adb.setMessage("Отсутствует интернет соединение! Перезапустите приложение!");
            // иконка
            adb.setIcon(android.R.drawable.ic_dialog_info);
            // кнопка положительного ответа
            adb.setPositiveButton("OK", myClickListener);
            // создаем диалог
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            finish();

        }
    };



}




package comindmytroskoryk.linkedin.ua.diplom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.Nullable;
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


/*
Класс реализует главную активность для аутентификации пользователя
 */
public class MainActivity extends AppCompatActivity {


    EditText etEmail;
    EditText etPass;
    Button btnLogin;
    Spinner spinnerGroups;

    final int DIALOG_EXIT = 1;
    private String apiKey = "";


    ProgressDialog pdWaitDownload;
    ArrayList<String> listGroups = new ArrayList<String>();
    ArrayList<Unswer> descriptionGroups = new ArrayList<>();


    public static final String BASE_URL = "http://labo-pbei.no-ip.org:10001/";
    private Gson gsonConverter = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .build();
    Link link = retrofit.create(Link.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /*
    Метод содержит объявление элементов активности,
    формирование списка групп с помощью метода setSpinerItems(),
    обработку нажатия кнопки.
    */
    @Override
    protected void onResume() {

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        spinnerGroups = (Spinner) findViewById(R.id.spinnerGroups);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setSpinerItems();

        //Log.d("LOGO",  " 1 response.body() = " + apiKey);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnLogin.setClickable(false);

                if ((etEmail.getText().toString().isEmpty() || etPass.getText().toString().isEmpty()) || (etEmail.getText().toString().isEmpty() && etPass.getText().toString().isEmpty())) {

                    Toast emptyFields = Toast.makeText(MainActivity.this, "Заполните пустые поля", Toast.LENGTH_LONG);
                    emptyFields.setGravity(Gravity.CENTER, 0, 0);
                    emptyFields.show();
                    btnLogin.setClickable(true);

                } else {

                    getApiKey(etEmail.getText().toString(), etPass.getText().toString());

                }
            }
        });

        //Log.d("LOGO",  " 4 response.body() = " + apiKey);
        super.onResume();
    }


    /*
    Метод реализует запрос на сервер для получения описания групп и обработку ответа
    с последующим сохранением его в список и передачей в setGroupName().
    */
    public void setSpinerItems() {

        final Call<ArrayList<Unswer>> call1 = link.getShortAboutGroups();
        call1.enqueue(new Callback<ArrayList<Unswer>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<Unswer>> response, Retrofit retrofit) {

                for (Unswer oneUnswerGroup : response.body()) {

                    listGroups.add(String.valueOf(oneUnswerGroup.getFirstName()));

                }

                setGroupName(listGroups);

            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("LOGO", " onFailure(Throwable t)  = " + t.getMessage());
                showDialog(DIALOG_EXIT);

            }
        });
    }


    /*
    Метод реализует заполнение списка именами групп, полученными от setSpinerItems();
     */
    public void setGroupName(ArrayList<String> groupsNames) {

        // адаптер для выпадающего списка групп
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupsNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setNotifyOnChange(true);

        spinnerGroups.setAdapter(adapter);
        spinnerGroups.setSelection(0);

    }

    /*
    Метод реализует запрос на сервер для получения API KEYя зарегестрированного пользователя,
    вызов метода getBeaconsDescription(), запуск прогресс-бара.
     */
    public void getApiKey(String email, String password) {

        Call<User> call = link.authentication(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit.Response<User> response, Retrofit retrofit) {

                try {

                    apiKey = response.body().getApiKey();

                    getBeaconsDescription(apiKey);

                    pdWaitDownload = new ProgressDialog(MainActivity.this);
                    pdWaitDownload.setTitle("Подождите !");
                    pdWaitDownload.setMessage("Идет загрузка данных...");
                    pdWaitDownload.show();

                } catch (NullPointerException np) {

                    Toast incorrectData = Toast.makeText(MainActivity.this, "Некорректные данные! Пользователь не зарегистрированн!", Toast.LENGTH_LONG);
                    incorrectData.setGravity(Gravity.CENTER, 0, 0);
                    incorrectData.show();
                    btnLogin.setClickable(true);

                }
                //Log.d("LOGO",  " 2 response.body() = " + apiKey);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("LOGO", " onFailure228(Throwable t)  = " + t.getMessage());
                showDialog(DIALOG_EXIT);
            }
        });

        // Log.d("LOGO",  " 3 response.body() = " + apiKey);

    }


    /*
    Метод реализует запрос полного содержания полей всех групп, исходя из полученного API KEYя пользователя,
    отбор и сохранение информации для выбранной пользователем группы из списка,
    вызов следующей активности и передачу отобранной информации
     */
    public void getBeaconsDescription(String apiKey) {

        Call<ArrayList<Unswer>> call1 = link.getAllAboutGroups("maintain-api/beacons?api_key=" + apiKey);
        call1.enqueue(new Callback<ArrayList<Unswer>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<Unswer>> response228, Retrofit retrofit) {

                //Log.d("LOGO", "Get unswer " +  String.valueOf(response228.body()));
                for (Unswer containGroupsInfo : response228.body()) {
                    if ((spinnerGroups.getSelectedItem().toString()).equals(containGroupsInfo.getGroupName())) {
                        descriptionGroups.add(containGroupsInfo);
                        Log.d("LOGO", "Get unswer " + String.valueOf(containGroupsInfo.getDescription()));
                    }

                }

                Intent intent = new Intent(MainActivity.this, ListGroupActivity.class);
                intent.putExtra("aboutGROUPS", descriptionGroups);
                intent.putExtra("apiKey", MainActivity.this.apiKey);
                startActivity(intent);

                pdWaitDownload.dismiss();
                //pdWaitDownload.dismiss();
                // db.write_DB(descriptionGroups);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    /*
    Метод реализует формирование диалога, когда отсутствует интернет-соединение
     */
    protected Dialog onCreateDialog(int id) {

        if (id == DIALOG_EXIT) {
            AlertDialog.Builder errorDialog = new AlertDialog.Builder(this);

            errorDialog.setCancelable(false);
            // заголовок
            errorDialog.setTitle("Ошибка");
            // сообщение
            errorDialog.setMessage("Отсутствует интернет соединение!");
            // иконка
            errorDialog.setIcon(android.R.drawable.ic_dialog_info);
            // кнопка положительного ответа
            errorDialog.setPositiveButton(R.string.btnClose, myClickListener);
            // кнопка отрицательного ответа
            errorDialog.setNegativeButton(R.string.btnReload, myClickListener);


            // создаем диалог
            return errorDialog.create();
        }
        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {
                case DialogInterface.BUTTON_NEGATIVE:
                    finish();
                    Intent reloadIntent = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(getBaseContext().getPackageName());
                    reloadIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    reloadIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(reloadIntent);

                    break;
                case DialogInterface.BUTTON_POSITIVE:
                    finish();
                    break;
            }
            finish();

        }
    };


    @Override
    public void onBackPressed() {
        // code here to show dialog
        // super.onBackPressed();  // optional depending on your needs
        finish();
    }

}


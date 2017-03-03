package comindmytroskoryk.linkedin.ua.diplom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/*
Класс реализует активность для редактирования контента
 */
public class EditActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvPreView;
    EditText etDescription;
    Button btnSave;
    Button btnPreView;
    ProgressDialog pdWaitDownload;

    String description = "";
    String title = "";
    String apiKey = "";
    String beaconsId = "";
    final int DIALOG_EXIT = 1;

    private ArrayList<Unswer> getUnswer = new ArrayList<>();


    final String BASE_URL = MainActivity.BASE_URL;
    private Gson gsonConverter = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .build();
    Link link = retrofit.create(Link.class);




    /*
    Метод содержит объявление элементов активности,
    обработку нажатия кнопки с последующей отправкой и сохранением
    контента на сервере и в list_group_activity. Также была добавлена функция предпросмотра
    редактированного контента
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);

        tvTitle = (TextView) findViewById(R.id.tvTITLE);
        tvPreView = (TextView) findViewById(R.id.tvPreView);
        etDescription = (EditText) findViewById(R.id.etDESC);
        btnSave = (Button) findViewById(R.id.bSAVE);
        btnPreView = (Button) findViewById(R.id.bPREVIEW);

        Intent intent = getIntent();

        title = intent.getStringExtra("Title");
        description = intent.getStringExtra("Description");
        getUnswer = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");
        apiKey = intent.getStringExtra("apiKey");
                            //Html.fromHtml//
        etDescription.setText((description));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnSave.setClickable(false);
                pdWaitDownload = new ProgressDialog(EditActivity.this);
                pdWaitDownload.setTitle("Подождите !");
                pdWaitDownload.setMessage("Идет загрузка данных...");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pdWaitDownload.show();

                    for (int i = 0; i < getUnswer.size() ; i++) {

                        if (getUnswer.get(i).getTitle().equals(title)){
                            getUnswer.get(i).setDescription(String.valueOf( etDescription.getText().toString()));
                            beaconsId = getUnswer.get(i).getId();
                        }

                    }

                Log.d("LOGO", "Промежуточная проверка " + description );
                Intent intent = new Intent(EditActivity.this,ListGroupActivity.class);
                //intent.setType("text/html");
                intent.putExtra("Title2",title);
                intent.putExtra("Description2", etDescription.getText().toString());
                intent.putExtra("aboutGROUPS", getUnswer);
                intent.putExtra("apiKey", apiKey);
                redactDescription(beaconsId, apiKey,title, etDescription.getText().toString() , intent);

            }

        });

        btnPreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                description = etDescription.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    tvPreView.setText(Html.fromHtml(description,Html.FROM_HTML_MODE_LEGACY));
                }
                else {
                    tvPreView.setText(Html.fromHtml(description));
                }

            }
        });
    }


    /*
    Метод реализует запрос на сервер для сохранения данных,
    исходя из ID маячка(НЕ ГРУППЫ!) и  API KEYя польхователя
     */
    public void redactDescription(String beaconId, String apiKey, final String title, String description, final Intent intent){

        Log.d("LOGO", "Отправка на сервер " + description );
        Call<StatusResult> call = link.redact( "maintain-api/edit?id=" + beaconId +"&api_key=" + apiKey , title , description);
        call.enqueue(new Callback<StatusResult>() {
            @Override
            public void onResponse(Response<StatusResult> response3, Retrofit retrofit) {
                startActivity(intent);
                Log.d("LOGO", "Get Status " +  String.valueOf(response3.message() + response3.code()));
            }

            @Override
            public void onFailure(Throwable t) {
                showDialog(DIALOG_EXIT);
            }
        });
    }


    /*
    Метод реализует формирование диалога, когда отсутствует интернет-соединение
     */
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

    @Override
    protected void onStop() {
        super.onStop();
        btnSave.setClickable(true);
        pdWaitDownload.dismiss();
    }
}
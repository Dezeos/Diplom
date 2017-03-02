package comindmytroskoryk.linkedin.ua.diplom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

/*
Класс реализует активность для редактирования контента
 */
public class EditActivity extends AppCompatActivity {

    TextView tvTitle;
    EditText etDescription;
    Button btnSave;
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
    контента на сервере и в GroupActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);

        tvTitle = (TextView) findViewById(R.id.tvTITLE);
        etDescription = (EditText) findViewById(R.id.etDESC);
        btnSave = (Button) findViewById(R.id.bSAVE);

        Intent intent = getIntent();

        title = intent.getStringExtra("Title");
        description = intent.getStringExtra("Description");
        getUnswer = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");
        apiKey = intent.getStringExtra("apiKey");

        etDescription.setText(Html.fromHtml(description));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    for (int i = 0; i < getUnswer.size() ; i++) {

                        if (getUnswer.get(i).getTitle().equals(title)){
                            getUnswer.get(i).setDescription(String.valueOf( etDescription.getText().toString()));
                            beaconsId = getUnswer.get(i).getId();
                        }

                    }

                Intent intent = new Intent(EditActivity.this,GroupActivity.class);
                intent.putExtra("Title2",title);
                intent.putExtra("Description2", etDescription.getText().toString());
                intent.putExtra("aboutGROUPS", getUnswer);
                intent.putExtra("apiKey", apiKey);

                redactDescription(beaconsId, apiKey,title, etDescription.getText().toString(), intent);

            }

        });
    }


    /*
    Метод реализует запрос на сервер для сохранения данных,
    исходя из ID маячка(НЕ ГРУППЫ!) и  API KEYя польхователя
     */
    public void redactDescription(String beaconId, String apiKey, final String title, String description, final Intent intent){

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
}
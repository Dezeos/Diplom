package comindmytroskoryk.linkedin.ua.diplom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;

/*
Класс реализует активность с прокручиваемым списком типа RecyclerView
 */
public class GroupActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    ArrayList<Unswer> unswerFromMain;
    private String apiKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.GroupActivity);

    }



    /*
    Метод предназначен для создания RecyclerView, который формируеться с помощью RecyclerAdapter.
    Передача в адаптер API KEY и содержимого группы обусловлена тем,
    что вызов активности для редактирования (RedactActivity) происходит из класса RecyclerAdapter
    */
    @Override
    protected void onResume() {
        super.onResume();

        Log.d("LOGO", "onResume " );

        unswerFromMain = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");
        apiKey = getIntent().getStringExtra("apiKey");

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerAdapter = new RecyclerAdapter(unswerFromMain, apiKey);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);


        String title = getIntent().getStringExtra("Title2");
        String desc = getIntent().getStringExtra("Description2");

        Log.d("LOGO", "IM " + title + " " + desc);

        Log.d("LOGO", "GOODBYE" + String.valueOf(unswerFromMain));

    }

}

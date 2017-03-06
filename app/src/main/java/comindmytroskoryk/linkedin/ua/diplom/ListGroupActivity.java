package comindmytroskoryk.linkedin.ua.diplom;

import android.content.Intent;
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
public class ListGroupActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    ArrayList<Unswer> unswerFromMain;
    private String apiKey = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_group_activity);

    }




    @Override
    protected void onResume() {
        super.onResume();

        unswerFromMain = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");
        apiKey = getIntent().getStringExtra("apiKey");

        makingList(unswerFromMain, apiKey);

    }


    /*
  Метод предназначен для создания RecyclerView, который формируеться с помощью RecyclerAdapter.
  Передача в адаптер API KEY и содержимого группы обусловлена тем,
  что вызов активности для редактирования (RedactActivity) происходит из класса RecyclerAdapter
  */
    public void makingList(ArrayList<Unswer> unswerFromMain, String apiKey){

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerAdapter = new RecyclerAdapter(unswerFromMain, apiKey, ListGroupActivity.this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);

        Log.d("LOGO", "Заполение полей " + String.valueOf(this.unswerFromMain));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data==null){
            Log.d("LOGO", "он активити резалт нет данных" );
            return;
        } else {

            unswerFromMain = (ArrayList<Unswer>) data.getSerializableExtra("aboutGROUPS");

            recyclerAdapter.updateAdapter(unswerFromMain);

        }

    }

}

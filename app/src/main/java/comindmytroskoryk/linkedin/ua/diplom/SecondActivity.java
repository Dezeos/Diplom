package comindmytroskoryk.linkedin.ua.diplom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


    }


        public void redactACTIVITY(Unswer uns){

            Intent intent2 = new Intent(SecondActivity.this, Redact_Activity.class);
            intent2.putExtra("Title",uns.getTitle());
            intent2.putExtra("Description",uns.getDescription());
            startActivityForResult(intent2,0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Unswer> getUNSWER = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerAdapter = new RecyclerAdapter(getUNSWER);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);

        String title = getIntent().getStringExtra("Title2");
        String desc = getIntent().getStringExtra("Description2");
        Log.d("LOGO", "IM " +title + " " + desc );
    }

    /*
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well


                if (view.getId() == R.id.imbSECOND) {
                    Toast.makeText(SecondActivity.this, "Single Click on position        :" + position,
                            Toast.LENGTH_SHORT).show();
                }
                else  Toast.makeText(SecondActivity.this, "AAAAAAAAAAAAA       :" + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(SecondActivity.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));

    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view, int position);
    }*/
}

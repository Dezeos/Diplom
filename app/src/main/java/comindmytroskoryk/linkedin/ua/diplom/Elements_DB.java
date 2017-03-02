package comindmytroskoryk.linkedin.ua.diplom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
/*
public class Elements_DB extends AppCompatActivity {

    private static final String TAG = "MyAppPP";

    ImageButton im_btn;
    int count = 1;
    String name = "";
    String  description = "";
    ArrayList<Unswer> getUNSWER = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements__db);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llSEL);

        LayoutInflater ltInflater = getLayoutInflater();
        getUNSWER = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");

        Log.d("LOGO", "Second activity " +  String.valueOf(getUNSWER));


        for (Unswer group : getUNSWER) {

            View item = ltInflater.inflate(R.layout.example_layout, linearLayout, false);
            im_btn = (ImageButton) item.findViewById(R.id.imbSECOND);
            im_btn.setId(Integer.parseInt(String.valueOf(count)));
            Log.i(TAG, String.valueOf(im_btn.getId()));
            TextView tvPOS = (TextView) item.findViewById(R.id.tvPOS);
            tvPOS.setText(String.valueOf(count));
            final TextView tvTITLE = (TextView) item.findViewById(R.id.tvNAME);
            tvTITLE.setText(group.getTitle());
            TextView tvDESCR = (TextView) item.findViewById(R.id.tvDESCR);
            tvDESCR.setText(Html.fromHtml(group.getDescription()));
            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;

            linearLayout.addView(item);

            im_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, String.valueOf(v.getId()));
                    Intent intent2 = new Intent(Elements_DB.this, EditActivity.class);
                    intent2.putExtra("Description",tvTITLE.getText().toString());
                    startActivityForResult(intent2,0);
                }
            });
            count++;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null) {return;}
        String name = data.getStringExtra("redaction Description");


    }

}

*/


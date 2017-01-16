package comindmytroskoryk.linkedin.ua.diplom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Elements_DB extends AppCompatActivity {

    private static final String TAG = "MyAppPP";

    ImageButton im_btn;
    int num = 0;
    String name = "";
    String  description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements__db);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llSEL);

        LayoutInflater ltInflater = getLayoutInflater();


        for (int i = 0; i <5 ; i++) {
            String buttonNAME = "im_btn" + Integer.toString(i);

            View item = ltInflater.inflate(R.layout.example_layout, linearLayout, false);
            im_btn = (ImageButton) item.findViewById(R.id.imbSECOND);
            im_btn.setId(i);
            Log.i(TAG, String.valueOf(im_btn.getId()));
            TextView tvPOS = (TextView) item.findViewById(R.id.tvPOS);
            tvPOS.setText(Integer.toString(i+1));
            final TextView tvNAME = (TextView) item.findViewById(R.id.tvNAME);
            tvNAME.setText(name);
            TextView tvDESCR = (TextView) item.findViewById(R.id.tvDESCR);
            tvDESCR.setText(description);
            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;

            linearLayout.addView(item);

            im_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, String.valueOf(v.getId()));
                    Intent intent2 = new Intent(Elements_DB.this, Redact_Activity.class);
                    intent2.putExtra("Description",tvNAME.getText().toString());
                    startActivity(intent2);
                }
            });

        }







    }





}

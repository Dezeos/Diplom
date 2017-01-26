package comindmytroskoryk.linkedin.ua.diplom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Redact_Activity extends AppCompatActivity {

    TextView tvTITLE ;
    EditText etDESC ;
    Button bSAVE;
    String description = "";
    String title = "";
    private ArrayList<Unswer> getUNSWER = new ArrayList<>();
    private ArrayList<Unswer> getUNSWER2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);

        tvTITLE = (TextView) findViewById(R.id.tvTITLE);
        etDESC = (EditText) findViewById(R.id.etDESC);
        bSAVE = (Button) findViewById(R.id.bSAVE);

        Intent intent = getIntent();

        title = intent.getStringExtra("Title");
        description = intent.getStringExtra("Description");
        getUNSWER = (ArrayList<Unswer>) getIntent().getSerializableExtra("aboutGROUPS");

        Log.d("LOGO", "IMAGEBUTTON " +title + " " + description );

        etDESC.setText(Html.fromHtml(description));


        bSAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    for (int i = 0; i < getUNSWER.size() ; i++) {

                        if (getUNSWER.get(i).getTitle().equals(title)){
                            getUNSWER.get(i).setDescription(String.valueOf( etDESC.getText().toString()));
                        }

                    }

                Intent intent = new Intent(Redact_Activity.this,SecondActivity.class);
                intent.putExtra("Title2",title);
                intent.putExtra("Description2", etDESC.getText().toString());
                intent.putExtra("aboutGROUPS",getUNSWER);
                startActivity(intent);
                //setResult(RESULT_OK, intent);
                //finish();
            }

        });
    }
}
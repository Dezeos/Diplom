package comindmytroskoryk.linkedin.ua.diplom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Redact_Activity extends AppCompatActivity {

    TextView tvTITLE ;
    EditText etDESC ;
    Button bSAVE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);

        tvTITLE = (TextView) findViewById(R.id.tvTITLE);
        etDESC = (EditText) findViewById(R.id.etDESC);
        bSAVE = (Button) findViewById(R.id.bSAVE);


    }
}

package comindmytroskoryk.linkedin.ua.diplom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Redact_Activity extends AppCompatActivity {

    TextView tvTITLE ;
    EditText etDESC ;
    Button bSAVE;
    String DESCRIPTION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact);

        tvTITLE = (TextView) findViewById(R.id.tvTITLE);
        etDESC = (EditText) findViewById(R.id.etDESC);
        bSAVE = (Button) findViewById(R.id.bSAVE);

        Intent intent = getIntent();

        DESCRIPTION = intent.getStringExtra("Description");
        etDESC.setText(DESCRIPTION);

        bSAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("redaction Description", etDESC.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

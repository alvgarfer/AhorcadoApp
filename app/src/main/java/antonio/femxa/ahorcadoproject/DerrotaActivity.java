package antonio.femxa.ahorcadoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DerrotaActivity extends AppCompatActivity {

    private String palabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derrota);
        palabra = getIntent().getStringExtra("palabra_clave");

        Button button = (Button) findViewById(R.id.boton_derrota_inicio);

        TextView textView = (TextView) findViewById(R.id.text_palabra_oculta);

        textView.setText(palabra);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DerrotaActivity.this, CategoriaActivity.class);

                startActivity(intent);
            }
        });
    }
}

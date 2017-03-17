package alvaro.games.ahorcadoApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VictoriaActivity extends AppCompatActivity {

    private String palabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(alvaro.games.ahorcadoApp.R.layout.activity_victoria);
        palabra = getIntent().getStringExtra("palabra_clave");

        Button button = (Button) findViewById(alvaro.games.ahorcadoApp.R.id.boton_victoria_inicio);

        TextView textView = (TextView) findViewById(alvaro.games.ahorcadoApp.R.id.text_palabra_oculta_victoria);

        textView.setText(palabra);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(VictoriaActivity.this, CategoriaActivity.class);

                startActivity(intent);
            }
        });
    }
}


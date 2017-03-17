package alvaro.games.ahorcadoApp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class CategoriaActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(alvaro.games.ahorcadoApp.R.layout.activity_categoria);
        this.spCategorias = (Spinner) findViewById(alvaro.games.ahorcadoApp.R.id.spinner_categorias);

        loadSpinnerCategorias();

    }

    /**
     * Cargamos el spinner con el array que esta en categorias.xml
     */
    public void loadSpinnerCategorias() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, alvaro.games.ahorcadoApp.R.array.categorias, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spCategorias.setAdapter(adapter);

        this.spCategorias.setOnItemSelectedListener(this);
    }

    /**
     * Cada vez que se cambie el spinner y no sea la posicion 0(selecciona una categoria) carga
     * el array conrrespondiente de esa categoria, obtiene un string aleatorio de ella
     * y se redirige a activity_tablero con el string conseguido
     * @param parent
     * @param view
     * @param pos La posicion del array de categorias
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if(pos!=0)
        {
            TypedArray array_categorias = getResources().obtainTypedArray(alvaro.games.ahorcadoApp.R.array.array_categorias);
            CharSequence [] array_especifico = array_categorias.getTextArray(pos);
            array_categorias.recycle();

            String palabra = palabraOculta(array_especifico);

            Log.d("MENSAJE2",palabra);

            Intent intent = new Intent(CategoriaActivity.this, TableroActivity.class);

            intent.putExtra("palabra_clave",palabra);

            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Dado un array de strings te devuelve un string aleatorio de ese array
     * @param array_especifico
     * @return
     */
    public String palabraOculta( CharSequence [] array_especifico){
        String palabra = null;

            int aleatoria = (int) (Math.random() * (array_especifico.length+1));
            Log.d("MENSAJE", aleatoria + "");
            palabra = array_especifico[aleatoria].toString();

        return palabra;

    }

}

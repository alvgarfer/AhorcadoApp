package alvaro.games.ahorcadoApp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TableroActivity extends AppCompatActivity {

    private String palabra,palabraAux;
    private int[] array_pics = {alvaro.games.ahorcadoApp.R.drawable.ic_cuerda, alvaro.games.ahorcadoApp.R.drawable.ic_cabeza, alvaro.games.ahorcadoApp.R.drawable.ic_cuerpo, alvaro.games.ahorcadoApp.R.drawable.ic_brazo, alvaro.games.ahorcadoApp.R.drawable.ic_brazos, alvaro.games.ahorcadoApp.R.drawable.ic_pierna};
    private static int contador;
    private static int tamaño_palabra;
    private static int contador_aciertos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(alvaro.games.ahorcadoApp.R.layout.activity_tablero);

        contador = 0;
        contador_aciertos = 0;

        palabra = getIntent().getStringExtra("palabra_clave");
        palabraAux = palabra;

        palabraAux.replace(" ","");

        tamaño_palabra = obtenerTamañoPalabra(palabraAux);

        ImageView imageView = (ImageView) findViewById(alvaro.games.ahorcadoApp.R.id.imagenes_ahorcado);
        imageView.setImageResource(array_pics[contador]);

        View v = findViewById(alvaro.games.ahorcadoApp.R.id.textView2);
        TextView textView = (TextView) v;

        textView.setText(palabra);

    }

    public void escribirNumero(View boton){
        // declaramos variables y hacemos el casteo del boton para usarle
        String palabra = getPalabra();
        Log.d("MENSAJE",palabra);
        Button btnPulsado = (Button) boton;
        String pulsado=  btnPulsado.getText().toString();//cogemos el texto del boton pulsado
       // String palabra="ISABEL";//nos creamos un string que le pasaremos al metodo

        //nos creamos una variable boleana que nos dara si es falso o verdadero con lo que salga del metodo
        // haremos una condicion if en la que nos dira si la encuentra que cambie el texto del boton y lo ponga del color verde
        //sino que la ponga de color rojo y no deje pulsarla otra vez la deshabilita
        palabra = palabra.toUpperCase();
        boolean encontrada=buscarLetra(pulsado, palabra);
        if(encontrada)
        {
            letraAcertada(btnPulsado);
        }
        else
        {
            letraFallada(btnPulsado);
        }


    }

    /**
     * Se cambia a verde el boton introducido y si el contador_aciertos es igual al tamaño de la palabra oculta
     * se redirige a VictoriaActivity
     * @param button
     */
    public void letraAcertada(Button button){

        button.setTextColor(Color.rgb(34, 153, 84));

        Log.d("MENSAJE",contador_aciertos+" contador");
        Log.d("MENSAJE",tamaño_palabra+" tamaño");

        if(contador_aciertos == tamaño_palabra)
        {
            Intent intent = new Intent(TableroActivity.this, VictoriaActivity.class);

            intent.putExtra("palabra_clave",palabra);

            startActivity(intent);
        }
    }

    /**
     * Cambia el color a rojo e inutiliza el boton introducido, si el contador de fallos
     * es igual a 6 se redirige a DerrotaActivity, si no, cambia la imagen del ahoracado
     * @param button
     */
    public void letraFallada(Button button){
        button.setTextColor(Color.RED);
        button.setEnabled(false);
        contador++;

        if(contador == 6)
        {
            Intent intent = new Intent(TableroActivity.this, DerrotaActivity.class);

            intent.putExtra("palabra_clave",palabra);

            startActivity(intent);
        }
        else
        {
            ImageView imageView = (ImageView) findViewById(alvaro.games.ahorcadoApp.R.id.imagenes_ahorcado);
            imageView.setImageResource(array_pics[contador]);
        }
    }

    /**
     * Busca en una palabra una letra introducidas, cada vez que encuentre esa letra en la
     * palabra se añade mas uno al contador_palabra_verdadero y devuelve un true
     * @param letra
     * @param palabra
     * @return
     */
    public boolean  buscarLetra(String letra, String palabra){
        boolean encontrado = false;
        char letrita= letra.charAt(0);
        for(int i=0; i<palabra.length(); i++){
            if(letrita == palabra.charAt(i))
            {
                encontrado=true;
                contador_aciertos++;
            }
        }

        return encontrado;
    }

    public String getPalabra(){
        return palabra;
    }

    /**
     * Dada una palabra introducida te dice su numero de letras, no cuenta los espacios
     * @param palabra
     * @return numero de posiciones de esa palabra
     */
    public int obtenerTamañoPalabra(String palabra){
        int contador = 0;

        palabra = palabra.replace(" ","");

        for(int i = 0;i<palabra.length();i++)
        {
            contador++;
        }

        return contador;
    }
}



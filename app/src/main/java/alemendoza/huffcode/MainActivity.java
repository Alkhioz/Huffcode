package alemendoza.huffcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txtCadena;
    private Button btnCodificar;
    metodos funciones = new metodos();
    ARBOL_HUFFMAN tree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCadena = (EditText) findViewById(R.id.caja);
        btnCodificar = (Button) findViewById(R.id.button);

        btnCodificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GridView grid = (GridView) findViewById(R.id.tabla);
                String cadena = txtCadena.getText().toString();
                ArrayAdapter<String> arrayadapter;

                if (!cadena.equals("")) {
                    //grid.setAdapter(null);
                    int[] charFreqs = new int[256];
                    // read each character and record the frequencies
                    for (char c : cadena.toCharArray())
                        charFreqs[c]++;

                    // build tree
                    tree = funciones.buildTree(charFreqs);
                    funciones.IMP_CODIGOS(tree, new StringBuffer());

                    int tamaño=funciones.simbolos.size();

                    final ArrayList<String> items;

                    if(tamaño>1){
                        funciones.ordenar();
                        int tamaño2=0;

                        DecimalFormat decimales = new DecimalFormat("0.00");

                        for(int j=0;j<tamaño;j++)
                        {
                            tamaño2+=Double.parseDouble(funciones.frecuencia.get(j));
                        }
                        items = new ArrayList<String>();
                        items.add("Sim");
                        items.add("Rep");
                        items.add("Fre");
                        items.add("Cod");
                        for(int j=0;j<tamaño;j++){
                            items.add(funciones.simbolos.get(j));
                            items.add(funciones.frecuencia.get(j));
                            items.add( String.valueOf(decimales.format((Double.parseDouble(funciones.frecuencia.get(j))/tamaño2))));
                            items.add(funciones.codigo.get(j));
                        }


                        arrayadapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, items);
                        grid.setAdapter(arrayadapter);

                        funciones.simbolos.clear();
                        funciones.frecuencia.clear();
                        funciones.codigo.clear();
                    }
                    else{
                        items = new ArrayList<String>();
                        items.add("Sim");
                        items.add("Rep");
                        items.add("Fre");
                        items.add("Cod");
                        items.add(funciones.simbolos.get(0));
                        items.add("1");
                        items.add("1");
                        items.add("0");

                        arrayadapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, items);
                        grid.setAdapter(arrayadapter);

                        funciones.simbolos.clear();
                        funciones.frecuencia.clear();
                        funciones.codigo.clear();
                    }
                }

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
package com.example.ciclodevida2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {
    String parcial;
    EditText tnombre, tedad;
    Button btnagregar,btnmostrar;
    FloatingActionButton bfinfo;
    GridView idlayout;
    DatoModel datoModel;
    List<Datos> lista = new ArrayList<Datos>();
    ArrayList<String> listaGrid = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tnombre = findViewById(R.id.txtnombre);
        tedad = findViewById(R.id.txtedad);
        btnagregar = findViewById(R.id.btnagregar);
        btnmostrar = findViewById(R.id.btnmostrar);
        bfinfo = findViewById(R.id.bfinfo);
        idlayout = (GridView) findViewById(R.id.idlayout);


        datoModel = new ViewModelProvider(this).get(DatoModel.class);
        adaptador();

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datos datos = new Datos(tnombre.getText().toString(),Integer.valueOf(tedad.getText().toString()));
                datoModel.agregar(datos);
                Toast.makeText(PrincipalActivity.this,"Se agrego el dato", Toast.LENGTH_SHORT).show();

            }
        });



        btnmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista = datoModel.getDatos();
                listaGrid.clear();

                for(Datos datos : lista){
                    listaGrid.add(datos.getNombre());
                    listaGrid.add(String.valueOf(datos.getEdad()));
                }

                datoModel.setListaActual(listaGrid);
                adaptador();
            }
        });

        bfinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //snackbar
                Snackbar.make(view, "Barra de mensaje", Snackbar.LENGTH_LONG);
                Toast.makeText(PrincipalActivity.this, "Mensaje", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void adaptador(){
        ArrayAdapter<String> adapter;
        adapter =  new ArrayAdapter<String>(PrincipalActivity.this, android.R.layout.simple_list_item_1, datoModel.getListaActual());
        idlayout.setAdapter(adapter);

    }
}
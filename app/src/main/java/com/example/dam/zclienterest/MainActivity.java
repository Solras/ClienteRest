package com.example.dam.zclienterest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dam.zclienterest.interfaces.ApiActividades;
import com.example.dam.zclienterest.pojo.Actividad;
import com.example.dam.zclienterest.util.AdaptadorActividades;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MIAPP";
    private final int NUEVA_ACTIVIDAD = 0;
    private final int EDITAR_ACTIVIDAD = 1;
    private List<Actividad> actividades;
    private android.widget.ListView listView;
    private Retrofit retrofit;
    private ApiActividades api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddActivity.class), NUEVA_ACTIVIDAD);
            }
        });
        this.listView = (ListView) findViewById(R.id.listView2);
        actividades = new ArrayList<>();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiActividades.class);
        listarActividades();
        this.registerForContextMenu(listView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case NUEVA_ACTIVIDAD: {
                    Actividad a = data.getParcelableExtra("actividad");
                    Log.v(TAG, "actres: " + a.toString());
                    nuevaActividad(a);
                    listarActividades();
                }
                case EDITAR_ACTIVIDAD:{
                    Actividad a = data.getParcelableExtra("actividad");
                    actualizarActividad(a);
                    listarActividades();
                }
                break;
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        Actividad a = actividades.get(position);
        switch (item.getItemId()) {
            case R.id.menu_editar: {
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                i.putExtra("actividad", a);
                startActivityForResult(i, EDITAR_ACTIVIDAD);
                Toast.makeText(this, a.getId() + a.getDescripcion(), Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.menu_borrar: {
                borrarActividad(a.getId());
                listarActividades();
                return true;
            }
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    public void listarActividades() {
        actividades = new ArrayList<>();
        Call<List<Actividad>> call = api.getActividades();
        call.enqueue(new Callback<List<Actividad>>() {
            @Override
            public void onResponse(Response<List<Actividad>> response, Retrofit retrofit) {
                for (Actividad a : response.body()) {
                    Log.v(TAG, "actcall: " + a.toString());
                    actividades.add(a);
                }
                Log.v(TAG, "actividades: " + actividades.toString());
                listView.setAdapter(new AdaptadorActividades(MainActivity.this, (ArrayList<Actividad>) actividades));
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    public void nuevaActividad(Actividad a) {
        Call<Actividad> call = api.setActividad(a);
        call.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                Log.v(TAG, "post: " + response.raw().toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void actualizarActividad(Actividad a) {
        Call<Actividad> call = api.updateActividad(a);
        call.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                Log.v(TAG, "put: " + response.raw().toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void borrarActividad(String id) {
        Call<Actividad> call = api.deleteActividad(id);
        call.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Response<Actividad> response, Retrofit retrofit) {
                Log.v(TAG, "put: " + response.raw().toString());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}

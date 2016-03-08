package com.example.dam.zclienterest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dam.zclienterest.interfaces.ApiActividades;
import com.example.dam.zclienterest.pojo.Actividad;
import com.example.dam.zclienterest.pojo.Profesor;
import com.example.dam.zclienterest.util.AdaptadorProfesores;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AddActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ApiActividades api;
    private TextView tvProfesor;
    private Spinner spinner;
    private RadioButton rbComplementaria;
    private RadioButton rbExtraescolar;
    private RadioGroup rgTipo;
    private EditText etLinicio;
    private TextView textView4;
    private TextView textView5;
    private NumberPicker npDiai;
    private TextView textView;
    private NumberPicker npMesi;
    private TextView textView2;
    private NumberPicker npAnioi;
    private NumberPicker npHorai;
    private TextView textView3;
    private NumberPicker npMinutosi;
    private RadioButton rbAMi;
    private RadioButton rbPMi;
    private RadioGroup rgHorarioi;
    private EditText etLfin;
    private TextView textView6;
    private TextView textView7;
    private NumberPicker npDiaf;
    private TextView textView8;
    private NumberPicker npMesf;
    private TextView textView9;
    private NumberPicker npAniof;
    private NumberPicker npHoraf;
    private TextView textView10;
    private NumberPicker npMinutosf;
    private RadioButton rbAMf;
    private RadioButton rbPMf;
    private RadioGroup rgHorariof;
    private EditText etDescripcion;
    private EditText etAlumno;
    private Button btCancelar;
    private Button btGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        this.btGuardar = (Button) findViewById(R.id.btGuardar);
        this.btCancelar = (Button) findViewById(R.id.btCancelar);
        this.etAlumno = (EditText) findViewById(R.id.etAlumno);
        this.etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        this.rgHorariof = (RadioGroup) findViewById(R.id.rgHorariof);
        this.rbPMf = (RadioButton) findViewById(R.id.rbPMf);
        this.rbAMf = (RadioButton) findViewById(R.id.rbAMf);
        this.npMinutosf = (NumberPicker) findViewById(R.id.npMinutosf);
        this.textView10 = (TextView) findViewById(R.id.textView10);
        this.npHoraf = (NumberPicker) findViewById(R.id.npHoraf);
        this.npAniof = (NumberPicker) findViewById(R.id.npAniof);
        this.textView9 = (TextView) findViewById(R.id.textView9);
        this.npMesf = (NumberPicker) findViewById(R.id.npMesf);
        this.textView8 = (TextView) findViewById(R.id.textView8);
        this.npDiaf = (NumberPicker) findViewById(R.id.npDiaf);
        this.textView7 = (TextView) findViewById(R.id.textView7);
        this.textView6 = (TextView) findViewById(R.id.textView6);
        this.etLfin = (EditText) findViewById(R.id.etLfin);
        this.rgHorarioi = (RadioGroup) findViewById(R.id.rgHorarioi);
        this.rbPMi = (RadioButton) findViewById(R.id.rbPMi);
        this.rbAMi = (RadioButton) findViewById(R.id.rbAMi);
        this.npMinutosi = (NumberPicker) findViewById(R.id.npMinutosi);
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.npHorai = (NumberPicker) findViewById(R.id.npHorai);
        this.npAnioi = (NumberPicker) findViewById(R.id.npAnioi);
        this.textView2 = (TextView) findViewById(R.id.textView2);
        this.npMesi = (NumberPicker) findViewById(R.id.npMesi);
        this.textView = (TextView) findViewById(R.id.textView);
        this.npDiai = (NumberPicker) findViewById(R.id.npDiai);
        this.textView5 = (TextView) findViewById(R.id.textView5);
        this.textView4 = (TextView) findViewById(R.id.textView4);
        this.etLinicio = (EditText) findViewById(R.id.etLinicio);
        this.rgTipo = (RadioGroup) findViewById(R.id.rgTipo);
        this.rbExtraescolar = (RadioButton) findViewById(R.id.rbExtraescolar);
        this.rbComplementaria = (RadioButton) findViewById(R.id.rbComplementaria);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        this.tvProfesor = (TextView) findViewById(R.id.tvProfesor);

        rellenarSpinnerProfesores();

        setNumberPicker(npDiai, 1, 31);
        setNumberPicker(npMesi, 1, 12);
        setNumberPicker(npAnioi, 2016, 2050);
        setNumberPicker(npHorai, 1, 12);
        setNumberPicker(npMinutosi, 0, 59);
        setNumberPicker(npDiaf, 1, 31);
        setNumberPicker(npMesf, 1, 12);
        setNumberPicker(npAniof, 2016, 2050);
        setNumberPicker(npHoraf, 1, 12);
        setNumberPicker(npMinutosf, 0, 59);

    }

    private void setNumberPicker(NumberPicker np, int min, int max) {
        np.setMinValue(min);
        np.setMaxValue(max);
    }

    private void rellenarSpinnerProfesores() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ieszv.x10.bz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiActividades.class);

        Call<List<Profesor>> call = api.getProfesores();
        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Response<List<Profesor>> response, Retrofit retrofit) {
                List<Profesor> adap = new ArrayList<>();
                for (Profesor p : response.body()) {
                    Log.v(MainActivity.TAG, "profesor: " + p.toString());
                    adap.add(p);
                }
                spinner.setAdapter(new AdaptadorProfesores(AddActivity.this, (ArrayList<Profesor>) adap));
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
    }

    public void guardar(View v) {
        Profesor p = (Profesor) spinner.getSelectedItem();
        String tipo = "";
        switch (rgTipo.getCheckedRadioButtonId()) {
            case R.id.rbComplementaria:
                tipo = "complementaria";
                break;
            case R.id.rbExtraescolar:
                tipo = "extraescolar";
                break;
        }
        String horai = "";
        horai += Integer.toString(npAnioi.getValue());
        horai += "-" + Integer.toString(npMesi.getValue());
        horai += "-" + Integer.toString(npDiai.getValue());
        horai += " " + Integer.toString(npHorai.getValue());
        horai += ":" + Integer.toString(npMinutosi.getValue());
        String horaf = "";
        horaf += Integer.toString(npAniof.getValue());
        horaf += "-" + Integer.toString(npMesf.getValue());
        horaf += "-" + Integer.toString(npDiaf.getValue());
        horaf += " " + Integer.toString(npHoraf.getValue());
        horaf += ":" + Integer.toString(npMinutosf.getValue());
        String lugarInicio = etLinicio.getText().toString();
        String lugarFin = etLfin.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String alumno = etAlumno.getText().toString();

        Actividad a = new Actividad("", p.getId(), tipo, horai, horaf, lugarInicio, lugarFin, descripcion, alumno);
        Intent i = this.getIntent();
        i.putExtra("actividad", a);
        setResult(RESULT_OK, i);
        finish();
    }

    public void cancelar(View v){
        finish();
    }

}

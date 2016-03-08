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

public class EditActivity extends AppCompatActivity {

    private Actividad a;
    private Retrofit retrofit;
    private ApiActividades api;
    private android.widget.Spinner spinner;
    private android.widget.RadioButton rbComplementaria;
    private android.widget.RadioButton rbExtraescolar;
    private android.widget.RadioGroup rgTipo;
    private android.widget.EditText etLinicio;
    private android.widget.NumberPicker npDiai;
    private android.widget.NumberPicker npMesi;
    private android.widget.NumberPicker npAnioi;
    private android.widget.NumberPicker npHorai;
    private android.widget.NumberPicker npMinutosi;
    private android.widget.RadioButton rbAMi;
    private android.widget.RadioButton rbPMi;
    private android.widget.RadioGroup rgHorarioi;
    private android.widget.EditText etLfin;
    private android.widget.NumberPicker npDiaf;
    private android.widget.NumberPicker npMesf;
    private android.widget.NumberPicker npAniof;
    private android.widget.NumberPicker npHoraf;
    private android.widget.NumberPicker npMinutosf;
    private android.widget.RadioButton rbAMf;
    private android.widget.RadioButton rbPMf;
    private android.widget.RadioGroup rgHorariof;
    private android.widget.EditText etDescripcion;
    private android.widget.EditText etAlumno;
    private android.widget.Button btCancelar;
    private android.widget.Button btGuardar;

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
        this.npHoraf = (NumberPicker) findViewById(R.id.npHoraf);
        this.npAniof = (NumberPicker) findViewById(R.id.npAniof);
        this.npMesf = (NumberPicker) findViewById(R.id.npMesf);
        this.npDiaf = (NumberPicker) findViewById(R.id.npDiaf);
        this.etLfin = (EditText) findViewById(R.id.etLfin);
        this.rgHorarioi = (RadioGroup) findViewById(R.id.rgHorarioi);
        this.rbPMi = (RadioButton) findViewById(R.id.rbPMi);
        this.rbAMi = (RadioButton) findViewById(R.id.rbAMi);
        this.npMinutosi = (NumberPicker) findViewById(R.id.npMinutosi);
        this.npHorai = (NumberPicker) findViewById(R.id.npHorai);
        this.npAnioi = (NumberPicker) findViewById(R.id.npAnioi);
        this.npMesi = (NumberPicker) findViewById(R.id.npMesi);
        this.npDiai = (NumberPicker) findViewById(R.id.npDiai);
        this.etLinicio = (EditText) findViewById(R.id.etLinicio);
        this.rgTipo = (RadioGroup) findViewById(R.id.rgTipo);
        this.rbExtraescolar = (RadioButton) findViewById(R.id.rbExtraescolar);
        this.rbComplementaria = (RadioButton) findViewById(R.id.rbComplementaria);
        this.spinner = (Spinner) findViewById(R.id.spinner);

        a = this.getIntent().getParcelableExtra("actividad");
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

                    adap.add(p);
                }
                spinner.setAdapter(new AdaptadorProfesores(EditActivity.this, (ArrayList<Profesor>) adap));
            }

            @Override
            public void onFailure(Throwable t) {
                t.getLocalizedMessage();
            }
        });
        spinner.setPrompt(a.getIdprofesor());
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

        a = new Actividad(a.getId(), p.getId(), tipo, horai, horaf, lugarInicio, lugarFin, descripcion, alumno);
        Intent i = this.getIntent();
        i.putExtra("actividad", a);
        setResult(RESULT_OK, i);
        finish();
    }

    public void cancelar(View v){
        finish();
    }
}

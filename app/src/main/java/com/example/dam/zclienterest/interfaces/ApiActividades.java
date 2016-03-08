package com.example.dam.zclienterest.interfaces;

import com.example.dam.zclienterest.pojo.Actividad;
import com.example.dam.zclienterest.pojo.Profesor;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface ApiActividades {
    @GET("restful/api/actividad/carlos")
    Call<List<Actividad>> getActividades();

    @GET("restful/api/profesor")
    Call<List<Profesor>> getProfesores();

    @POST("restful/api/actividad")
    Call<Actividad> setActividad(@Body Actividad act);

    @PUT("restful/api/actividad")
    Call<Actividad> updateActividad(@Body Actividad act);

    @DELETE("restful/api/actividad/{id}")
    Call<Actividad> deleteActividad(@Path("id") String id);
}

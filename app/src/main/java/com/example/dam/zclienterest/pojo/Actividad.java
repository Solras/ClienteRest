package com.example.dam.zclienterest.pojo;

import android.os.Parcel;
import android.os.Parcelable;


public class Actividad implements Parcelable{
    private String id;
    private String idprofesor;
    private String tipo;
    private String fechai;
    private String fechaf;
    private String lugari;
    private String lugarf;
    private String descripcion;
    private String alumno;

    public Actividad(String id, String idProfesor, String tipo, String fechai, String fechaf, String lugari, String lugarf, String descripcion, String alumno) {
        this.id = id;
        this.idprofesor = idProfesor;
        this.tipo = tipo;
        this.fechai = fechai;
        this.fechaf = fechaf;
        this.lugari = lugari;
        this.lugarf = lugarf;
        this.descripcion = descripcion;
        this.alumno = alumno;
    }

    protected Actividad(Parcel in) {
        id = in.readString();
        idprofesor = in.readString();
        tipo = in.readString();
        fechai = in.readString();
        fechaf = in.readString();
        lugari = in.readString();
        lugarf = in.readString();
        descripcion = in.readString();
        alumno = in.readString();
    }

    public static final Creator<Actividad> CREATOR = new Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(String idProfesor) {
        this.idprofesor = idProfesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public String getLugari() {
        return lugari;
    }

    public void setLugari(String lugari) {
        this.lugari = lugari;
    }

    public String getLugarf() {
        return lugarf;
    }

    public void setLugarf(String lugarf) {
        this.lugarf = lugarf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", idProfesor=" + idprofesor +
                ", tipo='" + tipo + '\'' +
                ", fechai='" + fechai + '\'' +
                ", fechaf='" + fechaf + '\'' +
                ", lugari='" + lugari + '\'' +
                ", lugarf='" + lugarf + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", alumno='" + alumno + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idprofesor);
        dest.writeString(tipo);
        dest.writeString(fechai);
        dest.writeString(fechaf);
        dest.writeString(lugari);
        dest.writeString(lugarf);
        dest.writeString(descripcion);
        dest.writeString(alumno);
    }

}

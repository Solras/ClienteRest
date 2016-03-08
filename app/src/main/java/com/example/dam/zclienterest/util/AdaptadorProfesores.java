package com.example.dam.zclienterest.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dam.zclienterest.R;
import com.example.dam.zclienterest.pojo.Profesor;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorProfesores extends ArrayAdapter<Profesor> {

    private Context ctx;
    private int res;
    private LayoutInflater lInflator;
    private ArrayList<Profesor> values;

    static class ViewHolder {
        TextView tv1;
    }

    public AdaptadorProfesores(Context context, ArrayList<Profesor> objects) {
        super(context, R.layout.elemento_lista_prof, R.id.tvNombreP, objects);
        this.ctx = context;
        this.res = R.layout.elemento_lista_prof;
        this.lInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = new ViewHolder();

        if (convertView == null) {
            convertView = lInflator.inflate(res, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tvNombreP);
            vh.tv1 = tv;
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tv1.setText(values.get(position).getNombre());


        return convertView;
    }

}

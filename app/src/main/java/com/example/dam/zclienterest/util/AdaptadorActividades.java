package com.example.dam.zclienterest.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dam.zclienterest.R;
import com.example.dam.zclienterest.pojo.Actividad;

import java.util.ArrayList;

public class AdaptadorActividades extends ArrayAdapter<Actividad> {

    private Context ctx;
    private int res;
    private LayoutInflater lInflator;
    private ArrayList<Actividad> values;

    static class ViewHolder {
        TextView tv1, tv2, tv3;
    }

    public AdaptadorActividades(Context context, ArrayList<Actividad> objects) {
        super(context, R.layout.elemento_lista, objects);
        this.ctx = context;
        this.res = R.layout.elemento_lista;
        this.lInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = new ViewHolder();

        if (convertView == null) {
            convertView = lInflator.inflate(res, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tvNombre);
            vh.tv1 = tv;
            tv = (TextView) convertView.findViewById(R.id.tvfi);
            vh.tv2 = tv;
            tv = (TextView) convertView.findViewById(R.id.tvff);
            vh.tv3 = tv;

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tv1.setText(values.get(position).getDescripcion());
        vh.tv2.setText(values.get(position).getFechai());
        vh.tv3.setText(values.get(position).getFechaf());


        return convertView;
    }

}

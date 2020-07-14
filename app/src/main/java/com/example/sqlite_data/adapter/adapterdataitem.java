package com.example.sqlite_data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sqlite_data.R;
import com.example.sqlite_data.dataadapter.itemjudul;

import java.util.ArrayList;

public class adapterdataitem extends ArrayAdapter<itemjudul> {
    private Context mcontext;
    private int mresource;

    public adapterdataitem(@NonNull Context context, int resource, @NonNull ArrayList<itemjudul> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater iflate = LayoutInflater.from(mcontext);
        convertView = iflate.inflate(mresource,parent,false);


        String judul = getItem(position).getJudul();
        String subjudul = getItem(position).getSubjudul();

        TextView tvjudul = convertView.findViewById(R.id.adapter_judul);
        TextView tvsubjudul = convertView.findViewById(R.id.adapter_subjudul);

        tvjudul.setText(judul);
        tvsubjudul.setText(subjudul);

        return convertView;

    }
}

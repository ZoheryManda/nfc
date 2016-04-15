package com.mbds.nfc.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mbds.nfc.R;
import com.mbds.nfc.model.Mouvement;

import java.text.SimpleDateFormat;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;

    private List<Mouvement> mvtItems;

    public CustomListAdapter(Activity activity, List<Mouvement> mvtItems) {
        this.activity = activity;
        this.mvtItems = mvtItems;
    }

    @Override
    public int getCount() {
        return mvtItems.size();
    }

    @Override
    public Object getItem(int location) {
        return mvtItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView dateemprunt = (TextView) convertView.findViewById(R.id.dateemprunt);
        TextView dateremise = (TextView) convertView.findViewById(R.id.dateremise);
        TextView materiel = (TextView) convertView.findViewById(R.id.materiel);

        Mouvement m = mvtItems.get(position);

        String pattern = "MM/dd/yyyy HH:mm";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        // date d'emprunt
        String dateempruntString = m.getDateemprunt() == null ? "-" : format.format(m.getDateemprunt());
        dateemprunt.setText("Date emprunt : " + dateempruntString);

        // date de remise
        String dateremiseString = m.getDateremise() == null ? "-" : format.format(m.getDateremise());
        dateremise.setText("Date remise : " + dateremiseString);


        // type de materiel emprunter
        String mat = m.getIdmateriel() == 1 ? "Laptop" : "Projecteur";
        materiel.setText(mat + " SN : " + m.getIdmateriel());

        return convertView;
    }

}
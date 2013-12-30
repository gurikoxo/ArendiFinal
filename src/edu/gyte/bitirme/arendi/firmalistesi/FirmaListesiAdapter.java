package edu.gyte.bitirme.arendi.firmalistesi;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;

public class FirmaListesiAdapter extends ArrayAdapter<Firma> {

	ArrayList<Firma> list = new ArrayList<Firma>();
	
	public FirmaListesiAdapter(Context context, int textViewResourceId,
			List<Firma> objects) {
		super(context, R.layout.firma_list_item, objects);
		// TODO Auto-generated constructor stub
		list=(ArrayList<Firma>) objects;
	}

    
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.firma_list_item, null);
		}

	
			TextView firmaAdi = (TextView) v.findViewById(R.id.firmaAdi);
			TextView firmaDurum = (TextView) v.findViewById(R.id.firmaDurum);
		
			firmaAdi.setText(list.get(position).getFirmaAdi());
			firmaDurum.setText(list.get(position).getFirmaDurum().toString());

		// the view must be returned to our activity
		return v;

	}
	
	
  }
package edu.gyte.bitirme.arendi.projeler;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;

public class ProjeListesiListAdapter extends ArrayAdapter<Proje> {

	ArrayList<Proje> list = new ArrayList<Proje>();
	
	public ProjeListesiListAdapter(Context context, int textViewResourceId,
			List<Proje> objects) {
		super(context, R.layout.proje_list_item, objects);
		// TODO Auto-generated constructor stub
		list=(ArrayList<Proje>) objects;
	}

    
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.proje_list_item, null);
		}

	
			TextView projeAdi = (TextView) v.findViewById(R.id.projeAdi);
		
			projeAdi.setText(list.get(position).getProjeadi());
			
			TextView sorumluPersonel = (TextView) v.findViewById(R.id.projeSorumluPersonel);
			
			sorumluPersonel.setText(list.get(position).getSorumluadi());

		return v;

	}
	
	
  
}

package edu.gyte.bitirme.arendi.projekategorilistesi;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.departmanlistesi.Departman;

public class ProjeKategoriListAdapter  extends ArrayAdapter<ProjeKategori> {

    	ArrayList<ProjeKategori> list = new ArrayList<ProjeKategori>();
    	
		public ProjeKategoriListAdapter(Context context, int textViewResourceId,
				List<ProjeKategori> objects) {
			super(context, R.layout.twin_list_item_std, objects);
			// TODO Auto-generated constructor stub
			list=(ArrayList<ProjeKategori>) objects;
		}

        
		public View getView(int position, View convertView, ViewGroup parent){

			// assign the view we are converting to a local variable
			View v = convertView;

			// first check to see if the view is null. if so, we have to inflate it.
			// to inflate it basically means to render, or show, the view.
			if (v == null) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.twin_list_item_std, null);
			}

		
				TextView firmaAdi = (TextView) v.findViewById(R.id.firmaAdi);
			
				firmaAdi.setText(list.get(position).getKategoriadi());

			// the view must be returned to our activity
			return v;

		}
		
		
      
}

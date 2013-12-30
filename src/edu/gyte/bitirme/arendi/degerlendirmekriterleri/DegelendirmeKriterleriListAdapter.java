package edu.gyte.bitirme.arendi.degerlendirmekriterleri;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;

public class DegelendirmeKriterleriListAdapter  extends ArrayAdapter<DegerlendirmeKriteri> {

    	ArrayList<DegerlendirmeKriteri> list = new ArrayList<DegerlendirmeKriteri>();
    	
		public DegelendirmeKriterleriListAdapter(Context context, int textViewResourceId,
				List<DegerlendirmeKriteri> objects) {
			super(context, R.layout.degerlendirme_kriteri_list_item, objects);
			// TODO Auto-generated constructor stub
			list=(ArrayList<DegerlendirmeKriteri>) objects;
		}

        
		public View getView(int position, View convertView, ViewGroup parent){

			// assign the view we are converting to a local variable
			View v = convertView;

			// first check to see if the view is null. if so, we have to inflate it.
			// to inflate it basically means to render, or show, the view.
			if (v == null) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.degerlendirme_kriteri_list_item, null);
			}

		
				TextView kriterAdi = (TextView) v.findViewById(R.id.kriterAdi);
				TextView kriterKatsay� = (TextView) v.findViewById(R.id.kriterPuan);
				
				kriterAdi.setText(list.get(position).getKriterAdi());
				kriterKatsay�.setText(String.valueOf(list.get(position).getKatsayi()));
				
			// the view must be returned to our activity
			return v;

		}
}

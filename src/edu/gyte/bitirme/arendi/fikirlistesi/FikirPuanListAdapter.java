package edu.gyte.bitirme.arendi.fikirlistesi;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.degerlendirmekriterleri.DegerlendirmeKriteri;

public class FikirPuanListAdapter extends ArrayAdapter<DegerlendirmeKriteri> {

	ArrayList<DegerlendirmeKriteri> list = new ArrayList<DegerlendirmeKriteri>();
	
	public FikirPuanListAdapter(Context context, int textViewResourceId,
			List<DegerlendirmeKriteri> objects) {
		super(context, R.layout.fikir_puan_list_item, objects);
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
			v = inflater.inflate(R.layout.fikir_puan_list_item, null);
		}

	
			TextView kriterAdi = (TextView) v.findViewById(R.id.puanKriterAdi);
			RatingBar ratingbar = (RatingBar) v.findViewById(R.id.ratingBar1);
			
			ratingbar.setFocusable(false);
			
			kriterAdi.setText(list.get(position).getKriterAdi());
			
		// the view must be returned to our activity
		return v;

	}
	
	
}

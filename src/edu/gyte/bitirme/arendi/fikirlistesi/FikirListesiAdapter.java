package edu.gyte.bitirme.arendi.fikirlistesi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;

public class FikirListesiAdapter extends ArrayAdapter<Fikir> {

	
	
	
    	ArrayList<Fikir> list = new ArrayList<Fikir>();
    	
		public FikirListesiAdapter(Context context, int textViewResourceId,
				List<Fikir> objects) {
			super(context, R.layout.fikir_listesi_list_item, objects);
			// TODO Auto-generated constructor stub
			list=(ArrayList<Fikir>) objects;
		}

        
		public View getView(int position, View convertView, ViewGroup parent){

			// assign the view we are converting to a local variable
			View v = convertView;

			// first check to see if the view is null. if so, we have to inflate it.
			// to inflate it basically means to render, or show, the view.
			if (v == null) {
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.fikir_listesi_list_item, null);
			}

		
				TextView fikirBaslik = (TextView) v.findViewById(R.id.fikirBaslik);
				TextView fikirSahibi = (TextView) v.findViewById(R.id.fikirSahibi);
				TextView fikirPuan = (TextView) v.findViewById(R.id.fikirPuan);
				
				fikirBaslik.setText(list.get(position).getBaslik());
				fikirSahibi.setText(list.get(position).getUsername());
				
				
				BigDecimal bd = new BigDecimal(list.get(position).getPuan());
			    bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			    
				fikirPuan.setText(String.valueOf(bd.doubleValue()));
				
				
				
			// the view must be returned to our activity
			return v;

		}
		
		
}

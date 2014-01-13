package edu.gyte.bitirme.arendi.personellistesi;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.login.User;

public class PersonelListesiAdapter extends ArrayAdapter<User> {

	ArrayList<User> list = new ArrayList<User>();
	
	public PersonelListesiAdapter(Context context, int textViewResourceId,
			List<User> objects) {
		super(context, R.layout.personel_listesi_custom_row_item, objects);
		// TODO Auto-generated constructor stub
		list=(ArrayList<User>) objects;
	}

    
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.personel_listesi_custom_row_item, null);
		}

	
			TextView personelAdi = (TextView) v.findViewById(R.id.personelName);
			TextView personelEmail = (TextView) v.findViewById(R.id.PersonelEMail);
			TextView personelFirma = (TextView) v.findViewById(R.id.PersonelFirma);
			CheckBox isManager = (CheckBox) v.findViewById(R.id.isManager);
			
			personelAdi.setText(list.get(position).getName());
			personelEmail.setText(list.get(position).getEmail());
			personelFirma.setText(list.get(position).getFirmaAdi());
			
			if(list.get(position).getJuri()==1){
				isManager.setChecked(true);
			}
			else{
				isManager.setChecked(false);
			}
			
		// the view must be returned to our activity
		return v;

	}
	
	
  }
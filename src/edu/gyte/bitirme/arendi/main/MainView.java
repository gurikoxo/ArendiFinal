package edu.gyte.bitirme.arendi.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import edu.gyte.bitirme.arendi.R;

public class MainView extends Fragment{

	Button fikirEkleBtn;
	Button fikirlerimBtn;
	Button fikirListesiBtn;
	Button personelListesiBtn;
	Button departmanListesiBtn;
	Button kategoriListesiBtn;
	
	
	 public static Fragment newInstance(Context context) {
		 MainView f = new MainView();
	 
	        return f;
	    }
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.main_view, null);
	        
	        fikirEkleBtn = (Button) root.findViewById(R.id.mainFikirEkleBtn);
	        fikirlerimBtn = (Button) root.findViewById(R.id.mainFikirlerimBtn);
	        fikirListesiBtn = (Button) root.findViewById(R.id.mainFikirListesiBtn);
	        personelListesiBtn = (Button) root.findViewById(R.id.mainPersonelListesiBtn);
	        departmanListesiBtn = (Button) root.findViewById(R.id.mainDepartmanListesiBtn);
	        kategoriListesiBtn = (Button) root.findViewById(R.id.mainKategoriListesi);
	        
	        
	        fikirEkleBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
					tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.fikirekle.FikirEkleView"));
					tx.addToBackStack(root.toString());
					tx.commit();
				}
			});
	        
	        fikirlerimBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
					tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.fikirlistesi.UserFikirListesiView"));
					tx.addToBackStack(root.toString());
					tx.commit();
					
				}
			});
	        
	        fikirListesiBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
					tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.fikirlistesi.FikirListesiView"));
					tx.addToBackStack(root.toString());
					tx.commit();
					
				}
			});
	        
	        personelListesiBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
					tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.personellistesi.PersonelListesiView"));
					tx.addToBackStack(root.toString());
					tx.commit();
					
				}
			});
	        
	        departmanListesiBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
					tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.departmanlistesi.DepartmanListesiView"));
					tx.addToBackStack(root.toString());
					tx.commit();
					
				}
			});
	        
	        kategoriListesiBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
					tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.projekategorilistesi.ProjeKategoriListesiView"));
					tx.addToBackStack(root.toString());
					tx.commit();
					
				}
			});
	        
	        return root;
	    }
}

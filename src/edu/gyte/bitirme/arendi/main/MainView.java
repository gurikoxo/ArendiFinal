package edu.gyte.bitirme.arendi.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.gyte.bitirme.arendi.R;

public class MainView extends Fragment{

	 public static Fragment newInstance(Context context) {
		 MainView f = new MainView();
	 
	        return f;
	    }
	 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
	        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.main_view, null);
	        
	      
	        
	        return root;
	    }
}

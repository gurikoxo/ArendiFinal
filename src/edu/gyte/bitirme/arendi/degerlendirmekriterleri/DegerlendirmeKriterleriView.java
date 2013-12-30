package edu.gyte.bitirme.arendi.degerlendirmekriterleri;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.services.Service;

public class DegerlendirmeKriterleriView extends Fragment {
	
	final String GET_KRITER_LIST_WS = Service.serverAddres + "get_kriter_list.php";
	Gson gson = new Gson();
	
	public static Fragment newInstance(Context context) {
		DegerlendirmeKriterleriView f = new DegerlendirmeKriterleriView();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        
    	ViewGroup root = (ViewGroup) inflater.inflate(R.layout.degerlendirme_kriterleri_view, null);
        
    	ArrayList<DegerlendirmeKriteri> list = new ArrayList<DegerlendirmeKriteri>();
    	
    	ListView degerlendirmeKriterListView = (ListView) root.findViewById(R.id.degerlendirmeKriterList);
        
        User user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid", String.valueOf(user
				.getFirmaId())));

		String result = Service.makeSimpleHttpGet(GET_KRITER_LIST_WS, params);

		if (result == null) {
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		} else {

			DegerlendirmeKriterJson kriter = new DegerlendirmeKriterJson();
			kriter = gson.fromJson(result, DegerlendirmeKriterJson.class);

			if (kriter.getSuccess() == 1) {
				list = kriter.getKriterlist();
				DegelendirmeKriterleriListAdapter adapter = new DegelendirmeKriterleriListAdapter(root.getContext(), R.layout.degerlendirme_kriteri_list_item, list);
		    	
		        degerlendirmeKriterListView.setAdapter(adapter);

			}else{
				 Crouton.makeText(getActivity(), "Personel Listesi Yüklenemedi", Style.ALERT).show();
			}

		}
    	
        return root;
    }
    
    
    
    
    
    
}

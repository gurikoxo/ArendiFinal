package edu.gyte.bitirme.arendi.projeler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.gson.Gson;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.departmanlistesi.Departman;
import edu.gyte.bitirme.arendi.departmanlistesi.DepartmanJson;
import edu.gyte.bitirme.arendi.departmanlistesi.DepartmanListAdapter;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.services.Service;

public class ProjeListesiView extends Fragment {
	
	final String GET_PROJE_LIST_WS = Service.serverAddres + "get_proje_list.php";
	Gson gson = new Gson();
	
	
	public static Fragment newInstance(Context context) {
		ProjeListesiView f = new ProjeListesiView();
 
        return f;
    }
 
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.proje_listesi_view, null);
        
        ListView listView = (ListView) root.findViewById(R.id.projeListesi);
        
        ArrayList<Proje> list = new ArrayList<Proje>();
        
        User user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid", String.valueOf(user
				.getFirmaId())));

		String result = Service.makeSimpleHttpGet(GET_PROJE_LIST_WS, params);
        
		if (result == null) {
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		} else {

			ProjeJson proje = new ProjeJson();
			proje = gson.fromJson(result, ProjeJson.class);

			if (proje.getSuccess() == 1) {
				list = proje.getProjelist();
				final ProjeListesiListAdapter adapter = new ProjeListesiListAdapter(root.getContext(), R.layout.proje_list_item, list);
		        listView.setAdapter(adapter);

			}else{
				 Crouton.makeText(getActivity(), "Proje Listesi Yüklenemedi", Style.ALERT).show();
			}

		}
		
		final ArrayList<Proje> listP = list;
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				
				Bundle args = new Bundle();
				args.putSerializable("proje", (Serializable) listP.get(position));
				
				FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
				tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.projeler.ProjeDetayView",args));
				tx.addToBackStack(root.toString());
				tx.commit();
				
			}
		
		
		});
        
        return root;
    }
    
}
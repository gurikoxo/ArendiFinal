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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.fikirlistesi.Fikir;
import edu.gyte.bitirme.arendi.fikirlistesi.FikirJson;
import edu.gyte.bitirme.arendi.fikirlistesi.FikirListesiAdapter;
import edu.gyte.bitirme.arendi.fikirlistesi.FikirListesiView;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.services.Service;

public class ProjeEkleView extends Fragment {
	final static String GET_FIKIR_LIST_WS = Service.serverAddres+ "getallfikir.php";
	Gson gson = new Gson();
	public static Fragment newInstance(Context context) {
		FikirListesiView f = new FikirListesiView();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.fikir_listesi_view, null);
		
		User user = (User) getActivity().getIntent().getExtras().getSerializable("user");
		ArrayList<Fikir> fikirlist = new ArrayList<Fikir>();
		final ListView listView = (ListView) root.findViewById(R.id.fikirListesi);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid",String.valueOf(user.getFirmaId())));
		
		String result = Service.makeSimpleHttpGet(GET_FIKIR_LIST_WS, params);

		if (result == null){
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT).show();
		}else{
			
			FikirJson fikirJson = new FikirJson();
			fikirJson = gson.fromJson(result, FikirJson.class);
			
			if(fikirJson.getSuccess()==1){
				fikirlist = fikirJson.getFikirlist();
				listView.setAdapter(new FikirListesiAdapter(root.getContext(),R.layout.fikir_listesi_list_item,fikirlist));
		}
		
		}
		final ArrayList<Fikir> list = fikirlist;
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				
				Bundle args = new Bundle();
				args.putSerializable("fikir", (Serializable) list.get(position));
				args.putBoolean("pro", true);
				
				FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
				tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.fikirlistesi.FikirDetayView",args));
				tx.addToBackStack(root.toString());
				tx.commit();
				
			}
		
		});
		
		return root;

	}
}
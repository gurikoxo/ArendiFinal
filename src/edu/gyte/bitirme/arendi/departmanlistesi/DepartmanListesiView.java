package edu.gyte.bitirme.arendi.departmanlistesi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.firmalistesi.Firma;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.personellistesi.PersonelJson;
import edu.gyte.bitirme.arendi.personellistesi.PersonelListesiAdapter;
import edu.gyte.bitirme.arendi.services.FirmaService;
import edu.gyte.bitirme.arendi.services.Service;

public class DepartmanListesiView extends Fragment {
	
	final String GET_DEPARTMAN_LIST_WS = Service.serverAddres + "get_departman_list.php";
	Gson gson = new Gson();
	
	
	public static Fragment newInstance(Context context) {
		DepartmanListesiView f = new DepartmanListesiView();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.departman_listesi, null);
        
        
        ListView listview = (ListView) root.findViewById(R.id.departmanListView);
        
        ArrayList<Departman> list = new ArrayList<Departman>();
        
        User user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid", String.valueOf(user
				.getFirmaId())));

		String result = Service.makeSimpleHttpGet(GET_DEPARTMAN_LIST_WS, params);

		if (result == null) {
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		} else {

			DepartmanJson departman = new DepartmanJson();
			departman = gson.fromJson(result, DepartmanJson.class);

			if (departman.getSuccess() == 1) {
				list = departman.getDepartmanlist();
				final DepartmanListAdapter adapter = new DepartmanListAdapter(root.getContext(), R.layout.firma_list_item, list);
		        listview.setAdapter(adapter);

			}else{
				 Crouton.makeText(getActivity(), "Departman Listesi Y�klenemedi", Style.ALERT).show();
			}

		}
     
        
        
        
        return root;
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.departmanListView) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
            Departman obj = (Departman) lv.getItemAtPosition(acmi.position);

//            menu.setHeaderTitle(obj.getFirmaAdı() +" / " + obj.getDurum());
            
            menu.add("Düzenle");
            menu.add("Sil");
        }
    }
    
    
    
}
package edu.gyte.bitirme.arendi.projekategorilistesi;

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
import android.widget.TextView;
import android.widget.Toast;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.personellistesi.PersonelJson;
import edu.gyte.bitirme.arendi.personellistesi.PersonelListesiAdapter;
import edu.gyte.bitirme.arendi.services.Service;

public class ProjeKategoriListesiView extends Fragment{

	final String GET_USER_LIST_WS = Service.serverAddres+ "get_kategori_list.php";
	Gson gson = new Gson();
	
	public static Fragment newInstance(Context context) {
		ProjeKategoriListesiView f = new ProjeKategoriListesiView();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.proje_kategori_listesi, null);
        
        ListView listview = (ListView) root.findViewById(R.id.projeKategoriListView);
        
        ArrayList<ProjeKategori> list = new ArrayList<ProjeKategori>();
     
        TextView firmaAdi = (TextView) root.findViewById(R.id.kategoriFirmaAdi);
        
        
        User user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");

        firmaAdi.setText(user.getFirmaAdi());
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid", String.valueOf(user
				.getFirmaId())));

		String result = Service.makeSimpleHttpGet(GET_USER_LIST_WS, params);

		if (result == null) {
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		} else {

			ProjeKategoriJson kategori = new ProjeKategoriJson();
			kategori = gson.fromJson(result, ProjeKategoriJson.class);

			if (kategori.getSuccess() == 1) {
				list = kategori.getKategorilist();
				final ProjeKategoriListAdapter adapter = new ProjeKategoriListAdapter(root.getContext(), R.layout.firma_list_item, list);
		        listview.setAdapter(adapter);

			}else{
				 Crouton.makeText(getActivity(), "Personel Listesi Yüklenemedi", Style.ALERT).show();
			}

		}
        
        return root;
    }
    
}

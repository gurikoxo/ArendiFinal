package edu.gyte.bitirme.arendi.fikirlistesi;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.degerlendirmekriterleri.DegerlendirmeKriterJson;
import edu.gyte.bitirme.arendi.degerlendirmekriterleri.DegerlendirmeKriteri;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.services.Service;

public class FikirPuanView extends Fragment {

	
	final String GET_KRITER_LIST_WS = Service.serverAddres + "get_kriter_list.php";
	Gson gson = new Gson();
	
	Button puanVerButton ;
	
	public static Fragment newInstance(Context context) {
		FikirPuanView f = new FikirPuanView();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.fikir_puan_view, null);

		Fikir fikir = (Fikir) getArguments().getSerializable("fikir");
		
		ArrayList<DegerlendirmeKriteri> list = new ArrayList<DegerlendirmeKriteri>();
		
		TextView fikirAdi = (TextView) root.findViewById(R.id.puanFikirAdi);
		
		puanVerButton = (Button) root.findViewById(R.id.puanVerButton);

		fikirAdi.setText(fikir.getBaslik());
		

		final ListView puanKriterList = (ListView) root.findViewById(R.id.puanKriterList);
		
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
				FikirPuanListAdapter adapter = new FikirPuanListAdapter(root.getContext(), R.layout.fikir_listesi_list_item, list);
		    	
				puanKriterList.setAdapter(adapter);

			}else{
				 Crouton.makeText(getActivity(), "Personel Listesi Yüklenemedi", Style.ALERT).show();
			}

		}
		
		puanVerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				CheckBox cb;
				RatingBar rating;
				TextView kriterKatsayi;
				for (int x = 0; x<puanKriterList.getCount();x++){
			        cb = (CheckBox)puanKriterList.getChildAt(x).findViewById(R.id.puanKriterAdi);
			        if(cb.isChecked()){
			            rating = (RatingBar) puanKriterList.getChildAt(x).findViewById(R.id.ratingBar1);
			            kriterKatsayi = (TextView) puanKriterList.getChildAt(x).findViewById(R.id.kriterpuanhint);
			            Log.d("puan List", cb.getText() + " " + kriterKatsayi.getText() + " " + rating.getRating());
			        }
			    }
			}
		});
		
		return root;

	}

}

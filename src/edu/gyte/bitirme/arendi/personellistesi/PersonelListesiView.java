package edu.gyte.bitirme.arendi.personellistesi;

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
import edu.gyte.bitirme.arendi.fikirlistesi.FikirJson;
import edu.gyte.bitirme.arendi.fikirlistesi.FikirListesiAdapter;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.login.UserJson;
import edu.gyte.bitirme.arendi.services.Service;

public class PersonelListesiView extends Fragment {

	final String GET_USER_LIST_WS = Service.serverAddres+ "get_user_list.php";
	Gson gson = new Gson();

	public static Fragment newInstance(Context context) {
		PersonelListesiView f = new PersonelListesiView();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.personel_listesi, null);

		ListView personelList = (ListView) root
				.findViewById(R.id.personelListesi);

		User user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid", String.valueOf(user
				.getFirmaId())));
		ArrayList<User> list = new ArrayList<User>();

		String result = Service.makeSimpleHttpGet(GET_USER_LIST_WS, params);

		if (result == null) {
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		} else {

			PersonelJson personel = new PersonelJson();
			personel = gson.fromJson(result, PersonelJson.class);

			if (personel.getSuccess() == 1) {
				list = personel.getUserlist();
				final PersonelListesiAdapter adapter = new PersonelListesiAdapter(
						root.getContext(),
						R.layout.personel_listesi_custom_row_item, list);
				personelList.setAdapter(adapter);

			}else{
				 Crouton.makeText(getActivity(), "Personel Listesi Yüklenemedi", Style.ALERT).show();
			}

		}
		return root;

	}

}

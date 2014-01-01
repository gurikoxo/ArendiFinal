package edu.gyte.bitirme.arendi.fikirlistesi;

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
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.services.Service;

public class UserFikirListesiView extends Fragment {
	final static String GET_USER_FIKIR_LIST_WS = Service.serverAddres
			+ "get_fikir_by_user.php";
	Gson gson = new Gson();

	public static Fragment newInstance(Context context) {
		UserFikirListesiView f = new UserFikirListesiView();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.fikir_listesi_by_user, null);

		User user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");
		ArrayList<Fikir> fikirlist = new ArrayList<Fikir>();
		final ListView listView = (ListView) root
				.findViewById(R.id.fikirlerimlist);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid", String.valueOf(user
				.getFirmaId())));
		params.add(new BasicNameValuePair("userid",
				String.valueOf(user.getId())));

		String result = Service.makeSimpleHttpGet(GET_USER_FIKIR_LIST_WS,
				params);

		if (result == null) {
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		} else {

			FikirJson fikirJson = new FikirJson();
			fikirJson = gson.fromJson(result, FikirJson.class);

			if (fikirJson.getSuccess() == 1) {
				fikirlist = fikirJson.getFikirlist();
				listView.setAdapter(new UserFikirListesiListAdapter(root
						.getContext(), R.layout.fikir_listesi_user_item,
						fikirlist));
			}

		}

		return root;

	}
}
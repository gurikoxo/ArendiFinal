package edu.gyte.bitirme.arendi.fikirlistesi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.personellistesi.PersonelJson;
import edu.gyte.bitirme.arendi.personellistesi.PersonelListesiAdapter;
import edu.gyte.bitirme.arendi.projeler.Proje;
import edu.gyte.bitirme.arendi.services.Service;


public class FikirDetayView extends Fragment {
	
		private static String IMAGE_URL= Service.serverAddres+ "images/";
		final String GET_USER_LIST_WS = Service.serverAddres+ "get_user_list.php";
		final String PROJE_EKLE_WS = Service.serverAddres+ "proje_ekle.php";
		Gson gson = new Gson();
	
	public static Fragment newInstance(Context context ) {
		FikirDetayView f = new FikirDetayView();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.fikir_detay_view, null);
		
		final Fikir fikir = (Fikir) getArguments().getSerializable("fikir");
		
		boolean check = getArguments().getBoolean("pro");
		
		Button projeYapBtn = (Button) root.findViewById(R.id.projeYapButton);
		if(!check)
			projeYapBtn.setVisibility(View.GONE);
		
		TextView header = (TextView) root.findViewById(R.id.fikirDetayHeader);
		
		header.setText(fikir.getBaslik());
		
		TextView fikirAciklama = (TextView) root.findViewById(R.id.fikirDetayAciklama);
		
		fikirAciklama.setText(fikir.getAciklama());
		
		TextView fikirSahibi = (TextView) root.findViewById(R.id.fikirSahibi);
		
		fikirSahibi.setText(fikir.getUsername());
		
		ImageView foto = (ImageView) root.findViewById(R.id.fikirFoto);
		
		UrlImageViewHelper.setUrlDrawable(foto, fikir.getFoto());
		
		Button puanButton = (Button) root.findViewById(R.id.puanVerBtn);
		
		puanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle args = new Bundle();
				args.putSerializable("fikir", (Serializable) fikir);
				
				FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
				tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.fikirlistesi.FikirPuanView",args));
				tx.addToBackStack(root.toString());
				tx.commit();
				
			}
		});
		
		
		User user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firmaid", String.valueOf(user
				.getFirmaId())));
		ArrayList<User> personelList = new ArrayList<User>();

		String result = Service.makeSimpleHttpGet(GET_USER_LIST_WS, params);

		if (result == null) {
			Toast.makeText(root.getContext(), "Error", Toast.LENGTH_SHORT)
					.show();
		} else {

			PersonelJson personel = new PersonelJson();
			personel = gson.fromJson(result, PersonelJson.class);

			if (personel.getSuccess() == 1) {
				personelList = personel.getUserlist();

			}else{
				 Crouton.makeText(getActivity(), "Personel Listesi Yüklenemedi", Style.ALERT).show();
			}

		
		}
		
		final ArrayList<User> personel = personelList;
		ArrayList<String> personelNameList = new ArrayList<String>();
		for (User user2 : personel) {
			personelNameList.add(user2.getName());
		}
		final ArrayList<String> personelNames = personelNameList;
		
		projeYapBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// get prompts.xml view
				LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

				View promptView = layoutInflater.inflate(R.layout.proje_ekle_dialog_view, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
				
				// set prompts.xml to be the layout file of the alertdialog builder
				alertDialogBuilder.setView(promptView);
				alertDialogBuilder.setTitle("Proje Ekle");

				final EditText input = (EditText) promptView.findViewById(R.id.yeniProjeAdi);
				final Spinner personelSpinner = (Spinner) promptView.findViewById(R.id.projeSorumluSpinner);
				
				personelSpinner.setAdapter(new ArrayAdapter<String>(promptView
						.getContext(),
						android.R.layout.simple_spinner_dropdown_item, personelNames));
				
				// setup a dialog window
				alertDialogBuilder
						.setCancelable(false)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										
										
									
										List<NameValuePair> params = new ArrayList<NameValuePair>();

										params.add(new BasicNameValuePair("fikirid", String.valueOf(fikir.getId())));
										params.add(new BasicNameValuePair(
												"sorumluid",
												String.valueOf(personel
														.get(personelSpinner
																.getSelectedItemPosition())
														.getId())));
										params.add(new BasicNameValuePair("projeadi", input.getText().toString()));
										
										String result = Service.makeSimpleHttpGet(PROJE_EKLE_WS, params);
									
										Log.d("DEBUD",result );
										
										Crouton.makeText(getActivity(), "Proje Baþarýyla EKlendi", Style.CONFIRM).show();
										
										FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
										tx.replace(R.id.main,Fragment.instantiate(getActivity(), "edu.gyte.bitirme.arendi.main.MainView"));
										
										tx.commit();
										
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,	int id) {
										dialog.cancel();
									}
								});

				// create an alert dialog
				AlertDialog alertD = alertDialogBuilder.create();

				alertD.show();
			}
		});
		
		return root;

		}
}
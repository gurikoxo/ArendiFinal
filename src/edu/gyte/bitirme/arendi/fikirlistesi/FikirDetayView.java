package edu.gyte.bitirme.arendi.fikirlistesi;

import java.io.Serializable;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.services.Service;


public class FikirDetayView extends Fragment {
	
		private static String IMAGE_URL= Service.serverAddres+ "images/";
	
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
		
		Toast.makeText(root.getContext(), fikir.getImage(), Toast.LENGTH_LONG).show();;
		
		
		TextView header = (TextView) root.findViewById(R.id.fikirDetayHeader);
		
		header.setText(fikir.getBaslik());
		
		TextView fikirAciklama = (TextView) root.findViewById(R.id.fikirDetayAciklama);
		
		fikirAciklama.setText(fikir.getAciklama());
		
		TextView fikirSahibi = (TextView) root.findViewById(R.id.fikirSahibi);
		
		fikirSahibi.setText(fikir.getUsername());
		
		ImageView foto = (ImageView) root.findViewById(R.id.fikirFoto);
		
		UrlImageViewHelper.setUrlDrawable(foto, fikir.getImage());
		
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
		
		return root;

	}
	
	private String imageUrlCreator(String url){
		StringBuilder build = new StringBuilder(IMAGE_URL);
		
		build.append(url);
		build.append(".JPG");
		
		return build.toString();
		
	}
}
package edu.gyte.bitirme.arendi.projeler;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.services.Service;

public class ProjeDetayView extends Fragment {

	private static String IMAGE_URL = Service.serverAddres + "images/";

	public static Fragment newInstance(Context context) {
		ProjeDetayView f = new ProjeDetayView();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.proje_detay_view, null);

		final Proje proje = (Proje) getArguments().getSerializable("proje");

		TextView header = (TextView) root.findViewById(R.id.projeDetayHeader);

		header.setText(proje.getProjeadi());

		TextView fikirBaslik = (TextView) root
				.findViewById(R.id.projefikirbaslik);

		fikirBaslik.setText(proje.getFikirbaslik());

		TextView projefikiraciklama = (TextView) root.findViewById(R.id.projefikiraciklama);
		
		projefikiraciklama.setText(proje.getFikiraciklama());
		
		TextView projeSorumluPersonel = (TextView) root.findViewById(R.id.projeSorumluPersonel);
		
		projeSorumluPersonel.setText(proje.getSorumluadi());
		
		ImageView foto = (ImageView) root.findViewById(R.id.projefikirimage);
		
		UrlImageViewHelper.setUrlDrawable(foto, proje.getFoto());
		
		return root;

	}
}
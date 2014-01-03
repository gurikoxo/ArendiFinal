package edu.gyte.bitirme.arendi.fikirekle;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.google.gson.Gson;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.fikirlistesi.Fikir;
import edu.gyte.bitirme.arendi.login.User;
import edu.gyte.bitirme.arendi.services.Service;

public class FikirEkleView extends Fragment {

	final String FIKIR_EKLE_WS = Service.serverAddres + "fikir_ekle.php";
	final String UPLOAD = Service.serverAddres + "upload.php";
	Gson gson = new Gson();
	EditText fikirBaslik;
	EditText fikirAciklama;
	Bitmap fikirImage;
	Spinner fikirGizlilikSpinner;
	User user;
	

	private static final int CAMERA_REQUEST = 1888;
	private ImageView imageView = null;
	Uri imageUri;

	public static Fragment newInstance(Context context) {
		FikirEkleView f = new FikirEkleView();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.fikir_ekle_view, null);

		user = (User) getActivity().getIntent().getExtras()
				.getSerializable("user");

		fikirGizlilikSpinner = (Spinner) root
				.findViewById(R.id.fikirGizlilikSpinner);

		fikirGizlilikSpinner.setAdapter(new ArrayAdapter<FikirGizlilik>(root
				.getContext(), android.R.layout.simple_spinner_dropdown_item,
				FikirGizlilik.values()));

		fikirBaslik = (EditText) root.findViewById(R.id.fikirBaslikField);
		fikirAciklama = (EditText) root.findViewById(R.id.fikirAciklamaField);

		Button fikirEkleButton = (Button) root.findViewById(R.id.fikirEkleBtn);

		imageView = (ImageView) root.findViewById(R.id.imageView1);

		fikirEkleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!fikirBaslik.getText().equals("")
						&& !fikirAciklama.getText().equals("")) {

					senderTask sender = new senderTask();

					try {
						if (sender.execute(fikirImage).get()) {
							Log.d("log", "Fikir Eklendi");
							Crouton.makeText(getActivity(), "Fikir Eklendi",
									Style.CONFIRM).show();
						} else {
							Crouton.makeText(getActivity(),
									"Hata! Fikir Eklenemedi.", Style.ALERT)
									.show();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else{
					Crouton.makeText(getActivity(),
							"Lütfen Bütün alanlarý doldurunuz.", Style.ALERT)
							.show();
				}

			}
		});

		Button cameraButton = (Button) root.findViewById(R.id.fotoEkleButton);

		cameraButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_REQUEST);
			}

		});

		return root;

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1888 && resultCode == Activity.RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			imageView.setImageBitmap(imageBitmap);
			fikirImage = imageBitmap;
		}
	}

	public class senderTask extends AsyncTask<Bitmap, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Bitmap... bitmaps) {
			// TODO Auto-generated method stub
			try {
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(FIKIR_EKLE_WS);
				Calendar cal = Calendar.getInstance();

				

//				// FileBody bin = new FileBody(file);
//				MultipartEntity reqEntity = new MultipartEntity(
//						HttpMultipartMode.BROWSER_COMPATIBLE);
//				if(bitmaps[0]!=null)
//				{
//				Bitmap bm = bitmaps[0];
//				
//				ByteArrayOutputStream bos = new ByteArrayOutputStream();
//				bm.compress(CompressFormat.JPEG, 75, bos);
//				byte[] data = bos.toByteArray();
//				ByteArrayBody bab = new ByteArrayBody(data, ""
//						+ cal.getTimeInMillis() + ".png");
//				
//				
//				reqEntity.addPart("userfile", bab);
				
				
				String fotoUrl = uploadFoto();
				
				Fikir yeniFikir= new Fikir();
				
				yeniFikir.setBaslik(fikirBaslik.getText().toString());
				yeniFikir.setAciklama(fikirAciklama.getText().toString());
				yeniFikir.setGizlilik(FikirGizlilik.valueOf(fikirGizlilikSpinner.getSelectedItem().toString()));
				yeniFikir.setUserid(user.getId());
				yeniFikir.setFirmaid( user.getFirmaId());
				yeniFikir.setFoto(fotoUrl);
				
				String fikirJson = gson.toJson(yeniFikir);
				
				Log.d("DEBUG", fikirJson);
				
				postRequest.setEntity(new StringEntity(fikirJson,HTTP.UTF_8));
				
				HttpResponse response = httpClient.execute(postRequest);

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				String sResponse;
				StringBuilder s = new StringBuilder();

				while ((sResponse = reader.readLine()) != null) {
					s = s.append(sResponse);
				}
				System.out.println("Response: " + s.toString());

			} catch (Exception e) { // handle exception here
				e.printStackTrace();
				Log.e(e.getClass().getName(), e.getMessage());
				return false;
			}
			return true;
		}

	}

	public String uploadFoto ()  {
			try {
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(UPLOAD);
				Calendar cal = Calendar.getInstance();

				// FileBody bin = new FileBody(file);
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);
				if(fikirImage==null)
					return null;
				Bitmap bm = fikirImage;
				
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				bm.compress(CompressFormat.JPEG, 75, bos);
				byte[] data = bos.toByteArray();
				ByteArrayBody bab = new ByteArrayBody(data, ""
						+ cal.getTimeInMillis() + ".png");
				
				
				reqEntity.addPart("userfile", bab);
				
				postRequest.setEntity(reqEntity);
				
				HttpResponse response = httpClient.execute(postRequest);

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				String sResponse;
				StringBuilder s = new StringBuilder();

				while ((sResponse = reader.readLine()) != null) {
					s = s.append(sResponse);
				}
				System.out.println("Response: " + s.toString());
				return s.toString();
			} catch (Exception e) { // handle exception here
				e.printStackTrace();
				Log.e(e.getClass().getName(), e.getMessage());
				return null;
		}
			
	}
	
}

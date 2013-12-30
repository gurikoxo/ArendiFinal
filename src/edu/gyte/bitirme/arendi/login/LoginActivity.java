package edu.gyte.bitirme.arendi.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.main.MainActivity;
import edu.gyte.bitirme.arendi.services.Service;

public class LoginActivity extends Activity {

	private final String LOGIN_WS = Service.serverAddres+ "login.php";
	Gson gson = new Gson();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);  
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		final EditText username = (EditText) findViewById(R.id.loginField);
		final EditText password = (EditText) findViewById(R.id.passwordField);
		username.setText("ossavci@gmail.com");
		password.setText("123456");
		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("username",username.getText().toString() ));
				params.add(new BasicNameValuePair("password",password.getText().toString() ));
				
				String userJsonResult = Service.makeSimpleHttpGet(LOGIN_WS, params);
//				Log.d(STORAGE_SERVICE, userJsonResult);
				if (userJsonResult == null){
					Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
				}else{
					Log.d(NOTIFICATION_SERVICE, userJsonResult);
					UserJson userJson = new UserJson();
					userJson = gson.fromJson(userJsonResult, UserJson.class);
					
					if(userJson.getSuccess()==1){
						
						User user = userJson.getUser();
						
						Intent intent = new Intent(LoginActivity.this,MainActivity.class);
						intent.putExtra("user", user);
						LoginActivity.this.startActivity(intent);
						finish();
					}else{
						
							username.clearFocus();
							password.clearFocus();
							Crouton.makeText(LoginActivity.this, "Kullanýcý Adý veya Þifre Hatalý", Style.ALERT).show();
						
					}
				}
				
			}
		});
		
		
		
		
		
		
		
		return true;
	}

}

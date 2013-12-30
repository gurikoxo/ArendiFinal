package edu.gyte.bitirme.arendi.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;


public class Service {

	public static final String serverAddres = "http://erenerdogan.com/osman/";
	
	public static String makeSimpleHttpGet(String url,
			List<NameValuePair> params) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url += "?" + paramString;
		HttpGet httpGet = new HttpGet(url);
		String content = null;

		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			content = EntityUtils.toString(httpEntity);
		} catch (UnsupportedEncodingException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (ClientProtocolException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		} catch (IOException e) {
			Log.e("Error: ", e.getMessage());
			return null;
		}
		return content;
	}
}

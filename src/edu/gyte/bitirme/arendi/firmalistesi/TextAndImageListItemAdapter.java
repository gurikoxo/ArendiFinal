package edu.gyte.bitirme.arendi.firmalistesi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gyte.bitirme.arendi.R;

public class TextAndImageListItemAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
 
	public TextAndImageListItemAdapter(Context context, String[] values) {
		super(context, R.layout.text_image_list_item, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.text_image_list_item, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
		textView.setText(values[position]);
 
		// Change icon based on name
		String s = values[position];
 
		System.out.println(s);
 
		imageView.setImageResource(R.drawable.arrow);
		
		return rowView;
	}
}
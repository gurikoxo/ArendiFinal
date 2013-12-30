package edu.gyte.bitirme.arendi.firmalistesi;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.services.FirmaService;
 
public class FirmaListesiView extends Fragment {
 
    public static Fragment newInstance(Context context) {
    	FirmaListesiView f = new FirmaListesiView();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.firma_listesi, null);
        
       	FirmaService.loadService();
       	
    
    
        ListView listview = (ListView) root.findViewById(R.id.firmaListesiView);
        
        ArrayList<Firma> list = FirmaService.getFirmaList();
        
        final FirmaListesiAdapter adapter = new FirmaListesiAdapter(root.getContext(), R.layout.firma_list_item, list);
        listview.setAdapter(adapter);
       
        registerForContextMenu(listview);
    
        return root;
    
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.firmaListesiView) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
            Firma obj = (Firma) lv.getItemAtPosition(acmi.position);

            menu.setHeaderTitle(obj.getFirmaAdi());
            
            menu.add("Detay Görüntüle");
            menu.add("Düzenle");
            menu.add("Sil");
        
        }
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {

    	Toast.makeText(getActivity().getApplicationContext(), item.getTitle(), Toast.LENGTH_LONG).show();
    	
    	return super.onContextItemSelected(item);
    }
    
    
}
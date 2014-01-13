package edu.gyte.bitirme.arendi.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
import edu.gyte.bitirme.arendi.R;
import edu.gyte.bitirme.arendi.login.LoginActivity;
import edu.gyte.bitirme.arendi.login.User;



public class MainActivity extends FragmentActivity {
	
	private ActionBarDrawerToggle mDrawerToggle;

	 	ExpandableListAdapter listAdapter;
	    ExpandableListView expListView;
	    List<String> listDataHeader;
	    HashMap<String, List<String>> listDataChild;
	    DrawerLayout drawer;
	    ExpandableListView navList ;
	    User user ;
	
	    Map fragmentMap = new HashMap<String, String>();
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_main);
		
		 fillFragmentMap();
		 
		 user = (User) getIntent().getExtras().getSerializable("user");
		 Crouton.makeText(this, "Hoþgeldiniz "+ user.getName() + user.getJuri(), Style.CONFIRM).show();
		 
		 drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		 navList = (ExpandableListView) findViewById(R.id.drawer);
		 
		 drawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		 
		 prepareListData();
	        
        View header = View.inflate(this, R.layout.arendi_logo, null);
        
        navList.addHeaderView(header);
        navList.setHeaderDividersEnabled(true);
        
        
       
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);   
	 
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, "edu.gyte.bitirme.arendi.main.MainView"));
        tx.commit();

        
        navList.setAdapter(listAdapter);
		 navList.setOnChildClickListener(new OnChildClickListener() {
	       	 
	            @Override
	            public boolean onChildClick(ExpandableListView parent, View v,
	                    int groupPosition, int childPosition, long id) {
	                Toast.makeText(
	                        getApplicationContext(),
	                        listDataHeader.get(groupPosition)
	                                + " : "
	                                + listDataChild.get(
	                                        listDataHeader.get(groupPosition)).get(
	                                        childPosition), Toast.LENGTH_SHORT)
	                        .show();
	                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
	                
	                	tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, (String) fragmentMap.get(listDataChild.get(
								listDataHeader.get(groupPosition)).get(
								childPosition))));
	                	tx.addToBackStack(this.toString());
	                	 tx.commit();
//	               
	                drawer.closeDrawer(navList);
                    
	                return false;
	            }
	        });
		 
		 navList.setOnGroupExpandListener(new OnGroupExpandListener() {
        	 
	            @Override
	            public void onGroupExpand(int groupPosition) {
	                Toast.makeText(getApplicationContext(),
	                        listDataHeader.get(groupPosition) + " Expanded",
	                        Toast.LENGTH_SHORT).show();
	            }
	            
	        });
		
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                drawer,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
	                R.string.app_name,  /* "open drawer" description for accessibility */
	                R.string.app_name  /* "close drawer" description for accessibility */
	                ) {
	            public void onDrawerClosed(View view) {
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            public void onDrawerOpened(View drawerView) {
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };
	        drawer.setDrawerListener(mDrawerToggle);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	        
	        
	        peekDrawer();
	}

	 @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	         // The action bar home/up action should open or close the drawer.
	         // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        // Handle action buttons
	        switch(item.getItemId()) {
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	
	private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Fikir");
        listDataHeader.add("Firma");
        listDataHeader.add("Sistem");
        listDataHeader.add("Proje Takibi");
        listDataHeader.add("Raporlar");
 
        // Adding child data
        List<String> projeHeader = new ArrayList<String>();
        projeHeader.add("Fikir Ekle");
        projeHeader.add("Fikirlerim");
        projeHeader.add("Fikir Listesi");
 
        List<String> firmaHeader = new ArrayList<String>();
        firmaHeader.add("Personel Listesi");
        firmaHeader.add("Departman Listesi");
        firmaHeader.add("Proje Kategori Listesi");
 
        List<String> sistemHeader = new ArrayList<String>();
        sistemHeader.add("Deðerlendirme Kriterleri");
 
        List<String> projeTakibiHeader = new ArrayList<String>();
        if(user.getJuri()==1)
        	projeTakibiHeader.add("Proje Kayýt");
        projeTakibiHeader.add("Projeler");
        
        List<String> raporlarHeader = new ArrayList<String>();
        raporlarHeader.add("Genel Rapor");
        
        listDataChild.put(listDataHeader.get(0), projeHeader); // Header, Child data
        listDataChild.put(listDataHeader.get(1), firmaHeader);
        listDataChild.put(listDataHeader.get(2), sistemHeader);
        listDataChild.put(listDataHeader.get(3), projeTakibiHeader);
        listDataChild.put(listDataHeader.get(4), raporlarHeader);
    }
	
	private void fillFragmentMap(){
		fragmentMap.put("Fikir Ekle", "edu.gyte.bitirme.arendi.fikirekle.FikirEkleView");
		fragmentMap.put("Fikir Listesi", "edu.gyte.bitirme.arendi.fikirlistesi.FikirListesiView");
		fragmentMap.put("Personel Listesi", "edu.gyte.bitirme.arendi.personellistesi.PersonelListesiView");
		fragmentMap.put("Departman Listesi", "edu.gyte.bitirme.arendi.departmanlistesi.DepartmanListesiView");
		fragmentMap.put("Proje Kategori Listesi", "edu.gyte.bitirme.arendi.projekategorilistesi.ProjeKategoriListesiView");
		fragmentMap.put("Fikirlerim", "edu.gyte.bitirme.arendi.fikirlistesi.UserFikirListesiView");
		fragmentMap.put("Deðerlendirme Kriterleri", "edu.gyte.bitirme.arendi.degerlendirmekriterleri.DegerlendirmeKriterleriView");
		fragmentMap.put("Projeler", "edu.gyte.bitirme.arendi.projeler.ProjeListesiView");
		fragmentMap.put("Proje Kayýt", "edu.gyte.bitirme.arendi.projeler.ProjeEkleView");
		
		
	}
	
	private static final int PEEK_DRAWER_TIME_SECONDS = 3;
	private long downTime;
	private long eventTime;
	private float x = 0.0f;
	private float y = 100.0f;
	private int metaState = 0;
	 
	protected void peekDrawer() {
	    downTime = SystemClock.uptimeMillis();
	    eventTime = SystemClock.uptimeMillis() + 100;
	    MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, x, y, metaState);
	    drawer.dispatchTouchEvent(motionEvent);
	    motionEvent.recycle();
	 
	    new Handler().postDelayed(new Runnable() {
	      @Override
	      public void run() {
	        downTime = SystemClock.uptimeMillis();
	        eventTime = SystemClock.uptimeMillis() + 100;
	        MotionEvent motionEvent = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, metaState);
	        drawer.dispatchTouchEvent(motionEvent);
	        motionEvent.recycle();
	      }
	    }, (long) (PEEK_DRAWER_TIME_SECONDS * DateUtils.SECOND_IN_MILLIS));
	  }
	
	
}

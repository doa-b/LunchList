package apt.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantMap extends Activity implements OnMarkerClickListener{
	public static final String EXTRA_LATITUDE="apt.tutorial,EXTRA_LATITUDE";
	public static final String EXTRA_LONGITUDE="apt.tutorial,EXTRA_LONGITUDE";
	public static final String EXTRA_NAME="apt.tutorial,EXTRA_NAME";
	private GoogleMap map=null;
	public static LatLng positie =null;
	public static Marker rest;
	String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		positie = new LatLng((getIntent().getDoubleExtra(EXTRA_LATITUDE, 0)), (getIntent().getDoubleExtra(EXTRA_LONGITUDE, 0)));
		name=getIntent().getStringExtra(EXTRA_NAME);
				
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		map.getUiSettings().setZoomControlsEnabled(true);
		
		map.setMyLocationEnabled(true);
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		map.setOnMarkerClickListener(this);
		
		rest = map.addMarker(new MarkerOptions().position(positie)
        		.title(name)
        		.snippet(name)
        		.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(positie, 12));						
	}
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		
		if (marker.equals(rest)) {
			Toast.makeText(RestaurantMap.this, name, Toast.LENGTH_SHORT).show();
		return true;		
	}
		else return false;
		
	}

}


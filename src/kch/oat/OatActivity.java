package kch.oat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class OatActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    super.setContentView(R.layout.oat_activity);
	    
	    TabHost tHost=(TabHost)this.findViewById(R.id.tabhost);
	    tHost.setup();
	    
	    TabHost.TabSpec tSpec=tHost.newTabSpec("tab1");
	    //tSpec.setContent(R)
	}

}

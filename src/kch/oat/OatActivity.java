package kch.oat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TabHost;

public class OatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oat);
		
		// TabHost ����
		TabHost tHost=(TabHost)this.findViewById(R.id.tabhost);
		tHost.setup();

		TabHost.TabSpec tSpec=tHost.newTabSpec("tab1");
		tSpec.setContent(R.id.tab1);
		tSpec.setIndicator("����");
		tHost.addTab(tSpec);
		
		// TabSpec ����
		tSpec=tHost.newTabSpec("tab2");
		tSpec.setContent(R.id.tab2);
		tSpec.setIndicator("����");
		tHost.addTab(tSpec);
		tHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.oat, menu);
		return true;
	}
}

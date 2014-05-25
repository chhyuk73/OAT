package kch.oat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class OatActivity extends Activity implements TabHost.TabContentFactory{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oat_activity);
		
		// TabHost 积己
		TabHost tHost=(TabHost)this.findViewById(R.id.tabhost);
		tHost.setup();

		TabHost.TabSpec tSpec=tHost.newTabSpec("science");
		tSpec.setIndicator("苞切");
		tSpec.setContent(this);
		tHost.addTab(tSpec);
		
		// TabSpec 积己
		tSpec=tHost.newTabSpec("math");
		tSpec.setContent(this);
		tSpec.setIndicator("荐切");
		tHost.addTab(tSpec);
		tHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.oat, menu);
		return true;
	}

	@Override
	public View createTabContent(String tag) {
		SubjectView sv=new SubjectView(this, tag);
		return sv;
	}
}

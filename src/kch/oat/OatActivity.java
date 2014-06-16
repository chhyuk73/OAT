package kch.oat;

/**
 * 1. �� ���� �� �׸��� ���屸���� ����
 * 2. �� ��Ƽ��� �����ؼ� �� �۾��� ��� ������ �����ؾ� ��
 * 
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class OatActivity extends TabActivity
			implements TabHost.TabContentFactory, View.OnClickListener{

	private Button activityNext;
	private SharedPreferences pref;
	private Set<String>	 subjectSet;
	private Object[] arrSubject;
	private HashMap<String, String> subjectHash = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oat_activity);
		
		activityNext = (Button)this.findViewById(R.id.activityNext);
		activityNext.setOnClickListener(this);
		
		// OAT���� ����� ������� Preferences�� �̿��Ͽ� �ٽ� ��������
		// �װ����� �Ǹ��� ���ϱ� ���Ͽ�...
		pref = getSharedPreferences("subject", Activity.MODE_PRIVATE);
		subjectSet = pref.getStringSet("subject", new HashSet<String>());
		
		// TabHost ����
		TabHost tHost = (TabHost)this.findViewById(android.R.id.tabhost);
		tHost.setup();
		
		arrSubject = subjectSet.toArray();
		for (int i=0; i<arrSubject.length; i++) {
			// HashMap�� ������ִ� ���� �켱���� �ؾ�
			// setContent�� �� createTabConten�� ������ ������ �� �ִ�.
			subjectHash.put("tab"+(i+1), arrSubject[i].toString());
			TabHost.TabSpec tSpec = tHost.newTabSpec("tab"+(i+1));
			tSpec.setIndicator(arrSubject[i].toString());
			tSpec.setContent(OatActivity.this);
			tHost.addTab(tSpec);
		}

		tHost.setCurrentTab(0);
	}

	@Override
	public View createTabContent(String subjectTag) {
		// ���񸶴� ���� �� View�� ����
		SubjectView sv = new SubjectView(this, subjectHash.get(subjectTag));
		return sv;
	}

	@Override
	public void onClick(View v) {
		if(v==activityNext){
			Intent intent=new Intent(OatActivity.this, Next2.class);
			startActivity(intent);
			this.finish();
		}
	}
}

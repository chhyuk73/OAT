package kch.oat;

/**
 * 1. 각 과목별 각 항목의 저장구조를 지정
 * 2. 빈 액티비라도 생성해서 각 작업이 어떻게 될지를 지정해야 함
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
		
		// OAT에서 저장된 과목들을 Preferences를 이용하여 다시 가져오기
		// 그것으로 탭명을 정하기 위하여...
		pref = getSharedPreferences("subject", Activity.MODE_PRIVATE);
		subjectSet = pref.getStringSet("subject", new HashSet<String>());
		
		// TabHost 생성
		TabHost tHost = (TabHost)this.findViewById(android.R.id.tabhost);
		tHost.setup();
		
		arrSubject = subjectSet.toArray();
		for (int i=0; i<arrSubject.length; i++) {
			// HashMap을 만들어주는 것을 우선으로 해야
			// setContent할 때 createTabConten로 과목을 전달할 수 있다.
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
		// 과목마다 들어가야 할 View를 생성
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

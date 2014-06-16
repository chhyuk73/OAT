package kch.oat;

/**
 * �н���ȹ�� �����ִ� ��
 * 
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class OAT extends Activity implements View.OnClickListener{
	private Button btnMainPlus, btnMainMinus;
	private Button btnNext;
	private InputDialog dialog;
	
	private ArrayList<String> nameData=new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private ListView listSubject;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private Set<String> mySet = new HashSet<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oat_main);
		
		btnMainPlus = (Button)this.findViewById(R.id.btnMainPlus);
		btnMainPlus.setOnClickListener(this);
		btnMainMinus = (Button)this.findViewById(R.id.btnMainMinus);
		btnMainMinus.setOnClickListener(this);
		listSubject = (ListView)this.findViewById(R.id.listSubject);
		btnNext = (Button)this.findViewById(R.id.btnNext);
		btnNext.setOnClickListener(this);
		
		// �ý��ۿ� ����� ���� ��������
		pref = getSharedPreferences("subject", Activity.MODE_PRIVATE);
		editor = pref.edit();
		mySet = pref.getStringSet("subject", new HashSet<String>());
		Object[] arr = mySet.toArray();
		
		if (arr.length==0) {
			nameData.add("����");
			nameData.add("����");
			nameData.add("����");
		}
		else {
			for (int i=0; i<arr.length; i++) {
				nameData.add(arr[i].toString());
			}
		}
		
		// ����� 
		adapter = new ArrayAdapter<String>(
			this,
			android.R.layout.simple_list_item_multiple_choice,
			nameData
		);
		listSubject.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listSubject.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.oat, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v==btnNext){
			Intent intent = new Intent(OAT.this, Next1.class);
			
			// SharedPreferences�� �����ϱ�
			//pref = getSharedPreferences("subject", Activity.MODE_PRIVATE);
			for (int i=0; i<nameData.size(); i++) {
				mySet.add(nameData.get(i));
			}
			
			editor.clear();
			editor.putStringSet("subject", mySet);
			editor.commit();
			
			startActivity(intent);
		}
		else{
			if(v==btnMainPlus){
				dialog = new InputDialog(OAT.this, true);
				dialog.setTitle("�����Է�");
				dialog.setOnDismissListener(new OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						String sSubject = OAT.this.dialog.getSubject();
						if (sSubject.equals("")) return;
						nameData.add(sSubject);
						adapter.notifyDataSetChanged();
						mySet.add(sSubject);
						saveData("subject", mySet);
					}
				});
				dialog.show();
			}
			else if (v==btnMainMinus) {
				/** 
				 * SparseBooleanArray Ȱ��
				 * 
				 */
				SparseBooleanArray pos = listSubject.getCheckedItemPositions();
				for (int i=0; i<pos.size(); i++) {
					if (pos.valueAt(i)) {
						mySet.remove(nameData.get(pos.keyAt(i)));
						nameData.remove(pos.keyAt(i));
						adapter.notifyDataSetChanged();
						removeData("subject", mySet);
					}
				}
			}
		}
	}
	
	private void saveData(String key, Set<String> tSet) {
		editor.clear();
		editor.putStringSet(key, tSet);
		editor.commit();
	}
	
	// ������ ������ SharedPreferences���� �����
	private void removeData(String key, Set<String> tSet) {
		editor.clear();
		editor.putStringSet(key, tSet);
		editor.commit();
		removeChildItems(key);
	}
	
	// �� ������ ���λ��׵� ���� �����
	private void removeChildItems(String key) {
		SharedPreferences childPref = getSharedPreferences(key, Activity.MODE_PRIVATE);
		SharedPreferences.Editor childEditor = childPref.edit();
		childEditor.remove(key);
		childEditor.commit();
	}
}

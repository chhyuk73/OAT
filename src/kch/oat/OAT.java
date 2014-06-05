package kch.oat;

/**
 * 학습계획을 도와주는 앱
 * 
 */

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

public class OAT extends Activity implements View.OnClickListener{
	private ImageButton imgPlus, imgMinus;
	private Button btnNext;
	
	private ListView listSubject;
	private ArrayList<String> nameData = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	
	private InputDialog dial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oat_main);
		
		imgPlus = (ImageButton)this.findViewById(R.id.imgPlus);
		imgPlus.setOnClickListener(this);
		imgMinus = (ImageButton)this.findViewById(R.id.imgMinus);
		imgMinus.setOnClickListener(this);
		listSubject = (ListView)this.findViewById(R.id.listSubject);
		btnNext = (Button)this.findViewById(R.id.btnNext);
		btnNext.setOnClickListener(this);
		
		// 어댑터 
		nameData.add("과학");
		nameData.add("수학");
		nameData.add("국어");
		adapter = new ArrayAdapter<String>(
			this,
			android.R.layout.select_dialog_multichoice,
			nameData
		);
		listSubject.setAdapter(adapter);
		listSubject.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
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
			startActivity(intent);
			this.finish();
		}
		else{
			if(v==imgPlus){
				dial = new InputDialog(this); // 사용자 Dialog
				dial.setOnDismissListener(new OnDismissListener(){ // Dialog 사라질 때 이벤트
					@Override
					public void onDismiss(DialogInterface arg0) {
						nameData.add(dial.getSubject());
						adapter.notifyDataSetChanged();
					}	
				});
				dial.show(); // Dialog 보이기
			}
			else if(v==imgMinus){
				SparseBooleanArray sb = listSubject.getCheckedItemPositions();
				String[] ta = new String[nameData.size()];
				int idx = 0;
				
				for(int i=0; i<nameData.size(); i++){
					if (sb.get(i)==false) {
						ta[idx++] = nameData.get(i);
					}
				}
				
				nameData.clear();
				
				for (int i=0; i<idx; i++) {
					nameData.add(ta[i]);
					adapter.notifyDataSetChanged();
				}
			}
		}
	}

}

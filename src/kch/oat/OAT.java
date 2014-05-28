package kch.oat;

/**
 * 학습계획을 도와주는 앱
 * 
 */

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class OAT extends Activity implements View.OnClickListener{
	private ImageButton imgPlus,imgMinus;
	private ListView listSubject;
	private Button btnNext;
	private ArrayList<String> nameData=new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oat_main);
		
		imgPlus=(ImageButton)this.findViewById(R.id.imgPlus);
		imgPlus.setOnClickListener(this);
		imgMinus=(ImageButton)this.findViewById(R.id.imgMinus);
		imgMinus.setOnClickListener(this);
		listSubject=(ListView)this.findViewById(R.id.listSubject);
		btnNext=(Button)this.findViewById(R.id.btnNext);
		btnNext.setOnClickListener(this);
		
		// 어댑터 
		nameData.add("과학");
		nameData.add("수학");
		nameData.add("국어");
		adapter=new ArrayAdapter<String>(
			this,
			android.R.layout.simple_list_item_1,
			nameData
		);
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
			Intent intent=new Intent(OAT.this, Next1.class);
			startActivity(intent);
			this.finish();
		}
		else{
			InputDialog dial;
			if(v==imgPlus){
				dial=new InputDialog(this);
				dial.show();
			}
		}
	}

}

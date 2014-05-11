package kch.oat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class OAT extends Activity {
	private ImageButton imgPlus,imgMinus;
	private ListView listSubject;
	private Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		imgPlus=(ImageButton)this.findViewById(R.id.imgPlus);
		imgMinus=(ImageButton)this.findViewById(R.id.imgMinus);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.oat, menu);
		return true;
	}

}

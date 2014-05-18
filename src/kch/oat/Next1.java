package kch.oat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Next1 extends Activity implements Runnable{
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.next1);
	    (new Thread(this)).start();
	}

	@Override
	public void run() {
		try{
			Thread.sleep(2000);
		}
		catch(Exception e){}
		
		Intent intent=new Intent(Next1.this,OatActivity.class);
		startActivity(intent);
		//finish();
		this.finish();
	}
}
package kch.oat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputDialog extends Dialog implements View.OnClickListener{
	public static String sSubject;
	EditText edtSubject;
	Button btnInputAdd;

	public InputDialog(Context context) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.input_subject);
		
		edtSubject=(EditText)this.findViewById(R.id.edtSubject);
		btnInputAdd=(Button)this.findViewById(R.id.btnInputAdd);
		
		btnInputAdd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		sSubject=edtSubject.getText().toString();
		this.dismiss();
	}
}

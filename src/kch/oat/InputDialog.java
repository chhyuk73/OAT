package kch.oat;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

public class InputDialog extends Dialog{

	public InputDialog(Context context) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.input_subject);
	}
}

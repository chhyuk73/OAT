package kch.oat;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputDialog extends Dialog implements View.OnClickListener {
	private TextView mInputSubjectMsg;
	private EditText edtSubject;
	private Button btnInputAdd, btnInputCancel;
	private String mSubject;
	private boolean mMsgSelectType;

	public InputDialog(Context context, boolean msgSelectType) {
		super(context);
		this.setTitle("������");
		this.setContentView(R.layout.input_subject);
		
		this.mMsgSelectType = msgSelectType;
		
		mInputSubjectMsg = (TextView)findViewById(R.id.txtInputSubjectMsg);
		edtSubject = (EditText)findViewById(R.id.edtSubject);
		btnInputAdd = (Button)findViewById(R.id.btnInputAdd);
		btnInputCancel = (Button)findViewById(R.id.btnInputCancel);
		
		if (msgSelectType==false) mInputSubjectMsg.setText("");
		
		btnInputAdd.setOnClickListener(this);
		btnInputCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v==btnInputAdd){
			mSubject = edtSubject.getText().toString();
			dismiss();
		}
		else if(v==btnInputCancel){
			cancel();
		}
	}
	
	/**
	 * ������ ȣ�����ʿ� �ѱ�� ����
	 */
	public String getSubject(){
		return mSubject;
	}
}

package com.xyw.android.homework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.Inflater;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogActivity extends Activity implements OnClickListener {
	private Button normalBtn, customBtn, progressBtn, popupWindowDialog, dateDialog, buttomDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_acitivity);
		normalBtn = (Button) findViewById(R.id.bt_normalDialog);
		customBtn = (Button) findViewById(R.id.bt_customDialog);
		progressBtn = (Button) findViewById(R.id.bt_progressDialog);
		popupWindowDialog = (Button) findViewById(R.id.bt_popupWindowDialog);
		dateDialog = (Button) findViewById(R.id.bt_dateDialog);
		buttomDialog = (Button) findViewById(R.id.bt_bottumDialog);
		normalBtn.setOnClickListener(this);
		customBtn.setOnClickListener(this);
		progressBtn.setOnClickListener(this);
		popupWindowDialog.setOnClickListener(this);
		dateDialog.setOnClickListener(this);
		buttomDialog.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_normalDialog:
			showNormalDialog();
			break;
		case R.id.bt_customDialog:
			showCustomDialog();
			break;
		case R.id.bt_progressDialog:
			showProgressDialog();
			break;
		case R.id.bt_popupWindowDialog:
			showPopupWindowDialog();
			break;
		case R.id.bt_dateDialog:
			showDateDialog();
			break;
		case R.id.bt_bottumDialog:
			showButtomDialog();
			break;
		}
	}

	private void showButtomDialog() {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(this).inflate(R.layout.popup_bottum_layout, null);
		PopupWindow buttomPPPW = new PopupWindow(this);
		buttomPPPW.setContentView(view);
		int[] location = new int[2];
		buttomPPPW.setWidth(getWindowManager().getDefaultDisplay().getWidth());
		buttomPPPW.setHeight(getWindowManager().getDefaultDisplay().getHeight());
		buttomPPPW.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_backgroud));
		buttomPPPW.setFocusable(true);
		buttomPPPW.showAtLocation(buttomDialog, Gravity.TOP | Gravity.LEFT, 0, 0);
	}

	private void showDateDialog() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		// SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:MM:ss");
		// TimePickerDialog tpk = new TimePickerDialog(DialogActivity.this, new
		// TimePickerDialog.OnTimeSetListener() {
		//
		// @Override
		// public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// // TODO Auto-generated method stub
		// Toast.makeText(DialogActivity.this, "时间：" + hourOfDay + ":" + minute,
		// Toast.LENGTH_LONG).show();
		// }
		// }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
		// tpk.show();
		DatePickerDialog dpk = new DatePickerDialog(DialogActivity.this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				Toast.makeText(DialogActivity.this, "当前日期：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "月",
						Toast.LENGTH_LONG).show();
			}
		}, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		dpk.show();
	}

	private void showPopupWindowDialog() {
		// TODO Auto-generated method stub
		PopupWindow pppw = new PopupWindow(DialogActivity.this);
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.popup_layout, null);
		pppw.setContentView(view);
		pppw.setWidth(200);
		pppw.setHeight(80);
		int[] location = new int[2];
		popupWindowDialog.getLocationInWindow(location);
		pppw.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_backgroud));
		pppw.setFocusable(true);
		pppw.showAtLocation(popupWindowDialog, Gravity.TOP | Gravity.LEFT,
				location[0] + popupWindowDialog.getWidth() / 2, location[1] - popupWindowDialog.getHeight() / 2);
	}

	private void showProgressDialog() {
		// TODO Auto-generated method stub
		ProgressDialog progressDialog = new ProgressDialog(DialogActivity.this);
		progressDialog.setTitle("进度条对话框测试");
		progressDialog.setMessage("加载中");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.show();
	}

	private void showCustomDialog() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.dialog_custom, null);
		AlertDialog.Builder builder = new Builder(this);
		builder.setView(view);
		final EditText et_userName = (EditText) view.findViewById(R.id.et_userName);
		builder.setTitle("自定义对话框");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(DialogActivity.this, "确定:" + et_userName.getText().toString(), Toast.LENGTH_LONG).show();

			}
		});
		builder.create().show();
	}

	private void showNormalDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("测试对话框-普通");
		builder.setMessage("对话框内容!");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_LONG).show();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_LONG).show();
			}
		});
		builder.create().show();
	}

}

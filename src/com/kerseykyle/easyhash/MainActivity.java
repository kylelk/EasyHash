package com.kerseykyle.easyhash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Share();

		Intent intent = getIntent();
		String action = intent.getAction();
		String type = intent.getType();

		if (Intent.ACTION_SEND.equals(action) && type != null) {
			if ("text/plain".equals(type)) {
				handleSendText(intent); // Handle text being sent
			} 
		} 

		EditText myTextBox = (EditText) findViewById(R.id.input);
		myTextBox.setSingleLine(true);
		myTextBox.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				String md5hash = CreateHash(s.toString(), "MD5");
				TextView md5 = (TextView) findViewById(R.id.md5);
				md5.setText(md5hash);
				md5.setTextIsSelectable(true);

				String sha1hash = CreateHash(s.toString(), "SHA-1");
				TextView sha1 = (TextView) findViewById(R.id.sha1);
				sha1.setText(sha1hash);
				sha1.setTextIsSelectable(true);


				String sha256hash = CreateHash(s.toString(), "SHA-256");
				TextView sha256 = (TextView) findViewById(R.id.sha256);
				sha256.setText(sha256hash);
				sha256.setTextIsSelectable(true);

				String sha512hash = CreateHash(s.toString(), "SHA-512");
				TextView sha512 = (TextView) findViewById(R.id.sha512);
				sha512.setText(sha512hash);
				sha512.setTextIsSelectable(true);
			}
		});

	}

	public static String CreateHash(String data, String function) {
		try {
			MessageDigest digest = MessageDigest.getInstance(function);
			byte[] hash = digest.digest(((String) data).getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public void Share() {

		TextView md5 = (TextView) findViewById(R.id.md5);
		md5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText myTextBox = (EditText) findViewById(R.id.input);
				String md5hash = CreateHash(myTextBox.getText().toString(),
						"MD5");
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, md5hash);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);

			}
		});

		TextView sha1 = (TextView) findViewById(R.id.sha1);
		sha1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText myTextBox = (EditText) findViewById(R.id.input);
				String sha1hash = CreateHash(myTextBox.getText().toString(),
						"SHA-1");
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, sha1hash);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
			}
		});

		TextView sha256 = (TextView) findViewById(R.id.sha256);
		sha256.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText myTextBox = (EditText) findViewById(R.id.input);
				String sha256hash = CreateHash(myTextBox.getText().toString(),
						"SHA-256");
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, sha256hash);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
			}
		});

		TextView sha512 = (TextView) findViewById(R.id.sha512);
		sha512.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText myTextBox = (EditText) findViewById(R.id.input);
				String sha512hash = CreateHash(myTextBox.getText().toString(),
						"SHA-512");
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, sha512hash);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
			}
		});

		{
		}
	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	void handleSendText(Intent intent) {
		String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
		if (sharedText != null) {
			
			EditText input = (EditText) findViewById(R.id.input);
			input.setText(sharedText);
			
			String md5hash = CreateHash(sharedText, "MD5");
			TextView md5 = (TextView) findViewById(R.id.md5);
			md5.setText(md5hash);
			md5.setTextIsSelectable(true);

			String sha1hash = CreateHash(sharedText, "SHA-1");
			TextView sha1 = (TextView) findViewById(R.id.sha1);
			sha1.setText(sha1hash);
			sha1.setTextIsSelectable(true);


			String sha256hash = CreateHash(sharedText, "SHA-256");
			TextView sha256 = (TextView) findViewById(R.id.sha256);
			sha256.setText(sha256hash);
			sha256.setTextIsSelectable(true);

			String sha512hash = CreateHash(sharedText, "SHA-512");
			TextView sha512 = (TextView) findViewById(R.id.sha512);
			sha512.setText(sha512hash);
			sha512.setTextIsSelectable(true);
			
		}
	}


	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

}

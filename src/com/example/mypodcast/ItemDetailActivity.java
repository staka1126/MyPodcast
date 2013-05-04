package com.example.mypodcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

// ItemDetailActivity.java
public class ItemDetailActivity extends Activity {
	private TextView mTitle;
	private TextView mDescr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_detail);
		
		Intent intent = getIntent();

		String title = intent.getStringExtra("TITLE");
		mTitle = (TextView) findViewById(R.id.item_detail_title);
		mTitle.setText(title);
		String descr = intent.getStringExtra("DESCRIPTION");
		mDescr = (TextView) findViewById(R.id.item_detail_descr);
		mDescr.setText(descr);
	}
}

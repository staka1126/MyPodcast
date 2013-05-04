package com.example.mypodcast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.ListView;

// RssParserTask.java
public class RssParserTask extends AsyncTask<String, Integer, RssListAdapter> {
	private MainActivity mActivity;
	private RssListAdapter mAdapter;
	private ProgressDialog mProgressDialog;

	// �R���X�g���N�^
	public RssParserTask(MainActivity activity, RssListAdapter adapter) {
		mActivity = activity;
		mAdapter = adapter;
	}

	// �^�X�N�����s��������ɃR�[�������
	@Override
	protected void onPreExecute() {
		// �v���O���X�o�[��\������
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setMessage("Now Loading...");
		mProgressDialog.show();
	}

	// �o�b�N�O���E���h�ɂ����鏈����S���B�^�X�N���s���ɓn���ꂽ�l�������Ƃ���
	@Override
	protected RssListAdapter doInBackground(String... params) {
		RssListAdapter result = null;
		try {
			// HTTP�o�R�ŃA�N�Z�X���AInputStream���擾����
			URL url = new URL(params[0]);
			InputStream is = url.openConnection().getInputStream();
			result = parseXml(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �����ŕԂ����l�́AonPostExecute���\�b�h�̈����Ƃ��ēn�����
		return result;
	}

	// ���C���X���b�h��Ŏ��s�����
	@Override
	protected void onPostExecute(RssListAdapter result) {
		mProgressDialog.dismiss();
		ListView lv = (ListView)mActivity.findViewById(R.id.list_view);
		lv.setAdapter(mAdapter);
	}

	// XML���p�[�X����
	public RssListAdapter parseXml(InputStream is) throws IOException, XmlPullParserException {
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, null);
			int eventType = parser.getEventType();
			Item currentItem = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tag = null;
				switch (eventType) {
					case XmlPullParser.START_TAG:
						tag = parser.getName();
						if (tag.equals("item")) {
							currentItem = new Item();
						} else if (currentItem != null) {
							if (tag.equals("title")) {
								currentItem.setTitle(parser.nextText());
							} else if (tag.equals("description")) {
								currentItem.setDescription(parser.nextText());
							}
						}
						break;
					case XmlPullParser.END_TAG:
						tag = parser.getName();
						if (tag.equals("item")) {
							mAdapter.add(currentItem);
						}
						break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mAdapter;
	}
}

package com.example.mypodcast;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

// RssListAdapter.java
public class RssListAdapter extends ArrayAdapter<Item> {
	private LayoutInflater mInflater;
	private TextView mTitle;
	private TextView mDescr;

	public RssListAdapter(Context context, List<Item> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 1�s���Ƃ̃r���[�𐶐�����
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_row, null);
		}

		// ���ݎQ�Ƃ��Ă��郊�X�g�̈ʒu����Item���擾����
		Item item = this.getItem(position);
		if (item != null) {
			// Item����K�v�ȃf�[�^�����o���A���ꂼ��TextView�ɃZ�b�g����
			String title = item.getTitle().toString();
			mTitle = (TextView) view.findViewById(R.id.item_title);
			mTitle.setText(title);
			String descr = item.getDescription().toString();
			mDescr = (TextView) view.findViewById(R.id.item_descr);
			mDescr.setText(descr);
		}
		return view;
	}
}

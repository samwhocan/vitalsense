package com.omniapps.vitalsense5.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NavigationDrawerAdapter extends ArrayAdapter<String> {
	
	private int mSelectedItem;

	public NavigationDrawerAdapter(Context context, int resource,
			String[] objects) {
		super(context, resource, objects);
	}
	
	public int getSelectedItem() {
		return mSelectedItem;
	}
	
	public void setSelectedItem(int selectedItem) {
		mSelectedItem = selectedItem;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) super.getView(position, convertView, parent);
		
		view.setText(position);
		
		if (position == mSelectedItem) {
			view.setTextColor(parent.getContext().getResources().getColor(android.R.color.black));
			
		} else {
			view.setTextColor(parent.getContext().getResources().getColor(android.R.color.white));
		}
		
		return view;
	}
}

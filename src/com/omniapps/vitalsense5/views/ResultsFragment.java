package com.omniapps.vitalsense5.views;

import com.omniapps.vitalsense5.R;
import com.omniapps.vitalsense5.daos.VitalsenseDbHelper;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultsFragment extends Fragment {
	public static final String ARG_MENU_ITEM_NUMBER = "menu_item_number";
	
	private Context context;
	private VitalsenseDbHelper dbHelper;
	
	public ResultsFragment() {
		// Empty constructor required for fragment subclasses
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = container.getContext();
		
		int i = getArguments().getInt(ARG_MENU_ITEM_NUMBER);
		View view = inflater.inflate(R.layout.results_view, container, false);		
		String menuItem = getResources().getStringArray(R.array.menu_array)[i];
		getActivity().setTitle(menuItem);
		
		TextView timeTextView = (TextView) view.findViewById(R.id.time_results_data);
		TextView systolicTextView = (TextView) view.findViewById(R.id.systolic_results_data);
		TextView diastolicTextView = (TextView) view.findViewById(R.id.diastolic_results_data);
		TextView pulseTextView = (TextView) view.findViewById(R.id.pulse_results_data);

		dbHelper = new VitalsenseDbHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String timeData = dbHelper.getTime(db);
		String systolicData = dbHelper.getSystolic(db);
		String diastolicData = dbHelper.getDiastolic(db);
		String pulseData = dbHelper.getPulse(db);
		
		timeTextView.setText(timeData);
		systolicTextView.setText(systolicData);
		diastolicTextView.setText(diastolicData);
		pulseTextView.setText(pulseData);
		
		return view;
	}
}
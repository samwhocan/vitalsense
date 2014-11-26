package com.omniapps.vitalsense5.views;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.omniapps.vitalsense5.MainActivity;
import com.omniapps.vitalsense5.R;
import com.omniapps.vitalsense5.daos.VitalsenseDbHelper;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputFragment extends Fragment implements OnClickListener {
	public static final String ARG_MENU_ITEM_NUMBER = "menu_item_number";
	
	private static final String LINE_BREAK = "\n";
	private static final String DOUBLE_LINE_BREAK = "\n\n";
	private static final String DATE_FORMAT = "EEE, dd MMM yyyy";
	private static final String TIME_FORMAT = "h:mm a";	
	
	private EditText systolicEditText, diastolicEditText, pulseEditText;
	private VitalsenseDbHelper dbHelper;	
	private Context context;
	
	public InputFragment() {
		// Empty constructor required for fragment subclasses
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = container.getContext();
		
		int i = getArguments().getInt(ARG_MENU_ITEM_NUMBER);
		View view = inflater.inflate(R.layout.input_view, container, false);		
		String menuItem = getResources().getStringArray(R.array.menu_array)[i];
		getActivity().setTitle(menuItem);
		
		final Button inputDataButton = (Button) view.findViewById(R.id.input_reading_button);
		systolicEditText = (EditText) view.findViewById(R.id.systolic_input_box);
		diastolicEditText = (EditText) view.findViewById(R.id.diastolic_input_box);
		pulseEditText = (EditText) view.findViewById(R.id.pulse_input_box);
		CheckBox checkbox = (CheckBox) view.findViewById(R.id.check_box);
		final TextView systolicTitle = (TextView) view.findViewById(R.id.systolic_title);
		final TextView diastolicTitle = (TextView) view.findViewById(R.id.diastolic_title);
		final TextView pulseTitle = (TextView) view.findViewById(R.id.pulse_title);
		
        inputDataButton.setOnClickListener(this);
        
        dbHelper = new VitalsenseDbHelper(context);        
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);        
        
        checkbox.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                if (((CompoundButton) view).isChecked()) {
                	systolicEditText.setEnabled(true);
                	diastolicEditText.setEnabled(true);
                	pulseEditText.setEnabled(true);
                	systolicTitle.setTextColor(Color.BLACK);
                	diastolicTitle.setTextColor(Color.BLACK);
                	pulseTitle.setTextColor(Color.BLACK);
                	inputDataButton.setEnabled(true);
                	inputDataButton.setBackgroundColor(Color.BLACK);
                	inputDataButton.setTextColor(Color.WHITE);
                	
                } else {
                	systolicEditText.setEnabled(false);
                	diastolicEditText.setEnabled(false);
                	pulseEditText.setEnabled(false);
                	systolicTitle.setTextColor(Color.GRAY);
                	diastolicTitle.setTextColor(Color.GRAY);
                	pulseTitle.setTextColor(Color.GRAY);
                	inputDataButton.setEnabled(false);
                	inputDataButton.setBackgroundColor(Color.GRAY);
                	inputDataButton.setTextColor(Color.DKGRAY);
                }

            }
        });
		
		return view;
	}
	
	@Override
	public void onClick(View view) {
        Boolean didItWork = true;
        
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT + LINE_BREAK + 
        		TIME_FORMAT + LINE_BREAK, Locale.UK);
        String time = formatter.format(date);
        
        int systolicInt = 0;
		
		try {
			systolicInt = Integer.parseInt(systolicEditText.getText().toString());
			
		} catch (NumberFormatException nfe) {
			Toast.makeText(getActivity(), "Error: Systolic Pressure:\n" + nfe, 
					Toast.LENGTH_SHORT).show();
			nfe.printStackTrace();
		}
		
		int diastolicInt = 0;
		
		try {
			diastolicInt = Integer.parseInt(diastolicEditText.getText().toString());
			
		} catch (NumberFormatException nfe) {
			Toast.makeText(getActivity(), "Error: Diastolic Pressure:\n" + nfe, 
					Toast.LENGTH_SHORT).show();
			nfe.printStackTrace();
		}
		
		int pulseInt = 0;
		
		try {
			pulseInt = Integer.parseInt(pulseEditText.getText().toString());
			
		} catch (NumberFormatException nfe) {
			Toast.makeText(getActivity(), "Error: Pulse Rate:\n" + nfe, 
					Toast.LENGTH_SHORT).show();
			nfe.printStackTrace();
		}
        
        StringBuilder systolicSB = new StringBuilder(Integer.toString(systolicInt));
        systolicSB.append(DOUBLE_LINE_BREAK);
        String systolicString = systolicSB.toString();        
        
        StringBuilder diastolicSB = new StringBuilder(Integer.toString(diastolicInt));
        diastolicSB.append(DOUBLE_LINE_BREAK);
        String diastolicString = diastolicSB.toString();        
        
        StringBuilder pulseSB = new StringBuilder(Integer.toString(pulseInt));
        pulseSB.append(DOUBLE_LINE_BREAK);
        String pulseString = pulseSB.toString();
        
        switch (view.getId()) {
	        case R.id.input_reading_button:
	        	if (systolicEditText.getText().toString().isEmpty() || systolicEditText.getText().toString() == null) {
	        		Toast.makeText(getActivity(), "Error: Systolic Pressure must not be empty or null.", 
	    					Toast.LENGTH_SHORT).show();
	    			
	    		} else if (diastolicEditText.getText().toString().isEmpty() || diastolicEditText.getText().toString() == null) {
	    			Toast.makeText(getActivity(), "Error: Diastolic Pressure must not be empty or null.", 
	    					Toast.LENGTH_SHORT).show();
	    			
	    		} else if (pulseEditText.getText().toString().isEmpty() || pulseEditText.getText().toString() == null) {
	    			Toast.makeText(getActivity(), "Error: Pulse Rate must not be empty or null.", 
	    					Toast.LENGTH_SHORT).show();
	    			
	    		} else if (systolicInt < 10 || systolicInt > 250) {
	    			Toast.makeText(getActivity(), "Error: Systolic Pressure of " + systolicInt + 
	    					" mm Hg is out of range.", Toast.LENGTH_SHORT).show();
	        		
	        	} else if (diastolicInt < 10 || diastolicInt > 200) {
	        		Toast.makeText(getActivity(), "Error: Diastolic Pressure of " + diastolicInt + 
	        				" mm Hg is out of range.", Toast.LENGTH_SHORT).show();
	        		
	        	} else if (pulseInt < 10 || pulseInt > 300) {
	        		Toast.makeText(getActivity(), "Error: Pulse Rate of " + pulseInt + 
	        				" bpm is out of range.", Toast.LENGTH_SHORT).show();
					
				} else {
					try {			
						dbHelper.createEntry(time, systolicString, diastolicString, pulseString);					
						((MainActivity)getActivity()).onNavigationDrawerItemSelected(1);
						
					} catch (Exception e) {
						didItWork = false;
						String error = e.toString();
						Toast.makeText(getActivity(), "Input failed.\n" + error, Toast.LENGTH_SHORT).show();
						
					} finally {
						if (didItWork) {
							Toast.makeText(getActivity(), "Data uploaded", Toast.LENGTH_SHORT).show();
						}
					}
				}
				
				break;
		}
	}
}
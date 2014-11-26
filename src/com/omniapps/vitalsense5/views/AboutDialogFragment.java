package com.omniapps.vitalsense5.views;

import com.omniapps.vitalsense5.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutDialogFragment extends DialogFragment {
	public static final String ARG_MENU_ITEM_NUMBER = "menu_item_number";

    public AboutDialogFragment() {
    	// Empty constructor required for DialogFragment
	}
    
    static AboutDialogFragment newInstance() {
        return new AboutDialogFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_dialog_box, container);
        getDialog().setTitle("About Vitalsense");
        
        return view;
    }
}
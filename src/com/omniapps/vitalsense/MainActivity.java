package com.omniapps.vitalsense;

import com.omniapps.vitalsense.views.AboutDialogFragment;
import com.omniapps.vitalsense.views.GraphFragment;
import com.omniapps.vitalsense.views.InputFragment;
import com.omniapps.vitalsense.views.LogOutFragment;
import com.omniapps.vitalsense.views.ResultsFragment;
import com.omniapps.vitalsense.views.SettingsFragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        
        // update the main content by replacing fragments
    	Fragment newFragment = null;
        Bundle args = new Bundle();        

        switch (position) {
			case 0:
				newFragment = new InputFragment();
				args.putInt(InputFragment.ARG_MENU_ITEM_NUMBER, position);
				break;
				
			case 1:
				newFragment = new ResultsFragment();
				args.putInt(ResultsFragment.ARG_MENU_ITEM_NUMBER, position);
				break;
				
			case 2:
				newFragment = new GraphFragment();
				args.putInt(GraphFragment.ARG_MENU_ITEM_NUMBER, position);
				break;
				
			case 3:
				newFragment = new SettingsFragment();
				args.putInt(SettingsFragment.ARG_MENU_ITEM_NUMBER, position);
				break;
				
			case 4:
				openDialog();
				args.putInt(AboutDialogFragment.ARG_MENU_ITEM_NUMBER, position);
				break;
				
			case 5:
				newFragment = new LogOutFragment();
				args.putInt(LogOutFragment.ARG_MENU_ITEM_NUMBER, position);
				//view = inflater.inflate(R.layout.log_out_view, container, false);
				break;
        }
        
        if (newFragment == null) {
        	newFragment = createFragment();
        }
        
        newFragment.setArguments(args);
        
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {    	
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
            
        } else {
            super.onBackPressed();
        }
    }
    
    private void openDialog() {
    	DialogFragment aboutDialog = new AboutDialogFragment();
    	aboutDialog.show(getFragmentManager(), "About Dialog");
	}
    
    private Fragment createFragment() {
    	Fragment fragment = new Fragment();
    	
		return fragment;
	}
}

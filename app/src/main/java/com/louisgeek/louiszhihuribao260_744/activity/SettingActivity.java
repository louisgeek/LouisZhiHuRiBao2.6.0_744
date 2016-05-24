package com.louisgeek.louiszhihuribao260_744.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.custom.LouisAppCompatActivity;

public class SettingActivity extends LouisAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_rl, new SettingPreferenceFragement());
        fragmentTransaction.commit();//

    }

    public static class SettingPreferenceFragement extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_setting);
        }
    }
}

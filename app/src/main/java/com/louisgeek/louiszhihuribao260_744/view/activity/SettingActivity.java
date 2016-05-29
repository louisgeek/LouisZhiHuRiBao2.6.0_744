package com.louisgeek.louiszhihuribao260_744.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.view.common.LouisBaseAppCompatActivity;
import com.louisgeek.louiszhihuribao260_744.view.custom.MyCheckBoxPreference;
import com.louisgeek.louiszhihuribao260_744.view.custom.MyPreference;

public class SettingActivity extends LouisBaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_setting);
        initToolbar();
        configFragment();
    }

    private void configFragment() {
        //
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_fl_4_fragment, new SettingPreferenceFragement());
        fragmentTransaction.commit();//
    }

    private void initToolbar() {
    /*单独使用   Toolbar toolbar = (Toolbar) findViewById(R.id.id_tb_bar);

       // Set an OnMenuItemClickListener to handle menu item clicks
       toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
               // Handle the menu item
               return true;
           }
       });

       // Inflate a menu to be displayed in the toolbar
       toolbar.inflateMenu(R.menu.toolbar_menu);*/

        Toolbar idtbbar = (Toolbar) findViewById(R.id.id_tb_bar);
        idtbbar.setTitle("设置");
        // idtbbar.setPopupTheme(R.style.ToolbarPopupTheme_black);
//        idtbbar.setSubtitle("Subtitle");
//        idtbbar.setLogo(R.mipmap.ic_launcher);
        //  idtbhome.setNavigationIcon(R.mipmap.logo);


        setSupportActionBar(idtbbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true); //设置返回键可用
        actionBar.setDisplayShowHomeEnabled(true);//使左上角图标是否显示 // 使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME

        actionBar.setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标// 决定左上角图标的左侧是否有向左的小箭头, true 有小箭头，并且图标可以点击
        //actionBar.setDefaultDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initContentViewLayoutID() {
        super.setLayoutID(R.layout.activity_setting);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndConfigAfterViewInited() {

    }

    public static class SettingPreferenceFragement extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_setting);

            MyCheckBoxPreference  checkbox_preference_wifi= (MyCheckBoxPreference) findPreference("checkbox_preference_wifi");
            MyCheckBoxPreference  checkbox_preference_nopic= (MyCheckBoxPreference) findPreference("checkbox_preference_nopic");
            MyCheckBoxPreference  checkbox_preference_big= (MyCheckBoxPreference) findPreference("checkbox_preference_big");
            MyCheckBoxPreference  checkbox_preference_msg= (MyCheckBoxPreference) findPreference("checkbox_preference_msg");
            MyCheckBoxPreference  checkbox_preference_share= (MyCheckBoxPreference) findPreference("checkbox_preference_share");

            MyPreference preference_clear= (MyPreference) findPreference("preference_clear");
            MyPreference preference_update= (MyPreference) findPreference("preference_update");

            preference_clear.setOnPreferenceClickListener(this);
            preference_update.setOnPreferenceClickListener(this);


            checkbox_preference_wifi.setOnPreferenceChangeListener(this);
            checkbox_preference_nopic.setOnPreferenceChangeListener(this);
            checkbox_preference_big.setOnPreferenceChangeListener(this);
            checkbox_preference_msg.setOnPreferenceChangeListener(this);
            checkbox_preference_share.setOnPreferenceChangeListener(this);

            /*//程序获取android实现的保存状态的SharedPreferences值 【方法1】
            SharedPreferences sp_wifi = checkbox_preference_wifi.getSharedPreferences();
            checkbox_preference_wifi.setChecked(sp_wifi.getBoolean("checkbox_preference_wifi", false));

            SharedPreferences sp_nopic = checkbox_preference_nopic.getSharedPreferences();
            checkbox_preference_nopic.setChecked(sp_nopic.getBoolean("checkbox_preference_nopic", false));

            SharedPreferences sp_big = checkbox_preference_big.getSharedPreferences();
            checkbox_preference_big.setChecked(sp_big.getBoolean("checkbox_preference_big", false));

            SharedPreferences sp_msg = checkbox_preference_msg.getSharedPreferences();
            checkbox_preference_msg.setChecked(sp_wifi.getBoolean("checkbox_preference_msg", false));*/

           /* // 程序获取android实现的保存状态的SharedPreferences值 【方法2】
            // 得到我们的存储Preferences值的对象，然后对其进行相应操作
            SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(this);
            boolean apply_wifiChecked = shp.getBoolean("apply_wifi", false);*/

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
           switch (preference.getKey()){
               case "checkbox_preference_wifi":
                   Toast.makeText(getActivity(), "checkbox_preference_wifi newValue:"+newValue, Toast.LENGTH_SHORT).show();
                   break;
               case "checkbox_preference_nopic":
                   Toast.makeText(getActivity(), "checkbox_preference_nopic newValue:"+newValue, Toast.LENGTH_SHORT).show();
                   break;
               case "checkbox_preference_big":
                   Toast.makeText(getActivity(), "checkbox_preference_big newValue:"+newValue, Toast.LENGTH_SHORT).show();
                   break;
               case "checkbox_preference_msg":
                   Toast.makeText(getActivity(), "checkbox_preference_msg newValue:"+newValue, Toast.LENGTH_SHORT).show();
                   break;
               case "checkbox_preference_share":
                   Toast.makeText(getActivity(), "checkbox_preference_share newValue:"+newValue, Toast.LENGTH_SHORT).show();
                   break;
           }
            return true;
           // return false;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            switch (preference.getKey()){
                case "preference_clear":
                    Toast.makeText(getActivity(), "preference_clear click", Toast.LENGTH_SHORT).show();
                    break;
                case "preference_update":
                    Toast.makeText(getActivity(), "preference_update click", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
            // return false;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

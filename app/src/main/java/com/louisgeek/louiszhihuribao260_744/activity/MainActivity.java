package com.louisgeek.louiszhihuribao260_744.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.adapter.MyBaseAdapter;
import com.louisgeek.louiszhihuribao260_744.adapter.MyBaseExpandableListAdapter;
import com.louisgeek.louiszhihuribao260_744.bean.ClassifyBean;
import com.louisgeek.louiszhihuribao260_744.bean.NewsBean;
import com.louisgeek.louiszhihuribao260_744.bean.NewsDateBean;
import com.louisgeek.louiszhihuribao260_744.custom.LouisAppCompatActivity;
import com.louisgeek.louiszhihuribao260_744.info.Constants;
import com.louisgeek.louiszhihuribao260_744.tool.InfoHolderSingletonTool;
import com.louisgeek.louiszhihuribao260_744.util.ThemeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends LouisAppCompatActivity {

    private static final String TAG = "MainActivity";

    DrawerLayout mIddrawer;
    MenuItem mTheLastSelectedMenuItem;
    SliderLayout mSliderLayout;
    Toolbar idtbbar;
    ExpandableListView expandableListView;
    MyBaseExpandableListAdapter myBaseExpandableListAdapter;
    LinearLayout id_ll_top_indicatorGroup;

    private int indicatorGroupHeight;
    private int indicatorGroupId = -1;


    List<ClassifyBean> classifyBeanList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏窗口写在了基类aty里 在这里报错
        setContentView(R.layout.activity_main);





        id_ll_top_indicatorGroup = (LinearLayout) findViewById(R.id.id_ll_top_indicatorGroup);
//=================================
        idtbbar = (Toolbar) findViewById(R.id.id_tb_bar);
        TextView idtv4bar = (TextView) findViewById(R.id.id_tv_4bar);
        idtv4bar.setVisibility(View.GONE);

        idtbbar.setTitle("首页");
//        idtbbar.setSubtitle("Subtitle");
//        idtbbar.setLogo(R.mipmap.ic_launcher);
        //  idtbhome.setNavigationIcon(R.mipmap.logo);


        setSupportActionBar(idtbbar);

      /*
       换成三条线  这里就不需要了
       ActionBar actionBar = getSupportActionBar();
        //actionBar.setDefaultDisplayHomeAsUpEnabled(true);//这里无效
        actionBar.setHomeButtonEnabled(true); //设置返回键可用
         actionBar.setDisplayHomeAsUpEnabled(true);*/


        /**
         * If you are calling  setSupportActionBar() you don't need to use toolbar.inflateMenu() because the Toolbar is acting as your ActionBar. All menu related callbacks are via the default ones. The only time you need to call toolbar.inflateMenu() is when you are using the Toolbar as a standalone widget. In this case you will also have to handle menu item click events via
         */
        // idtbhome.inflateMenu(R.menu.toolbar_menu);
        //用onCreateOptionsMenu   getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        //放在setSupportActionBar之后有效
        idtbbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.id_item_message:
                        Toast.makeText(MainActivity.this, "id_item_message", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.id_item_changeTheme:
                        ThemeUtil.changeTheme(getApplicationContext());
                        //Toast.makeText(MainActivity.this, "id_item_changeTheme", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_item_setting:
                        Intent intent=new Intent(MainActivity.this,SettingActivity.class);
                        MainActivity.this.startActivity(intent);
                        //Toast.makeText(MainActivity.this, "id_item_setting", Toast.LENGTH_SHORT).show();
                        break;
                }


                return false;
            }
        });


        mIddrawer = (DrawerLayout) findViewById(R.id.id_drawer);
       /* NavigationView idnv = (NavigationView) findViewById(R.id.id_nv);
        idnv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //存放点击的item 进行取消选中
                if (mTheLastSelectedMenuItem != null) {
                    mTheLastSelectedMenuItem.setChecked(false);
                }
                menuItem.setChecked(true);
                mTheLastSelectedMenuItem = menuItem;
                mIddrawer.closeDrawers();

                //分组头不能被点击
                switch (menuItem.getItemId()) {

                    case R.id.mg:
                        Toast.makeText(MainActivity.this, "mg", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.xl:
                        Toast.makeText(MainActivity.this, "xl", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });*/


        ListView id_drawer_list = (ListView) findViewById(R.id.id_drawer_list);

        View drawer_header_view = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);

        id_drawer_list.addHeaderView(drawer_header_view);
        MyBaseAdapter adapter = new MyBaseAdapter(this, classifyBeanList);
        adapter.setOnDingYue(new MyBaseAdapter.OnDingYue() {
            @Override
            public boolean dingyue(int pos) {
                Toast.makeText(MainActivity.this, "dingyue" + pos, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        id_drawer_list.setAdapter(adapter);
        id_drawer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, mIddrawer, idtbbar, R.string.drawer_open, R.string.drawer_close);
        abdt.syncState();


        mIddrawer.addDrawerListener(abdt);

        //================================
        View listview_header = LayoutInflater.from(this).inflate(R.layout.listview_header, null);
        mSliderLayout = (SliderLayout) listview_header.findViewById(R.id.id_slider);
        initSliderLayout();

        //=================================
        List<NewsDateBean> newsDateList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsDateBean newsDate = new NewsDateBean();
            newsDate.setDateStr("1992-06-29 18:54:3" + i);

            List<NewsBean> newsBeanList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                NewsBean newsBean = new NewsBean();
                newsBean.setTitle(i + "标题" + j);
                newsBean.setContent(i + "内容" + j);
                newsBeanList.add(newsBean);
            }

            newsDate.setNewsBeanList(newsBeanList);
            newsDateList.add(newsDate);
        }

        expandableListView = (ExpandableListView) findViewById(R.id.id_elv);

        myBaseExpandableListAdapter = new MyBaseExpandableListAdapter(this, newsDateList);

        expandableListView.setAdapter(myBaseExpandableListAdapter);

        //默认展开
        for (int i = 0; i < myBaseExpandableListAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, "childPosition" + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // DO NOTHING
                // Toast.makeText(MainActivity.this, "groupPosition"+groupPosition, Toast.LENGTH_SHORT).show();
                return true;//消费掉事件
                // return false;
            }
        });

        expandableListView.addHeaderView(listview_header);

        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                //ExpandableListView expandableListView= (ExpandableListView) view;

                /**
                 * calculate point (0,0)
                 */
                int npos = view.pointToPosition(0, 0);// 其实就是firstVisibleItem
                Log.d(TAG, "onScroll: npos" + npos);
                if (npos != AdapterView.INVALID_POSITION) {// 如果第一个位置值不无效
                    long pos = expandableListView.getExpandableListPosition(npos);
                    int childPos = ExpandableListView.getPackedPositionChild(pos);// 获取第一行child的id
                    int groupPos = ExpandableListView.getPackedPositionGroup(pos);// 获取第一行group的id
                    Log.d(TAG, "onScroll: childPos"+childPos);
                    Log.d(TAG, "onScroll: groupPos"+groupPos);
                    if (childPos == AdapterView.INVALID_POSITION) {//
                        View groupView = expandableListView.getChildAt(npos
                                - expandableListView.getFirstVisiblePosition());// 第一行的view
                        indicatorGroupHeight = groupView.getHeight();// 获取group的高度
                    }
                    // get an error data, so return now
                    if (indicatorGroupHeight == 0) {
                        Log.d(TAG, "onScroll: indicatorGroupHeight ERROR");
                        return;
                    }
                    //第一个不显示
                    if (groupPos == AdapterView.INVALID_POSITION) {
                        //2016年5月24日09:10:36
                        idtbbar.setTitle("首页");
                        id_ll_top_indicatorGroup.setVisibility(View.GONE);
                        return;
                    }

                    Log.d(TAG, "onScroll: indicatorGroupId"+indicatorGroupId);
                    // update the data of indicator group view
                    if (groupPos != indicatorGroupId) {// 如果指示器显示的不是当前group
                    /*myBaseExpandableListAdapter.getGroupView(groupPos, expandableListView.isGroupExpanded(groupPos),
                            id_ll_top_indicatorGroup.getChildAt(0), null);// 将指示器更新为当前group*/

                        indicatorGroupId = groupPos;



                        // mAdapter.hideGroup(indicatorGroupId); // we set this group view
                        // to be hided
                        // 为此指示器增加点击事件
                   /* id_ll_top_indicatorGroup.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            expandableListView.collapseGroup(indicatorGroupId);
                        }
                    });*/

                        id_ll_top_indicatorGroup.setVisibility(View.GONE);
                    } else {
                        id_ll_top_indicatorGroup.setVisibility(View.GONE);
                        //###2016年5月24日09:11:08 【group停留】 为了显示在标题栏，注释了
                        //###id_ll_top_indicatorGroup.setVisibility(View.VISIBLE);
                    }
                    // Log.d(TAG, "bind to new group,group position = " + groupPos);
                    NewsDateBean newsdate = (NewsDateBean) myBaseExpandableListAdapter.getGroup(indicatorGroupId);
                    if (newsdate != null) {
                            /*
                            //###2016年5月24日09:11:08 【group停留】
                            TextView tv = (TextView) id_ll_top_indicatorGroup.getChildAt(0);
                            tv.setText(newsdate.getDateStr());*/

                        //2016年5月24日09:10:36
                        idtbbar.setTitle(newsdate.getDateStr());

                    }

                    if (indicatorGroupId == -1) // 如果此时group的id无效，则返回
                    {
                        return;
                    }

                    int showHeight = calculateShowHeight();

                    // update group position
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) id_ll_top_indicatorGroup
                            .getLayoutParams();
                    layoutParams.topMargin = -(indicatorGroupHeight - showHeight);
                    id_ll_top_indicatorGroup.setLayoutParams(layoutParams);
                }
            }

            /**
             * calculate point (0,indicatorGroupHeight) 往上推出的效果
             */
            private int calculateShowHeight() {
                int showHeight = indicatorGroupHeight;
                int nEndPos = expandableListView.pointToPosition(0, indicatorGroupHeight);// 第二个item的位置
                if (nEndPos != AdapterView.INVALID_POSITION) {// 如果无效直接返回
                    long pos = expandableListView.getExpandableListPosition(nEndPos);
                    int groupPos = ExpandableListView.getPackedPositionGroup(pos);// 获取第二个group的id
                    if (groupPos != indicatorGroupId) {// 如果不等于指示器当前的group
                        View viewNext = expandableListView.getChildAt(nEndPos - expandableListView.getFirstVisiblePosition());
                        showHeight = viewNext.getTop();
                    }
                }
                return showHeight;
            }
        });

      /*  if (listView.getChildAt(0).getTop() < 0) {
            int firstCompletelyVisiblePos = listView.getFirstVisiblePosition() + 1;
        }
*/


        //===============================
        for (int i = 0; i < 10; i++) {
            ClassifyBean classifyBean = new ClassifyBean();
            classifyBean.setClassifyTitle("菜单" + i);
            classifyBean.setHasHolder(true);
            classifyBeanList.add(classifyBean);
        }
    }




    private void initSliderLayout() {
       /* HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
*/
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal", R.mipmap.ic_launcher);
        file_maps.put("Big Bang Theory", R.mipmap.logo);
        file_maps.put("House of Cards", R.mipmap.ic_launcher);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Toast.makeText(MainActivity.this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);
        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Log.d(TAG, "Page Changed: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mSliderLayout.stopAutoCycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       // setMenuBackgroundAndTextColor();

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
        // return super.onCreateOptionsMenu(menu);
    }
    /** 设置Menu的背景图 */
    protected void setMenuBackgroundAndTextColor() {
       LayoutInflater layoutInflater=getLayoutInflater();
        layoutInflater.setFactory(new LayoutInflater.Factory()
        {

            @Override
            public View onCreateView(String name, Context context,
                                     AttributeSet attrs)
            {
                System.out.println(name);
                if (name.equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")
                        || name.equalsIgnoreCase("com.android.internal.view.menu.ActionMenuItemView"))
                {
                    try
                    {
                        LayoutInflater f = getLayoutInflater();
                        final View view = f.createView(name, null, attrs);
                        System.out.println((view instanceof TextView));
                        if(view instanceof TextView){

                            String nowThemeValue= InfoHolderSingletonTool.getInstance().getMapObj(Constants.INFOHOLDER_NOW_THEME_KEY).toString();
                            if (nowThemeValue.equals(Constants.THEME_DEFAULT_THEMEFLAG)) {
                                ((TextView)view).setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.items_menu_text_color));
                            }else if (nowThemeValue.equals(Constants.THEME_BLACK_THEMEFLAG)) {
                                ((TextView)view).setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.items_menu_text_color_black));
                            }


                        }else{
                            new Handler().post(new Runnable() {
                                public void run() {
                                    String nowThemeValue= InfoHolderSingletonTool.getInstance().getMapObj(Constants.INFOHOLDER_NOW_THEME_KEY).toString();
                                    if (nowThemeValue.equals(Constants.THEME_DEFAULT_THEMEFLAG)) {
                                        //设置背景
                                        view.setBackgroundResource(R.color.items_menu_bg_color);
                                    }else if (nowThemeValue.equals(Constants.THEME_BLACK_THEMEFLAG)) {
                                        //设置背景
                                        view.setBackgroundResource(R.color.items_menu_bg_color_black);
                                    }



                                }
                            });
                        }
                        return view;
                    } catch (InflateException e)
                    {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                return null;
            }

        });

    }

/*
采用 ActionBarDrawerToggle 关联iddrawer和idtbbar 实现三条线和返回箭头动态变化
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            // 将Drawer与Android home点击事件绑定到一起
            case android.R.id.home: {
                iddrawer.openDrawer(GravityCompat.START);
                break;
            }
        }
        return true;
        //return super.onOptionsItemSelected(item);
    }*/
}

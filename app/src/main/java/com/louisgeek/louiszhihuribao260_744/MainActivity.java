package com.louisgeek.louiszhihuribao260_744;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DrawerLayout mIddrawer;
    MenuItem mTheLastSelectedMenuItem;
    SliderLayout mSliderLayout;
    Toolbar idtbbar;
    ExpandableListView expandableListView;
    MyBaseExpandableListAdapter myBaseExpandableListAdapter;
    LinearLayout  id_ll_top_indicatorGroup;

    private int  indicatorGroupHeight;
    private int  indicatorGroupId=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);//报错
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//还要加上 <item name="android:windowActionBar">false</item>
        setContentView(R.layout.activity_main);

         id_ll_top_indicatorGroup= (LinearLayout) findViewById(R.id.id_ll_top_indicatorGroup);
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

                switch (item.getItemId()){
                    case R.id.id_item01:
                        Toast.makeText(MainActivity.this, "id_item01", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_item02:
                        Toast.makeText(MainActivity.this, "id_item02", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_item03:
                        Toast.makeText(MainActivity.this, "id_item03", Toast.LENGTH_SHORT).show();
                        break;
                }


                return false;
            }
        });


        mIddrawer = (DrawerLayout) findViewById(R.id.id_drawer);
        NavigationView idnv = (NavigationView) findViewById(R.id.id_nv);
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
        });


        ActionBarDrawerToggle abdt=new ActionBarDrawerToggle(this,mIddrawer,idtbbar,R.string.drawer_open,R.string.drawer_close);
        abdt.syncState();


        mIddrawer.addDrawerListener(abdt);

        //================================
       View listview_header  =  LayoutInflater.from(this).inflate(R.layout.listview_header,null);
        mSliderLayout= (SliderLayout) listview_header.findViewById(R.id.id_slider);
       initSliderLayout();

        //=================================
        List<NewsDate>  newsDateList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsDate newsDate=new NewsDate();
            newsDate.setDateStr("1992-06-29 18:54:3"+i);

            List<NewsBean> newsBeanList=new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                NewsBean newsBean=new NewsBean();
                newsBean.setTitle(i+"标题"+j);
                newsBean.setContent(i+"内容"+j);
                newsBeanList.add(newsBean);
            }

            newsDate.setNewsBeanList(newsBeanList);
            newsDateList.add(newsDate);
        }

        expandableListView= (ExpandableListView) findViewById(R.id.id_elv);

         myBaseExpandableListAdapter=new MyBaseExpandableListAdapter(this,newsDateList);

        expandableListView.setAdapter(myBaseExpandableListAdapter);

        //默认展开
        for (int i = 0; i <myBaseExpandableListAdapter.getGroupCount() ; i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, "childPosition"+childPosition, Toast.LENGTH_SHORT).show();
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
                if (npos != AdapterView.INVALID_POSITION)// 如果第一个位置值无效
                {
                long pos = expandableListView.getExpandableListPosition(npos);
                int childPos = ExpandableListView.getPackedPositionChild(pos);// 获取第一行child的id
                int groupPos = ExpandableListView.getPackedPositionGroup(pos);// 获取第一行group的id
                if (childPos == AdapterView.INVALID_POSITION) {// 第一行不是显示child,就是group,此时没必要显示指示器
                    View groupView = expandableListView.getChildAt(npos
                            - expandableListView.getFirstVisiblePosition());// 第一行的view
                    indicatorGroupHeight = groupView.getHeight();// 获取group的高度
                   //### id_ll_top_indicatorGroup.setVisibility(View.GONE);// 隐藏指示器
                } else {
                   //### id_ll_top_indicatorGroup.setVisibility(View.VISIBLE);// 滚动到第一行是child，就显示指示器
                }
                // get an error data, so return now
                if (indicatorGroupHeight == 0) {
                    return;
                }
                // update the data of indicator group view
                if (groupPos != indicatorGroupId) {// 如果指示器显示的不是当前group
                    /*myBaseExpandableListAdapter.getGroupView(groupPos, expandableListView.isGroupExpanded(groupPos),
                            id_ll_top_indicatorGroup.getChildAt(0), null);// 将指示器更新为当前group*/
                    indicatorGroupId = groupPos;
                    Log.d(TAG,  "bind to new group,group position = " + groupPos);
                   NewsDate newsdate= (NewsDate) myBaseExpandableListAdapter.getGroup(groupPos);
                    if (newsdate!=null) {
                        TextView tv = (TextView) id_ll_top_indicatorGroup.getChildAt(0);
                        tv.setText(newsdate.getDateStr());
                    }
                // mAdapter.hideGroup(indicatorGroupId); // we set this group view
                // to be hided
                // 为此指示器增加点击事件
                   /* id_ll_top_indicatorGroup.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            expandableListView.collapseGroup(indicatorGroupId);
                        }
                    });*/

                    id_ll_top_indicatorGroup.setVisibility(View.GONE);
                    }
                    else {
                    id_ll_top_indicatorGroup.setVisibility(View.VISIBLE);
                    }

                    if (indicatorGroupId == -1) // 如果此时grop的id无效，则返回
                { return;}
/**
 * calculate point (0,indicatorGroupHeight) 往上推出的效果
 */
                int showHeight =calculateShowHeight();

                // update group position
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) id_ll_top_indicatorGroup
                        .getLayoutParams();
                layoutParams.topMargin = -(indicatorGroupHeight - showHeight);
                id_ll_top_indicatorGroup.setLayoutParams(layoutParams);
            }
            }
            private int calculateShowHeight() {
                int showHeight = indicatorGroupHeight;
                int nEndPos = expandableListView.pointToPosition(0, indicatorGroupHeight);
                if (nEndPos != AdapterView.INVALID_POSITION) {
                    long pos = expandableListView.getExpandableListPosition(nEndPos);
                    int groupPos = ExpandableListView.getPackedPositionGroup(pos);
                    if (groupPos != indicatorGroupId) {
                        View viewNext = expandableListView.getChildAt(nEndPos - expandableListView.getFirstVisiblePosition());
                        showHeight = viewNext.getTop();
                    }
                }
                return showHeight;
            }


            private int calculateShowHeightTwo() {
                int showHeight = indicatorGroupHeight;
                int nEndPos = expandableListView.pointToPosition(0, indicatorGroupHeight);// 第二个item的位置
                if (nEndPos != AdapterView.INVALID_POSITION)// 如果无效直接返回
                {
                long pos2 = expandableListView.getExpandableListPosition(nEndPos);
                int groupPos2 = ExpandableListView.getPackedPositionGroup(pos2);// 获取第二个group的id
                if (groupPos2 != indicatorGroupId) {// 如果不等于指示器当前的group
                    View viewNext = expandableListView.getChildAt(nEndPos
                            - expandableListView.getFirstVisiblePosition());
                    showHeight = viewNext.getTop();
                    Log.e(TAG,  "update the show part height of indicator group:"
                            + showHeight);
                }
                }
                return showHeight;
            }


        });

      /*  if (listView.getChildAt(0).getTop() < 0) {
            int firstCompletelyVisiblePos = listView.getFirstVisiblePosition() + 1;
        }
*/
    }

    private void initSliderLayout() {
       /* HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
*/
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.mipmap.ic_launcher);
        file_maps.put("Big Bang Theory",R.mipmap.logo);
        file_maps.put("House of Cards",R.mipmap.ic_launcher);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Toast.makeText(MainActivity.this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

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
                Log.d(TAG, "Page Changed: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
       // return super.onCreateOptionsMenu(menu);
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

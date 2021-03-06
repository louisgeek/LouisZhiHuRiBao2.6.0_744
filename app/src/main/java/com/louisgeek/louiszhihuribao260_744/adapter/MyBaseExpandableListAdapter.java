package com.louisgeek.louiszhihuribao260_744.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.model.bean.NewsBean;
import com.louisgeek.louiszhihuribao260_744.model.bean.NewsDateBean;
import com.louisgeek.louiszhihuribao260_744.app.LouisApplication;
import com.louisgeek.louiszhihuribao260_744.info.Constants;
import com.louisgeek.louiszhihuribao260_744.tool.HolderSingletonTool;
import com.zhy.changeskin.SkinManager;

import java.util.List;

/**
 * Created by louisgeek on 2016/5/23.
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    public MyBaseExpandableListAdapter(Context context, List<NewsDateBean> newsDateList) {
        mContext = context;
        mNewsDateList = newsDateList;

        //UPDATE
        NewsDateBean newsDate= mNewsDateList.get(0);
        newsDate.setDateStr("今日新闻");
        mNewsDateList.set(0,newsDate);
    }

    private Context mContext;
    private List<NewsDateBean> mNewsDateList;

    @Override
    public int getGroupCount() {
        return mNewsDateList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mNewsDateList.get(groupPosition).getNewsBeanList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mNewsDateList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mNewsDateList.get(groupPosition).getNewsBeanList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupView groupView ;
        if (convertView==null){
            groupView=new GroupView();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.main_list_group_item,parent,false);
            groupView.date= (TextView) convertView.findViewById(R.id.date);

            convertView.setTag(R.id.tag_viewholder,groupView);

        }else{
            groupView= (GroupView) convertView.getTag(R.id.tag_viewholder);
        }
        //2016年5月24日11:43:54  滚动时候换肤仍然有效
        SkinManager.getInstance().injectSkin(convertView);

        NewsDateBean newsDate= mNewsDateList.get(groupPosition);


            groupView.date.setText(newsDate.getDateStr());
            convertView.setTag(R.id.tag_group_text,newsDate.getDateStr());



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildView childView ;
        if (convertView==null){
            childView=new ChildView();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.main_list_child_item,parent,false);
            childView.title= (TextView) convertView.findViewById(R.id.title);
            childView.content= (TextView) convertView.findViewById(R.id.content);

            childView.id_cardview= (CardView) convertView.findViewById(R.id.id_cardview);

            convertView.setTag(R.id.tag_viewholder,childView);
        }else{
            childView= (ChildView) convertView.getTag(R.id.tag_viewholder);
        }
        //2016年5月24日11:43:54  滚动时候换肤仍然有效
        SkinManager.getInstance().injectSkin(convertView);
        initCardViewSkin(childView.id_cardview);

        NewsDateBean newsDate= mNewsDateList.get(groupPosition);
       // convertView.setTag(R.id.tag_group_text,newsDate.getDateStr());

        NewsBean newsBean= newsDate.getNewsBeanList().get(childPosition);
        childView.title.setText(newsBean.getTitle());
        childView.content.setText(newsBean.getContent());


        return convertView;
    }

    private void initCardViewSkin(CardView cardView) {
        String nowThemeValue= HolderSingletonTool.getInstance().getMapObj(Constants.INFOHOLDER_NOW_THEME_KEY).toString();
        if (nowThemeValue.equals(Constants.THEME_DEFAULT_THEMEFLAG)) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(LouisApplication.getAppContext(), R.color.items_child_cardview_bg_color));
        }else if (nowThemeValue.equals(Constants.THEME_BLACK_THEMEFLAG)) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(LouisApplication.getAppContext(), R.color.items_child_cardview_bg_color_black));
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class GroupView{
        private TextView date;
    }

    class ChildView{
        private TextView title;
        private TextView content;
        private CardView id_cardview;
    }
}

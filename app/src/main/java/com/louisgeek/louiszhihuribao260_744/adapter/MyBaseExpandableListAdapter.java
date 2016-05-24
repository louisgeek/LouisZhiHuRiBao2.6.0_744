package com.louisgeek.louiszhihuribao260_744.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.bean.NewsBean;
import com.louisgeek.louiszhihuribao260_744.bean.NewsDateBean;
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.group_item,parent,false);
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.child_item,parent,false);
            childView.title= (TextView) convertView.findViewById(R.id.title);
            childView.content= (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(R.id.tag_viewholder,childView);
        }else{
            childView= (ChildView) convertView.getTag(R.id.tag_viewholder);
        }
        //2016年5月24日11:43:54  滚动时候换肤仍然有效
        SkinManager.getInstance().injectSkin(convertView);

        NewsDateBean newsDate= mNewsDateList.get(groupPosition);
       // convertView.setTag(R.id.tag_group_text,newsDate.getDateStr());

        NewsBean newsBean= newsDate.getNewsBeanList().get(childPosition);
        childView.title.setText(newsBean.getTitle());
        childView.content.setText(newsBean.getContent());


        return convertView;
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
    }
}

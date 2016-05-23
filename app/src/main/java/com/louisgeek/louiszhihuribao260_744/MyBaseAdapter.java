package com.louisgeek.louiszhihuribao260_744;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by louis on 2016/5/23.
 */
public class MyBaseAdapter extends BaseAdapter {

    private Context context;

    public MyBaseAdapter(Context context, List<ClassifyBean> classifyBeanList) {
        this.context = context;
        this.classifyBeanList = classifyBeanList;
    }

    private List<ClassifyBean> classifyBeanList;
    @Override
    public int getCount() {
        return classifyBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return classifyBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.left_listview_item,parent,false);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.id_lv_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(classifyBeanList.get(position).getClassifyTitle());
        return convertView;
    }

    class  ViewHolder{
        TextView textView;
    }
}

package com.louisgeek.louiszhihuribao260_744.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.louisgeek.louiszhihuribao260_744.R;
import com.louisgeek.louiszhihuribao260_744.bean.ClassifyBean;

import java.util.List;

/**
 * Created by louis on 2016/5/23.
 */
public class MyBaseAdapter extends BaseAdapter {

    private Context context;

    public void setOnDingYue(OnDingYue onDingYue) {
        this.onDingYue = onDingYue;
    }

    OnDingYue onDingYue;
    public MyBaseAdapter(Context context, List<ClassifyBean> classifyBeanList) {
        this.context = context;
        this.classifyBeanList = classifyBeanList;
    }

    private List<ClassifyBean> classifyBeanList;

    private  final int ITEM_HOME=0;
    private  final  int ITEM_NORMAL=1;

    private int[] itemTypes=new int[]{ITEM_HOME,ITEM_NORMAL};
    @Override
    public int getCount() {
        return classifyBeanList.size();
    }



    @Override
    public int getViewTypeCount() {
        return itemTypes.length;
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if (position==0){
            return  ITEM_HOME;
        }else{
            return  ITEM_NORMAL;
        }

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        int itemType=getItemViewType(position);
        switch (itemType){
            case ITEM_HOME:

                HomeViewHolder homeViewHolder;
                if (convertView==null){
                    homeViewHolder=new HomeViewHolder();
                    convertView= LayoutInflater.from(context).inflate(R.layout.left_list_item_home,parent,false);
                    homeViewHolder.homeTv= (TextView) convertView.findViewById(R.id.id_lv_tv);
                    convertView.setTag(homeViewHolder);
                }else{
                    homeViewHolder= (HomeViewHolder) convertView.getTag();
                }
                homeViewHolder.homeTv.setText(classifyBeanList.get(position).getClassifyTitle());

                break;
            case ITEM_NORMAL:

                ViewHolder viewHolder;
                if (convertView==null){
                    viewHolder=new ViewHolder();
                    convertView= LayoutInflater.from(context).inflate(R.layout.left_list_item,parent,false);
                    viewHolder.textView= (TextView) convertView.findViewById(R.id.id_lv_tv);
                    viewHolder.id_iv_dy= (ImageView) convertView.findViewById(R.id.id_iv_dy);
                    viewHolder.id_iv_dy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //
                            onDingYue.dingyue(position);
                        }
                    });
                    convertView.setTag(viewHolder);
                }else{
                    viewHolder= (ViewHolder) convertView.getTag();
                }
                viewHolder.textView.setText(classifyBeanList.get(position).getClassifyTitle());

                break;
        }

        return convertView;
    }

    class  HomeViewHolder{
        TextView homeTv;
    }
    class  ViewHolder{
        TextView textView;
        ImageView id_iv_dy;
    }

   public interface  OnDingYue{
        boolean dingyue(int pos);
    }


}

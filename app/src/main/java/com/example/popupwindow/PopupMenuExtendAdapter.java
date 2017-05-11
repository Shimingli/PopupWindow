package com.example.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author shiming
 * @time 2017/5/11 19:08
 * @desc  可以动态的设置很多
 */

public class PopupMenuExtendAdapter extends BaseAdapter {
    private Context mContext;
    private List<PopupItem> mPopupItemList;
    private final LayoutInflater mInflater;

    public PopupMenuExtendAdapter(Context context, List<PopupItem> popupItemList) {
        mContext = context;
        mPopupItemList = popupItemList;
        //通过这个方法去拿到 infalter
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPopupItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPopupItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder cache;
        if (convertView == null) {
             cache =  new ViewHolder();
            convertView = mInflater.inflate(R.layout.nim_popup_menu_list_item, null);
            cache.icon = (ImageView) convertView.findViewById(R.id.popup_menu_icon);
            cache.title = (TextView) convertView.findViewById(R.id.popup_menu_title);
            convertView.setTag(cache);
        } else {
           cache = (ViewHolder) convertView.getTag();
        }
        PopupItem item = mPopupItemList.get(position);
        cache.title.setText(item.getTitle());
        if (item.getIcon() != 0) {
            cache.icon.setVisibility(View.VISIBLE);
            cache.icon.setImageResource(item.getIcon());
        } else {
            cache.icon.setVisibility(View.GONE);
        }
        // 下面代码实现数据绑定
        return convertView;
    }

    private final class ViewHolder {

        public ImageView icon;

        public TextView title;
    }

}

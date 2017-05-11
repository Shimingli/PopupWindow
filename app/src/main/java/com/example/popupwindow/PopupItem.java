package com.example.popupwindow;

import android.content.Context;

/**
 * @author shiming
 * @time 2017/5/11 18:37
 * @desc  可以扩展的item
 */

public class PopupItem {
    private String title;
    private int tag;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private int icon;
    private Context context;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public PopupItem(int tag, String title) {
        this.tag = tag;
        this.title = title;
    }
    public PopupItem(Context context,String title, int tag, int icon) {
        this(tag,title);
        this.icon=icon;
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.example.popupwindow;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

/**
 * @author shiming
 * @time 2017/5/11 19:21
 * @desc
 */

public class ExtendPopupWindow {

    private Context context;

    private List<PopupItem> items;

    private PopupMenuExtendAdapter adapter;
    //当扩张到一定的地步是不是可以滑动
    private boolean scroll = false;

    private MenuItemClickListener listener;

    public PopupWindow popWindow;

    private View rootView;

    public ExtendPopupWindow(Context context, List<PopupItem> items,  MenuItemClickListener listener) {
        this(context,items,false,listener);
    }

    public ExtendPopupWindow(Context context, List<PopupItem> items,  boolean scroll, MenuItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.scroll = scroll;
        this.listener = listener;
        init();
    }

    private void init() {
        if (rootView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.nim_popup_menu_layout, null);
            ListView listView = (ListView) rootView.findViewById(R.id.popmenu_listview);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (listener != null) {
                        popWindow.dismiss();
                        listener.onItemClick(items.get(position));
                    }
                }
            });
            adapter = new PopupMenuExtendAdapter(context, items);
            listView.setAdapter(adapter);
        }
        // focusableInTouchMode.这个属性的意思一如字面所述,就是在进入触摸输入模式后,该控件是否还有获得焦点的能力.
        // 可以简单的理解为,用户一旦开始通过点击屏幕的方式输入,手机就进入了"touch mode".
        // focusableInTouchMode这种属性,多半是设给EditText这种即使在TouchMode下,依然需要获取焦点的控件
        rootView.setFocusableInTouchMode(true);
        //监听返回的按钮，然后 隐藏pop
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && popWindow.isShowing()
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    popWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        //初始化我们的popuwindow
        if (popWindow == null) {
            popWindow = new PopupWindow(context);
            popWindow.setContentView(rootView);
            popWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
            if (scroll) {
                popWindow.setHeight(context.getApplicationContext().getResources().getDisplayMetrics().heightPixels * 2 / 3);
            } else {
                popWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            }
            popWindow.setTouchable(true);
            popWindow.setBackgroundDrawable(new BitmapDrawable());
            popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                }
            });
        }
    }
    public void notifyData() {
        adapter.notifyDataSetChanged();
    }

    public void show(View v) {
        if (popWindow == null) {
            return;
        }
        if (popWindow.isShowing()) {
            popWindow.dismiss();
        } else {
            if (scroll) {// 当可以滚动时，横竖屏切换时，重新确定高度
                Configuration configuration = context.getResources().getConfiguration();
                int ori = configuration.orientation;
                if (ori == Configuration.ORIENTATION_LANDSCAPE) {
                    popWindow.setHeight(context.getApplicationContext().getResources().getDisplayMetrics().widthPixels* 2 / 3);
                } else {
                    popWindow.setHeight(context.getApplicationContext().getResources().getDisplayMetrics().heightPixels  * 2 / 3);
                }
            }
            popWindow.setFocusable(true);
            popWindow.showAsDropDown(v, -10, 0);
        }
    }

    public boolean isShowing() {
        return popWindow != null && popWindow.isShowing();
    }

    public void dissmiss() {
        if (isShowing())
            popWindow.dismiss();
    }
    public interface MenuItemClickListener {
         void onItemClick(PopupItem item);
    }

}

package com.example.popupwindow;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PopupItem> menuItemList;
    private ExtendPopupWindow popupMenu;
    private View mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImg = findViewById(R.id.img);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuptWindow(MainActivity.this, mImg);
            }
        });
    }

    /**
     * 窗体泄漏造成的原因，是我们的view虽然已经有了这个，但是我们在没有通过点击事件
     * 或者其他的动作，去开启这个popwindow的话，我们的窗体就会泄漏
     * 哈哈
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
//        initPopuptWindow(this, mImg);
    }

    private void initPopuptWindow(Context context, View view) {
        if (popupMenu == null) {
            menuItemList = new ArrayList<>();
            popupMenu = new ExtendPopupWindow(context, menuItemList, listener);
        }
        menuItemList.clear();
        menuItemList.addAll(getMoreMenuItems(context));
        popupMenu.notifyData();
        popupMenu.show(view);
    }

    private static List<PopupItem> getMoreMenuItems(Context context) {
        List<PopupItem> moreMenuItems = new ArrayList<PopupItem>();
        moreMenuItems.add(new PopupItem(context,
                "feiji  0", 1,0));
        moreMenuItems.add(new PopupItem(context,
                "feiji  1", 2,0));
        moreMenuItems.add(new PopupItem(context,
                "feiji  2", 3,0));
        moreMenuItems.add(new PopupItem(context,
                "feiji  3", 4,0));
        return moreMenuItems;
    }

    private ExtendPopupWindow.MenuItemClickListener listener = new ExtendPopupWindow.MenuItemClickListener() {

        @Override
        public void onItemClick(final PopupItem item) {
            switch (item.getTag()) {
                case 1:
                    Toast.makeText(MainActivity.this, "feiji  0",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(MainActivity.this, "feiji  1",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(MainActivity.this, "feiji  2",Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(MainActivity.this, "feiji  3",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}

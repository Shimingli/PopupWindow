package com.example.popupwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author shiming
 * @time 2017/5/8 19:56
 * @desc popup  Menu  为了可以扩展 这里继承了 listview
 */

public class PopupMenuExtendListview extends ListView {

    public PopupMenuExtendListview(Context context) {
        this(context, null);
    }

    public PopupMenuExtendListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 1.View本身大小多少，这由onMeasure()决定；
     * 2.View在ViewGroup中的位置如何，这由onLayout()决定；
     * 3.绘制View，onDraw()定义了如何绘制这个View。
     */
    /**
     * View本身大小多少，这由onMeasure()决定
     * @param widthMeasureSpec 代模式的32 位
     * @param heightMeasureSpec  代模式的32位
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidthByChilds() + getPaddingLeft() + getPaddingRight();
        //重新的测量这个 精确的模式下面
        super.onMeasure(MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY), heightMeasureSpec);
    }

    /**
     * 测量listview中的孩子的宽度
     *
     * @return width
     */
    private int measureWidthByChilds() {
        int width = 0;
        View view = null;
        for (int i = 0; i < getAdapter().getCount(); i++) {
            view = getAdapter().getView(i, view, this);
            if (view != null) {
                //为什么要传入的是0
                // *设置与此视图相关联的布局参数。这些供应
                //*参数，以指定此视图应该如何
                // *设置。有许多子viewgroup.layoutparams，这些
                // *对应的ViewGroup负责不同的子类
                view.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
                // MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，
                // 一般都是父控件是AdapterView，通过measure方法传入的模式。
                view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                if (view.getMeasuredWidth()>width){
                    width=view.getMeasuredWidth();
                }
            }
        }
        return width;
    }
}

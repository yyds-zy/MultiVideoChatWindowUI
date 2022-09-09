package com.yyds.multichatlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MultiVideoChatLayout extends ViewGroup {

    public static final String TAG = "MultiVideoChatLayout";
    private int mScreenWidth;
    private int mScreenHeight;
    //人数为2时的宽度
    private int mSizeModelWidthFew;
    //人数为2时的高度
    private int mSizeModelHeightFew;
    //人数为3时的宽度
    private int mSizeModelWidthMany;
    //人数为3时的高度
    private int mSizeModelHeightMany;
    //人数为4、5、6时的宽度
    private int mSizeModelWidthThree;
    //人数为4、5、6时的高度
    private int mSizeModelHeightThree;

    public MultiVideoChatLayout(Context context) {
        super(context);
    }

    public MultiVideoChatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public MultiVideoChatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        mSizeModelWidthFew = mScreenWidth / 2;
        mSizeModelHeightFew = (9 * mSizeModelWidthFew / 16);

        mSizeModelWidthThree = mScreenWidth / 3;
        mSizeModelHeightThree = (9 * mSizeModelWidthThree / 16);

        mSizeModelWidthMany = mScreenWidth / 4;
        mSizeModelHeightMany = (9 * mSizeModelWidthMany / 16);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽度默认给屏幕的宽度，高度直接取屏幕高度
        final int width = MeasureSpec.makeMeasureSpec(mScreenWidth, MeasureSpec.EXACTLY);
        final int height = MeasureSpec.makeMeasureSpec(mScreenHeight, MeasureSpec.EXACTLY);
        setMeasuredDimension(width, height);
        Log.d(TAG,"width: " + width + " + height:" + height);
        // 1 2
        final int childWidthFew = MeasureSpec.makeMeasureSpec(mSizeModelWidthFew, MeasureSpec.EXACTLY);
        final int childHeightFew = MeasureSpec.makeMeasureSpec(mSizeModelHeightFew, MeasureSpec.EXACTLY);
        // 3
        final int childWidthThree = MeasureSpec.makeMeasureSpec(mSizeModelWidthThree, MeasureSpec.EXACTLY);
        final int childHeightThree = MeasureSpec.makeMeasureSpec(mSizeModelHeightThree, MeasureSpec.EXACTLY);
        // 4 5 6
        final int childWidthMany = MeasureSpec.makeMeasureSpec(mSizeModelWidthMany, MeasureSpec.EXACTLY);
        final int childHeightMany = MeasureSpec.makeMeasureSpec(mSizeModelHeightMany, MeasureSpec.EXACTLY);

        if (getChildCount() > 2) {
            if (getChildCount() == 3) {
                for (int i = 0; i < getChildCount(); i++) {
                    View childAt = getChildAt(i);
                    childAt.measure(childWidthThree, childHeightThree);
                }
            } else {
                for (int i = 0; i < getChildCount(); i++) {
                    View childAt = getChildAt(i);
                    childAt.measure(childWidthMany, childHeightMany);
                }
            }
        } else {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                childAt.measure(childWidthFew, childHeightFew);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 2) {
            if (getChildCount() == 3) {
                layoutThreeView();
            } else if (getChildCount() == 4) {
                layoutThreeFour();
            } else {
                layoutModelMany();
            }
        } else {
            layoutModelFew();
        }
    }

    private void layoutModelFew(){
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (i == 0) {
                childAt.layout(0, 60 * 2, mSizeModelWidthFew, mSizeModelHeightFew + 60 * 2);
            } else if (i == 1) {
                childAt.layout(mSizeModelWidthFew, 60 * 2, mSizeModelWidthFew * 2, mSizeModelHeightFew + 60 * 2);
            }
        }
    }

    private void layoutThreeView(){
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (i == 0) {
                childAt.layout(0, 150 * 2, mSizeModelWidthThree, mSizeModelHeightThree + 150 * 2);
            } else if (i == 1) {
                childAt.layout(mSizeModelWidthThree, 150 * 2, mSizeModelWidthThree * 2, mSizeModelHeightThree + 150 * 2);
            } else if (i == 2) {
                childAt.layout(mSizeModelWidthThree * 2, 150 * 2, mSizeModelWidthThree * 3, mSizeModelHeightThree + 150 * 2);
            }
        }
    }

    private void layoutThreeFour(){
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (i == 0) {
                childAt.layout(0, 195 * 2, mSizeModelWidthMany, mSizeModelHeightMany + 195 * 2);
            } else if (i == 1) {
                childAt.layout(mSizeModelWidthMany, 195 * 2, mSizeModelWidthMany * 2, mSizeModelHeightMany + 195 * 2);
            } else if (i == 2) {
                childAt.layout(mSizeModelWidthMany * 2, 195 * 2, mSizeModelWidthMany * 3, mSizeModelHeightMany + 195 * 2);
            } else if (i == 3) {
                childAt.layout(mSizeModelWidthMany * 3, 195 * 2, mSizeModelWidthMany * 4, mSizeModelHeightMany + 195 * 2);
            }
        }
    }

    private void layoutModelMany(){
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (i == 0) {
                childAt.layout(0, 60 * 2, mSizeModelWidthMany, mSizeModelHeightMany + 60 * 2);
            } else if (i == 1) {
                childAt.layout(mSizeModelWidthMany, 60 * 2, mSizeModelWidthMany * 2, mSizeModelHeightMany + 60 * 2);
            } else if (i == 2){
                childAt.layout(mSizeModelWidthMany * 2,60 * 2,mSizeModelWidthMany * 3,mSizeModelHeightMany + 60 * 2);
            } else if (i == 3) {
                childAt.layout(mSizeModelWidthMany * 3,60 * 2,mSizeModelWidthMany * 4,mSizeModelHeightMany + 60 * 2);
            } else if (i == 4) {
                childAt.layout(0,mSizeModelHeightMany + 60 * 2,mSizeModelWidthMany,mSizeModelHeightMany * 2 + 60 * 2);
            } else if (i == 5) {
                childAt.layout(mSizeModelWidthMany,mSizeModelHeightMany + 60 * 2,mSizeModelWidthMany * 2,mSizeModelHeightMany * 2 + 60 * 2);
            }
        }
    }
}

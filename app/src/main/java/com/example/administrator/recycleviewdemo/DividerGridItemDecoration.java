package com.example.administrator.recycleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by apple on 16-6-13.
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] AttRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider; //分割线的drawble

    public DividerGridItemDecoration(Context context) {

        final TypedArray array = context.obtainStyledAttributes(AttRS);  //获取到自定义控件的属性
        mDivider = array.getDrawable(0);    //获取第一个属性值
        array.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {  //绘制水平的分割线

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) { //绘制垂直的分割线
         final int childCount = parent.getChildCount();
        for (int i= 0 ;i<childCount; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom()+params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();

            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);



        }

    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            int spanCount = getSpanCount(parent);

        int childCount = parent.getAdapter().getItemCount();
        if(isLastRaw(parent,itemPosition,spanCount,childCount)){
                outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }else if(isLastColum(parent,itemPosition,spanCount,childCount)){
                outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else {
            outRect.set(0,0,mDivider.getIntrinsicWidth(),mDivider.getIntrinsicHeight());
        }

    }

    private boolean isLastColum(RecyclerView parent, int itemPosition, int spanCount, int childCount) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof  GridLayoutManager){
            if ((spanCount +1)%spanCount==0){
                return  true;
            }
        }else if (layoutManager instanceof  StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if(orientation == StaggeredGridLayoutManager.VERTICAL){
                if ((spanCount+1)%spanCount == 0){
                    return true;

                }
            }else{
                childCount = childCount - childCount%spanCount;
                if (spanCount >- childCount)
            return  true;
            }
        }
        return false;

    }

    private boolean isLastRaw(RecyclerView parent, int itemPosition, int spanCount, int childCount) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            childCount = childCount - childCount%spanCount;
            if(spanCount >= childCount)
                return  true;
        }else if(layoutManager instanceof  StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if(orientation == StaggeredGridLayoutManager.VERTICAL){
                childCount = childCount - childCount%spanCount;
                if(spanCount >= childCount)
                    return true;
            }else{
                if ((spanCount+1)%spanCount ==0 ){
                    return true;
                }
            }
        }
        return  false;
    }

    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }else if(layoutManager instanceof StaggeredGridLayoutManager){
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }


        return  spanCount;

    }



}

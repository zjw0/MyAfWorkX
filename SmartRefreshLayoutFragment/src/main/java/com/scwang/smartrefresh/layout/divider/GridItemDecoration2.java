package com.scwang.smartrefresh.layout.divider;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;

import androidx.core.content.ContextCompat;

@SuppressWarnings("rawtypes")
public class GridItemDecoration2 extends UniversalItemDecoration {

    private Context mContext;
    private BaseQuickAdapter mAdapter;
    private boolean mMargin;
    private int mColor;
    private int mHeight;

    public GridItemDecoration2(Context context, BaseQuickAdapter adapter) {
        this(context, adapter, false);
    }

    public GridItemDecoration2(Context context, BaseQuickAdapter adapter, boolean margin) {
        mContext = context;
        mAdapter = adapter;
        setDecorationMargin(margin);
        setDecorationColor(0xfff5f5f5);
        setDecorationHeight(4.0f);
    }

    /**
     * 设置是否显示边距
     *
     * @param margin
     */
    public void setDecorationMargin(boolean margin) {
        mMargin = margin;
    }

    /**
     * 设置颜色
     *
     * @param colorId
     */
    public void setDecorationColorRes(int colorId) {
        setDecorationColor(ContextCompat.getColor(mContext, colorId));
    }

    /**
     * 设置颜色
     *
     * @param color
     */
    public void setDecorationColor(int color) {
        mColor = color;
    }

    /**
     * 设置高度
     *
     * @param heightId
     */
    public void setDecorationHeightRes(int heightId) {
        mHeight = (int) mContext.getResources().getDimension(heightId);
    }

    /**
     * 设置高度
     *
     * @param height
     */
    public void setDecorationHeight(float height) {
        mHeight = dip2px(mContext, height);
    }

    @Override
    public Decoration getItemOffsets(int position) {
        ColorDecoration decoration = new ColorDecoration();
        decoration.decorationColor = mColor;
        if (isFixedViewType(mAdapter.getItemViewType(position))) {
            decoration.top = 0;
            decoration.bottom = 0;
            decoration.left = 0;
            decoration.right = 0;
        } else {
            position = position - mAdapter.getHeaderLayoutCount();
            if (position % 2 == 0) {
                // 左边
                decoration.top = position == 0 ? (mMargin ? mHeight : 0) : 0;
                decoration.bottom = mHeight;
                decoration.left = mMargin ? mHeight : 0;
                decoration.right = mHeight / 2;
            } else {
                // 右边
                decoration.top = position == 1 ? (mMargin ? mHeight : 0) : 0;
                decoration.bottom = mHeight;
                decoration.left = mHeight / 2;
                decoration.right = mMargin ? mHeight : 0;
            }
        }
        return decoration;
    }

    private boolean isFixedViewType(int type) {
        return type == BaseQuickAdapter.EMPTY_VIEW || type == BaseQuickAdapter.HEADER_VIEW
                || type == BaseQuickAdapter.FOOTER_VIEW || type == BaseQuickAdapter.LOAD_MORE_VIEW;
    }
}

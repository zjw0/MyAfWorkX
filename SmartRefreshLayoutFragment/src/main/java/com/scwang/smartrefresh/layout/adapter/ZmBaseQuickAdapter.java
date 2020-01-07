package com.scwang.smartrefresh.layout.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import zm.bus.event.BusProvider;

/**
 * 带加载更多的BaseQuickAdapter
 */
public abstract class ZmBaseQuickAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder>
        implements LoadMoreModule {

    public ZmBaseQuickAdapter(int layoutResId) {
        super(layoutResId);
        BusProvider.getInstance().register(this);
    }

    public ZmBaseQuickAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
        BusProvider.getInstance().register(this);
    }

}

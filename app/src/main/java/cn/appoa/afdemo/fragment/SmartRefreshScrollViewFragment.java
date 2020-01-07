package cn.appoa.afdemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.fragment.PullToRefreshScrollViewFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appoa.afdemo.R;
import cn.appoa.afdemo.adapter.RefreshBeanAdapter;
import cn.appoa.afdemo.bean.RefreshBean;
import cn.appoa.afdemo.net.API;
import cn.appoa.aframework.okgo.OkGoDatasListener;
import cn.appoa.aframework.okgo.ZmOkGo;

public class SmartRefreshScrollViewFragment extends PullToRefreshScrollViewFragment {

    @Override
    public void initRefreshLayout(Bundle savedInstanceState) {
        setRefreshContentView(R.layout.fragment_refresh_scrollview);
    }

    private ListView lv_refresh;
    private List<RefreshBean> dataList;
    private RefreshBeanAdapter adapter;

    @Override
    public void initRefreshContentView(View view) {
        if (view == null)
            return;
        lv_refresh = (ListView) view.findViewById(R.id.lv_refresh);
        dataList = new ArrayList<>();
        adapter = new RefreshBeanAdapter(getActivity(), dataList, R.layout.item_refresh_bean_list);
        lv_refresh.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onLoadMore() {
        getDataList();
    }

    @Override
    public void initData() {
        getBannerData();
        getCategoryData();
        getData1();
        getData2();
        getData3();
        getDataList();
    }

    /**
     * 轮播
     */
    private void getBannerData() {
    }

    /**
     * 分类
     */
    private void getCategoryData() {
    }

    /**
     * 推荐1
     */
    private void getData1() {
    }

    /**
     * 推荐2
     */
    private void getData2() {
    }

    /**
     * 推荐3
     */
    private void getData3() {
    }

    /**
     * 模拟数据
     */
    private void getDataList() {
        if (pageindex == 1) {
            dataList.clear();
            adapter.setList(dataList);
        }
        Map<String, String> params = new HashMap<>();
        params.put("pageindex", pageindex + "");
        params.put("pagesize", 10 + "");
        showLoading("获取中...");
        ZmOkGo.request(API.faq_list, params).tag(this.getRequestTag())
                .execute(new OkGoDatasListener<RefreshBean>(
                        this, "RefreshScrollViewFragment", RefreshBean.class) {
                    @Override
                    public void onDatasResponse(List<RefreshBean> datas) {
                        isMore = true;
                        dataList.addAll(datas);
                        adapter.setList(dataList);
                    }

                    @Override
                    public void onSuccess(Response<String> responses) {
                        stopRefresh();
                        stopLoadMore();
                        dismissLoading();
                        super.onSuccess(responses);
                    }

                    @Override
                    public void onError(Response<String> error) {
                        stopRefresh();
                        stopLoadMore();
                        dismissLoading();
                        super.onError(error);
                    }
                });
    }

}

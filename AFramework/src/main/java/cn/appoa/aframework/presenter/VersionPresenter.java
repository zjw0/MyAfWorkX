package cn.appoa.aframework.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appoa.aframework.bean.AppVersion;
import cn.appoa.aframework.okgo.OkGoDatasListener;
import cn.appoa.aframework.okgo.ZmOkGo;
import cn.appoa.aframework.view.IBaseView;
import cn.appoa.aframework.view.IVersionView;

public class VersionPresenter extends BasePresenter {

    protected IVersionView mVersionView;

    @Override
    public void onAttach(IBaseView view) {
        if (view instanceof IVersionView) {
            mVersionView = (IVersionView) view;
        }
    }

    @Override
    public void onDetach() {
        if (mVersionView != null) {
            mVersionView = null;
        }
    }

    /**
     * 获取版本信息
     *
     * @param GetAppVersion 接口地址
     */
    public void getVersion(String GetAppVersion) {
        if (mVersionView == null)
            return;
        Map<String, String> params = new HashMap<>();
        ZmOkGo.request(GetAppVersion, params).tag(mVersionView.getRequestTag())
                .execute(new OkGoDatasListener<AppVersion>(
                        mVersionView, "版本更新", AppVersion.class) {
                    @Override
                    public void onDatasResponse(List<AppVersion> datas) {
                        if (datas != null && datas.size() > 0) {
                            mVersionView.setVersion(datas.get(0));
                        }
                    }
                });
    }
}

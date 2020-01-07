package cn.appoa.aframework.net;

/**
 * 网络状态观察者
 */
public interface NetStateChangeObserver {

    void onNetDisconnected();

    void onNetConnected(NetworkType networkType);

}

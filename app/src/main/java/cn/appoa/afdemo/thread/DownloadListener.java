package cn.appoa.afdemo.thread;

/**
 * Author by Jack_Zhao, Date on 2020/6/9.
 * PS: Not easy to write code, please indicate.
 */
public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}

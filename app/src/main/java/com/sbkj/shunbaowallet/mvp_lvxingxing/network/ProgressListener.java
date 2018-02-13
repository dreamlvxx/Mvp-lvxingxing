package com.sbkj.shunbaowallet.mvp_lvxingxing.network;

/**
 * Created by lvxingxing on 2018/2/13.
 *
 * @author lvxingxing
 */

interface ProgressListener {
    /**
     *
     * @param hasWrittenLen 已下载
     * @param totalLen 总程度
     * @param hasFinish 是否下载完成
     */
    void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish);
}

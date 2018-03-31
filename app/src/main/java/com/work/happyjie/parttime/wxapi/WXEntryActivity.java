package com.work.happyjie.parttime.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lib.llj.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.work.happyjie.parttime.consts.GlobalConsts;


/**
 * 微信客户端回调activity示例
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, GlobalConsts.WECHAT_APP_ID, false);
        //微信回调
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * 微信主动请求第三方
     *
     * @param arg0
     */
    @Override
    public void onReq(BaseReq arg0) {
    }

    /**
     * 第三方请求微信
     *
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {
        finish();
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                ToastUtils.showShort("分享成功");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtils.showShort("取消分享");
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtils.showShort("分享拒绝");
                break;
            default:
                ToastUtils.showShort("分享失败" + resp.errCode);
                break;
        }
    }
}

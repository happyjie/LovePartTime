package com.work.happyjie.parttime.tools.share;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.work.happyjie.parttime.consts.GlobalConsts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by chenyk on 2016/5/9.
 * describe the function:分享消息给微信好友或朋友圈
 */
public class ShareMsgToWechat {
    private Activity mContext;
    private IWXAPI api;
    static Bitmap bitmapReturn;


    public ShareMsgToWechat(Activity context) {
        mContext = context;
        // 微信注册初始化,获取IWXAPI实例
        api = WXAPIFactory.createWXAPI(mContext, GlobalConsts.WECHAT_APP_ID, false);
        api.registerApp(GlobalConsts.WECHAT_APP_ID);
    }

    /**
     * 分享网页
     *
     * @param flag (0:分享到微信好友，1：分享到微信朋友圈)
     */
    public void wechatShareWebPage(String urlString, String title, String content, Bitmap thumb, int flag) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = urlString;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = content.length() >= 50 ? content.substring(0, 50) : content;//文本字数限制50以内
        if (thumb != null) {
            msg.thumbData = Util.bmpToByteArray(thumb, true);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    /**
     * 构建一个唯一标志
     *
     * @param type
     * @return
     */
    private static String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : (type + System.currentTimeMillis());
    }


    /**
     * 将网络资源图片转化成bitmap
     *
     * @param url 网络资源图片链接
     */
    public static void GetLocalOrNetBitmap(final String url, final GetBitmapListener listener) {
        new Thread() {
            public void run() {
                bitmapReturn = null;
                //访问网络代码
                Bitmap bitmap;
                InputStream in;
                BufferedOutputStream out;
                try {
                    in = new BufferedInputStream(new URL(url).openStream(), 1024);
                    final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
                    out = new BufferedOutputStream(dataStream, 1024);
                    copy(in, out);
                    out.flush();
                    byte[] data = dataStream.toByteArray();
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    bitmapReturn = Bitmap.createScaledBitmap(bitmap, 120, 120, true);

                    if(null != listener){
                        listener.onSuccess(bitmapReturn);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[1024];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }

    public interface GetBitmapListener{
        public void onSuccess(Bitmap bitmap);
    }
}

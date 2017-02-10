package com.example.miaodj.upppay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.unionpay.UPPayAssistEx;

/**
 * Created by jiejp on 2016/6/6.
 * 银联工具类
 */
public class UnionPayUtil {

    public static final int PLUGIN_VALID = 0;
    public static final int PLUGIN_NOT_INSTALLED = -1;
    public static final int PLUGIN_NEED_UPGRADE = 2;

    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
     *****************************************************************/
    private static String mMode = "01";

    public static void doStartUnionPayPlugin(final Activity activity, String tn){
        doStartUnionPayPlugin(activity, tn, mMode);
    }

    public static void doStartUnionPayPlugin(final Activity activity, String tn, String mode) {
        int ret = UPPayAssistEx.startPay(activity, null, null, tn, mode);
        if (ret == PLUGIN_NEED_UPGRADE || ret == PLUGIN_NOT_INSTALLED) {
            // 需要重新安装控件
//            Log.e(LOG_TAG, " plugin not found or need upgrade!!!");

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("提示");
            builder.setMessage("完成购买需要安装银联支付控件，是否安装？");

            builder.setNegativeButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UPPayAssistEx.installUPPayPlugin(activity);
                            dialog.dismiss();
                        }
                    });

            builder.setPositiveButton("取消",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        }
    }

}

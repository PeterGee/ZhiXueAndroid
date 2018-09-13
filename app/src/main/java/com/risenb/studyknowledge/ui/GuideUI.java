package com.risenb.studyknowledge.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.mutils.banner.BannerUtils;
import com.lidroid.mutils.banner.BaseBannerView;
import com.lidroid.mutils.utils.UIManager;
import com.lidroid.mutils.utils.Utils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.GuideBean;
import com.risenb.studyknowledge.utils.MyConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 *
 * @author Administrator
 */
@ContentView(R.layout.ui_guide)
public class GuideUI extends BaseUI {
    private static final int REQUEST = 112;

    @ViewInject(R.id.vp_guide)
    private ViewPager vp_guide;

    @ViewInject(R.id.ll_guide)
    private LinearLayout ll_guide;

    private List<GuideBean> list = new ArrayList<GuideBean>();

    private BannerUtils<GuideBean> bannerUtils = new BannerUtils<GuideBean>();

    @Override
    protected void back() {
        UIManager.getInstance().popActivity(getClass());
    }

    @Override
    protected void onCreate() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
    }

    @Override
    protected void setControlBasis() {
        setTitle("引导页");
        com.lidroid.mutils.MUtils.getMUtils().machineInformation();
        list.add(new GuideBean(R.drawable.guide1));
        list.add(new GuideBean(R.drawable.guide2));
        list.add(new GuideBean(R.drawable.guide3));
    }

    @Override
    protected void prepareData() {
        if (!isTaskRoot()) {
            finish();
            return;
        }
//        if (!LimitConfig.getLimitConfig().isLimit()) {
//            Intent uninstall_intent = new Intent();
//            uninstall_intent.setAction(Intent.ACTION_DELETE);
//            uninstall_intent.setData(Uri.parse("package:" + getPackageName()));
//            startActivity(uninstall_intent);
//            return;
//        }

        bannerUtils.setActivity(getActivity());
        bannerUtils.setViewPager(vp_guide);
        bannerUtils.setDianGroup(ll_guide);
        bannerUtils.setList(list);
        bannerUtils.setLoop(false);
        bannerUtils.setDefaultImg(R.drawable.banner);
        bannerUtils.setColorTrue(0xffffffff);
        bannerUtils.setColorFalse(0xffffffff);
        bannerUtils.setMarginTrue(Utils.getUtils().getDimen(R.dimen.dm001));
        bannerUtils.setMarginFalse(Utils.getUtils().getDimen(R.dimen.dm003));
        bannerUtils.setDianSize(Utils.getUtils().getDimen(R.dimen.dm020));
        bannerUtils.setBaseBannerView(new BannerView());
        bannerUtils.info();

        if (!isPower()) {
            return;
        }

        if (!application.getOne()) {

//            List<BannerBean> list = new ArrayList<BannerBean>();
//            list.add(new BannerBean());
//            list.add(new BannerBean());
//            list.add(new BannerBean());
//            Intent intent = new Intent(getActivity(), ShowImgUI.class);
//            intent.putExtra("list", (Serializable) list);
//            intent.putExtra("idx", 0);
//            startActivity(intent);

//            Intent intent = new Intent(getActivity(), GoodsArrUI.class);
//            startActivity(intent);

            Intent intent = new Intent(getActivity(), NavigUI.class);
            startActivity(intent);
            back();
        }
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {
        if (!isPowerRequest()) {
            return;
        }
    }

    /**
     * 权限
     */
    private boolean isPower() {
        if (android.os.Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (int i = 0; i < MyConfig.purview.length; i++) {
            boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                    MyConfig.purview[i]) == PackageManager.PERMISSION_GRANTED);
            if (!hasPermission) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限
     */
    private boolean isPowerRequest() {
        if (android.os.Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (int i = 0; i < MyConfig.purview.length; i++) {
            boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                    MyConfig.purview[i]) == PackageManager.PERMISSION_GRANTED);
            if (!hasPermission) {
                //申请权限
                ActivityCompat.requestPermissions(getActivity(), MyConfig.purview, REQUEST);
                return false;
            }
        }
        return true;
    }

    private void getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        startActivity(localIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                //无权限
                makeText("请开启权限");
                getAppDetailSettingIntent();
                System.exit(0);
                return;
            }
        }
        //有权限
        prepareData();
    }

    private class BannerView extends BaseBannerView {
        @Override
        protected View getView(int i) {
            final ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(list.get(i).getDrawable());
            if (i == list.size() - 1) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), NavigUI.class);
                        getActivity().startActivity(intent);
                        back();
                    }
                });
            }
            return imageView;
        }

    }
}

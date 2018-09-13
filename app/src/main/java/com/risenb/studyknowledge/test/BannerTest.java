package com.risenb.studyknowledge.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.mutils.banner.BannerUtils;
import com.lidroid.mutils.banner.BaseBannerView;
import com.lidroid.mutils.banner.FixedSpeedScroller;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;
import com.risenb.studyknowledge.beans.BannerBean;
import com.risenb.studyknowledge.ui.BaseUI;

/**
 * @author 作者: wangjian
 * @version 创建时间：2015年7月14日 下午6:54:45
 * @类说明
 */
public class BannerTest extends BaseUI {
    @ViewInject(R.id.vp_banner)
    private ViewPager vp_banner;

    @ViewInject(R.id.ll_banner)
    private LinearLayout ll_banner;

    @ViewInject(R.id.tv_banner)
    private TextView tv_banner;

    @Override
    protected void back() {

    }

    @Override
    protected void setControlBasis() {

    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    public void banner() {
        List<BannerBean> list = new ArrayList<BannerBean>();
        BannerUtils<BannerBean> bannerUtils = new BannerUtils<BannerBean>();
        bannerUtils.setActivity(getActivity());
        bannerUtils.setViewPager(vp_banner);
        bannerUtils.setDianGroup(ll_banner);
        bannerUtils.setTextView(tv_banner);
        bannerUtils.setList(list);
        bannerUtils.setDefaultImg(R.drawable.banner);
        bannerUtils.setColorTrue(0xffff0000);
        bannerUtils.setColorFalse(0xff00ff00);
        bannerUtils.setBaseBannerView(new BannerView());
        bannerUtils.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }
        });
        bannerUtils.info();
        bannerUtils.start();

        try
        {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            FixedSpeedScroller mScroller = new FixedSpeedScroller(vp_banner.getContext(), new AccelerateInterpolator());
            mField.set(vp_banner, mScroller);
            mScroller.setmDuration(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private class BannerView extends BaseBannerView {
        @Override
        protected View getView(int i) {
            View photoView = LayoutInflater.from(getActivity()).inflate(R.layout.keyboard, null);

            // TextView tv_rice_bing_item = BaseViewHolder.get(photoView, R.id.tv_rice_bing_item);
            // ImageView iv_rice_bing_item = BaseViewHolder.get(photoView, R.id.iv_rice_bing_item);
            //
            // CardListBean bean = myCardBean.getCardInfo().get(i);
            //
            // tv_rice_bing_item.setText(bean.getBannerBeanTitle());
            // bitmapHelp.display(iv_rice_bing_item, bean.getBannerBeanImage(), true);

            return photoView;
        }

    }
}

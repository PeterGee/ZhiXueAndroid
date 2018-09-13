package com.risenb.studyknowledge.ui;

import android.widget.RelativeLayout;

import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.risenb.studyknowledge.R;

@ContentView(R.layout.keyboard)
public class ZFUI extends BaseUI {
    @ViewInject(R.id.back)
    private RelativeLayout back;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected void setControlBasis() {
        setTitle("");
    }

    @Override
    protected void prepareData() {
        // Intent intent = new Intent(getActivity(), ZUI.class);
        // startActivity(intent);
    }

    @Override
    protected void netWork() {

    }

    @Override
    public void onLoadOver() {

    }

    // /** 新闻 */
    // @ViewInject(R.id.rb_news_1)
    // private RadioButton rb_news_1;
    //
    // /** 公告 */
    // @ViewInject(R.id.rb_news_2)
    // private RadioButton rb_news_2;
    //
    // /** 活动 */
    // @ViewInject(R.id.rb_news_3)
    // private RadioButton rb_news_3;
    //
    // /** 赛事 */
    // @ViewInject(R.id.rb_news_4)
    // private RadioButton rb_news_4;
    //
    // /** 论坛 */
    // @ViewInject(R.id.rb_news_5)
    // private RadioButton rb_news_5;
    //
    // private RadioButton[] rb_news;
    //
    // private Fragment[] fragments;
    // private int idx = 0;
    // private int oldIdx = 0;
    //
    // private NewsFragment newsFragment = new NewsFragment();
    // private NoticeFragment noticeFragment = new NoticeFragment();
    // private ActFragment actFragment = new ActFragment();
    // private MatchFragment matchFragment = new MatchFragment();
    // private AbsFragment absFragment = new AbsFragment();
    //
    // @Override
    // protected void back()
    // {
    // exit();
    // }
    //
    // @Override
    // protected void setControlBasis()
    // {
    // setTitle("资讯");
    // rb_news_1.setChecked(true);
    // rb_news = new RadioButton[] { rb_news_1, rb_news_2, rb_news_3, rb_news_4, rb_news_5 };
    // for (int i = 0; i < rb_news.length; i++)
    // {
    // rb_news[i].setOnClickListener(onClick);
    // }
    // }
    //
    // @Override
    // protected void prepareData()
    // {
    // fragments = new Fragment[] { newsFragment, noticeFragment, actFragment, matchFragment, absFragment };
    //
    // FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
    // trx.add(R.id.rl_news, newsFragment);
    // trx.add(R.id.rl_news, noticeFragment);
    // trx.hide(noticeFragment);
    // trx.show(newsFragment).commit();
    //
    // // getSupportFragmentManager().beginTransaction().add(R.id.rl_news, fragments[idx]).show(fragments[idx]).commit();
    // oldIdx = idx;
    // }
    //
    // private OnClickListener onClick = new OnClickListener()
    // {
    // @Override
    // public void onClick(View v)
    // {
    // switch (v.getId())
    // {
    // case R.id.rb_news_1:
    // idx = 0;
    // break;
    // case R.id.rb_news_2:
    // idx = 1;
    // break;
    // case R.id.rb_news_3:
    // idx = 2;
    // break;
    // case R.id.rb_news_4:
    // idx = 3;
    // break;
    // case R.id.rb_news_5:
    // idx = 4;
    // break;
    // }
    // if (oldIdx != idx)
    // {
    // FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
    // trx.hide(fragments[oldIdx]);
    // if (!fragments[idx].isAdded())
    // {
    // trx.add(R.id.rl_news, fragments[idx]);
    // }
    // trx.show(fragments[idx]).commit();
    // oldIdx = idx;
    // }
    // }
    // };
}

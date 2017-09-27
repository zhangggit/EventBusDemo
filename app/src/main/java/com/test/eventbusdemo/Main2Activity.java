package com.test.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.textview2)
    TextView textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        //在接收消息的页面注册
//        EventBus.getDefault().register(this);

    }

    //接收粘性时间传过来的值
//    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
//    public void getStickyData(Bean1 bean1) {
//        Logger.e("接收粘性时间" + bean1.name + bean1.age);
//        textview1.setText(bean1.name + "-----" + bean1.age);
//    }

    public void textview1(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //TextView1发送 子线程发送
                Logger.e("TextView1发送的数据");
                EventBus.getDefault().post(new Bean1("张三", "20"));
                finish();
            }
        }.start();

    }

    public void textview2(View view) {
        //TextView2发送 主线程发送
        Logger.e("TextView2发送的数据");
        EventBus.getDefault().post(new Bean2("李四"));
        finish();
    }
}

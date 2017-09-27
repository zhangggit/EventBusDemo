package com.test.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 多渠道打包在app-build-outputs-apk
 * by zhang-g
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //在接收消息的页面注册
        EventBus.getDefault().register(this);

        //发送一个粘性事件
//        EventBus.getDefault().postSticky(new Bean1("王五", "24"));

        //点击跳转页面
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

    }

    //接收Main2中TextView1传来的值
    @Subscribe(threadMode = ThreadMode.MAIN)  //在主线程接收
    public void getTextView1Data(Bean1 bean1) {
        Logger.e("接收TextView1" + bean1.getName() + bean1.age);
        button.setText(bean1.getName() + bean1.age);
    }

    //接收Main2中TextView2传来的值
    @Subscribe(threadMode = ThreadMode.ASYNC)  //在子线程接收
    public void getTextView2Data(Bean2 bean2) {
        Logger.e("接收TextView2" + bean2.name);
        button.setText(bean2.name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}

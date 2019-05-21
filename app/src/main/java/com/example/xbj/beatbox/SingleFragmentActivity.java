package com.example.xbj.beatbox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        /*为什么需要进行fragment==null的判定
        因为当设备旋转或回收内存时，Android系统会销毁活动而后重建时，会调用onCreate(Bundle)方法
        活动被销毁时，它的FragmentManager会将fragment队列保存下来，这样，当活动重建时，FragmentManager会首先获取保存的队列，然后恢复到原来的状态
        */
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}

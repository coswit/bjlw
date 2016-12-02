package coswit.com.github.bxlw;

import android.app.Activity;
import android.content.Intent;

import coswit.com.github.libs.BaseActivity;

public class MainActivity extends BaseActivity {


    public static void startAction(Activity activity) {
        activity.startActivity(new Intent(activity,MainActivity.class));
        activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }
}

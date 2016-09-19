package com.bxlwt.www.bxlwt.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bxlwt.www.bxlwt.R;
import com.bxlwt.www.bxlwt.Utils.JsonHelper;
import com.bxlwt.www.bxlwt.Utils.Keys;
import com.bxlwt.www.bxlwt.Utils.SharedPreferencesUtil;
import com.bxlwt.www.bxlwt.Utils.ToastUtil;
import com.bxlwt.www.bxlwt.Utils.Utils;
import com.bxlwt.www.bxlwt.base.BaseSetAct;
import com.bxlwt.www.bxlwt.bean.MessageBean;
import com.bxlwt.www.bxlwt.bean.UserInfoBean;
import com.bxlwt.www.bxlwt.http.NetUrls;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhengj on 2016/8/29.
 */
public class SetUserInfoAct extends BaseSetAct {
    private static final int REQUESTCODE_CUTTING = 102;

    Intent mIntent;
    @BindView(R.id.civ_setUserInfo)
    CircleImageView mProfile;
    @BindView(R.id.tv_setUserInfo_nickname)
    TextView mNickname;
    @BindView(R.id.tv_setUserInfo_mobile)
    TextView mMobile;

    public static final int RESULT_PROFILE = 101;
    private Uri mDataUri;
    private String mUserId;
    private ProgressDialog mProgressDialog;
    private UserInfoBean mUserInfoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText("个人资料");
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_set_userinfo, mContainer);
        ButterKnife.bind(this, view);
        mProgressDialog = new ProgressDialog(this);


    }


    @OnClick({R.id.ll_setAct_userInfo_editIcon, R.id.ll_setAct_userInfo_editNickname, R.id.ll_setAct_userInfo_editPhone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setAct_userInfo_editIcon://头像修改
                //上传头像
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_PROFILE);

                break;
            case R.id.ll_setAct_userInfo_editNickname://用户昵称修改
                mIntent = new Intent(mContext, SetEditNickname.class);
                startActivity(mIntent);
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
            case R.id.ll_setAct_userInfo_editPhone://手机号绑定
//                mIntent = new Intent(mContext, SetEditPhone.class);
//                startActivity(mIntent);
//                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
                break;
        }
    }

    /**
     * 上传头像
     *
     * @param file
     */
    private void uploadProfile(File file) {
        if (mUserId != null) {
            mProgressDialog.setMessage("正在上传头像中");
            mProgressDialog.show();
            String requestUrl = NetUrls.HOST + NetUrls.UPDATE_USERINFO + "?" + NetUrls.USER_ID + "=" + mUserId;
            RequestParams requestParams = new RequestParams(requestUrl);
            Logger.d("上传头像" + requestUrl);
            requestParams.setMultipart(true);
            requestParams.addBodyParameter(NetUrls.USER_FACE, file);
            x.http().post(requestParams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        Logger.d(result);
                        MessageBean bean = JsonHelper.json2Bean(result, MessageBean.class);
                        if (bean.getCode() == 200) {
                            ToastUtil.show(mContext, bean.getMsg());
                            Logger.d("上传成功");
                            Glide.with(mContext).load(mDataUri).into(mProfile);
                            getUserInfo();
                        } else {
                            mProgressDialog.dismiss();
                            ToastUtil.show(mContext, bean.getMsg());
                        }
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    ToastUtil.show(mContext, "请检查网路是否连接");
                    mProgressDialog.dismiss();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    mProgressDialog.dismiss();
                }

                @Override
                public void onFinished() {

                }

            });

        }
    }

    private void getUserInfo() {
        if (mUserInfoBean != null) {
            final String requestUrl = NetUrls.HOST + NetUrls.GET_USERINFO + "?" + NetUrls.USER_ID + "=" + mUserInfoBean.getUser_id();
            Logger.d("获取用户信息：" + requestUrl);
            x.http().get(new RequestParams(requestUrl), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result != null) {
                        UserInfoBean userInfoBean = JsonHelper.json2Bean(result, UserInfoBean.class);
                        Logger.d("用户信息获取" + userInfoBean.getMsg());
                        if (userInfoBean.getCode() == 200) {
                            SharedPreferencesUtil.saveString(mContext, Keys.USER_INFO, result);

                        }
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                    ToastUtil.show(mContext, "请检查网路是否连接");
                    mProgressDialog.dismiss();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    mProgressDialog.dismiss();
                }

                @Override
                public void onFinished() {
                    mProgressDialog.dismiss();
                }
            });
        }
    }

    /**
     * 拿到头像的路径上传后进行上传
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_PROFILE && resultCode == RESULT_OK && data != null) {

//            Intent intent = new Intent("com.android.camera.action.CROP");
//            intent.setDataAndType(data.getData(), "image/*");
//            // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
//            intent.putExtra("crop", "true");
//            // aspectX aspectY 是宽高的比例
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 1);
//            // outputX outputY 是裁剪图片宽高
//            intent.putExtra("outputX", 300);
//            intent.putExtra("outputY", 300);
//            intent.putExtra("return-data", true);
//            startActivityForResult(intent, REQUESTCODE_CUTTING);
            //要上传图片的uri
            mDataUri = data.getData();
            Logger.d("头像上传" + mDataUri);
            File file = Utils.getRealPathFromURI(mContext, mDataUri);
            uploadProfile(file);

        }
        if (requestCode ==REQUESTCODE_CUTTING && resultCode == RESULT_OK && data != null) {
            Logger.d(data.getData());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mUserInfoBean = getUserInfoBean();
        if (mUserInfoBean != null) {
            mUserId = mUserInfoBean.getUser_id();
//            String nickname = mUserInfoBean.getData().getNickname();
            String nickname=null;
            try {
                nickname = new String(mUserInfoBean.getData().getNickname().getBytes(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mNickname.setText(nickname);
            mMobile.setText(mUserInfoBean.getData().getMobile());

                Glide.with(mContext).load(mUserInfoBean.getData().getFace()).into(mProfile);
//                Glide.with(mContext).load(mDataUri).into(mProfile);

        }
    }



    /**
     * 显示修改头像的对话框
     */
  /*  protected static Uri tempUri;
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image*//*");
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }
    *//**
     * 裁剪图片方法实现
     *
     * @param uri
     *//*
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image*//*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    *//**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
     *//*
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
//            iv_personal_icon.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了

        String imagePath = Utils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath+"");
        if(imagePath != null){
            // 拿着imagePath上传了
            // ...
        }
    }

*/


}

package com.imooc.dialogdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

class AlertController {
    public  AlertDialog mDialog;
    private Window mWindow;
    public DialogViewHelper mViewHelper;

    public Window getWindow() {
        return mWindow;
    }

    public AlertDialog getDialog() {
        return mDialog;
    }

    public DialogViewHelper getViewHelper() {
        return mViewHelper;
    }

    public AlertController(AlertDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public void setViewHelper(DialogViewHelper viewHelper) {
        this.mViewHelper = viewHelper;
    }

    // 设置文本
    public void setText(int viewId,CharSequence text){
        mViewHelper.setText(viewId,text);
    }
    public <T extends View> T getView(int viewId){
        return (T) mViewHelper.getView(viewId);
    }
    public void setOnclickListener(int viewId, View.OnClickListener onClickListener) {
        mViewHelper.setOnclickListener(viewId,onClickListener);
    }
    public static class AlertParams{
        public Context mContext;
        public int mLayoutId;
        public int mThemeResId;
        public boolean mCancelable;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public View mView;
        // 存放TextView集合
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        // 存放点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        public int mWidth= ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mHeight= ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mAnimations = 0;
        public int mGravity= Gravity.CENTER;

        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }
//
//        public AlertParams(View view, int themeResId) {
//            this.mView = view;
//            this.mViewLayoutResId = themeResId;
//        }
        /**
         * 设置和绑定参数
         * @param mAlert
         */
        public void apply(AlertController mAlert){
            //1.设置布局 DialogHelper
            DialogViewHelper viewHelper = null;
            if (mLayoutId!=0){
                viewHelper = new DialogViewHelper(mContext,mLayoutId);
            }
            if(mView!=null){
                viewHelper = new DialogViewHelper();
                viewHelper.setContentView(mView);
            }
            if (viewHelper==null){
                throw  new IllegalArgumentException("请设置布局setContentView");
            }

            // 给Dialog 设置布局
            mAlert.getDialog().setContentView(viewHelper.getContentView());

            //2.设置文本
            int textSize = mTextArray.size();
            for (int i = 0; i < textSize; i++) {
                viewHelper.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
            }

            //3.设置点击
            int clickSize = mClickArray.size();
            for (int i = 0; i < clickSize; i++) {
                viewHelper.setOnclickListener(mClickArray.keyAt(i),mClickArray.valueAt(i));
            }

            // 给Dialog设置布局
            mAlert.setViewHelper(viewHelper);

            // 4.配置自定义的效果
            Window window =  mAlert.getWindow();
            window.setGravity(mGravity);

            if (mAnimations!=0){
                window.setWindowAnimations(mAnimations);
            }
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
        }
    }
}

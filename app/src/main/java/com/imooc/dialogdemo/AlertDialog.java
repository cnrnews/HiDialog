package com.imooc.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

public class AlertDialog extends Dialog {

    private AlertController mAlert;

    public AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this,getWindow());
    }
    // 设置文本
    public void setText(int viewId,CharSequence text){
       mAlert.setText(viewId,text);
    }
    public <T extends View> T getView(int viewId){
        return (T) mAlert.getView(viewId);
    }

    public void setOnclickListener(int viewId, View.OnClickListener onClickListener) {
        mAlert.setOnclickListener(viewId,onClickListener);
    }

    public static class Builder{

        private AlertController.AlertParams P;
        public Builder(Context context) {
            this(context,R.style.dialog);
        }

        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context,themeResId);
        }

        public Builder setContentView(int layoutId){
            P.mView = null;
            P.mLayoutId = layoutId;
            return this;
        }
        public Builder setContentView(View view){
            P.mLayoutId = 0;
            P.mView = view;
            return this;
        }
        // 设置文本
        public Builder setText(int viewId,CharSequence text){
            P.mTextArray.put(viewId, text);
            return this;
        }
        // 设置点击事件
        public Builder setOnclickListener(int viewId, View.OnClickListener listener){
            P.mClickArray.put(viewId, listener);
            return this;
        }
        public Builder setCancelable(boolean cancelable){
            P.mCancelable = cancelable;
            return this;
        }
        public Builder setOnCancelListener(OnCancelListener onCancelListener){
            P.mOnCancelListener = onCancelListener;
            return this;
        }
        public Builder setOnDismissListener(OnDismissListener onDismissListener){
            P.mOnDismissListener = onDismissListener;
            return this;
        }
        public Builder setOnKeyListener(OnKeyListener onKeyListener){
            P.mOnKeyListener = onKeyListener;
            return this;
        }
        public Builder fullWidth(){
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }
        public Builder fromBottom(boolean isAnimation){
            if (isAnimation){
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 设置Dialog的宽高
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width,int height){
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }
        public Builder addDefaultAnimation(){
            P.mAnimations = R.style.dialog_from_bottom_anim;
            return this;
        }

        /**
         * 设置动画
         * @param styleAnimation
         * @return
         */
        public Builder setAnimation(int styleAnimation){
            P.mAnimations = styleAnimation;
            return this;
        }

        public AlertDialog create(){
            // Context has already been wrapped with the appropriate theme.
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }
        public AlertDialog show(){
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}

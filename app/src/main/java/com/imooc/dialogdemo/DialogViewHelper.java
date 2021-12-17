package com.imooc.dialogdemo;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class DialogViewHelper {

    private View mContentView;
    private SparseArray<WeakReference<View>> mViews;

    public DialogViewHelper(Context context, int layoutResId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutResId,null);
    }

    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    public void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        if (tv!=null){
            tv.setText(text);
        }
    }

    public void setOnclickListener(int viewId, View.OnClickListener onClickListener) {
        TextView tv =getView(viewId);
        if (tv!=null){
            tv.setOnClickListener(onClickListener);
        }
    }
    public  <T extends View> T getView(int viewId){
        WeakReference<View> weakReference = mViews.get(viewId);
        View view = null;
        if (weakReference!=null){
            view= weakReference.get();
        }
        if (view==null){
            view = mContentView.findViewById(viewId);
            mViews.put(viewId,new WeakReference<>(view));
        }
        return (T) view;
    }

    /**
     * 获取mContentView
     * @return
     */
    public View getContentView(){
        return mContentView;
    }
}

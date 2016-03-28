package com.meishengkangle.mskl.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;


/**
 * 
 */
public class CustomLayoutDialog extends Dialog{
    Context context;
    int layoutId;
    public CustomLayoutDialog(Context context) {
        super(context);
        this.context = context;
    }
    /**
     * 自定义主题及布局的构造方法
     * @param context
     * @param theme
     */
    public CustomLayoutDialog(Context context,int theme,int layoutId){
        super(context, theme);
        this.context = context;
        this.layoutId = layoutId;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layoutId);
    }
}

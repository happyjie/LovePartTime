package com.work.happyjie.parttime.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.happyjie.parttime.R;


public class LoadingDialog extends Dialog {
	ImageView animationIV;
	private TextView tvMsg;
	private Context context;

	public LoadingDialog(Context context) {
		super(context);
		this.context = context;
	}

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.context = context;
	}
	
	public void changeLoadingText(String txt){
		this.tvMsg.setText(txt);
	}

	protected void onCreate(Bundle bundle) {

		View view = LayoutInflater.from(getContext()).inflate( R.layout.dialog_loading, null);
		tvMsg = ((TextView) view.findViewById(R.id.tv_message));
		animationIV = ((ImageView) view.findViewById(R.id.animationIV));

		// 加载动画
		Animation loadAnimation = AnimationUtils.loadAnimation(
				context, R.anim.loading_animation);
		// 使用ImageView显示动画
		animationIV.startAnimation(loadAnimation);

		setContentView(view);
		
	}

	public void onWindowFocusChanged(boolean hasFocus) {
		try {
			((AnimationDrawable) this.animationIV.getBackground()).start();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMessage(String msg) {
		this.tvMsg.setText(msg);
	}
}

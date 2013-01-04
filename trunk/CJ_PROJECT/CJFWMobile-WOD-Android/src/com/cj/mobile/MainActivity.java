package com.cj.mobile;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cj.mobile.animation.AnimatedLoadingImageView;
import com.cj.mobile.config.FreshConfig;
import com.cj.mobile.database.PezDatabaseHelper;
import com.cj.mobile.util.CustomMenu.OnMenuItemSelectedListener;
import com.cj.mobile.util.PezAndroidUtil;

/**
 * 메인 어플리케이션
 * 
 * @author 이상용
 * @since 2012. 7. 19.
 * @version 1.0
 * 
 */
public class MainActivity extends PezCommonActivity implements
		OnMenuItemSelectedListener {

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		handler.removeMessages(INTRO);
	}

	/**
	 * 모든 Activity 공통 Activity 복원 시, cookie sync
	 */
	@Override
	protected void onResume() {
		(new Thread() {
			@Override
			public void run() {
				if (FreshConfig.DEVELOPER_MODE) {
					Log.d(FreshConfig.CMTAG, "clear resource!!");
				}
				PezAndroidUtil.clearApplicationCache(getApplicationContext(),
						null);
			}
		}).start();
		dbHelper = new PezDatabaseHelper(this);
		CookieSyncManager.getInstance().startSync();
		super.onResume();
	}

	/**
	 * 모든 Activity 공통 Activity 실행 시, cookie sync manager 생성
	 */
	@Override
	protected void onStart() {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "##### onStart #####");
		}
		super.onStart();
		CookieSyncManager.createInstance(this);
	}

	/**
	 * 모든 Activity 공통 Activity 일시 정지 시, cookie sync 정지
	 */
	@Override
	protected void onPause() {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "##### onPause #####");
		}
		super.onPause();
		CookieSyncManager.getInstance().stopSync();
	}

	/**
	 * 종료시 어플리케이션의 케시를 삭제한다.
	 */
	@Override
	protected void onStop() {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "##### onStop #####");
		}
		super.onStop();
	}

	/*
	 * @Override protected void onDestroy() { super.onDestroy(); }
	 */

	/**
	 * 인트로 생성
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mContext = this;

		try {
			PackageInfo i = getApplicationContext()
					.getPackageManager()
					.getPackageInfo(getApplicationContext().getPackageName(), 0);
			this.AppVersion = i.versionName;
		} catch (NameNotFoundException e) {
		}

		setWebViewSetting();

		header = (RelativeLayout) findViewById(R.id.header);

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

		floatingBtn = (ImageView) findViewById(R.id.floating_button);

		// 에니메이션을 구동하기 위한 이미지뷰를 가져와 애니메이션을 세팅합니다.
		loadingDialog = (AnimatedLoadingImageView) findViewById(R.id.loading_dialog);
		loadingDialog.setImageResource(R.anim.loading_dialog);

		handler.sendEmptyMessageDelayed(INTRO, INTRO_DELAY);
	}

	protected void setWebViewSetting() {
		webView = (WebView) findViewById(R.id.main_webview);
		webView.setWebViewClient(new FreshWayWebView());
		webView.setWebChromeClient(new FreshWayChromeWebView());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.addJavascriptInterface(new Javascript(), "pezjs");
		webView.requestFocus();
		webView.bringToFront();
		webView.loadUrl(WODURLS.INTRO_PAGE_URL);
	}

	/**
	 * 키가 눌렸을 경우 처리
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			handler.sendEmptyMessage(PAGE_BACK);
			return true;
		case KeyEvent.KEYCODE_MENU:
			doMenu();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		doMenu();
		doMenu();
		super.onConfigurationChanged(newConfig);
	}
}

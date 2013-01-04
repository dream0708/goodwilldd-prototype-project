package cj.fwmobile.sfa.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ProgressBar;
import cj.fwmobile.sfa.android.config.FreshConfig;
import cj.fwmobile.sfa.android.db.PezDatabaseHelper;
import cj.fwmobile.sfa.android.pez.PezAndroidCommonActivity;
import cj.fwmobile.sfa.android.pez.PezCommonInterface;

/**
 * 로그인 화면
 * 
 * @author LeeSangYong
 * 
 */
public class LoginActivity extends PezAndroidCommonActivity implements
		PezCommonInterface {
	/**
	 * 로그인 화면 생성
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "--------------- onCreate -----");
		}

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		setWebViewSetting();

		progressBar = (ProgressBar) findViewById(R.id.login_progress_horizontal);
		// setHeader();
		// 세로 고정
		setOrientation(true);
	}

	/**
	 * 상위 webView 를 리턴한다.
	 */
	public WebView getWebView() {
		return webView;
	}

	/**
	 * 환경이 변경 되었을 경우 처리
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "----- onConfigurationChanged -----");
		}
	}

	/**
	 * onResume / onRestart 저장된 상태 값을 복원
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "----- onRestoreInstanceState -----");
		}
	}

	/**
	 * onPause / onStop 환경 및 기타 설정 값 저장
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "----- onSaveInstanceState -----");
		}
	}

	/**
	 * LoginActivty 복원 시
	 */
	@Override
	protected void onResume() {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "----- onResume -----");
		}
		super.onResume();
		// check if GPS enabled
		// if (gps.canGetLocation()) {
		// double latitude = gps.getLatitude();
		// double longitude = gps.getLongitude();
		// if (FreshConfig.DEVELOPER_MODE) {
		// Log.d(FreshConfig.CMTAG, "latitude : " + latitude
		// + " / longitude : " + longitude);
		// }
		// } else {
		// gps.showSettingsAlert();
		// }

		dbHelper = new PezDatabaseHelper(this);
	}

	/**
	 * 키가 눌렸을 경우 처리
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			showLogoutDialog();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 현 Activity를 반환
	 */
	@Override
	public Activity getCurrentActivity() {
		return this;
	}

	/**
	 * webView 설정
	 */
	@Override
	public void setWebViewSetting() {
		webView = (WebView) findViewById(R.id.login_webview);
		webView.setWebViewClient(new FreshWayWebView());
		webView.setWebChromeClient(new FreshWayChromeWebView());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.requestFocus();
		webView.addJavascriptInterface(
				getJavascriptInterfaceInstance(LoginActivity.this),
				"JavascriptInterface");
		webView.loadUrl((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
				: FreshConfig.REAL_URL)
				+ "/mobile/web/sfa/CJFWAuthority/loginPage.fo?currentAppVersion="
				+ getAppVersion());
	}

	/**
	 * 로그인 화면에서 종료 확인 창
	 */
	@Override
	public void showLogoutDialog() {
		new AlertDialog.Builder(this)
				// .setTitle(R.string.application_end_confirm_title)
				.setMessage(R.string.application_end_confirm_text)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								moveTaskToBack(true);
								finish();
							}
						}).setNegativeButton(android.R.string.cancel, null)
				.show();
	}

	/**
	 * 커스텀 메뉴 Show hide 처리 / 로그인 화면에서는 처리하지 않음
	 */
	@Override
	public void doMenu() {
		// do nothing!
	}
}

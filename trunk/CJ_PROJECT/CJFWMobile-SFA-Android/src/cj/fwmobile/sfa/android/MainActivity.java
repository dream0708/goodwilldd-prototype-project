package cj.fwmobile.sfa.android;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import cj.fwmobile.sfa.android.config.FreshConfig;
import cj.fwmobile.sfa.android.db.PezDatabaseHelper;
import cj.fwmobile.sfa.android.pez.PezAndroidCommonActivity;
import cj.fwmobile.sfa.android.pez.PezCommonInterface;
import cj.fwmobile.sfa.android.pez.menu.CustomMenu;
import cj.fwmobile.sfa.android.pez.menu.CustomMenu.OnMenuItemSelectedListener;
import cj.fwmobile.sfa.android.pez.menu.CustomMenuItem;
import cj.fwmobile.sfa.android.util.PezAndroidUtil;

/**
 * 메인 Activity
 * 
 * @author LeeSangYong
 * 
 */
public class MainActivity extends PezAndroidCommonActivity implements
		PezCommonInterface, OnMenuItemSelectedListener {
	public static final int MENU_ITEM_1 = 1;
	public static final int MENU_ITEM_2 = 2;
	public static final int MENU_ITEM_3 = 3;
	public static final int MENU_ITEM_4 = 4;

	public ProgressDialog dialog;
	public boolean isFloatingBtnUsing = false;
	public boolean isFloatingLock = false;

	/**
	 * 생성
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "--------------- onCreate -----");
		}

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		setWebViewSetting();

		progressBar = (ProgressBar) findViewById(R.id.main_progress_horizontal);

		// initialize the menu
		mMenu = new CustomMenu(this, this, getLayoutInflater());
		mMenu.setHideOnSelect(true);
		mMenu.setItemsPerLineInPortraitOrientation(4);
		mMenu.setItemsPerLineInLandscapeOrientation(8);
		// load the menu items
		loadMenuItems(FreshConfig.MAUTH.getValue(PezAndroidUtil
				.getCookie("g_mauth")));

		floatingBtn = (ImageView) findViewById(R.id.floating_button);
		if (PezAndroidUtil.isTablet(getApplicationContext())
				|| !PezAndroidUtil.hasMenuKey(getApplicationContext())) {
			isFloatingBtnUsing = true;
			floatingBtn.setPadding(2, 2, 2, FLOATINGDOWN);
			floatingBtn.setVisibility(View.VISIBLE);
			floatingBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					doMenu();
				}
			});
		}

		final View activityRootView = findViewById(R.id.main_framelayout);
		activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						int heightDiff = activityRootView.getRootView()
								.getHeight() - activityRootView.getHeight();

						int keyboardHeight = 100;
						// 372
						if (PezAndroidUtil.isTablet(getApplicationContext())) {
							keyboardHeight = 350;
						}

						if (FreshConfig.DEVELOPER_MODE) {
							Log.d(FreshConfig.CMTAG,
									"PezAndroidUtil.hasMenuKey(getApplicationContext()) : "
											+ PezAndroidUtil
													.hasMenuKey(getApplicationContext()));
							Log.d(FreshConfig.CMTAG,
									"@activityRootView.getRootView().getHeight() : "
											+ activityRootView.getRootView()
													.getHeight());
							Log.d(FreshConfig.CMTAG,
									"@activityRootView.getHeight() : "
											+ activityRootView.getHeight());
							Log.d(FreshConfig.CMTAG, "@heightDiff : "
									+ heightDiff);
							Log.d(FreshConfig.CMTAG, "@keyboardHeight : "
									+ keyboardHeight);
						}

						if (heightDiff > keyboardHeight) {
							if (floatingBtn.isShown()) {
								isFloatingLock = false;
								floatingBtn.setVisibility(View.INVISIBLE);
								mMenu.hide();
							}
						} else {
							if (isFloatingBtnUsing) {
								if (!isFloatingLock) {
									if (FreshConfig.DEVELOPER_MODE)
										Log.d(FreshConfig.CMTAG, "%%%%%%%%%%1");
									floatingBtn
											.setImageResource(R.drawable.btn_downarr);
									floatingBtn.setPadding(2, 2, 2,
											FLOATINGDOWN);
									floatingBtn.setVisibility(View.VISIBLE);
								}
							}
						}
					}
				});

		// setHeader();
		// 세로 고정 해제
		setOrientation(false);
	}

	@Override
	protected void onStop() {
		super.onStop();
		clearApplicationCache(null);
	}

	/**
	 * 웹뷰 반환
	 */
	public WebView getWebView() {
		return webView;
	}

	/**
	 * 환경이 변할 경우, 메뉴를 다시 그려준다.
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "----- onConfigurationChanged -----");
		}

		doMenu();
		doMenu();
	}

	/**
	 * 저장된 상태값을 북구한다.
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "----- onRestoreInstanceState -----");
		}
	}

	/**
	 * 상태값을 저장한다.
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "----- onSaveInstanceState -----");
		}
	}

	/**
	 * Activity Resume 시
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
			if (mMenu.isShowing()) {
				mMenu.hide();
				doFloatingBtn();
			} else {
				handler.post(new Runnable() {
					@Override
					public void run() {
						Log.d(FreshConfig.CMTAG, "@@@fn_back call@@@");
						webView.loadUrl("javascript:fn_back();");
					}
				});
			}
			return true;
		case KeyEvent.KEYCODE_MENU:
			doMenu();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 자신의 Activity를 반환한다.
	 */
	@Override
	public Activity getCurrentActivity() {
		return this;
	}

	/**
	 * webView를 설정한다
	 */
	@Override
	public void setWebViewSetting() {
		webView = (WebView) findViewById(R.id.main_webview);
		webView.setWebViewClient(new FreshWayWebView());
		webView.setWebChromeClient(new FreshWayChromeWebView());
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.requestFocus();
		webView.addJavascriptInterface(
				getJavascriptInterfaceInstance(MainActivity.this),
				"JavascriptInterface");
		webView.loadUrl((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
				: FreshConfig.REAL_URL) + "/mobile/web/sfa/Main/dispatch.fo");
	}

	/**
	 * 메뉴 선택 시 처리
	 */
	@Override
	public void MenuItemSelectedEvent(CustomMenuItem selection) {
		switch (selection.getId()) {
		case MENU_ITEM_1:
			webView.loadUrl((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
					: FreshConfig.REAL_URL)
					+ "/mobile/web/sfa/Main/dispatch.fo");
			break;
		case MENU_ITEM_2:
			webView.loadUrl((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
					: FreshConfig.REAL_URL)
					+ "/mobile/web/sfa/Main/dvlpCustomerRegStatic.fo");
			break;
		case MENU_ITEM_3:
			webView.loadUrl((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
					: FreshConfig.REAL_URL)
					+ "/mobile/web/sfa/Main/getMarketEnrollStaticPage.fo");
			break;
		case MENU_ITEM_4:
			webView.loadUrl((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
					: FreshConfig.REAL_URL)
					+ "/mobile/web/sfa/Main/settingPage.fo");
			break;
		}

		doFloatingBtn();
	}

	/**
	 * 메뉴 설정
	 */
	private void loadMenuItems(FreshConfig.MAUTH mauth) {
		ArrayList<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		CustomMenuItem cmi = new CustomMenuItem();
		cmi.setCaption(getString(R.string.menu1));
		cmi.setImageResourceId(R.drawable.pez_custom_home_selector);
		cmi.setId(MENU_ITEM_1);
		menuItems.add(cmi);

		switch (mauth) {
		case ALL:
			cmi = new CustomMenuItem();
			cmi.setCaption(getString(R.string.menu2));
			cmi.setImageResourceId(R.drawable.pez_custom_newdata_selector);
			cmi.setId(MENU_ITEM_2);
			menuItems.add(cmi);
			cmi = new CustomMenuItem();
			cmi.setCaption(getString(R.string.menu3));
			cmi.setImageResourceId(R.drawable.pez_custom_data_selector);
			cmi.setId(MENU_ITEM_3);
			menuItems.add(cmi);
			break;

		case WHLS:
			cmi = new CustomMenuItem();
			cmi.setCaption(getString(R.string.menu3));
			cmi.setImageResourceId(R.drawable.pez_custom_data_selector);
			cmi.setId(MENU_ITEM_3);
			menuItems.add(cmi);
			break;
		case DVLP:
			cmi = new CustomMenuItem();
			cmi.setCaption(getString(R.string.menu2));
			cmi.setImageResourceId(R.drawable.pez_custom_newdata_selector);
			cmi.setId(MENU_ITEM_2);
			menuItems.add(cmi);
			break;
		case NONE:
			break;
		}
		cmi = new CustomMenuItem();
		cmi.setCaption(getString(R.string.menu4));
		cmi.setImageResourceId(R.drawable.pez_custom_set_selector);
		cmi.setId(MENU_ITEM_4);
		menuItems.add(cmi);
		if (!mMenu.isShowing())
			try {
				mMenu.setMenuItems(menuItems);
			} catch (Exception e) {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setTitle("Egads!");
				alert.setMessage(e.getMessage());
				alert.show();
			}
	}

	/**
	 * 커스텀 메뉴 토글 처리 및
	 */
	public void doMenu() {
		if (mMenu.isShowing()) {
			mMenu.hide();
			doFloatingBtn();
		} else {
			mMenu.show(findViewById(R.id.any_old_widget));
			doFloatingBtn();
		}
	}

	/**
	 * floating 버튼 처리
	 */
	private void doFloatingBtn() {
		if (floatingBtn.isShown()) {
			switch (floatingBtn.getPaddingBottom()) {
			case FLOATINGUP:
			case FLOATINGUP_ICS:
				if (FreshConfig.DEVELOPER_MODE)
					Log.d(FreshConfig.CMTAG, "%%%%%%%%%%2");
				floatingBtn.setImageResource(R.drawable.btn_downarr);
				floatingBtn.setPadding(2, 2, 2, FLOATINGDOWN);
				break;
			case FLOATINGDOWN:
				if (FreshConfig.DEVELOPER_MODE)
					Log.d(FreshConfig.CMTAG, "%%%%%%%%%%3");
				isFloatingLock = true;
				floatingBtn.setImageResource(R.drawable.btn_opnearr);
				if (!PezAndroidUtil.isTablet(getApplicationContext())
						&& !PezAndroidUtil.hasMenuKey(getApplicationContext())) {
					floatingBtn.setPadding(2, 2, 2, FLOATINGUP_ICS);
				} else {
					floatingBtn.setPadding(2, 2, 2, FLOATINGUP);
				}
				break;
			}
		}
	}

	/**
	 * 메인 화면에서 로그아웃 다이얼로그를 보여준다.
	 */
	public void showLogoutDialog() {
		new AlertDialog.Builder(this)
				// .setTitle(R.string.logout_confirm_title)
				.setMessage(R.string.logout_end_confirm_text)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(MainActivity.this,
										LoginActivity.class);
								startActivity(intent);
								finish();
							}
						}).setNegativeButton(android.R.string.cancel, null)
				.show();
	}

}

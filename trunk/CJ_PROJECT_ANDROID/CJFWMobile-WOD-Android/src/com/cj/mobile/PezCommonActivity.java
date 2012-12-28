package com.cj.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cj.mobile.animation.AnimatedLoadingImageView;
import com.cj.mobile.config.AppSettings;
import com.cj.mobile.config.FreshConfig;
import com.cj.mobile.database.PezDatabaseConstans.TBL_MENUS;
import com.cj.mobile.database.PezDatabaseHelper;
import com.cj.mobile.util.CustomMenu;
import com.cj.mobile.util.CustomMenu.OnMenuItemSelectedListener;
import com.cj.mobile.util.CustomMenuItem;
import com.cj.mobile.util.EncryptionUtil;
import com.cj.mobile.util.PezAndroidUtil;

public abstract class PezCommonActivity extends Activity implements
		Handler.Callback, OnMenuItemSelectedListener {

	static DefaultHttpClient mClient = AppSettings.getClient();

	public PezCommonActivity() {
		handler = new Handler(this);
	}

	protected static final int INTRO = 0;
	protected static final int GO_TO_MAIN = 1;
	protected static final int SETTING_PREV_BTN = 2;
	protected static final int SETTING_NEXT_BTN = 3;
	protected static final int ACTION_CALL = 8;
	protected static final int PAGE_BACK = 9;
	protected static final int APPLICATION_INIT = 10;
	protected static final int WEBVIEW_TOP = 11;
	protected static final int NOT_EXIST_CALL = 81;
	protected static final int GET_APPLICATION_VERSION = 100;
	protected static final int WEBVIEW_LOAD_URL = 101;
	protected static final int CALL_BROWSER = 200;
	protected static final int PASSWORD_ENCRYPTION = 300;
	protected static final int SHOW_LOADING_DIALOG = 400;
	protected static final int HIDE_LOADING_DIALOG = 401;
	protected static final int END_APPLICATION = 900;
	protected static final int END_APPLICATION_INSTANTLY = 901;

	protected static final int INTRO_DELAY = 2000;

	protected Context mContext;
	protected Handler handler;
	protected String AppVersion;
	protected WebView webView;
	protected RelativeLayout header;
	protected TextView title;
	protected TextView prev_btn;
	protected TextView next_btn;
	protected PezDatabaseHelper dbHelper;
	protected CustomMenu mMenu;
	protected AnimatedLoadingImageView loadingDialog;

	protected ImageView floatingBtn;
	public boolean isFloatingBtnUsing = false;
	public boolean isFloatingLock = false;
	public static final int FLOATINGUP = 94;
	public static final int FLOATINGUP_ICS = 164;
	public static final int FLOATINGDOWN = 10;

	public interface WODURLS {
		public static final int NPORT = (FreshConfig.DEVELOPER_MODE ? FreshConfig.DEV_PORT
				: FreshConfig.REAL_PORT);
		public static final String SPORT = ":" + NPORT;
		public static final String INTRO_PAGE_URL = FreshConfig.PREFIX_ASSET
				+ "www/html/intro.html";
		public static final String LOGIN_PAGE_URL = (FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
				: FreshConfig.REAL_URL)
				+ SPORT + "/wod/mobile/web/wod/authority/viewLoginPage.fo";
		public static final String MAIN_PAGE_URL = (FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
				: FreshConfig.REAL_URL)
				+ SPORT + "/wod/mobile/web/wod/authority/main.fo";
		public static final String MENU_INFO_URL = (FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
				: FreshConfig.REAL_URL)
				+ SPORT + "/wod/mobile/web/wod/menus/getQuickMenuList.fo";
	}

	public String getApplicationVersion() {
		return this.AppVersion;
	}

	protected abstract void setWebViewSetting();

	protected final void showAlert(int msg, final Message handlerMessage,
			boolean isCancel) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				webView.getContext());
		builder.setMessage(msg);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (handlerMessage != null)
							handler.sendMessage(handlerMessage);
						dialog.dismiss();
					}
				});
		if (isCancel) {
			builder.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
		}
		AlertDialog dialog = builder.setCancelable(false).create();
		dialog.show();
	}

	protected final void showAlert(String msg, final Message handlerMessage,
			boolean isCancel) {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				webView.getContext());
		builder.setMessage(msg);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (handlerMessage != null)
							handler.sendMessage(handlerMessage);
						dialog.dismiss();
					}
				});
		if (isCancel) {
			builder.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
		}
		AlertDialog dialog = builder.setCancelable(false).create();
		dialog.show();
	}

	protected WebView getWebView() {
		return this.webView;
	}

	protected void doEndApplicationDialog() {
		Message handlerMessage = handler
				.obtainMessage(END_APPLICATION_INSTANTLY);
		showAlert(R.string.endApplication, handlerMessage, true);
	}

	protected void showAlertServerStatusFailure() {
		showAlert(R.string.dialog_http_status_check_failure_msg, null, false);
	}

	public class Javascript {
		/**
		 * Login후 Main으로 이동
		 */
		public void gotoMain() {
			handler.sendEmptyMessage(GO_TO_MAIN);
		}

		/**
		 * 이전 버튼을 설정한다.
		 */
		public void settingPrevBtn(final String headType, final String links,
				final String names) {
			HashMap<String, String> pref = new HashMap<String, String>();
			pref.put("headType", headType);
			pref.put("links", links);
			pref.put("names", names);
			Message msg = handler.obtainMessage(SETTING_PREV_BTN, pref);
			handler.sendMessage(msg);
		}

		/**
		 * 다음 버튼을 설정한다.
		 */
		public void settingNextBtn(final String headType, final String links,
				final String names) {
			HashMap<String, String> pref = new HashMap<String, String>();
			pref.put("headType", headType);
			pref.put("links", links);
			pref.put("names", names);
			Message msg = handler.obtainMessage(SETTING_NEXT_BTN, pref);
			handler.sendMessage(msg);
		}

		public void setApplicationInit() {
			Log.d(FreshConfig.CMTAG, "########################################");
			handler.sendEmptyMessage(APPLICATION_INIT);
		}

		/**
		 * 프로그램 업데이트를 위하여 Browser를 호출한다.
		 */
		public void doUpdateApplication(final String updateUrl) {
			Message msg = handler.obtainMessage(CALL_BROWSER, updateUrl);
			handler.sendMessage(msg);
		}

		/**
		 * app 버전 반환
		 */
		public void getAppVersion() {
			Message msg = handler.obtainMessage(GET_APPLICATION_VERSION,
					getApplicationVersion());
			handler.sendMessage(msg);
		}

		/**
		 * 비밀번호 암호화
		 * 
		 * @param pwd
		 */
		public void passwordEncrytion(final String pwd) {
			Message msg = handler.obtainMessage(PASSWORD_ENCRYPTION, pwd);
			handler.sendMessage(msg);
		}

		/**
		 * 화면 회전 설정 호출
		 * 
		 * @param option
		 */
		public void orientation(String option) {
			PezAndroidUtil.setOrientation(getApplicationContext(),
					"0".equals(option));
		}

		public void endApplication() {
			handler.sendEmptyMessage(END_APPLICATION);
		}

		public void directEndApplication() {
			handler.sendEmptyMessage(END_APPLICATION_INSTANTLY);
		}

		public void showLoadingDialog() {
			handler.sendEmptyMessage(SHOW_LOADING_DIALOG);
		}

		public void hideLoadingDialog() {
			handler.sendEmptyMessage(HIDE_LOADING_DIALOG);
		}
	}

	/**
	 * 웹뷰 공통 설정
	 * 
	 * @author LeeSangYong
	 * 
	 */
	public class FreshWayWebView extends WebViewClient {
		/**
		 * 리소스 로드 시
		 */
		@Override
		public void onLoadResource(WebView view, String url) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "----- onLoadResource -----" + url);
			}
			super.onLoadResource(view, url);
		}

		/**
		 * URL 변경 시
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view,
				final String urlConection) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "----- shouldOverrideUrlLoading -----");
			}

			if (PezAndroidUtil.aviliableCALL(getApplicationContext())) {
				if (urlConection.startsWith("tel:")) {
					Message handlerMessage = handler.obtainMessage(ACTION_CALL,
							urlConection);
					showAlert(R.string.calling_ment, handlerMessage, true);
					return true;
				}
			} else {
				showAlert(R.string.not_exist_call_ment, null, false);
			}

			return super.shouldOverrideUrlLoading(view, urlConection);
		}

		public String convertToString(InputStream inputStream) {
			StringBuffer string = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String line;
			try {
				while ((line = reader.readLine()) != null) {
					string.append(line + "\n");
				}
			} catch (IOException e) {
			}
			return string.toString();
		}

		/**
		 * 페이지 종료 시
		 */
		@Override
		public void onPageFinished(WebView view, String url) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "----- onPageFinished -----" + url);
			}
			CookieSyncManager.getInstance().sync();
			if (title != null)
				title.setText(webView.getTitle());

			handler.sendEmptyMessage(HIDE_LOADING_DIALOG);
			handler.sendEmptyMessage(WEBVIEW_TOP);
			super.onPageFinished(view, url);

		}

		/**
		 * 페이지 시작 시
		 */
		@Override
		public void onPageStarted(final WebView view, final String url,
				Bitmap favicon) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "----- onPageStarted -----" + url);
			}
			settingHeaderInit();
			if (title != null)
				title.setText(webView.getTitle());

			if (mMenu != null && mMenu.isShowing())
				mMenu.hide();

			handler.sendEmptyMessage(SHOW_LOADING_DIALOG);
			super.onPageStarted(view, url, favicon);
		}

		/**
		 * 페이지 로딩 및 서버 오류 발생 시
		 */
		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.e(FreshConfig.CMTAG, "----- onReceivedError -----");
			}

			view.loadUrl(FreshConfig.PREFIX_ASSET + "www/html/error.html");
			showAlertServerStatusFailure();
			webView.setOnKeyListener(new View.OnKeyListener() {

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_BACK:
						webView.goBack();
						return true;
					case KeyEvent.KEYCODE_MENU:
						doMenu();
						return true;
					default:
						return false;
					}

				}
			});
		}
	}

	/**
	 * 웹뷰 설정
	 * 
	 * @author LeeSangYong
	 * 
	 */
	public class FreshWayChromeWebView extends WebChromeClient {

		/**
		 * Progress 바 상태 변경
		 */
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
		}

		/**
		 * Alert Native 구현
		 */
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				final android.webkit.JsResult result) {
			if (!onJsBeforeUnload(view, url, message, result)) {
				new AlertDialog.Builder(view.getContext())
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								}).setCancelable(false).create().show();
			}
			return true;
		}

		/**
		 * Confirm Dialog Native 구현
		 */
		@Override
		public boolean onJsConfirm(WebView view, String url, String message,
				final android.webkit.JsResult result) {
			if (!onJsBeforeUnload(view, url, message, result)) {
				new AlertDialog.Builder(view.getContext())
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								})
						.setNegativeButton(android.R.string.cancel,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.cancel();
									}
								}).setCancelable(false).create().show();
			}
			return true;
		}

		/**
		 * 커스터마이징 해야함
		 */
		@Override
		public boolean onJsPrompt(WebView view, String url, String message,
				String defaultValue, final JsPromptResult result) {
			AlertDialog.Builder builder;
			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.alert_prompt,
					(ViewGroup) findViewById(R.id.layout_root));

			final EditText registeNumber = (EditText) layout
					.findViewById(R.id.promptInputText);

			registeNumber.setInputType(InputType.TYPE_CLASS_NUMBER);

			builder = new AlertDialog.Builder(view.getContext());
			builder.setView(layout);
			builder.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							registeNumber.setText(registeNumber.getText()
									.toString());
							result.confirm(registeNumber.getText().toString());

						}
					});

			builder.setNegativeButton(android.R.string.cancel,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							result.cancel();
						}
					});
			builder.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					Log.d(FreshConfig.CMTAG, "keyCode " + keyCode + " : "
							+ KeyEvent.KEYCODE_ENTER);
					switch (keyCode) {
					case KeyEvent.KEYCODE_0:
					case KeyEvent.KEYCODE_1:
					case KeyEvent.KEYCODE_2:
					case KeyEvent.KEYCODE_3:
					case KeyEvent.KEYCODE_4:
					case KeyEvent.KEYCODE_5:
					case KeyEvent.KEYCODE_6:
					case KeyEvent.KEYCODE_7:
					case KeyEvent.KEYCODE_8:
					case KeyEvent.KEYCODE_9:
					case KeyEvent.KEYCODE_DEL:
						// case KeyEvent.KEYCODE_MINUS:
						return false;
					case KeyEvent.KEYCODE_ENTER:
						registeNumber.setText(registeNumber.getText()
								.toString());
						result.confirm(registeNumber.getText().toString());
						return true;
					default:
						return true;
					}
				}
			});
			builder.setCancelable(false);
			AlertDialog dialog = builder.create();
			dialog.setOnShowListener(new OnShowListener() {

				@Override
				public void onShow(DialogInterface dialog) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.showSoftInput(registeNumber,
							InputMethodManager.SHOW_IMPLICIT);
				}
			});
			dialog.show();
			return true;
		}
	}

	public void doMenu() {
		if (mMenu != null)
			if (mMenu.isShowing()) {
				mMenu.hide();
				doFloatingBtn();
			} else {
				mMenu.show(findViewById(R.id.any_old_widget));
				doFloatingBtn();
			}
	}

	public boolean handleMessage(Message msg) {

		switch (msg.what) {
		case INTRO:
			getWebView().loadUrl(WODURLS.LOGIN_PAGE_URL);
			break;
		case GO_TO_MAIN:
			mainPageInit();
			break;
		case SETTING_PREV_BTN:
			settingHeader(prev_btn, (HashMap) msg.obj);
			break;
		case SETTING_NEXT_BTN:
			settingHeader(next_btn, (HashMap) msg.obj);
			break;
		case NOT_EXIST_CALL:
			break;
		case ACTION_CALL:
			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse((msg.obj)
					.toString())));
			break;
		case PAGE_BACK:
			if (mMenu != null)
				if (mMenu.isShowing())
					mMenu.hide();

			getWebView().loadUrl("javascript:fn_back();");
			break;
		case GET_APPLICATION_VERSION:
			getWebView().loadUrl(
					"javascript:settingVersion('" + (msg.obj).toString()
							+ "');");
			break;
		case CALL_BROWSER:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((msg.obj)
					.toString())));
			finish();
			break;
		case PASSWORD_ENCRYPTION:
			getWebView().loadUrl(
					"javascript:setPassword('"
							+ EncryptionUtil.digestSHA256((msg.obj).toString())
							+ "');");
			break;
		case END_APPLICATION:
			doEndApplicationDialog();
			break;
		case END_APPLICATION_INSTANTLY:
			moveTaskToBack(true);
			finish();
			break;
		case SHOW_LOADING_DIALOG:
			loadingDialog.setVisibility(View.VISIBLE);
			loadingDialog.bringToFront();
			loadingDialog.onAttachedToWindow();
			break;
		case HIDE_LOADING_DIALOG:
			loadingDialog.setVisibility(View.INVISIBLE);
			loadingDialog.onDetachedFromWindow();
			getWebView().bringToFront();
			break;
		case APPLICATION_INIT:
			Intent intent = getIntent();
			finish();
			startActivity(intent);
			break;
		case WEBVIEW_TOP:
			// webView.scrollTo(0, 0);
			break;
		case WEBVIEW_LOAD_URL:
			getWebView().loadUrl((msg.obj).toString());
			break;
		default:
			break;
		}
		return false;
	}

	private final String HeadTypeGone = "GONE";
	private final String HeadType1 = "TYPE1";
	private final String HeadType2 = "TYPE2";

	protected void mainPageInit() {
		if (header != null)
			header.setVisibility(View.VISIBLE);

		title = (TextView) findViewById(R.id.title);
		prev_btn = (TextView) findViewById(R.id.prev_btn);
		next_btn = (TextView) findViewById(R.id.next_btn);

		Log.d(FreshConfig.CMTAG,
				"isTablet ?		"
						+ PezAndroidUtil.isTablet(getApplicationContext()));
		Log.d(FreshConfig.CMTAG,
				"isHasMenuKey ?		"
						+ PezAndroidUtil.hasMenuKey(getApplicationContext()));
		if (PezAndroidUtil.isTablet(getApplicationContext())
				|| !PezAndroidUtil.hasMenuKey(getApplicationContext())) {
			isFloatingBtnUsing = true;
			floatingBtn.setPadding(2, 2, 11, FLOATINGDOWN);
			floatingBtn.setVisibility(View.VISIBLE);
			floatingBtn.bringToFront();
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

						if (heightDiff > keyboardHeight) {
							if (floatingBtn.isShown()) {
								isFloatingLock = false;
								floatingBtn.setVisibility(View.INVISIBLE);

								if (mMenu != null)
									mMenu.hide();
							}
						} else {
							if (isFloatingBtnUsing) {
								if (!isFloatingLock) {
									floatingBtn
											.setImageResource(R.drawable.btn_downarr);
									floatingBtn.setPadding(2, 2, 11,
											FLOATINGDOWN);
									floatingBtn.setVisibility(View.VISIBLE);
								}
							}
						}
					}
				});
		new UpdateTask().execute();
	}

	protected void settingHeaderInit() {
		// 항상 페이지를 시작하면 title bar에 버튼을 초기화한다.
		if (prev_btn != null) {
			prev_btn.setVisibility(View.GONE);
			prev_btn.setOnClickListener(null);
		}

		if (next_btn != null) {
			next_btn.setVisibility(View.GONE);
			next_btn.setOnClickListener(null);
		}
	}

	protected void settingHeader(TextView t, HashMap<String, String> pref) {
		final String headType = pref.get("headType");
		final String links = pref.get("links");
		final String names = pref.get("names");
		if (HeadTypeGone.equals(headType)) {
			t.setVisibility(View.GONE);
		} else if (HeadType1.equals(headType)) {
			t.setVisibility(View.VISIBLE);
			t.setText(names);
			t.setBackgroundResource(R.drawable.prev);
			t.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String callUrls = links;
					if (!links.startsWith("javascript")) {
						callUrls = (FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
								: FreshConfig.REAL_URL)
								+ WODURLS.SPORT + links;
					}
					Message msg = handler.obtainMessage(WEBVIEW_LOAD_URL,
							callUrls);
					handler.sendMessage(msg);
				}
			});
		} else if (HeadType2.equals(headType)) {
			t.setVisibility(View.VISIBLE);
			t.setVisibility(View.VISIBLE);
			t.setBackgroundResource(R.drawable.next);
			t.setText(names);
			t.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String callUrls = links;
					if (!links.startsWith("javascript")) {
						callUrls = (FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
								: FreshConfig.REAL_URL)
								+ WODURLS.SPORT + links;
					}
					Message msg = handler.obtainMessage(WEBVIEW_LOAD_URL,
							callUrls);
					handler.sendMessage(msg);
				}
			});
		}
	}

	@Override
	public void MenuItemSelectedEvent(CustomMenuItem selection) {
		ArrayList<HashMap<String, String>> menus = dbHelper.getSelect(
				TBL_MENUS.TBL_MENUS, TBL_MENUS.getSelectColumns(), null, null,
				null, null, TBL_MENUS.MENUS_ORDER + " asc");
		HashMap<String, String> menuItem = menus.get(selection.getId());
		Log.d(FreshConfig.CMTAG,
				"call!!! "
						+ (FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
								: FreshConfig.REAL_URL) + WODURLS.SPORT
						+ menuItem.get(TBL_MENUS.MENUS_LINK));
		webView.loadUrl((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL
				: FreshConfig.REAL_URL)
				+ WODURLS.SPORT
				+ menuItem.get(TBL_MENUS.MENUS_LINK));
		doFloatingBtn();
	}

	public String convertInputStreamToString(InputStream is) {
		try {
			if (is != null) {
				Writer writer = new StringWriter();

				char[] buffer = new char[1024];
				try {
					Reader reader = new BufferedReader(new InputStreamReader(
							is, "UTF-8"));
					int n;
					while ((n = reader.read(buffer)) != -1) {
						writer.write(buffer, 0, n);
					}
				} finally {
					is.close();
				}
				return writer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public InputStream getInputStreamFromUrl(String url, String userId,
			String userTypeCd, String comCd) {
		InputStream content = null;
		try {
			HttpPost post = new HttpPost(url);
			List<BasicNameValuePair> param = new ArrayList<BasicNameValuePair>();
			param.add(new BasicNameValuePair("g_userId", userId));
			param.add(new BasicNameValuePair("g_userTypeCd", userTypeCd));
			param.add(new BasicNameValuePair("g_comCd", comCd));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param,
					HTTP.UTF_8);
			post.setEntity(entity);
			HttpResponse response = mClient.execute(post);
			content = response.getEntity().getContent();
		} catch (Exception e) {
		}
		return content;
	}

	class UpdateTask extends AsyncTask<Void, String, Void> {
		private ProgressDialog dialog;

		@Override
		protected void onPostExecute(Void result) {
			// 작업이 완료 된 후 할일
			dialog.dismiss();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// 작업을 시작하기 전 할일
			dialog = new ProgressDialog(mContext);
			dialog.setTitle(R.string.dialog_menu_title);
			dialog.setMessage(getResources().getText(
					R.string.dialog_menu_content));
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			String c_userId = PezAndroidUtil.getCookie("c_userId");
			String c_userTypeCd = PezAndroidUtil.getCookie("c_userTypeCd");
			String c_comCd = PezAndroidUtil.getCookie("c_comCd");

			String res = convertInputStreamToString(getInputStreamFromUrl(
					WODURLS.MENU_INFO_URL, c_userId, c_userTypeCd, c_comCd));
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, res);
			}

			if (res != null) {
				try {
					JSONObject menuData = new JSONObject(res);
					// menuData.getString("menus_length");
					String serverMenuVersion = menuData
							.getString("menus_version");

					// 버전 비교 후, 값이 다를 경우에만 실행한다.
					ArrayList<HashMap<String, String>> defaultMenus = dbHelper
							.getSelect(TBL_MENUS.TBL_MENUS,
									TBL_MENUS.getSelectColumns(),
									TBL_MENUS.MENUS_VERSION + "=?",
									new String[] { serverMenuVersion }, null,
									null, TBL_MENUS.MENUS_ORDER + " asc");
					boolean isUpdate = false;
					for (HashMap<String, String> tmp : defaultMenus) {
						if (!serverMenuVersion.equals(tmp
								.get(TBL_MENUS.MENUS_VERSION))) {
							isUpdate = true;
						} else {
							isUpdate = false;
						}

						if (FreshConfig.DEVELOPER_MODE)
							Log.d(FreshConfig.CMTAG, serverMenuVersion + " :: "
									+ tmp.get(TBL_MENUS.MENUS_VERSION) + " :: "
									+ isUpdate);
						break;
					}

					if (FreshConfig.DEVELOPER_MODE)
						isUpdate = true;

					if (isUpdate || defaultMenus.size() == 0) {
						// 전체 삭제 후, 데이터 입력
						dbHelper.doDeleteAllMenus();

						JSONArray menus = menuData.getJSONArray("menus");
						JSONObject to = null;
						ContentValues cv = null;
						for (int i = 0; i < menus.length(); i++) {
							cv = new ContentValues();
							to = menus.getJSONObject(i);
							cv.put(TBL_MENUS.MENUS_ICON,
									to.getString(TBL_MENUS.MENUS_ICON));
							cv.put(TBL_MENUS.MENUS_LINK,
									to.getString(TBL_MENUS.MENUS_LINK));
							cv.put(TBL_MENUS.MENUS_NAME,
									to.getString(TBL_MENUS.MENUS_NAME));
							cv.put(TBL_MENUS.MENUS_ORDER,
									to.getString(TBL_MENUS.MENUS_ORDER));
							cv.put(TBL_MENUS.MENUS_VERSION,
									to.getString(TBL_MENUS.MENUS_VERSION));
							dbHelper.doInsert(TBL_MENUS.TBL_MENUS,
									TBL_MENUS.getInsertColumns(), cv);
						}
					}

					/* DB 에서 메뉴 정보를 가져온다. */
					ArrayList<HashMap<String, String>> dbRes = dbHelper
							.getSelect(TBL_MENUS.TBL_MENUS,
									TBL_MENUS.getSelectColumns(),
									TBL_MENUS.MENUS_VERSION + "=?",
									new String[] { serverMenuVersion }, null,
									null, TBL_MENUS.MENUS_ORDER + " asc");

					// initialize the menu
					mMenu = new CustomMenu(mContext, PezCommonActivity.this,
							getLayoutInflater());
					mMenu.setHideOnSelect(true);
					mMenu.setItemsPerLineInPortraitOrientation(4);
					mMenu.setItemsPerLineInLandscapeOrientation(8);
					loadMenuItems(dbRes);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			return null;
		}
	}

	/**
	 * 메뉴 설정
	 */
	public void loadMenuItems(ArrayList<HashMap<String, String>> dbRes) {
		ArrayList<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		CustomMenuItem cmi = null;
		HashMap<String, String> tm = null;
		for (int i = 0; i < dbRes.size(); i++) {
			tm = dbRes.get(i);
			cmi = new CustomMenuItem();
			cmi.setCaption(tm.get(TBL_MENUS.MENUS_NAME));
			if (tm.get(TBL_MENUS.MENUS_ICON).equals("MBQM001")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQN001")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQW001")) {
				// 홈
				cmi.setImageResourceId(R.drawable.mbqc001_selector);
			} else if (tm.get(TBL_MENUS.MENUS_ICON).equals("MBQM002")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQN002")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQW002")) {
				// 주문현황
				cmi.setImageResourceId(R.drawable.mbnq001_selector);
			} else if (tm.get(TBL_MENUS.MENUS_ICON).equals("MBQM003")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQN003")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQW003")) {
				// 단가조회 / 매출현황
				if (tm.get(TBL_MENUS.MENUS_ICON).equals("MBQM003")) {
					// 매출현황
					cmi.setImageResourceId(R.drawable.mbmq002_selector);
				} else if (tm.get(TBL_MENUS.MENUS_ICON).equals("MBQN003")
						|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQW003")) {
					// 단가조회
					cmi.setImageResourceId(R.drawable.mbnq002_selector);
				}
			} else if (tm.get(TBL_MENUS.MENUS_ICON).equals("MBQM004")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQN004")
					|| tm.get(TBL_MENUS.MENUS_ICON).equals("MBQW004")) {
				// 설정
				cmi.setImageResourceId(R.drawable.mbqc002_selector);
			}
			cmi.setId(i);
			menuItems.add(cmi);
		}

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
	 * floating 버튼 처리
	 */
	private void doFloatingBtn() {
		if (floatingBtn.isShown()) {
			switch (floatingBtn.getPaddingBottom()) {
			case FLOATINGUP:
			case FLOATINGUP_ICS:
				floatingBtn.setImageResource(R.drawable.btn_downarr);
				floatingBtn.setPadding(2, 2, 11, FLOATINGDOWN);
				// floatingBtn.setPadding(left, top, right, bottom)
				break;
			case FLOATINGDOWN:
				isFloatingLock = true;
				floatingBtn.setImageResource(R.drawable.btn_openarr);
				if (!PezAndroidUtil.isTablet(getApplicationContext())
						&& !PezAndroidUtil.hasMenuKey(getApplicationContext())) {
					floatingBtn.setPadding(2, 2, 11, FLOATINGUP_ICS);
				} else {
					floatingBtn.setPadding(2, 2, 11, FLOATINGUP);
				}
				break;
			}
		}
	}
}

package cj.fwmobile.sfa.android.pez;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import cj.fwmobile.sfa.android.R;
import cj.fwmobile.sfa.android.config.FreshConfig;
import cj.fwmobile.sfa.android.db.PezDatabaseHelper;
import cj.fwmobile.sfa.android.pez.menu.CustomMenu;
import cj.fwmobile.sfa.android.script.JavascriptInterface;
import cj.fwmobile.sfa.android.util.PezAndroidUtil;

/**
 * 전체 공통 메소드 및 설정 클래스
 * 
 * @author LeeSangYong
 * 
 */
public abstract class PezAndroidCommonActivity extends Activity implements
		PezCommonInterface {
	protected final Handler handler = new Handler();
	protected static JavascriptInterface jinterface;
	protected ProgressBar progressBar;
	// GPS
	// protected GPSTrackerService gps;
	protected WebView webView;
	protected String AppVersion;

	protected Button preBtn;
	protected Button saveBtn;
	protected Button menu1;
	protected Button menu2;
	protected Button menu3;

	protected CustomMenu mMenu;
	protected ImageView floatingBtn;
	public static final int FLOATINGUP = 79;
	public static final int FLOATINGUP_ICS = 149;
	public static final int FLOATINGDOWN = 1;

	protected PezDatabaseHelper dbHelper;

	protected final int sdkVersion = Build.VERSION.SDK_INT;

	EditText input;

	/**
	 * 모든 Activity 공통 Activity 복원 시, cookie sync
	 */
	@Override
	protected void onResume() {
		super.onResume();
		CookieSyncManager.getInstance().startSync();
	}

	/**
	 * 모든 Activity 공통 Activity 실행 시, cookie sync manager 생성
	 */
	@Override
	protected void onStart() {
		super.onStart();
		CookieSyncManager.createInstance(this);
	}

	/**
	 * 모든 Activity 공통 Activity 일시 정지 시, cookie sync 정지
	 */
	@Override
	protected void onPause() {
		super.onPause();
		CookieSyncManager.getInstance().stopSync();
	}

	/**
	 * 모든 Activity 공통 Activity 생성 시, App 버전 및 GPS Service를 생성함
	 */
	public void onCreate(Bundle savedInstanceState) {
		// gps = new GPSTrackerService(this);

		if (FreshConfig.DEVELOPER_MODE) {
			/*
			 * 해당 기능은 개발 모드에서만 지원함. 완료 후, 지원 여부는 따로 결정
			 */
			if (sdkVersion < Build.VERSION_CODES.HONEYCOMB) {
				Toast.makeText(this, R.string.application_version_warming_text,
						Toast.LENGTH_SHORT).show();
			}
		}

		try {
			PackageInfo i = getApplicationContext()
					.getPackageManager()
					.getPackageInfo(getApplicationContext().getPackageName(), 0);
			this.AppVersion = i.versionName;
		} catch (NameNotFoundException e) {
		}

		super.onCreate(savedInstanceState);
	}

	/**
	 * DB Helper를 반환함
	 */
	public PezDatabaseHelper getDbHelper() {
		return dbHelper;
	}

	/**
	 * Javascript Interface 객체를 반환한다.
	 * 
	 * @return
	 */
	protected JavascriptInterface getJavascriptInterfaceInstance(
			PezCommonInterface activity) {
		return new JavascriptInterface(activity);
	}

	/**
	 * Handler 객체를 반환한다.
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * 웹뷰 공통 설정
	 * 
	 * @author LeeSangYong
	 * 
	 */
	public class FreshWayWebView extends WebViewClient {
		// boolean timeout = true;
		//
		// public FreshWayWebView() {
		// timeout = true;
		// }

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
					new AlertDialog.Builder(view.getContext())
							// .setTitle("타이틀")
							.setMessage(R.string.calling_ment)
							.setPositiveButton(R.string.calling_btn,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											Intent i = new Intent(
													Intent.ACTION_CALL,
													Uri.parse(urlConection));
											startActivity(i);
										}
									})
							.setNegativeButton(android.R.string.cancel,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();
										}
									})
							// .setOnKeyListener(new OnKeyListener() {
							//
							// @Override
							// public boolean onKey(DialogInterface dialog,
							// int keyCode, KeyEvent event) {
							// if (keyCode == KeyEvent.KEYCODE_BACK) {
							// dialog.dismiss();
							// }
							// return true;
							// }
							// })
							.setCancelable(false).create().show();
					return true;
				}
			} else {
				new AlertDialog.Builder(view.getContext())
						.setMessage(R.string.not_exist_call_ment)
						.setPositiveButton(android.R.string.ok,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
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

		boolean isFirst = true;

		/**
		 * 페이지 종료 시
		 */
		@Override
		public void onPageFinished(WebView view, String url) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "----- onPageFinished -----" + url);
			}
			super.onPageFinished(view, url);
			progressBar.setVisibility(View.INVISIBLE);
			CookieSyncManager.getInstance().sync();
			// timeout = false;

			if (isFirst && url.indexOf("/mobile/web/sfa/Main/dispatch.fo") > 0) {
				doMenu();
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						mMenu.hide();
						floatingBtn.setImageResource(R.drawable.btn_downarr);
						floatingBtn.setPadding(2, 2, 2, FLOATINGDOWN);
					}
				}, 3000);
				isFirst = false;
			}
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

			// 5초 이상 응답이 없을 경우 다시 시도하라는 페이지로 유도함
			// Runnable run = new Runnable() {
			// public void run() {
			//
			// if (timeout) {
			// showRetryDialog(view, url);
			// }
			// }
			// };
			// handler.postDelayed(run, 10000);

			super.onPageStarted(view, url, favicon);
			progressBar.setVisibility(View.VISIBLE);
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
			// showRetryDialog(view, failingUrl);
		}
	}

	/**
	 * 서버 응답이 없거나, 오류 발생시, 다시 시도할라는 메세지를 보여줌
	 * 
	 * @param view
	 * @param url
	 */
	private void showRetryDialog(final WebView view, final String url) {
		view.loadUrl(FreshConfig.PREFIX_ASSET + "www/html/error.html");
		new AlertDialog.Builder(view.getContext())
				// .setTitle("타이틀")
				.setMessage(R.string.retry_ment)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.loadUrl(url);
							}
						})
				.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						})
				// .setOnKeyListener(new OnKeyListener() {
				//
				// @Override
				// public boolean onKey(DialogInterface dialog, int keyCode,
				// KeyEvent event) {
				// if (keyCode == KeyEvent.KEYCODE_BACK) {
				// dialog.dismiss();
				// }
				// return true;
				// }
				// })
				.setCancelable(false).create().show();
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
			progressBar.setProgress(newProgress);
		}

		/**
		 * Alert Native 구현
		 */
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				final android.webkit.JsResult result) {
			if (!onJsBeforeUnload(view, url, message, result)) {
				new AlertDialog.Builder(view.getContext())
						// .setTitle("타이틀")
						.setMessage(message)
						.setPositiveButton(android.R.string.ok,
								new AlertDialog.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										result.confirm();
									}
								})
						// .setOnKeyListener(new OnKeyListener() {
						// @Override
						// public boolean onKey(DialogInterface dialog,
						// int keyCode, KeyEvent event) {
						// if (keyCode == KeyEvent.KEYCODE_BACK) {
						// result.cancel();
						// dialog.dismiss();
						// }
						// return true;
						// }
						// })
						.setCancelable(false).create().show();
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
						// .setTitle("타이틀")
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
								})
						// .setOnKeyListener(new OnKeyListener() {
						// @Override
						// public boolean onKey(DialogInterface dialog,
						// int keyCode, KeyEvent event) {
						// if (keyCode == KeyEvent.KEYCODE_BACK) {
						// result.cancel();
						// dialog.dismiss();
						// }
						// return true;
						// }
						// })
						.setCancelable(false).create().show();
			}
			return true;
		}

		/**
		 * prompt native 구현 : 사용하지 않음
		 */
		@Override
		public boolean onJsPrompt(WebView view, String url, String message,
				String defaultValue, final JsPromptResult result) {
			AlertDialog.Builder builder;
			LayoutInflater inflater = (LayoutInflater) getCurrentActivity()
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.custom_dialog1,
					(ViewGroup) findViewById(R.id.layout_root));

			final EditText registeNumber = (EditText) layout
					.findViewById(R.id.promptInputText);

			registeNumber.setInputType(InputType.TYPE_CLASS_NUMBER);

			builder = new AlertDialog.Builder(getCurrentActivity());
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

	/**
	 * 화면 회전을 고정시킨다.
	 */
	public void setOrientation(boolean isFlag) {
		if (isFlag) {
			android.provider.Settings.System.putInt(getContentResolver(),
					android.provider.Settings.System.ACCELEROMETER_ROTATION, 0);// 불가능
		} else {
			android.provider.Settings.System.putInt(getContentResolver(),
					android.provider.Settings.System.ACCELEROMETER_ROTATION, 1);// 자동회전가능
		}
	}

	/**
	 * App 버전을 반환한다.
	 */
	@Override
	public String getAppVersion() {
		return this.AppVersion;
	}

	/**
	 * @location :
	 * @writeDay :
	 * @return : void
	 * @Todo : 캐쉬삭제
	 */
	protected void clearApplicationCache(File dir) {
		if (dir == null) {
			dir = getCacheDir();
		}
		File[] child = dir.listFiles();
		try {
			for (File childern : child) {
				if (childern.isDirectory()) {
					clearApplicationCache(childern);
				} else {
					childern.delete();
				}
			}
		} catch (Exception e) {
			Log.e("debug",
					getClass().getName() + " Exception : " + e.getMessage());
		}
	}
}

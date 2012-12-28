package cj.fwmobile.sfa.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.webkit.WebView;
import cj.fwmobile.sfa.android.config.FreshConfig;

/**
 * 인트로 화면
 * 
 * @author 이상용
 * @since 2012. 7. 19.
 * @version 1.0
 * 
 */
public class IntroActivity extends Activity {
	Handler handler;

	private WebView introView;
	/**
	 * 인트로 대기 시간
	 */
	private final int POST_DELAY = 2000;

	/**
	 * 인트로 시작
	 */
	Runnable intro = new Runnable() {

		@Override
		public void run() {
			Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
	};

	/**
	 * 인트로 종료 후, Back키를 눌렀을 경우 다시 인트로화면이 나오는 것을 방지하기 위한 코드
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		handler.removeCallbacks(intro);
	}

	/**
	 * 인트로 생성
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_intro);

		introView = (WebView) findViewById(R.id.intro_webview);
		introView.getSettings().setJavaScriptEnabled(true);
		introView.requestFocus();
		introView.loadUrl(FreshConfig.PREFIX_ASSET + "www/html/intro.html");

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

		handler = new Handler();
		handler.postDelayed(intro, POST_DELAY);
	}

}

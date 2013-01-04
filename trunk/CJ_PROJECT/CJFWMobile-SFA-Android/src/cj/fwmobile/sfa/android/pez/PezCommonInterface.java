/**
 * 
 */
package cj.fwmobile.sfa.android.pez;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import cj.fwmobile.sfa.android.db.PezDatabaseHelper;

/**
 * 공통 인터페이스 정의
 * 
 * @author LeeSangYong
 * 
 */
public interface PezCommonInterface {
	/**
	 * 생성 / 반드시 구현해야함
	 * 
	 * @param savedInstanceState
	 */
	public void onCreate(Bundle savedInstanceState);

	/**
	 * 웹뷰를 반환한다.
	 * 
	 * @return
	 */
	public WebView getWebView();

	/**
	 * 핸들러를 반환한다.
	 * 
	 * @return
	 */
	public Handler getHandler();

	/**
	 * 데이터 베이스 Helper를 반환한다.
	 * 
	 * @return
	 */
	public PezDatabaseHelper getDbHelper();

	/**
	 * 자신의 Activity를 반환한다.
	 * 
	 * @return
	 */
	public Activity getCurrentActivity();

	/**
	 * 좌우 회전 설정
	 * 
	 * @param op
	 */
	public void setOrientation(boolean isFlag);

	/**
	 * webView를 설정
	 */
	public void setWebViewSetting();

	/**
	 * 종료 / 로그아웃 다이얼로그 구현
	 */
	public void showLogoutDialog();

	/**
	 * 커스텀 메뉴를 show / hide 한다.
	 */
	public void doMenu();

	/**
	 * 이 App의 버전을 반환한다.
	 * 
	 * @return
	 */
	public String getAppVersion();
}

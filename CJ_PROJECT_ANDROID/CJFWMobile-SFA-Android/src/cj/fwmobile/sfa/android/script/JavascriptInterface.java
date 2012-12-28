package cj.fwmobile.sfa.android.script;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import cj.fwmobile.sfa.android.LoginActivity;
import cj.fwmobile.sfa.android.MainActivity;
import cj.fwmobile.sfa.android.config.FreshConfig;
import cj.fwmobile.sfa.android.db.PezConstants.FreshDB;
import cj.fwmobile.sfa.android.pez.PezCommonInterface;
import cj.fwmobile.sfa.android.util.PezAndroidUtil;

/**
 * Javascript <=> Native 연동 클래스
 * 
 * @author LeeSangYong
 * 
 */
public class JavascriptInterface {
	PezCommonInterface activity;

	/**
	 * 생성자
	 * 
	 * @param activity
	 */
	public JavascriptInterface(PezCommonInterface activity) {
		this.activity = activity;

	}

	/**
	 * 프로그램 업데이트를 위하여 Browser를 호출한다.
	 */
	public void doUpdateApplication(final String updateUrl) {
		activity.getHandler().post(new Runnable() {

			@Override
			public void run() {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
				activity.getCurrentActivity().startActivity(i);
				activity.getCurrentActivity().finish();
			}
		});
	}

	/**
	 * app 버전 반환
	 */
	public void getAppVersion() {
		activity.getHandler().post(new Runnable() {
			@Override
			public void run() {
				activity.getWebView().loadUrl(
						"javascript:settingVersion('"
								+ activity.getAppVersion() + "');");
			}
		});
	}

	/**
	 * 커스텀 메뉴 호출
	 */
	public void showCustomMenu() {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "##### showCustomMenu #####");
		}

		activity.getHandler().post(new Runnable() {
			@Override
			public void run() {
				activity.doMenu();
			}
		});
	}

	/**
	 * 테블릿인지 판단
	 */
	public void isTablet() {
		activity.getWebView().loadUrl(
				"javascript:setTablet('"
						+ PezAndroidUtil.isTablet(activity.getCurrentActivity()
								.getApplicationContext()) + "');");
	}

	/**
	 * 잘못된 접근일 경우 호출
	 */
	public void doLogoutByIncorrectConnect() {
		activity.getHandler().post(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(activity.getCurrentActivity(),
						LoginActivity.class);
				activity.getCurrentActivity().startActivity(intent);
				activity.getCurrentActivity().finish();
			}
		});
	}

	/**
	 * 로그아웃
	 */
	public void doLogout() {
		activity.getCurrentActivity().overridePendingTransition(
				android.R.anim.fade_in, android.R.anim.fade_out);
		activity.getHandler().post(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(activity.getCurrentActivity(),
						LoginActivity.class);
				activity.getCurrentActivity().startActivity(intent);
				activity.getCurrentActivity().finish();
			}
		});
	}

	/**
	 * 로그인
	 */
	public void doSuccessLogin() {
		activity.getCurrentActivity().overridePendingTransition(
				android.R.anim.fade_in, android.R.anim.fade_out);

		activity.getHandler().post(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(activity.getCurrentActivity(),
						MainActivity.class);
				activity.getCurrentActivity().startActivity(intent);
				activity.getCurrentActivity().finish();
			}
		});
	}

	/**
	 * 비밀번호 암호화
	 * 
	 * @param pwd
	 */
	public void passwordEncrytion(final String pwd) {
		activity.getHandler().post(new Runnable() {
			@Override
			public void run() {
				activity.getWebView().loadUrl(
						"javascript:setPassword('"
								+ EncryptionUtil.digestSHA256(pwd) + "');");
			}
		});
	}

	/**
	 * 로컬 디비에 저장된 사용자 아이디 반환
	 */
	@Deprecated
	public void getAuthInfo() {
		final StringBuilder returnData = new StringBuilder();
		activity.getHandler().post(new Runnable() {
			@Override
			public void run() {
				ArrayList<HashMap<String, String>> result = activity
						.getDbHelper().getSelect(FreshDB.TABLE_AUTH,
								FreshDB.getTableAuthColumnsForSelection(),
								FreshDB.C_ID + "=1", null, null, null, null);
				String _id = "";
				String userid = "";
				String isRememberId = "";
				for (HashMap<String, String> inner : result) {
					_id = inner.get(FreshDB.C_ID).toString();
					userid = inner.get(FreshDB.C_USERID).toString();
					isRememberId = inner.get(FreshDB.C_ISREMEMBERID).toString();
				}

				if (FreshConfig.DEVELOPER_MODE) {
					Log.d(FreshConfig.CMTAG, returnData.toString());
				}
				activity.getWebView().loadUrl(
						"javascript:setLoginInfo('" + _id + "','" + userid
								+ "','" + isRememberId + "');");
			}
		});
	}

	/**
	 * 로컬 디비에 저장된 사용자 업데이트
	 * 
	 * @param userId
	 * @param isRememberId
	 */
	@Deprecated
	public void doLoginSuccess(final String userId, final String isRememberId) {
		activity.getHandler().post(new Runnable() {
			@Override
			public void run() {
				ContentValues values = new ContentValues();
				values.put(FreshDB.C_USERID, userId);
				values.put(FreshDB.C_ISREMEMBERID, isRememberId);

				ArrayList<HashMap<String, String>> result = activity
						.getDbHelper().getSelect(FreshDB.TABLE_AUTH,
								FreshDB.getTableAuthColumnsForSelection(),
								FreshDB.C_USERID + "=?",
								new String[] { userId }, null, null, null);
				switch (result.size()) {
				case 0:
					activity.getDbHelper().doInsert(FreshDB.TABLE_AUTH,
							FreshDB.getTableAuthColumns(), values);
					break;
				case 1:
					activity.getDbHelper().doUpdate(FreshDB.TABLE_AUTH, values,
							null, null);
					break;
				default: // 이외
					break;
				}

				Activity ac = activity.getCurrentActivity();
				Intent intent = new Intent(ac, MainActivity.class);
				ac.startActivity(intent);
				ac.finish();
			}
		});
	}

	/** Show a toast from the web page */
	public void showToast(final String toast) {
		activity.getHandler().post(new Runnable() {
			@Override
			public void run() {
				if (FreshConfig.DEVELOPER_MODE) {
					Log.d(FreshConfig.CMTAG,
							"----- call showToast - start -----");
				}
				Toast.makeText(activity.getCurrentActivity(), toast,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 화면 회전 설정 호출
	 * 
	 * @param option
	 */
	public void orientation(String option) {
		activity.setOrientation(option.equals("0"));
	}
}

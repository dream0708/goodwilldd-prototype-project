package cj.fwmobile.sfa.android.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.ViewConfiguration;
import android.webkit.CookieManager;
import cj.fwmobile.sfa.android.config.FreshConfig;

/**
 * App 에서 사용하는 Util 클래스
 * 
 * @author LeeSangYong
 * 
 */
public class PezAndroidUtil {
	/**
	 * 테블릿 여부 판단
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isTablet(Context context) {
		// TODO: This hacky stuff goes away when we allow users to target
		// devices
		int xlargeBit = 4; // Configuration.SCREENLAYOUT_SIZE_XLARGE; // upgrade
							// to HC SDK to get this
		Configuration config = context.getResources().getConfiguration();
		return (config.screenLayout & xlargeBit) == xlargeBit;
	}

	public static boolean hasMenuKey(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return ViewConfiguration.get(context).hasPermanentMenuKey();
		} else {
			return true;
		}
	}

	public static String getCookie(String key) {
		CookieManager cm = CookieManager.getInstance();
		String ck = cm.getCookie((FreshConfig.DEVELOPER_MODE ? FreshConfig.HOST_URL : FreshConfig.REAL_URL));
		Map<String, String> res = new HashMap<String, String>();
		String[] mm = null;

		for (String k : ck.split(";")) {
			mm = k.trim().split("=");
			switch (mm.length) {
			case 2:
				res.put(mm[0], mm[1]);
				break;
			default:
				res.put(mm[0], "");
				break;

			}
		}

		return res.get(key);
	}

	/**
	 * 전화가 가능한지 확인
	 * 
	 * @param context
	 * @return
	 */
	public static boolean aviliableCALL(Context context) {
		PackageManager pac = context.getPackageManager();

		Uri callUri = Uri.parse("tel:");
		Intent callIntent = new Intent(Intent.ACTION_CALL, callUri);

		List<ResolveInfo> list = pac.queryIntentActivities(callIntent,
				PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
		ArrayList<ActivityInfo> tempList = new ArrayList<ActivityInfo>();

		int count = list.size();
		String packageName = "";

		for (int i = 0; i < count; i++) {
			ResolveInfo firstInfo = list.get(i);
			packageName = firstInfo.activityInfo.applicationInfo.packageName;
			tempList.add(list.get(i).activityInfo);

			Log.d("packageName", "packageName = " + packageName);
		}

		if (packageName == null || packageName.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * SMS 가능 여부
	 * 
	 * @param context
	 * @return
	 */
	public static boolean aviliableSMS(Context context) {

		PackageManager pac = context.getPackageManager();

		Uri smsUri = Uri.parse("sms:");
		Intent smsIntent = new Intent(Intent.ACTION_SENDTO, smsUri);

		List<ResolveInfo> list = pac.queryIntentActivities(smsIntent,
				PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
		ArrayList<ActivityInfo> tempList = new ArrayList<ActivityInfo>();

		int count = list.size();
		String packageName = "";

		for (int i = 0; i < count; i++) {
			ResolveInfo firstInfo = list.get(i);
			packageName = firstInfo.activityInfo.applicationInfo.packageName;
			tempList.add(list.get(i).activityInfo);

			Log.d("packageName", "packageName = " + packageName);
		}

		if (packageName == null || packageName.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * MMS 여부
	 */
	public static boolean aviliableMMS(Context context) {

		PackageManager pac = context.getPackageManager();

		Uri mmsUri = Uri.parse("mmsto:");
		Intent mmsIntent = new Intent(Intent.ACTION_VIEW, mmsUri);

		List<ResolveInfo> list = pac.queryIntentActivities(mmsIntent,
				PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
		ArrayList<ActivityInfo> tempList = new ArrayList<ActivityInfo>();

		int count = list.size();
		String packageName = "";

		for (int i = 0; i < count; i++) {
			ResolveInfo firstInfo = list.get(i);
			packageName = firstInfo.activityInfo.applicationInfo.packageName;
			tempList.add(list.get(i).activityInfo);

			Log.d("packageName", "packageName = " + packageName);
		}

		if (packageName == null || packageName.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}

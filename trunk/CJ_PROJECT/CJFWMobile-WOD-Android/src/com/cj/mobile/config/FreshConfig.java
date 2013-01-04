package com.cj.mobile.config;

import android.os.Build;

public interface FreshConfig {
	/**
	 * 개발 모드 설정
	 */
	public static final boolean DEVELOPER_MODE = false;

	/**
	 * 전체 charset
	 */
	public static final String CHARSET = "UTF-8";

	/**
	 * 개발 서버 포트
	 */
	public static final int DEV_PORT = 8080;

	/**
	 * 운영서버 포트
	 */
	public static final int REAL_PORT = 8080;

	/**
	 * 서버 URL
	 */
	// public static final String HOST_URL = "http://52.90.235.32";
	public static final String HOST_URL = "http://devm.onlyonefood.net";

	/**
	 * 실 운영 URL
	 */
	public static final String REAL_URL = "http://m.onlyonefood.net";

	/**
	 * ASSET_PREFIX
	 */
	public static final String PREFIX_ASSET = "file:///android_asset/";

	/**
	 * 전체 공통 TAG
	 */
	public static final String CMTAG = "wod";

	public final int sdkVersion = Build.VERSION.SDK_INT;
}

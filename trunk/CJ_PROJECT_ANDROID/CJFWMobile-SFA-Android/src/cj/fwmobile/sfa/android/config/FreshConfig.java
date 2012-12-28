package cj.fwmobile.sfa.android.config;

/**
 * App 전체 환경 설정 파일
 * 
 * @author LeeSangYong
 * 
 */
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
	 * 서버 URL
	 */
	public static final String HOST_URL = "http://52.90.235.32:7001/sfa";

	/**
	 * 실 운영 URL
	 */
	public static final String REAL_URL = "http://mproma.cjfreshway.co.kr:8080/sfa";

	/**
	 * ASSET_PREFIX
	 */
	public static final String PREFIX_ASSET = "file:///android_asset/";

	/**
	 * 전체 공통 TAG
	 */
	public static final String CMTAG = "CJ PRO MA";

	/*
	 * 모바일 권한 ALL : 양쪽 모두의 권한 WHLS : 도가관리 DVLP : 신규개척 NONE : 권한없음
	 */
	public static enum MAUTH {
		ALL, WHLS, DVLP, NONE;

		public static MAUTH getValue(String auth) {
			try {
				MAUTH mauth = MAUTH.valueOf(auth);
				switch (mauth) {
				case ALL:
				case WHLS:
				case DVLP:
					return mauth;
				default:
					return NONE;
				}
			} catch (Exception e) {
				return NONE;
			}
		}
	};
}

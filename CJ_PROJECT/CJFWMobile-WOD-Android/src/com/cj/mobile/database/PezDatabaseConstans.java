package com.cj.mobile.database;

import android.provider.BaseColumns;

/**
 * 로컬 데이터 베이스 환경 파일
 * 
 * @author LeeSangYong
 * 
 */
public final class PezDatabaseConstans implements BaseColumns {
	private PezDatabaseConstans() {
	}

	// Database Name & Version
	public static final String DB_NAME = "wod.db";
	public static final int DB_VERSION = 3;

	public static final class TBL_LOGIN implements BaseColumns {
	}

	public static final class TBL_MENUS implements BaseColumns {
		private TBL_MENUS() {
		}

		public static final String TBL_MENUS = "wod_menus";
		public static final String MENUS_ID = "_id";
		public static final String MENUS_NAME = "menu_name";
		public static final String MENUS_LINK = "menu_link";
		public static final String MENUS_ICON = "menu_icon";
		public static final String MENUS_ORDER = "menu_order";
		public static final String MENUS_VERSION = "menu_version";

		public static final String getInsertColumns() {
			return MENUS_VERSION + ", " + MENUS_NAME + "," + MENUS_LINK + ","
					+ MENUS_ICON + "," + MENUS_ORDER;
		}

		public static final String[] getSelectColumns() {
			return new String[] { MENUS_VERSION, MENUS_NAME, MENUS_LINK,
					MENUS_ICON, MENUS_ORDER };
		}
	}

}

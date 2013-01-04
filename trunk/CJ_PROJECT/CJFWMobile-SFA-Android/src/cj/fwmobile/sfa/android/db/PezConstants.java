package cj.fwmobile.sfa.android.db;

import android.provider.BaseColumns;

/**
 * 로컬 데이터 베이스 환경 파일
 * 
 * @author LeeSangYong
 * 
 */
public final class PezConstants implements BaseColumns {
	private PezConstants() {
	}

	public static final class FreshDB implements BaseColumns {
		private FreshDB() {
		}

		// Database Name & Version
		public static final String DB_NAME = "freshway.db";
		public static final int DB_VERSION = 4;

		// TableName
		// 사용자 정보(아이디/비밀번호) 및 자동 로그인 여부
		public static final String TABLE_AUTH = "fwauthrity";

		// field
		public static final String C_ID = "_id";
		public static final String C_USERID = "userId";
		public static final String C_ISREMEMBERID = "isRememberId";

		public static final String getTableAuthColumns() {
			return C_USERID + "," + C_ISREMEMBERID;
		}

		public static final String[] getTableAuthColumnsForSelection() {
			return new String[] { C_ID, C_USERID, C_ISREMEMBERID };
		}
	}
}

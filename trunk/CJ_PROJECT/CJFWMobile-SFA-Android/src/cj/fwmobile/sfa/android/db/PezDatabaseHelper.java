/**
 * 
 */
package cj.fwmobile.sfa.android.db;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import cj.fwmobile.sfa.android.config.FreshConfig;
import cj.fwmobile.sfa.android.db.PezConstants.FreshDB;

/**
 * 로컬 데이터 베이스 Helper
 * 
 * @author LeeSangYong
 * 
 */
public class PezDatabaseHelper {
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	/**
	 * 생성자
	 * 
	 * @param context
	 */
	public PezDatabaseHelper(Context context) {
		this.context = context;
	}

	/**
	 * Database open
	 * 
	 * @return
	 * @throws SQLException
	 */
	private PezDatabaseHelper open() throws SQLException {
		DBHelper = new DatabaseHelper(this.context);
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * database close
	 */
	private void close() {
		DBHelper.close();
	}

	/**
	 * 업데이트
	 * 
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdate(String table, ContentValues values,
			String whereClause, String[] whereArgs) throws SQLException {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG,
					"#################### doUpdate ####################");
		}
		boolean result = false;
		open();
		db.beginTransaction();
		try {
			result = db.update(table, values, whereClause, whereArgs) > 0;
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			close();
		}
		return result;
	}

	/**
	 * 인서트
	 * 
	 * @param table
	 * @param columns
	 * @param values
	 * @return
	 * @throws SQLException
	 */
	public long doInsert(String table, String columns, ContentValues values)
			throws SQLException {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG,
					"#################### doInsert ####################");
		}
		long result = 0;
		open();
		db.beginTransaction();
		try {
			result = db.insert(table, columns, values);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			close();
		}
		return result;
	}

	/**
	 * 결과값을 ArrayList로 반환한다.
	 * 
	 * @param table
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param groupBy
	 * @param having
	 * @param orderBy
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<HashMap<String, String>> getSelect(String table,
			String[] columns, String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) throws SQLException {
		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG,
					"#################### getSelect ####################");
		}
		open();
		try {
			Cursor cursor = db.query(table, columns, selection, selectionArgs,
					groupBy, having, orderBy);
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG,
						"cursor.getCount() ::: " + cursor.getCount());
			}
			ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			if (cursor.moveToFirst()) {
				HashMap<String, String> innerData = null;
				while (!cursor.isAfterLast()) {
					innerData = new HashMap<String, String>();
					String[] cols = cursor.getColumnNames();
					for (String col : cols) {
						innerData.put(col,
								cursor.getString(cursor.getColumnIndex(col)));
					}
					result.add(innerData);
					cursor.moveToNext();
				}
				return result;
			} else {
				return result;
			}
		} finally {
			close();
		}
	}

	/**
	 * 내부 Helper 클래스
	 * 
	 * @author LeeSangYong
	 * 
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
		/**
		 * 생성자
		 * 
		 * @param context
		 */
		public DatabaseHelper(Context context) {
			this(context, FreshDB.DB_NAME, null, FreshDB.DB_VERSION);
		}

		/**
		 * 생성자
		 * 
		 * @param context
		 * @param name
		 * @param factory
		 * @param version
		 */
		private DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		/**
		 * 최초 데이터 베이스 생성
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG,
						"----- Database - Create Table Start -----");
			}
			StringBuilder generateDatabaseQuery = new StringBuilder();
			generateDatabaseQuery.append("CREATE TABLE ");
			generateDatabaseQuery.append(FreshDB.TABLE_AUTH);
			generateDatabaseQuery.append(" (");
			generateDatabaseQuery.append(FreshDB.C_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT");
			generateDatabaseQuery.append(",");
			generateDatabaseQuery.append(FreshDB.C_USERID + " text not null");
			generateDatabaseQuery.append(",");
			generateDatabaseQuery.append(FreshDB.C_ISREMEMBERID + " text");
			generateDatabaseQuery.append(");");
			db.execSQL(generateDatabaseQuery.toString());
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG,
						"----- Database Create Table End -----");
			}
		}

		/**
		 * 데이터 베이스 업그레이드 시 : 버전이 변경되면 다시 생성함
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "----- Database onUpgrade Start -----");
			}
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "Version mismatch " + oldVersion + ":"
						+ newVersion + ": drop table and new create table");
			}
			StringBuilder generateDatabaseQuery = new StringBuilder();
			generateDatabaseQuery.append("DROP TABLE IF EXISTS ");
			generateDatabaseQuery.append(FreshDB.TABLE_AUTH);
			db.execSQL(generateDatabaseQuery.toString());
			onCreate(db);
			if (FreshConfig.DEVELOPER_MODE) {
				Log.d(FreshConfig.CMTAG, "----- Database onUpgrade End -----");
			}
		}
	}

}

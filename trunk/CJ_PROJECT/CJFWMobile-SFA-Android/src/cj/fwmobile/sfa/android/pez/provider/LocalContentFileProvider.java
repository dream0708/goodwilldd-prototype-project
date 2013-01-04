package cj.fwmobile.sfa.android.pez.provider;

import java.io.FileNotFoundException;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import cj.fwmobile.sfa.android.config.FreshConfig;

/**
 * 컨텐트 프로바이더
 * 
 * @author LeeSangYong
 * 
 */
public class LocalContentFileProvider extends ContentProvider {

	private static final String URI_PREFIX = "file:///android_assert///www/cj.mobile";

	public static String constructUri(String url) {
		Uri uri = Uri.parse(url);
		return uri.isAbsolute() ? url : URI_PREFIX + url;
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode)
			throws FileNotFoundException {

		IFilePathItem requestedFile = new FilePathItem(uri.getPath());
		ParcelFileDescriptor parcel = null;

		IFindTheSame find = new FindTheSame(getContext(), requestedFile);

		if (find.isFind()) {
			parcel = ParcelFileDescriptor.open(find.getResultFile(),
					ParcelFileDescriptor.MODE_READ_ONLY);
			return parcel;
		}

		if (FreshConfig.DEVELOPER_MODE) {
			Log.d(FreshConfig.CMTAG, "No Found! Make files!!!!!!!!!");
		}

		new MakeFiles(getContext());

		if (find.isFind()) {
			parcel = ParcelFileDescriptor.open(find.getResultFile(),
					ParcelFileDescriptor.MODE_READ_ONLY);
			return parcel;
		}

		return parcel;
	}

	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public int delete(Uri uri, String s, String[] as) {
		throw new UnsupportedOperationException(
				"Not supported by this provider");
	}

	@Override
	public String getType(Uri uri) {
		throw new UnsupportedOperationException(
				"Not supported by this provider");
	}

	@Override
	public Uri insert(Uri uri, ContentValues contentvalues) {
		throw new UnsupportedOperationException(
				"Not supported by this provider");
	}

	@Override
	public Cursor query(Uri uri, String[] as, String s, String[] as1, String s1) {
		throw new UnsupportedOperationException(
				"Not supported by this provider");
	}

	@Override
	public int update(Uri uri, ContentValues contentvalues, String s,
			String[] as) {
		throw new UnsupportedOperationException(
				"Not supported by this provider");
	}

}

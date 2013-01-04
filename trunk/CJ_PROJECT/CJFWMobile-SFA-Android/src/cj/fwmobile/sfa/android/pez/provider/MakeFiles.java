package cj.fwmobile.sfa.android.pez.provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import cj.fwmobile.sfa.android.config.FreshConfig;

public class MakeFiles {
	private static final String defaultPath = "www/cj.mobile";
	private static final String TAG = "MakeFiles";

	private Context ctx;
	private static String rootPath;
	private AssetManager amanager;
	private List<String> assetList = new ArrayList<String>();
	private List<String> dataList = new ArrayList<String>();

	public MakeFiles(Context ctx) {
		super();
		this.ctx = ctx;
		rootPath = ctx.getFilesDir().getAbsolutePath();
		doExecute();
	}

	private void doExecute() {
		amanager = ctx.getAssets();
		// makeFileList(defaultPath);
		displayFiles(amanager, defaultPath, 0);
		for (String data : dataList) {
			// Log.e(TAG, "file list = "+asset);
			IFilePathItem item = new FilePathItem(data);
			String root = "";
			for (String dir : item.getPath()) {
				makeDir(root + dir);
				root += dir + "/";
			}
			// Log.e(TAG,"ab path = "+item.getAbsolutePath());
		}
		writingFiles();

	}

	/**
	 * To be fully recursive you can update the method as following : calling
	 * displayFiles(mgr, "", 0);
	 * 
	 * @param mgr
	 * @param path
	 * @param level
	 */
	private void displayFiles(AssetManager mgr, String path, int level) {
		Log.v(FreshConfig.CMTAG, "enter displayFiles(" + path + ")");
		try {
			String list[] = mgr.list(path);
			Log.v(FreshConfig.CMTAG,
					"L" + level + ": list:" + Arrays.asList(list));

			if (list != null)
				for (int i = 0; i < list.length; ++i) {
					if (level >= 1) {
						displayFiles(mgr, path + "/" + list[i], level + 1);

						dataList.add(rootPath + path + "/" + list[i]);
						assetList.add(defaultPath + path + "/" + list[i]);
					} else {
						displayFiles(mgr, list[i], level + 1);
					}
				}
		} catch (IOException e) {
			Log.v(FreshConfig.CMTAG, "List error: can't list" + path);
		}

	}

	private void makeFileList(String dir) {
		String[] list;
		try {
			list = amanager.list(dir);
			for (String s : list) {
				if (s.indexOf(".") > 0) {
					String path = dir.substring(defaultPath.length());
					// Log.e(TAG, "asset = "+(defaultPath+path+"/"+s));
					dataList.add(rootPath + path + "/" + s);
					assetList.add(defaultPath + path + "/" + s);
				} else {
					makeFileList(dir + "/" + s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void makeDir(String dir) {
		// Log.e(TAG, "makeDir = "+dir);
		File file = new File(dir);
		// Log.e(TAG, "file.exists() = "+file.exists());
		if (!file.exists()) {
			if (!file.mkdir()) {
				Log.e(TAG,
						"Making directory fail! -------- "
								+ file.getAbsolutePath());
			}
		}
	}

	private void writingFiles() {

		for (int i = 0; i < assetList.size(); i++) {

			IFilePathItem data = new FilePathItem(dataList.get(i));
			IFilePathItem asset = new FilePathItem(assetList.get(i));
			// Log.e(TAG, "data = "+data.getAbsolutePath().substring(1)+"/"+
			// data.getFileName());
			// Log.e(TAG,
			// "asset = "+asset.getAbsolutePath().substring(1)+"/"+asset.getFileName());
			File file = new File(data.getAbsolutePath().substring(1),
					data.getFileName());

			// 해당 파일이 존재하면 리턴시켜 준다.
			if (file.exists())
				return;

			try {
				InputStream inputStream = amanager.open(asset.getAbsolutePath()
						.substring(1) + "/" + asset.getFileName());
				OutputStream outStream = new FileOutputStream(file);
				// Log.e(TAG,
				// "file path = "+file.getPath()+", file name = "+file.getName());

				// 읽어들일 버퍼크기를 메모리에 생성
				byte[] buf = new byte[1024];
				int len = 0;
				// 끝까지 읽어들이면서 File 객체에 내용들을 쓴다
				while ((len = inputStream.read(buf)) > 0) {
					outStream.write(buf, 0, len);
				}

				// outStream.flush();

				// Stream 객체를 모두 닫는다.
				outStream.close();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.e(TAG, "1File not found Exception!@@!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
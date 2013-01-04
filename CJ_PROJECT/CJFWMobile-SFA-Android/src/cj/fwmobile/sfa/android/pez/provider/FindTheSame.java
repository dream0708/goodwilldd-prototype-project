package cj.fwmobile.sfa.android.pez.provider;

import java.io.File;
import java.util.LinkedList;

import android.content.Context;

public class FindTheSame implements IFindTheSame {
	private static final String TAG = "FindTheSame";
	private Context ctx;
	private IFilePathItem sourceItem;
	private File theFile;
	private boolean found = false;

	public FindTheSame(Context ctx, IFilePathItem item) {
		super();
		this.ctx = ctx;
		this.sourceItem = item;
	}

	@Override
	public boolean isFind() {
		File list = ctx.getFilesDir();
		String removePath = list.getAbsolutePath();
		// Log.e(TAG,"removePath = "+removePath);

		found = findFile(list, removePath);

		return found;
	}

	private boolean findFile(File file, String removePath) {

		for (File f : file.listFiles()) {

			if (f.isFile()) {
				String path = f.getAbsolutePath()
						.substring(removePath.length());
				// Log.e(TAG,"path = "+path+", removePath.length() = "+removePath.length());
				IFilePathItem compareFile = new FilePathItem(path);
				if (isMatch(compareFile, sourceItem)) {
					found = true;
					theFile = f;
					// Log.e(TAG, "found file = "+f.getPath());
					return found;
				}
			} else {
				findFile(f, removePath);
			}
		}

		return found;
	}

	private boolean isMatch(IFilePathItem compareFile, IFilePathItem sourceFile) {
		boolean result = true;
		LinkedList<String> compare = compareFile.getPath();
		LinkedList<String> source = sourceFile.getPath();

		// Log.e(TAG, "source != null ?"+(source !=
		// null)+", compare != null ? "+(compare != null));
		if (source != null && compare != null) {

			if (source.size() != compare.size()) {
				// Log.e(TAG, "1000 - return false;");
				return false;
			}

			for (int i = 0; i < source.size(); i++) {
				// Log.e(TAG,"source.get(i) = "+source.get(i)+", compare.get(i) = "+compare.get(i));
				if (!source.get(i).equals(compare.get(i))) {
					result = false;
					break;
				}
			}
		} else if (source == null && compare == null) {
			// do nothing
		} else {
			// Log.e(TAG, "2000 - return false;");
			return false;
		}

		// Log.e(TAG,"file.getFileName() = "+compareFile.getFileName()+", item2.getFileName() = "+sourceFile.getFileName());
		if (result
				&& (compareFile.getFileName().equals(sourceFile.getFileName()))) {

		} else {
			result = false;
		}
		// Log.e(TAG, "3000 - return result ? "+result);
		return result;
	}

	@Override
	public File getResultFile() {
		if (found) {
			return theFile;
		}
		return null;
	}

}
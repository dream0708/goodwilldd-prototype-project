package cj.fwmobile.sfa.android.pez.provider;

import java.util.LinkedList;

public class FilePathItem implements IFilePathItem {
	private LinkedList<String> path;
	private String fileName = "";

	public FilePathItem(String item) {
		if (item.indexOf("/") == 0) {
			item = item.substring(1);
		}

		if (item.indexOf("/") > 0) {
			path = new LinkedList<String>();

			if (item.indexOf(".") > 0) { // 확장자가 있어야만 파일이다.
				fileName = item.substring(item.lastIndexOf("/") + 1);
				item = item.substring(0, item.lastIndexOf("/"));
			}

			String[] pathArr = item.split("/");

			for (String p : pathArr) {
				path.addLast(p);
			}
		} else {
			if (item.indexOf(".") > 0) { // 확장자가 있어야만 파일이다.
				fileName = item;
			} else {
				path = new LinkedList<String>();
				path.add(item);
			}

		}
		// Log.e("AssetItem", toString());
	}

	@Override
	public LinkedList<String> getPath() {
		return path;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("fileName = " + fileName);
		if (path != null) {
			sb.append("\npath = ");
			for (String s : getPath()) {
				sb.append("/" + s);
			}
		}
		return sb.toString();
	}

	@Override
	public String getAbsolutePath() {
		StringBuilder sb = new StringBuilder();
		if (path != null) {
			for (String s : getPath()) {
				sb.append("/" + s);
			}
		} else {
			return null;
		}
		return sb.toString();
	}
}
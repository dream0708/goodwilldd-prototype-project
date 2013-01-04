package cj.fwmobile.sfa.android.pez.provider;

import java.util.LinkedList;

public interface IFilePathItem {
	public LinkedList<String> getPath();

	public String getAbsolutePath();

	public String getFileName();
}
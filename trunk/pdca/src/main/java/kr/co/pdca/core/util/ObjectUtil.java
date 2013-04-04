package kr.co.pdca.core.util;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * <pre>
 * 객체 Util Class
 * </pre>
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 *
 */
@SuppressWarnings({ "rawtypes" })
public class ObjectUtil {

	public boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}

		if (object instanceof String) {
			String str = (String) object;
			return str.length() == 0;
		}

		if (object instanceof Collection) {
			Collection collection = (Collection) object;
			return collection.size() == 0;
		}

		if (object.getClass().isArray()) {
			try {
				if (Array.getLength(object) == 0) {
					return true;
				}
			} catch (Exception e) {
				// do nothing
			}
		}

		return false;
	}
}

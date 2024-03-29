package kr.co.pdca.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * View에서 공통적으로 사용하게 될 전역 변수를 설정
 * </pre>
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 *
 */
public class CommonWebFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		addCommonAttributes(request);
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	public static void addCommonAttributes(ServletRequest req) {
		HttpServletRequest request = (HttpServletRequest) req;
		String context = request.getContextPath();
		request.setAttribute("CONTEXT", context);
		request.setAttribute("styleUrl", context + "/css");
		request.setAttribute("imgUrl", context + "/resources/images");
		request.setAttribute("jsUrl", context + "/js");
	}

}

package kr.co.insoft.core.exception;

/**
 *  <pre>
 *  일반적인 오류를 처리하는 클래스
 *  </pre>
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 *
 */
public class GenericException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5467531161799157039L;
	private String message;

	public GenericException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

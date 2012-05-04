package com.platform.exception;

/**
 * <p>业务逻辑层异常类</p>
 * @version 1.0
 * @author <a href="mailto:ght616@126.com">GHT</a>
 * @since 2005-01-20
*/
 
public class ServiceException extends RuntimeException {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public ServiceException(Throwable cause) {
        super(cause);
    }
    
   
    public ServiceException(String message) {
        super(message);

    }

    public ServiceException(String msg, Throwable ex) {
        super(msg, ex);

    }
 
}
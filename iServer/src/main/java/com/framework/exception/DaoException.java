package com.framework.exception;
 
public class DaoException extends RuntimeException {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(Throwable ex) {
        super(ex);
    }
     
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String msg, Throwable ex) {
        super(msg, ex);
    }
 
}

package com.people.dptwb.tools;

import org.apache.log4j.Logger;

//包装类，封装日志文件参数。取代构造函数传参
public class PageFullLog {
	public static final Logger logger = Logger.getLogger(PageFullLog.class);

	private int userId;
	private String userName;
	private String nickName;
	private long accountId;
	private long contentId;
	private String className;
	private String methodName;
	private String param;
	private String logContent;
	private long logTime;

	public static class Builder {
		private int userId; // 必须参数
		private String logContent; // 必须参数
		private String userName = "";
		private String nickName = "";
		private long accountId = 0;
		private long contentId = 0;
		private String className = "";
		private String methodName = "";
		private String param = "";
		private long logTime = UtilTools.getTimestamp();

		public Builder(int userId, String logContent) {
			this.userId = userId;
			this.logContent = logContent;
		}

		public Builder userName(String val) {
			userName = val;
			return this;
		}

		public Builder nickName(String val) {
			nickName = val;
			return this;
		}

		public Builder accountId(long val) {
			accountId = val;
			return this;
		}

		public Builder contentId(long val) {
			accountId = val;
			return this;
		}

		public Builder className(String val) {
			className = val;
			return this;
		}

		public Builder methodName(String val) {
			methodName = val;
			return this;
		}

		public Builder param(String val) {
			param = val;
			return this;
		}

		public Builder logTime(long val) {
			logTime = val;
			return this;
		}

		public PageFullLog build() {
			return new PageFullLog(this);
		}
	}

	private PageFullLog(Builder builder) {
		userId = builder.userId;
		logContent = builder.logContent;
		userName = builder.userName;
		nickName = builder.nickName;
		accountId = builder.accountId;
		contentId = builder.contentId;
		className = builder.className;
		methodName = builder.methodName;
		param = builder.param;
		logTime = builder.logTime;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickName() {
		return nickName;
	}

	public long getAccountId() {
		return accountId;
	}

	public long getContentId() {
		return contentId;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getParam() {
		return param;
	}

	public String getLogContent() {
		return logContent;
	}

	public long getLogTime() {
		return logTime;
	}

}
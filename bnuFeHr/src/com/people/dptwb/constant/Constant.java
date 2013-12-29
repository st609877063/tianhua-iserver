package com.people.dptwb.constant;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;


/**
 * 系统常量类，用于存放系统整体的一些常量
 */
public class Constant {
	// COOKIE前缀名称
	public static final String BNUFE_COOKIE_PREFIX = "bnufe_gl_";
	public static final String BNUFE_COOKIE_UID = BNUFE_COOKIE_PREFIX + "uid";
	public static final String BNUFE_COOKIE_USERNAME = BNUFE_COOKIE_PREFIX + "username";
	public static final String BNUFE_COOKIE_NICKNAME = BNUFE_COOKIE_PREFIX + "nickname";
	public static final String BNUFE_COOKIE_ACCOUNTID = BNUFE_COOKIE_PREFIX + "accountid";
	public static final String BNUFE_COOKIE_ISADMIN = BNUFE_COOKIE_PREFIX + "isadmin";
	
	// 用户session数据常量
	public static final String SESSION_BNUFE_CONSTANT = "SESSOIN_BNUFE_USERINFO";
	// 随机验证码图片session数据常量
	public static final String SESSION_BNUFE_RANDCODE = "SESSOIN_BNUFE_RANDCODE";
	
	
	// 用户过期标志session数据常量
	public static final String SESSION_BNUFE_THIRDSET_RXPIRY_FLAG = "SESSION_BNUFE_THIRDSET_RXPIRY_FLAG";
	
	// 合法的用户昵称pattern
	public static final Pattern PATTERN_RIGHT_NICK_NAME = Pattern.compile("^([a-zA-Z0-9\u4e00-\u9fa5]{2,16})$");
	
	// 合法EMAIL地址的PATTERN
	private static final String STRING_RIGHT_EMAIL = "^\\w+([-+\\.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	public static final Pattern PATTERN_RIGHT_EMAIL = Pattern.compile(STRING_RIGHT_EMAIL);
	
	// 合法密码的PATTERN
	private static final String STRING_RIGHT_PASSWORD = "^([a-zA-Z0-9]{6,18})$";
	public static final Pattern PATTERN_RIGHT_PASSWORD = Pattern.compile(STRING_RIGHT_PASSWORD);

	// 用户注册用户名即手机号的合法PATTERN
	private static final String STRING_RIGHT_MOBILE = "^1\\d{10}$";
	public static final Pattern PATTERN_RIGHT_MOBILE = Pattern.compile(STRING_RIGHT_MOBILE);
	
	// 超级管理员COOKIE前缀
	public static final String PEOPLE_T_COOKIE_SUPERADMIN = BNUFE_COOKIE_PREFIX + "superadmin";
	// 管理员COOKIE前缀
	public static final String PEOPLE_T_COOKIE_ADMIN = BNUFE_COOKIE_PREFIX + "admin";

	// 日期格式对象，标准模式
	public static final SimpleDateFormat PEOPLE_T_CURDATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	// 系统ACTION请求后缀
	public static final String URL_PREFIX = ".action";

	// 登录、退出相关常量
	public static final int LOGIN_LOGININFO_INVALID = -3;
	public static final int LOGIN_WAIT_TO_CHECK = -2;
	public static final int LOGIN_FORBBIDEN = -1;
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_ACTIVE_SUCCESS = 2;

	// cookie加解密密钥
	public static final String STRING_CRYPT_KEY = "_yF$(1)2#m";
	public static final String DES = "DES";

	public static final String COOKIE_STARTWITH_STRING = "84ADF5035C13460E5"; // 多平台管理的COOKIE前缀

	public static final String BASE_PATH = System.getProperty("webappRoot").substring(0, System.getProperty("webappRoot").length()-1);

}
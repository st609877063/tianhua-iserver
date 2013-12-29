package com.people.dptwb.common;

import java.io.UnsupportedEncodingException;


/**
 * 
 * 汉字转化为全拼,码表利用输入法的码表导出，汉字信息非常全，平时见到的汉字
 * 转拼音的API只能处理几千个一级汉字，很多字都没有法查到，尤其是我自己的姓
 * 都查不到所以只好自己做一个汉字转拼音的API，该API除可以处理99％以上的汉字，
 * 而且可以返回多音字的读音
 */
public class CnToSpell {
	private CnToSpell() {
	}

	/**
	 * 获得单个汉字的Ascii，并用"-"连接成一个字符串
	 * 
	 * @param cn char 汉字字符
	 * @return string 错误返回 空字符串,否则返回ascii
	 */
	public static String getCnAscii(char cn) {
		byte[] bytes;
		try {
			bytes = (String.valueOf(cn)).getBytes("GBK");

			// System.out.println(bytes.length);
			if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
				return "";
			}
			if (bytes.length == 1) { // 英文字符
				return new String(bytes);
			}
			if (bytes.length == 2) { // 中文字符
				int hightByte = 256 + bytes[0];
				int lowByte = 256 + bytes[1];

				String ascii = hightByte + "-" + lowByte;

				// System.out.println("ASCII=" + ascii);

				return ascii;
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ""; // 错误		
	}


	/**
	 * 返回字符串的全拼,是汉字转化为全拼,其它字符不进行转换
	 * 
	 * @param cnStr String字符串
	 * @return String 转换成全拼后的字符串
	 */
	public static String getFullSpell(String cnStr)
	{
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			if (ascii.length() == 0) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				String spell = CnToSpellUtil.getSpellByAscii(ascii);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					if(spell.length()==1)
						retuBuf.append(spell.toUpperCase());
					else
						retuBuf.append(spell.substring(0, 1).toUpperCase()+spell.substring(1));
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for

		return retuBuf.toString();
	}

	/**
	 * 获取汉语字符串的声母组合，每个汉字取拼音的第一个字符组成的一个字符串
	 * @param cnStr 汉字的字符串
	 * @return 每个汉字拼音的第一个字母所组成的汉字
	 */
	public static String getFirstSpell(String cnStr)
	{
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			//System.out.println(ascii);
			if (ascii.length() == 1) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				
				String spell = CnToSpellUtil.getSpellByAscii(ascii).substring(0, 1);
				//System.out.println(spell);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell);
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
		return retuBuf.toString();
	}
	/**
	 * 获取汉语字符串的声母组合，每个汉字取拼音的第一个字符组成的一个字符串
	 * @param cnStr 汉字的字符串
	 * @return 每个汉字拼音的第一个字母所组成的汉字拼音的大写
	 */
	public static String getFirstSpellUpper(String cnStr)
	{
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			//System.out.println(ascii);
			if (ascii.length() == 1) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				//System.out.println(ascii);
				String spell = CnToSpellUtil.getSpellByAscii(ascii);
				//System.out.println(spell);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					spell = spell.substring(0, 1);
					if(spell == null){
						retuBuf.append(chars[i]);
					}else {
						retuBuf.append(spell);
					}
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
		return retuBuf.toString().toUpperCase();
	}
	/**
	 * 获取汉语字符串的声母组合，每个汉字取拼音的第一个字符组成的一个字符串
	 * @param cnStr 汉字的字符串
	 * @return 词组中第一个汉字的拼音的第一个字母的大写
	 */
	public static String getPrimoSpell(String cnStr)
	{
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			//System.out.println(ascii);
			if (ascii.length() == 1) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				//System.out.println(ascii);
				String spell = CnToSpellUtil.getSpellByAscii(ascii);
				//System.out.println(spell);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					spell = spell.substring(0, 1);
					if(spell == null){
						retuBuf.append(chars[i]);
					}else {
						retuBuf.append(spell);
					}
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
		return retuBuf.toString().toUpperCase().substring(0,1);
	}
	/**
	 * 获取汉语字符串的声母组合，每个汉字取拼音的第一个字符组成的一个字符串
	 * @param cnStr 汉字的字符串
	 * @return 词组的第一个声母+空格+汉字字符串；例如：郑州，return "Z 郑州"
	 */
	public static String getSelectSpell(String cnStr)
	{
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			//System.out.println(ascii);
			if (ascii.length() == 1) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				//System.out.println(ascii);
				String spell = CnToSpellUtil.getSpellByAscii(ascii);
				//System.out.println(spell);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					spell = spell.substring(0, 1);
					if(spell == null){
						retuBuf.append(chars[i]);
					}else {
						retuBuf.append(spell);
					}
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
		String spell =  retuBuf.toString().toUpperCase().substring(0,1);
		String returnValue = spell+" "+cnStr;
		return returnValue;
	}

    /**
     * 返回客人名字的全拼,是汉字转化为全拼,其它字符不进行转换(如果是英文名字，则变为英文大写)
     * 拼音全部为大写，并在名字的第一个字的拼音后加一个空格（出境频道专用）
     * @param cnStr String字符串
     * @return String 转换成全拼后的字符串
     */
    public static String getCustomerNameSpellUpper(String cnStr)
    {
        if (null == cnStr || "".equals(cnStr.trim())) {
            return cnStr;
        }

        char[] chars = cnStr.toCharArray();
        StringBuffer retuBuf = new StringBuffer();
        for (int i = 0, Len = chars.length; i < Len; i++) {
            
            String ascii = CnToSpell.getCnAscii(chars[i]);
            if (ascii.length() == 0) { // 取ascii时出错
                retuBuf.append(chars[i]);
            } else {
                String spell = CnToSpellUtil.getSpellByAscii(ascii);
                if (spell == null) {
                    retuBuf.append(chars[i]);
                } else {
                    retuBuf.append(spell.split(",")[0]);
                } // end of if spell == null
                if(i == 0 && ascii.length() > 1){
                    retuBuf.append(" ");
                }
            } // end of if ascii <= -20400
        } // end of for

        return retuBuf.toString().toUpperCase();
    }

	public static void main(String[] args) {
//		Date start = new Date();
//		System.out.println(start.getTime());
//		String str = null;
//		str = "逯 闫 乐";
//		System.out.println("Spell=" + CnToSpell.getFullSpell(str));
//		System.out.println("Spell=" + CnToSpell.getFirstSpell(str));
//		Date end = new Date();日本精华6日游（春节国航）(FBJP6CJ-050209)
//		System.out.println(end.getTime());
    //  System.out.println("Spell=" + CnToSpell.getSelectSpell("日本精华6日游（春节国航）(FBJP6CJ-050209)"));
		
		String test="北京欢迎你";  
		System.out.println(getSelectSpell(test));
		System.out.println(getPrimoSpell(test));
		System.out.println(getFirstSpellUpper(test));
		System.out.println(getFirstSpell(test));
		System.out.println(getFullSpell(test));

		System.out.println(getCnAscii('s'));
		
		System.out.println(DanYinHao(" tom's dog "));
	}
	
	public static String DanYinHao(String str) {
	    return str.replaceAll("/'","////'");
	}
}
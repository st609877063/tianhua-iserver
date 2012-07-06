package bnu.util;

import com.actionsoft.application.server.conf.*;

/**
 * ѡ���ťUI
 * 
 * @author jackliu
 * @version 2.2.1
 * @preserve �����˷�������JOC����
 */
public class HRCardButton {
	
	private StringBuffer _sb = new StringBuffer();

	/**
	 * @param title
	 *            ����
	 * @param onClick
	 *            �����¼�
	 * @param onOff
	 *            �Ƿ񱻰���ѡ��
	 * @preserve �����˷�������JOC����
	 */
	public HRCardButton(String title, String onClick, boolean onOff) {
		this(title, onClick, onOff, "");
	}

	/**
	 * @param title
	 *            ����
	 * @param onClick
	 *            �����¼�
	 * @param onOff
	 *            �Ƿ񱻰���ѡ��
	 * @preserve �����˷�������JOC����
	 */
	public HRCardButton(String title, String onClick, boolean onOff, String buttonStyle) {

		String bt = "";
		bt = (onOff == true) ? "class=\"bg1\"" : "class=\"bg2\"";
		this._sb.append("<td align=\"center\" nowrap=\"nowrap\" style='line-height: 25px;' ").append(bt).append("><a href=\"#\"").append("' onclick=\"return ").append(onClick).append(";return false;\">").append(title).append("</a></td>");
//		this._sb.append("<td valign='middle' height=22 width='80' class='actionsoftPageButton' background='").append(bt).append("' onclick=\"return ").append(onClick).append(";return false;\">").append(title).append("</td>");
	}

	/**
	 * @preserve �����˷�������JOC����
	 * @return ƴ�հ�ť��htmlƬ��
	 */
	public String toString() {
		return this._sb.toString();
	}
}

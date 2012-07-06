package bnu.util;

import com.actionsoft.application.server.conf.*;

/**
 * 选项卡按钮UI
 * 
 * @author jackliu
 * @version 2.2.1
 * @preserve 声明此方法不被JOC混淆
 */
public class HRCardButton {
	
	private StringBuffer _sb = new StringBuffer();

	/**
	 * @param title
	 *            标题
	 * @param onClick
	 *            单击事件
	 * @param onOff
	 *            是否被按下选中
	 * @preserve 声明此方法不被JOC混淆
	 */
	public HRCardButton(String title, String onClick, boolean onOff) {
		this(title, onClick, onOff, "");
	}

	/**
	 * @param title
	 *            标题
	 * @param onClick
	 *            单击事件
	 * @param onOff
	 *            是否被按下选中
	 * @preserve 声明此方法不被JOC混淆
	 */
	public HRCardButton(String title, String onClick, boolean onOff, String buttonStyle) {

		String bt = "";
		bt = (onOff == true) ? "class=\"bg1\"" : "class=\"bg2\"";
		this._sb.append("<td align=\"center\" nowrap=\"nowrap\" style='line-height: 25px;' ").append(bt).append("><a href=\"#\"").append("' onclick=\"return ").append(onClick).append(";return false;\">").append(title).append("</a></td>");
//		this._sb.append("<td valign='middle' height=22 width='80' class='actionsoftPageButton' background='").append(bt).append("' onclick=\"return ").append(onClick).append(";return false;\">").append(title).append("</td>");
	}

	/**
	 * @preserve 声明此方法不被JOC混淆
	 * @return 拼凑按钮的html片断
	 */
	public String toString() {
		return this._sb.toString();
	}
}

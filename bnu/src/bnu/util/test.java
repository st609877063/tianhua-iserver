package bnu.util;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test1 = "aaaaaaaaaaaaaaaaaaa <a href='' onclick=\"return gotoPage(frmMain,'XSLW',2)DDDDDDDDDDDDDDDDDDDDD";
		System.out.println("test1="+test1);
		String test2 = test1.replaceAll("frmMain", "frmXslw");
		System.out.println("test2="+test2);
	}

}

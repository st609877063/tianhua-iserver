
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtil
{
  public static NumberFormat numberFormat;
  public static DecimalFormat df;

  public static String formatNum(double paramDouble)
  {
    if (numberFormat == null)
      numberFormat = NumberFormat.getNumberInstance();
    String str1 = "" + paramDouble;
    String str2;
    if ((paramDouble < 99.0D) && (paramDouble > -99.0D))
    {
      numberFormat.setMaximumFractionDigits(2);
      numberFormat.setMaximumIntegerDigits(2);
      str2 = numberFormat.format(paramDouble);
    }
    else if (str1.indexOf("E") > 0)
    {
      str2 = str1.substring(0, str1.indexOf(".") + 3) + str1.substring(str1.indexOf("E"));
    }
    else if (paramDouble > 9999.0D)
    {
      str2 = str1.substring(0, 1) + "." + str1.substring(1, 3) + "E" + str1.indexOf(".");
    }
    else if (paramDouble < -9999.0D)
    {
      str2 = str1.substring(0, 2) + "." + str1.substring(2, 4) + "E" + (str1.indexOf(".") - 1);
    }
    else
    {
      numberFormat.setMaximumFractionDigits(1);
      numberFormat.setMaximumIntegerDigits(6);
      str2 = numberFormat.format(paramDouble);
    }
    return str2;
  }

  public static String formatNumforWrite(double paramDouble)
  {
    if (df == null)
      df = new DecimalFormat("#.###");
    return df.format(paramDouble);
  }

  public static String formatNumforWriteD(double paramDouble)
  {
    if (df == null)
      df = new DecimalFormat("#");
    return df.format(paramDouble);
  }

  public static String formatNumforWriteLong(double paramDouble)
  {
    if (df == null)
      df = new DecimalFormat("#.#########");
    return df.format(paramDouble);
  }

  public static String formatNumforWrite(float paramFloat)
  {
    if (df == null)
      df = new DecimalFormat("#");
    return df.format(paramFloat);
  }

  public static String formatNumforWrite1(float paramFloat)
  {
    if (df == null)
      df = new DecimalFormat("#.##");
    return df.format(paramFloat);
  }

  public static String formatNum(float paramFloat)
  {
    df = new DecimalFormat("#.####");
    return df.format(paramFloat);
  }

  public static String formatNumD(double paramDouble)
  {
    df = new DecimalFormat("#.##");
    return df.format(paramDouble);
  }

  public static String formatRate(double paramDouble)
  {
    df = new DecimalFormat("0.####");
    return df.format(paramDouble);
  }

  public static void main(String[] paramArrayOfString)
  {
    String str = formatRate(97.819600000000000000000000);
    System.out.println(str);
  }
}

/* Location:           C:\Documents and Settings\Administrator\桌面\资金系统\KondorRMB1104\KondorRMB1104.jar
 * Qualified Name:     com.gwsi.utils.NumberUtil
 * JD-Core Version:    0.5.3
 */
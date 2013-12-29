package com.people.dptwb.tools;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author mayq
 * @version 1.0
 */




public class Base64Encoder
{

    public Base64Encoder()
    {
    }

    public String encodeBuffer(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer("");
        if(abyte0 == null)
            return null;
        int i = abyte0.length / 3;       //循环次数
        int j = abyte0.length % 3;
        int k;
        //每3个字节执行一次编码过程
        for(k = 0; k < i; k++)
            encode64(abyte0, k * 3, 3, stringbuffer);

        //小于3的余数
        if(j > 0)
            encode64(abyte0, k * 3, j, stringbuffer);

        return stringbuffer.toString();
    }

    /**
     * 进行BASE64编码的方法
     * @param abyte0           源字符串的byte数组
     * @param i                循环起始位置（字节）
     * @param j                循环长度（字节）
     * @param stringbuffer     BASE64编码后的StringBuffer
     */
    private void encode64(byte abyte0[], int i, int j, StringBuffer stringbuffer)
    {
        byte byte0 = 0;
        byte byte1 = 3;
        int k = 0;

        //每一个字节进行1次循环
        for(int l = 2; k < j; l += 2)
        {
            //算法说明：
            //原数据的每3个字节为一个循环，BASE64后会变为4个字节
            //1、第一个字节右移2位，前两位补0，产生BASE64的第一个字节，
            //2、第一个字节左移6位，第二个字节右移4位，连接在一起，前两位补0，产生BASE64的第二个字节
            //3、第二个字节左移4位，第三个字节右移6位，连接在一起，前两位补0，产生BASE64的第三个字节
            //4、第三个字节左移6位，前两位补0，产生BASE64的第四个字节
            byte0 |= (abyte0[k + i] & 0xff) >> l;
            append64(stringbuffer, byte0);
            byte0 = (byte)(abyte0[k + i] & byte1);
            byte0 <<= 4 - k * 2;
            byte1 <<= 2;
            byte1 |= 3;
            k++;
        }

        append64(stringbuffer, byte0);
        for(; k < 3; k++)
            append64(stringbuffer, (byte)64);

    }


    /**
     * 组合产生的BASE64编码
     * @param stringbuffer    BASE64编码后的StringBuffer
     * @param byte0           欲添加的一位BASE64编码
     */
    private void append64(StringBuffer stringbuffer, byte byte0)
    {
        //BASE64编码算法
        //BASE64编码后产生的字符的ASCII值会介于0~63之间
        //0~25对应于"A....Z"，"26~51"对应于"a....z"，"52~61"对应于"0...9"
        //62对应于"+"，63对应于"/"
        //其余的变为"="
        if(byte0 < 26)
            stringbuffer.append((char)(byte0 + 65));
        else
        if(byte0 < 52)
            stringbuffer.append((char)((byte0 - 26) + 97));
        else
        if(byte0 < 62)
            stringbuffer.append((char)((byte0 - 52) + 48));
        else
        if(byte0 == 62)
            stringbuffer.append('+');
        else
        if(byte0 == 63)
            stringbuffer.append('/');
        else
            stringbuffer.append('=');
    }
}
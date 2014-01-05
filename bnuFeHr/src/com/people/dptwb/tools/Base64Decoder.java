package com.people.dptwb.tools;

/**
 * <p>Title: Base64Decoder.java</p>
 * <p>Description: BASE64解码类</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Itown.Co.Ltd</p>
 * @author mayq
 * @version 1.0
 */



import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Base64Decoder
{

    public Base64Decoder()
    {
    }


    //算法说明：
    //原数据的每3个字节为一个循环，BASE64后会变为4个字节
    //1、第一个字节右移2位，前两位补0，产生BASE64的第一个字节，
    //2、第一个字节左移6位，第二个字节右移4位，连接在一起，前两位补0，产生BASE64的第二个字节
    //3、第二个字节左移4位，第三个字节右移6位，连接在一起，前两位补0，产生BASE64的第三个字节
    //4、第三个字节左移6位，前两位补0，产生BASE64的第四个字节



    /**
     * BASE64解码函数
     * @param s              BASE64编码的字符串
     * @return
     * @throws IOException
     */
    public byte[] decodeBuffer(String s)
        throws IOException
    {
        if(s == null)
            return null;
        int i = 0;
        byte abyte0[] = new byte[4];
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        int j = s.length();           //字符串总长度
        int k = 0;
        do
        {
            int l;
            for(l = 0; l < 4 && k < j; k++)
            {
                char c = s.charAt(k);
                if(c >= 'A' && c <= 'Z')
                    abyte0[l] = (byte)(c - 65);
                else
                if(c >= 'a' && c <= 'z')
                    abyte0[l] = (byte)((26 + c) - 97);
                else
                if(c >= '0' && c <= '9')
                    abyte0[l] = (byte)((52 + c) - 48);
                else
                if(c == '=')
                    i++;
                else
                if(c == '+')
                {
                    abyte0[l] = 62;
                } else
                {
                    if(c != '!' && c != '/')
                        continue;
                    abyte0[l] = 63;
                }
                l++;

            }

            if(l > 0)
            {
                if(l != 4 || i > 0 && k != j)
                {
                    bytearrayoutputstream.close();
                    throw new IOException("illegal word");
                }
                decode(bytearrayoutputstream, abyte0, i);
            } else
            {
                byte abyte1[] = bytearrayoutputstream.toByteArray();
                bytearrayoutputstream.close();
                return abyte1;
            }
        } while(true);
    }


    private void decode(ByteArrayOutputStream bytearrayoutputstream, byte abyte0[], int i)
    {
        int j = 0;
        for(int k = 1; i < 3; k++)
        {
            byte byte0 = abyte0[j];
            byte0 <<= k * 2;
            byte0 |= abyte0[k] >> 4 - j * 2;
            bytearrayoutputstream.write(byte0);
            i++;
            j++;
        }

    }
}
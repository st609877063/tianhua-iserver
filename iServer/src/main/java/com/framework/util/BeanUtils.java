package com.framework.util;

public class BeanUtils {
        public static void copy(Object des,Object src){
                try{
                        org.apache.commons.beanutils.BeanUtils.copyProperties(des,src);
                }catch(Exception e){
                        e.printStackTrace();
                }
        }
}
// vim: ft=java


package com.kyh.msm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by kongyunhui on 16/10/8.
 */
public class Utils {
    public static void main(String[] args) throws Exception{
//        String s = new String("中国abc".getBytes("utf-8"), "utf-8");
//        System.out.println(getEncoding(s));
//        System.out.println(new String(s.getBytes("iso-8859-1"),"utf-8"));

        List<Map<String, Object>> listMap1 = null;
        List<Map<String, Object>> listMap2 = new ArrayList<Map<String, Object>>();
        for(int i=0; i<5;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("i", i);
            map.put("content", "xxx");
            listMap2.add(map);
        }
        List<Map<String, Object>> listMapAll = new ArrayList<Map<String, Object>>();
        if(listMap1!=null && listMap1.size()>0) listMapAll.addAll(listMap1);
        if(listMap2!=null && listMap2.size()>0) listMapAll.addAll(listMap2);
        System.out.println(listMapAll);
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
}

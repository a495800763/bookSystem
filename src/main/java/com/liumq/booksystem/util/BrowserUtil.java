package com.liumq.booksystem.util;

public class BrowserUtil {

    /**
     * 检测浏览器是不是极速模式
     * @param userAgent
     * @return  true 极速模式 false 兼容模式
     */
    public static boolean checkUserAgent (String userAgent)
    {
        boolean flag = true;
        userAgent = userAgent.toLowerCase();
        if(userAgent.indexOf("trident")!=1)
        {
            flag =false;
        }
        return flag;
    }
}

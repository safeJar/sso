/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.sso.client.demo;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Carl
 * @date 2017/9/28
 * @since 1.5.0
 */
public class SimpleUrlsPatternMatcherStrategy implements UrlPatternMatcherStrategy {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean matches(String url) {


        if(url.contains("/logout")){
            return true;
        }

        List<String> list = Arrays.asList(
                "/",
                "/index",
                "/favicon.ico",
                "/zhangsan.jsp",
                "/code.js",
                "/jquery-1.7.1.js",
                "/getExecution.jhtml"
        );

        String name = url.substring(url.lastIndexOf("/"));
        if (name.indexOf("?") != -1) {
            name = name.substring(0, name.indexOf("?"));
        }

        System.out.println("name：" + name);
        boolean result = list.contains(name);
        if (!result) {
            System.out.println("拦截URL：" + url);
        }
        return result;
        //logger.debug("访问路径：" + url);
        //return url.contains("zhangsan.jsp");
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }


    private String pattern;

    public SimpleUrlsPatternMatcherStrategy() {
    }

    public SimpleUrlsPatternMatcherStrategy(String pattern) {
        this.setPattern(pattern);
    }


}

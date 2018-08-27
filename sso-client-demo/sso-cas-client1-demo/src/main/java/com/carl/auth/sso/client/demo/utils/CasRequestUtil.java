package com.carl.auth.sso.client.demo.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CasRequestUtil {


    private static final String ENCODE ="utf-8";


    public static Map<String, String> validate(HttpServletRequest req) throws Exception {
        String loginName = req.getParameter("username");
        String password = req.getParameter("password");
        boolean rememberMe = false;
        if (StringUtils.isNotBlank(req.getParameter("rememberMe"))) {
            rememberMe = true;
        }
        return validate(loginName, password, rememberMe);
    }

    /**
     *
     * @param loginUrl
     *            https://passport.hope.com:8443/cas/login
     * @param username
     *            张三
     * @param password
     *            111
     * @return
     * @throws Exception
     */
    public static Map<String, String> validate(String loginName, String password, boolean rememberMe) throws Exception {
        String loginUrl = "https://passport.hope.com:8443/cas/login";
        //LOG.info("[{}]开始登录", loginName);
        HttpClient httpclient = new HttpClient();
        PostMethod method = new PostMethod();
        Map<String, String> params = getParams(loginUrl);
        String execution = params.get("execution");
        String cookie = params.get("Set-Cookie");
        String reqCnt = "username=" + URLEncoder.encode(loginName,ENCODE) +
                "&password=" + URLEncoder.encode(password,ENCODE) + "&execution=" + execution + "&_eventId=submit&submit=LOGIN";
        if (rememberMe) {
            reqCnt = reqCnt + "&rememberMe=true";
        }
        method.setRequestBody(reqCnt);
        method.setFollowRedirects(false);
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        method.addRequestHeader(new Header("Cookie", cookie));
        method.setPath(loginUrl);
        httpclient.executeMethod(method);
        System.out.println(method.getRequestEntity());
        String casTgtCookie = null;
        Header tgtHead = method.getResponseHeader("Set-Cookie");
        if (tgtHead != null) {
            casTgtCookie = tgtHead.getValue();
        }
        Map<String, String> resp = new HashMap();
        resp.put("TGC", casTgtCookie);
        resp.put("msg", "登录成功");
        resp.put("JSESSIONID",StringUtils.substringBetween(cookie,"JSESSIONID=","; Path="));
        resp.put("JSESSIONIDSTR",cookie.replace("Path=/cas","Path=/"));
        System.out.println(casTgtCookie);
        String respStr = method.getResponseBodyAsString();
        if (StringUtils.contains(respStr, "登录成功")) {
            String ticket = StringUtils.substringBetween(tgtHead.getValue(), "TGC=", "; Max-Age");
            resp.put("ticket", ticket);
            return resp;
        }
        Document doc = Jsoup.parse(respStr);
        String msg = doc.select("#fm1 .alert").text();
        resp.put("msg", msg);

        return resp;

    }

    public static void main(String[] args) throws Exception {
        validate("tokangning@163.com", "1234", false);
    }

    static Map<String, String> getParams(String loginUrl) throws Exception {
        Map<String, String> params = new HashMap();
        HttpClient httpclient = new HttpClient();
        HttpMethod method = new GetMethod();
        method.setPath(loginUrl);
        method.setFollowRedirects(false);
        httpclient.executeMethod(method);
        String cont = method.getResponseBodyAsString();
        Document doc = Jsoup.parse(cont);
        params.put("Set-Cookie", method.getResponseHeader("Set-Cookie").getValue());
        params.put("execution", doc.getElementsByAttributeValue("name", "execution").attr("value"));
        return params;
    }




    public static Map<String, String> login(String loginName, String password, String execution) throws Exception {
        String loginUrl = "https://passport.hope.com:8443/cas/login";
        HttpClient httpclient = new HttpClient();
        PostMethod method = new PostMethod();
        String reqCnt = "username=" + URLEncoder.encode(loginName,ENCODE) +
                "&password=" + URLEncoder.encode(password,ENCODE) + "&execution=" + execution + "&_eventId=submit&submit=LOGIN";
        method.setRequestBody(reqCnt);
        method.setFollowRedirects(false);
        method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        //method.addRequestHeader(new Header("Cookie", cookie));
        method.setPath(loginUrl);
        httpclient.executeMethod(method);
        String casTgtCookie = null;
        Header tgtHead = method.getResponseHeader("Set-Cookie");
        if (tgtHead != null) {
            casTgtCookie = tgtHead.getValue();
        }
        Map<String, String> resp = new HashMap();
        resp.put("TGC", casTgtCookie);
        resp.put("msg", "登录成功");
        String respStr = method.getResponseBodyAsString();
        if (StringUtils.contains(respStr, "登录成功")) {
            String ticket = StringUtils.substringBetween(tgtHead.getValue(), "TGC=", "; Max-Age");
            resp.put("ticket", ticket);
            return resp;
        }
        Document doc = Jsoup.parse(respStr);
        String msg = doc.select("#fm1 .alert").text();
        resp.put("msg", msg);

        return resp;

    }


}
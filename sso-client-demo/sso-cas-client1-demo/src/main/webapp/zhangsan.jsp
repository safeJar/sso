<%@ page import="com.carl.auth.sso.client.demo.utils.WebUtils" %>
<%@ page import="java.util.HashMap" %><%--
  ~ 版权所有.(c)2008-2017. 卡尔科技工作室
  --%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>张三</title>
    <script type="text/javascript" src="http://localhost:9090/static/jquery-1.7.1.js" ></script>
    <script type="text/javascript" src="http://localhost:9090/static/code.js" ></script>
    <%
        String url = "https://passport.hope.com:8443/cas/login?service=http%3A%2F%2Flocalhost%3A9090%2Fwangwu.jsp";
        String rr = WebUtils.httpsRequest(url,"GET",new HashMap<String, Object>());
        rr=rr.substring(rr.indexOf("value")+7,rr.lastIndexOf("\""));
    %>
</head>
<body>
可以不登录就访问
    <form method="post" action="https://passport.hope.com:8443/cas/login?service=http%3A%2F%2Flocalhost%3A9090">
        <input type="text" name="username" id="username" /><br/>
        <input type="text" name="password" id="password" /><br/>
        <input type="hidden" name="_eventId" value="submit"/>
        <input type="hidden" name="execution" value="<%=rr%>"/>
        <input type="hidden" name="geolocation"/>
        <input type="button" value="登录" id="btn_login" onclick="login()" class="btn_login"/>
    </form>


<div id="temp" >

</div>
</body>
</html>

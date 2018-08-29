package com.carl.auth.sso.client.demo.controller;

import com.carl.auth.sso.client.demo.utils.CasRequestUtil;
import com.carl.auth.sso.client.demo.utils.WebUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {




    @GetMapping("/hi")
    @ResponseBody
    public String getExecution(){


        return "ok";
    }


    @RequestMapping("home")
    public String home(Model model){
        String url = "https://passport.hope.com:8443/cas/login?service=http%3a%2f%2flocalhost%3a9090%2fuser%2fcaslogin.jhtml";
        String rr = WebUtils.httpsRequest(url,"GET",new HashMap<String, Object>());
        rr=rr.substring(rr.indexOf("value")+7,rr.lastIndexOf("\""));
        model.addAttribute("execution",rr);
        return "home";
    }




    @RequestMapping(value="login",method = RequestMethod.POST)
    public String login(HttpServletResponse httpServletResponse,
                        HttpServletRequest request,
                        String username, String password,
                        String execution,Model model){
        try {
            Map<String,String> result =CasRequestUtil.login(username,password,execution);
            model.addAttribute("msg",result.get("msg"));
            model.addAttribute("TGC",result.get("TGC"));
            model.addAttribute("ticket",result.get("ticket"));
            httpServletResponse.addHeader("Set-Cookie",result.get("TGC"));


            /*Map<String,String> result =CasRequestUtil.validate(username,password,false);
            Cookie tgccookie = new Cookie("tgc",result.get("ticket"));
            tgccookie.setMaxAge(1209600);
            tgccookie.setPath("/cas");
            tgccookie.setHttpOnly(true);
            httpServletResponse.addCookie(tgccookie);

            Cookie JsessionId = new Cookie("JSESSIONID",result.get("JSESSIONID"));
            JsessionId.setSecure(true);
            JsessionId.setPath("/cas");
            JsessionId.setMaxAge(1209600);
            JsessionId.setHttpOnly(true);
            httpServletResponse.addCookie(JsessionId);
            //httpServletResponse.addHeader("Set-Cookie",result.get("JSESSIONIDSTR"));
            System.out.println("JSESSIONID:"+request.getSession().getId());
            model.addAttribute("msg",result.get("msg"));
            model.addAttribute("TGC",result.get("TGC"));
            model.addAttribute("ticket",result.get("ticket"));
            model.addAttribute("JSESSIONID",request.getSession().getId());*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }


    @RequestMapping("caslogin")
    public void caslogin(HttpServletRequest request,HttpServletResponse httpServletResponse,String ticket){
        Principal userPrincipal = request.getUserPrincipal();
        String result = "flightHandler('"+userPrincipal.getName()+"');";
        try {
            httpServletResponse.getOutputStream().print(result);
        } catch (IOException e) {

        }

    }

}
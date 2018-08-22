/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.controller;

import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.profile.CasProfile;
import org.pac4j.cas.profile.CasRestProfile;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Carl
 * @date 2017/9/16
 * @since 1.0.0
 */
@Controller
public class IndexController {
    @Autowired
    private JwtGenerator generator;

    @Autowired
    private CasRestFormClient casRestFormClient;

    @Value("${cas.serviceUrl}")
    private String serviceUrl;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        //用户详细信息
        Principal principal = request.getUserPrincipal();
        model.addAttribute("user", principal);
        //打开index.html页面
        return "index";
    }

    @GetMapping("/user/details")
    @ResponseBody
    public Object detail(HttpServletRequest request) {
        return request.getUserPrincipal();
    }


    @RequestMapping("/user/login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        J2EContext context = new J2EContext(request, response);
        final ProfileManager<CasRestProfile> manager = new ProfileManager(context);
        final Optional<CasRestProfile> profile = manager.get(true);
        //获取ticket
        TokenCredentials tokenCredentials = casRestFormClient.requestServiceTicket(serviceUrl, profile.get(), context);
        //根据ticket获取用户信息
        final CasProfile casProfile = casRestFormClient.validateServiceTicket(serviceUrl, tokenCredentials, context);
        //生成jwt token
        String token = generator.generate(casProfile);
        model.put("token", token);
        //需要将token管理
        return new HttpEntity<>(model);
    }

}

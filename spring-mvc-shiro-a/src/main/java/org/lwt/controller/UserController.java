package org.lwt.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLogin() {
        return "login";
    }
    @RequestMapping(value="/403", method=RequestMethod.GET)
    public String fobibden() {
        return "403";
    }
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam String username, 
            @RequestParam String password, HttpSession httpSession) {
        System.out.println(username+":"+password);
        if(!SecurityUtils.getSubject().isRemembered() || !SecurityUtils.getSubject().isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                
                SecurityUtils.getSubject().login(token);
            } catch (UnknownAccountException e) {
                System.out.println("用户名不存在。");
                // 用户名不存在
                //e.printStackTrace();
                return "redirect:login";
            } catch(IncorrectCredentialsException e) {
                System.out.println("密码错误。");
                // 密码错误
                return "redirect:login";
            }catch(ExcessiveAttemptsException e) {
                System.out.println("超出登录次数限制。");
                return "redirect:login";
            }
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("username", username);
        //httpSession.setAttribute("username", username);
        return "redirect:index";
    }
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }
}

package org.lwt.test;




import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ShiroTest {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroTest.class);
    public static void main(String[] args) {
        
        log.info("开始执行main方法。");
        // 1. 获取SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        // 2. 设置SecurityUtils,设置一个全局securityManager
        SecurityUtils.setSecurityManager(securityManager);
        // 3. 获取当前的用户主题
        Subject currentUser = SecurityUtils.getSubject();
        
        // 4. 获取当前用户对应的session对象，将用户标识设置到session中
        Session session = currentUser.getSession();
        session.setAttribute("userKey", "alice");
        String value = (String) session.getAttribute("userKey");
        if (value.equals("alice")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }
        // 5. 进行身份认证，登陆
        if(!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("alice","123456");
            token.setRememberMe(true);          // 记住我
            try {
                currentUser.login(token);
            }catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }
        // 6. 获取用户的身份信息
        String username = (String) currentUser.getPrincipal();
        log.info("User [" + username + "] logged in successfully.");
        // 7. 获取角色
        if(currentUser.hasRole("admin")) {
            log.info("May the admin be with you!");
        }else {
            log.info("Hello, mere mortal.");
        }
        // 8. 获取权限
        if(currentUser.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        }else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        // 9. 退出登陆
        currentUser.logout();
        /*AesCipherService cipherService = new AesCipherService();
        cipherService.setKeySize(256);
      //create a test key:
        byte[] testKey = cipherService.generateNewKey();
        
        //encrypt a file’s bytes:
        byte[] encrypted =
            cipherService.encrypt(fileBytes, testKey);*/
    }
}

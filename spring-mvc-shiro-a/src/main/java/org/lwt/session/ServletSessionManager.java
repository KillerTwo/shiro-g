package org.lwt.session;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class ServletSessionManager extends DefaultWebSessionManager {

    @Override
    public boolean isServletContainerSessions() {
        // 返回false表示使用servlet的session,返回true表示使用shiro自己的session。
        return false;
    }
    
}

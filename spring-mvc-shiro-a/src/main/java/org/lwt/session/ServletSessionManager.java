package org.lwt.session;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class ServletSessionManager extends DefaultWebSessionManager {

    @Override
    public boolean isServletContainerSessions() {
        // ����false��ʾʹ��servlet��session,����true��ʾʹ��shiro�Լ���session��
        return false;
    }
    
}

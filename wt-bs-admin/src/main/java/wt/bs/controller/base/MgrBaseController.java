package wt.bs.controller.base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wt.bs.exception.BsAssert;
import wt.bs.vo.MgrSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MgrBaseController {

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected void writeSession(MgrSession mgrSession) {
        HttpSession session = getRequest().getSession(true);
        session.setAttribute("userId", mgrSession.getId());
        session.setAttribute("loginName", mgrSession.getLoginName());
        session.setAttribute("identity", mgrSession.getIdentity());
    }

    protected void delSession() {
        HttpSession session = getRequest().getSession(true);
        session.removeAttribute("userId");
        session.removeAttribute("loginName");
        session.removeAttribute("identity");

    }

    /**
     * 获取登录名称
     */
    protected String getUserName() {
        HttpServletRequest request = this.getRequest();
        return request.getSession().getAttribute("loginName") + "";
    }

    /**
     * 获取登录userId
     */
    protected Long getUserId() {
        HttpServletRequest request = this.getRequest();
        String userId = request.getSession().getAttribute("userId") + "";
        BsAssert.isBlank(userId, "userId为空");
        return Long.parseLong(userId);
    }

    /**
     * 获取登录身份
     */
    protected String getIdentity() {
        HttpServletRequest request = this.getRequest();
        return request.getSession().getAttribute("identity") + "";
    }
}

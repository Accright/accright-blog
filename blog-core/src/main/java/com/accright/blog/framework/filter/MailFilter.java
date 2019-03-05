package com.accright.blog.framework.filter;

import org.apache.http.util.TextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@WebFilter(filterName="MailFilter",urlPatterns = "/mail")
public class MailFilter implements Filter {

    protected String fail = "https://www.accright.com/error/wechatError";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        //针对csrf攻击，对http referer校验
        String referer=req.getHeader("Referer");
        if (!TextUtils.isEmpty(referer)){
            if (referer.startsWith("https://servicewechat.com/wx9648d5e82288f4d1/")){
                filterChain.doFilter(request, response);
            }else {
                wrapper.sendRedirect("/error/wechatError");
                return;
            }
        }else {
            wrapper.sendRedirect("/error/wechatError");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}

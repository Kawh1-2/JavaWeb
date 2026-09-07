package com.ss.fliter;

import com.ss.utils.CurrentHolder;
import com.ss.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 令牌过滤器, 拦截请求验证令牌
 */
@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 登录请求放行
        String requestURI = request.getRequestURI();    // 获取请求的URI(资源访问路径)
        if (requestURI.contains("/login")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }
        // 获取令牌
        String token = request.getHeader("token");
        // 验证令牌是否存在，如果不存在，返回401
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401, uri: {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 验证令牌是否有效，如果无效，返回401
        try {
            Claims claims = JwtUtils.parseToken(token);// 解析令牌
            Integer id = Integer.valueOf(claims.get("id").toString());  // 从令牌中获取用户ID
            CurrentHolder.setCurrentId(id);     // 存入用户ID
            log.info("当前员工ID: {}，将其存入ThreadLocal", id);
        } catch (Exception e) {
            log.info("令牌无效，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        log.info("令牌存在，放行");
        filterChain.doFilter(request, response);

        CurrentHolder.remove();  // 移除当前线程的ThreadLocal数据
    }
}

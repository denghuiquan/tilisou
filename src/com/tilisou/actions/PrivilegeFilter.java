package com.tilisou.actions;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tilisou.beans.Admin;
import com.tilisou.utils.WebUtil;

/**
 * 管理员后台权限过滤器
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 上午11:01:42
 *
 */
public class PrivilegeFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		Admin admin = WebUtil.getAdmin(request);
		if(admin==null){//如果员工未登录，重定向到员工登陆界面
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect("/admin/logon.do");
		}else{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}

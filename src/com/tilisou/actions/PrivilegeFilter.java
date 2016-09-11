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
 * ����Ա��̨Ȩ�޹�����
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 ����11:01:42
 *
 */
public class PrivilegeFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		Admin admin = WebUtil.getAdmin(request);
		if(admin==null){//���Ա��δ��¼���ض���Ա����½����
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect("/admin/logon.do");
		}else{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}

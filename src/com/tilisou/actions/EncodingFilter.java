package com.tilisou.actions;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.velocity.app.Velocity;

import com.tilisou.beans.Grade;
import com.tilisou.beans.Subject;
import com.tilisou.viewmodel.GradeConverter;
import com.tilisou.viewmodel.SubjectConverter;

/**
 * 编码过滤器，用于设置访问请求的编码格式
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-30 下午12:35:12
 * 
 */
public class EncodingFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("UTF-8");
		filterchain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		ConvertUtils.register(new GradeConverter(), Grade.class);
		ConvertUtils.register(new SubjectConverter(), Subject.class);
		/**
		 * Velocity 模版静态化
		 */
		try {
			Properties prop = new Properties();
			prop.put(
					"runtime.log",
					config.getServletContext().getRealPath(
							"/WEB-INF/log/velocity.log"));
			prop.put("file.resource.loader.path", config.getServletContext()
					.getRealPath("/WEB-INF/vm"));
			prop.put("input.encoding", "UTF-8");
			prop.put("output.encoding", "UTF-8");
			Velocity.init(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

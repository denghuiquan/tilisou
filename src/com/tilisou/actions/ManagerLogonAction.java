package com.tilisou.actions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.tilisou.service.IAdminService;
import com.tilisou.viewmodel.AdminForm;
/**
 * 管理员登录
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 上午10:59:12
 */
@Controller("/admin/logon")
public class ManagerLogonAction extends Action {
	@Resource IAdminService adminService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminForm formbean = (AdminForm)form;
		//1>显示登录界面(当用户没有提供用户名及密码的时候) 2>实现用户名及密码的校验
		if(formbean.getManagername()!=null && !"".equals(formbean.getManagername().trim())
				&& formbean.getPassword()!=null && !"".equals(formbean.getPassword().trim())){
			if(adminService.validate(formbean.getManagername().trim(),formbean.getPassword().trim())){
				request.getSession().setAttribute("admin", adminService.find(formbean.getManagername().trim()));
				return mapping.findForward("main");
			}else{
				request.setAttribute("message", "管理员名或密码有误");
			}
		}
		return mapping.findForward("logon");
	}
}

package com.tilisou.actions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.tilisou.service.IAdminService;
import com.tilisou.utils.SiteUrl;
import com.tilisou.viewmodel.AdminForm;
/**
 * ����Ա����
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 ����11:00:25
 */
@Controller("/control/admin/manage")
public class ManagerManageAction extends DispatchAction {
	@Resource IAdminService adminService;		
	/**
	 * ���������޸Ľ���	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editpasswordUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		return mapping.findForward("editpassword");
	}
	
	/**
	 * �޸Ĺ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editpassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		AdminForm formbean = (AdminForm)form;
		adminService.updatePassword(formbean.getManagername(),formbean.getPassword());				
		request.setAttribute("message", "���������޸ĳɹ�");		
		request.setAttribute("urladdress", SiteUrl.readUrl("control.center.main"));
		return mapping.findForward("message");
	}
}

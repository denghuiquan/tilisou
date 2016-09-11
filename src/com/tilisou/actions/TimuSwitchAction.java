package com.tilisou.actions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.tilisou.service.ITimuService;
import com.tilisou.utils.WebUtil;

/**
 * ��Ŀ�����ʷ������
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-1 ����4:40:58
 *	<br/>��Ҫ����ӦAjax����
 */
@Controller("/timu/switch")
public class TimuSwitchAction extends DispatchAction {
	@Resource(name = "timuServiceBean")
	private ITimuService timuService;
	private static Logger logger=Logger.getLogger("ProductSwitchAction");

	/**
	 * ��ȡ10���û����������Ŀ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getViewHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cookieValue = WebUtil.getCookieByName(request, "timuViewHistory");
		//12- 13 -14 -16-17 �������Ʒid
		if(cookieValue!=null && !"".equals(cookieValue.trim())){			
			String[] ids = cookieValue.split("-");
			Integer[] tids = new Integer[ids.length];
			for(int i=0 ;i<ids.length; i++){
				tids[i]=new Integer(ids[i].trim());
			}
			try {
				/*timuService.getViewHistory(tids, 10);*/
				request.setAttribute("viewHistory",timuService.getViewHistory(tids, 10));
			} catch (Exception e) {
				logger.error("��ȡ���ݴ���"+e.toString());
				return mapping.findForward("viewHistory");
			}
		}
		return mapping.findForward("viewHistory");
	}
}

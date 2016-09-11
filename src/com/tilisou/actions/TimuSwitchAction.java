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
 * 题目浏览历史控制器
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-1 下午4:40:58
 *	<br/>主要是响应Ajax请求
 */
@Controller("/timu/switch")
public class TimuSwitchAction extends DispatchAction {
	@Resource(name = "timuServiceBean")
	private ITimuService timuService;
	private static Logger logger=Logger.getLogger("ProductSwitchAction");

	/**
	 * 获取10个用户浏览过的题目
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
		//12- 13 -14 -16-17 浏览的商品id
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
				logger.error("获取数据错误："+e.toString());
				return mapping.findForward("viewHistory");
			}
		}
		return mapping.findForward("viewHistory");
	}
}

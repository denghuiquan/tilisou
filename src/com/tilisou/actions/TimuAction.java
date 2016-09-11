package com.tilisou.actions;

import java.util.LinkedHashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.stereotype.Controller;

import com.tilisou.beans.Timu;
import com.tilisou.service.ITimuSearchService;
import com.tilisou.service.ITimuService;
import com.tilisou.viewmodel.PageView;
import com.tilisou.viewmodel.TimuForm;


/**
 * 后台题目列表查看
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 上午11:02:24
 *
 */
@Controller("/control/timu/list")
public class TimuAction extends Action {
	
	@Resource(name = "timuSearchServiceBean")
	ITimuSearchService timuSearchService;
	
	@Resource
	private ITimuService timuService;
	
	private static Logger logger=Logger.getLogger("TimuAction");
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimuForm formbean = (TimuForm) form;
		PageView<Timu> pageView = new PageView<Timu>(12,
				formbean.getPage());
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("tId", "desc");

		if ("true".equals(formbean.getQuery())) {
			
			String keywordString= null;
			if (formbean.getWord()!=null&&!"".equals(formbean.getWord().trim())) {
				keywordString=formbean.getWord().trim();
				request.setAttribute("word", keywordString);
				
				try {
					pageView.setQueryResult(timuSearchService.query(
							timuSearchService.query(keywordString,
									pageView.getFirstResult(), pageView.getMaxresult()),
							pageView.getFirstResult(), pageView.getMaxresult()));
					request.setAttribute("query", "true");
					request.setAttribute("word", keywordString);
					request.setAttribute("pageView", pageView);
					return mapping.findForward("list");
				} catch (Exception e) {
					logger.error("更新数据错误：" + e.toString());
					request.setAttribute("message", "Sorry,本次搜题失败");
					request.setAttribute("pageView", pageView);
					System.out.println("5...");
					return mapping.findForward("list");
				}
			}else {
				try {
					pageView.setQueryResult(timuService.getScrollData(
							pageView.getFirstResult(), pageView.getMaxresult(), orderby));
				} catch (Exception e) {
					logger.error("获取数据错误："+e.toString());
					return mapping.findForward("list");
				}
			}			
			
		} else {
			try {
				pageView.setQueryResult(timuService.getScrollData(
						pageView.getFirstResult(), pageView.getMaxresult(), orderby));
			} catch (Exception e) {
				logger.error("获取数据错误："+e.toString());
				return mapping.findForward("list");
			}
		}
		request.setAttribute("pageView", pageView);
		return mapping.findForward("list");
	}
}

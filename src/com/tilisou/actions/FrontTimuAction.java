package com.tilisou.actions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.tilisou.service.ITimuService;
import com.tilisou.utils.WebUtil;
import com.tilisou.viewmodel.PageView;
import com.tilisou.viewmodel.TimuForm;

/**
 * 用户前台题目显示控制器
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-1 上午9:00:47
 * 
 */
@Controller("/timu/list/display")
public class FrontTimuAction extends Action {

	@Resource(name = "timuServiceBean")
	private ITimuService timuService;

	private static Logger logger = Logger.getLogger("FrontTimuAction");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimuForm formbean = (TimuForm) form;
		PageView<Timu> pageView = new PageView<Timu>(10, formbean.getPage());
		pageView.setPagecode(10);
		int firstindex = (pageView.getCurrentpage() - 1)
				* pageView.getMaxresult();
		// 构建排序条件键值对
		LinkedHashMap<String, String> orderby = buildOrder(formbean.getSort());
		// 构建where查询条件
		StringBuffer jpql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (formbean.getGrade() != null&&!"".equals(formbean.getGrade())) {	
			if (params.size() > 0) {
				jpql.append(" and ");
			}
			jpql.append(" o.grade=?").append(params.size() + 1);
			params.add(formbean.getGrade());
			request.setAttribute("grade", formbean.getGrade());			
		}
		if (formbean.getSubject() != null&&!"".equals(formbean.getSubject())) {
			if (params.size() > 0) {
				jpql.append(" and ");
			}
			jpql.append("o.subject=?").append(params.size() + 1);
			params.add(formbean.getSubject());
			request.setAttribute("subject", formbean.getSubject());
		}
		if (formbean.getType()!= null&&!"".equals(formbean.getType().trim())) {
			if (params.size() > 0) {
				jpql.append(" and ");
			}
			jpql.append("o.type=?").append(params.size() + 1);
			params.add(new String(formbean.getType().getBytes("iso-8859-1"),"utf-8"));
		}
		try {
			//设置返回页面容器的查询结果内容
			pageView.setQueryResult(timuService.getScrollData(firstindex,
					pageView.getMaxresult(), jpql.toString(), params.toArray(),
					orderby));
		} catch (Exception e) {
			logger.error("获取数据错误：" + e.toString());
			return mapping.findForward("list");
		}

		for (Timu timu : pageView.getRecords()) {
			// 注意:执行此句代码会把修改后的数据同步回数据库,如果不想把数据同步回数据库,请在其后调用productInfoService.clear();
			timu.setProblemContentText(WebUtil.HtmltoText(timu
					.getProblemContentText()));
			timu.setProSourceDesc(WebUtil.HtmltoText(timu.getProSourceDesc()));
		}
		// 让托管状态的实体成为游离状态
		timuService.clear();

		request.setAttribute("pageView", pageView);

		return mapping.findForward("list");
	}

	/**
	 * 组拼排序键值对
	 * 
	 * @param orderfied
	 *            排序方式指示字符串
	 * @return LinkedHashMap
	 */
	private LinkedHashMap<String, String> buildOrder(String orderfied) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		if ("pubDateasc".equals(orderfied)) {
			orderby.put("pubDate", "asc");
		} else if ("pubDatedesc".equals(orderfied)) {
			orderby.put("pubDate", "desc");
		} else {
			orderby.put("pubDate", "desc");
		}
		return orderby;
	}
}

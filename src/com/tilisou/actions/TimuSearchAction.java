package com.tilisou.actions;

import java.io.File;
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
import com.tilisou.utils.BaiduOcrUtil;
import com.tilisou.utils.SiteUrl;
import com.tilisou.viewmodel.BaseForm;
import com.tilisou.viewmodel.PageView;
import com.tilisou.viewmodel.TimuQueryForm;

/**
 * 前台题目搜索控制器
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-1 上午9:51:31
 * 
 */
@Controller("/timu/query")
public class TimuSearchAction extends Action {
	@Resource(name = "timuSearchServiceBean")
	ITimuSearchService timuSearchService;
	@Resource(name = "timuServiceBean")
	ITimuService timuService;
	private static Logger logger = Logger.getLogger("TimuSearchAction");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimuQueryForm formbean = (TimuQueryForm) form;
		PageView<Timu> pageView = new PageView<Timu>(12, formbean.getPage());
		String keywordString= null;
		/** 表单校验 **/
		if (formbean.getImagefile()==null) {
			if ((formbean.getWord()==null||"".equals(formbean.getWord().trim()))) {
				request.setAttribute("message", "Sorry,请输入关键词");
				request.setAttribute("pageView", pageView);
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}else if (formbean.getWord()!=null&&!"".equals(formbean.getWord().trim())) {
				keywordString=formbean.getWord().trim();
				request.setAttribute("word", keywordString);
			}else {
				request.setAttribute("message", "Sorry,请输入关键词");
				request.setAttribute("pageView", pageView);
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
		}else if (formbean.getImagefile()!=null) {
			if (formbean.getImagefile().getFileName()==null||"".equals(formbean.getImagefile().getFileName())) {
				request.setAttribute("pageView", pageView);
				request.setAttribute("message", "Sorry,请选择题目图片");
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
			if (!BaseForm.validateImageFileType(formbean.getImagefile())) {
				request.setAttribute("pageView", pageView);
				request.setAttribute("message", "Sorry,目前只允许用.jpg格式图片搜题");
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
			if (formbean.getImagefile().getFileSize() > 1024000) {
				request.setAttribute("pageView", pageView);
				request.setAttribute("message", "Sorry,图片不能大于1MB");
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
			if (formbean.getImagefile().getFileSize() >0) {				
				try {
					// 缓存文件
					File savedir = new File(request.getSession().getServletContext()
							.getRealPath("/tmp/"));
					File tmpFile= BaseForm.saveFile(savedir, "tmp.jpg", formbean.getImagefile().getFileData());
					
					keywordString = BaiduOcrUtil.getTimuDesc(BaiduOcrUtil
						.ocr4File(tmpFile));
					request.setAttribute("ocrword", keywordString);
					request.setAttribute("imagefile", tmpFile.getAbsolutePath());
				} catch (Exception e) {
					logger.error("更新数据错误：" + e.toString());
					request.setAttribute("pageView", pageView);
					request.setAttribute("message", "Sorry,本次图片搜题失败");
					return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
				}				
			}
		} 
		try {
			pageView.setQueryResult(timuSearchService.query(
					timuSearchService.query(keywordString,
							pageView.getFirstResult(), pageView.getMaxresult()),
					pageView.getFirstResult(), pageView.getMaxresult()));
			request.setAttribute("pageView", pageView);
			return mapping.findForward("list");
		} catch (Exception e) {
			logger.error("更新数据错误：" + e.toString());
			request.setAttribute("message", "Sorry,本次搜题失败");
			request.setAttribute("pageView", pageView);
			return mapping.findForward("list");
		}
	}
}

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
 * ǰ̨��Ŀ����������
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-1 ����9:51:31
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
		/** ��У�� **/
		if (formbean.getImagefile()==null) {
			if ((formbean.getWord()==null||"".equals(formbean.getWord().trim()))) {
				request.setAttribute("message", "Sorry,������ؼ���");
				request.setAttribute("pageView", pageView);
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}else if (formbean.getWord()!=null&&!"".equals(formbean.getWord().trim())) {
				keywordString=formbean.getWord().trim();
				request.setAttribute("word", keywordString);
			}else {
				request.setAttribute("message", "Sorry,������ؼ���");
				request.setAttribute("pageView", pageView);
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
		}else if (formbean.getImagefile()!=null) {
			if (formbean.getImagefile().getFileName()==null||"".equals(formbean.getImagefile().getFileName())) {
				request.setAttribute("pageView", pageView);
				request.setAttribute("message", "Sorry,��ѡ����ĿͼƬ");
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
			if (!BaseForm.validateImageFileType(formbean.getImagefile())) {
				request.setAttribute("pageView", pageView);
				request.setAttribute("message", "Sorry,Ŀǰֻ������.jpg��ʽͼƬ����");
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
			if (formbean.getImagefile().getFileSize() > 1024000) {
				request.setAttribute("pageView", pageView);
				request.setAttribute("message", "Sorry,ͼƬ���ܴ���1MB");
				return new ActionForward(SiteUrl.readUrl("timu.list.display"), false);
			}
			if (formbean.getImagefile().getFileSize() >0) {				
				try {
					// �����ļ�
					File savedir = new File(request.getSession().getServletContext()
							.getRealPath("/tmp/"));
					File tmpFile= BaseForm.saveFile(savedir, "tmp.jpg", formbean.getImagefile().getFileData());
					
					keywordString = BaiduOcrUtil.getTimuDesc(BaiduOcrUtil
						.ocr4File(tmpFile));
					request.setAttribute("ocrword", keywordString);
					request.setAttribute("imagefile", tmpFile.getAbsolutePath());
				} catch (Exception e) {
					logger.error("�������ݴ���" + e.toString());
					request.setAttribute("pageView", pageView);
					request.setAttribute("message", "Sorry,����ͼƬ����ʧ��");
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
			logger.error("�������ݴ���" + e.toString());
			request.setAttribute("message", "Sorry,��������ʧ��");
			request.setAttribute("pageView", pageView);
			return mapping.findForward("list");
		}
	}
}

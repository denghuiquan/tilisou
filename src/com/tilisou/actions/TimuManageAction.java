package com.tilisou.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.stereotype.Controller;

import com.tilisou.beans.Timu;
import com.tilisou.service.ITimuService;
import com.tilisou.utils.BaiduOcrUtil;
import com.tilisou.utils.DateFormatutil;
import com.tilisou.utils.SiteUrl;
import com.tilisou.viewmodel.BaseForm;
import com.tilisou.viewmodel.TimuForm;
/**
 * ��̨��Ŀ����
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 ����11:03:16
 *
 */
@Controller("/control/timu/manage")
public class TimuManageAction extends DispatchAction {
	@Resource
	private ITimuService timuService;
	private static Logger logger=Logger.getLogger("TimuManageAction");
	
	/**
	 * ��Ŀ�޸Ľ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimuForm formbean = (TimuForm) form;
		Timu timu = timuService.find(formbean.gettId());
		
		request.setAttribute("timu", timu);
		return mapping.findForward("edit");
	}

	/**
	 * �޸���Ŀ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * <br/>���������޸ĵ㣺�ļ��ı���ռ�÷�����Ӳ�̣���ɾ�����ļ�
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimuForm formbean = (TimuForm) form;
		Timu timu = timuService.find(formbean.gettId());
		//���»�ȡͼƬ�浽��������ʶ��õ����֣����ĸ���Ŀ�Ķ�Ӧ���ԡ�
		StringBuffer qPicfile = new StringBuffer(),
				aPicfile = new StringBuffer(), 
				qPicUrl = new StringBuffer(), 
				aPicUrl = new StringBuffer();
		String problemContentText = "";
		
		/**
		 * ��У��
		 */
		if (formbean.getSubject()==null) {
			request.setAttribute("message", "��ѡ���Ŀ");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getGrade()==null) {
			request.setAttribute("message", "��ѡ���꼶");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getType()==null) {
			request.setAttribute("message", "��ѡ������");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getProSourceDesc()==null||"".equals(formbean.getProSourceDesc().trim())) {
			request.setAttribute("message", "����д��Ŀ��Դ˵��");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getProblemContentText()==null||"".equals(formbean.getProblemContentText().trim())) {
			if (formbean.getqPicFile().getFileName()==null||"".equals(formbean.getqPicFile().getFileName())) {
				request.setAttribute("message", "����д��ĿͼƬʶ����������ݻ�ѡ����ĿͼƬ");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
		}else {
			problemContentText=formbean.getProblemContentText();
		}
		
		if(formbean.getqPicFile().getFileName()!=null&&!"".equals(formbean.getqPicFile().getFileName())) {
			if (!(BaseForm.validateImageFileType(formbean.getqPicFile()))) {
				request.setAttribute("message", "Sorry,Ŀǰֻ������.jpg��ʽͼƬ");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
			if (formbean.getqPicFile().getFileSize() > 1024000) {
				request.setAttribute("message", "Sorry,ͼƬ���ܴ���1MB");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}			
		}	
		
		if (formbean.getaPicFile().getFileName()!=null&&!"".equals(formbean.getaPicFile().getFileName())) {
			if (!(BaseForm.validateImageFileType(formbean.getaPicFile()))) {
				request.setAttribute("message", "Sorry,Ŀǰֻ������.jpg��ʽͼƬ");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
			if (formbean.getaPicFile().getFileSize() > 1024000) {
				request.setAttribute("message", "Sorry,ͼƬ���ܴ���1MB");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
		}
		
		if(formbean.getqPicFile().getFileName()!=null&&!"".equals(formbean.getqPicFile().getFileName())) {			
			if (formbean.getqPicFile().getFileSize() >0) {				
				try {
					String ext = BaseForm.getExt(formbean.getqPicFile());

					// ��ȡ�������ļ�����Ӧ·��

					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MMdd");
					// �����ļ������Ŀ¼
					String pathdir = "/pic/timu/" + dateformat.format(new Date());
					// �õ�ͼƬ����Ŀ¼����ʵ·��
					String realpathdir = request.getSession().getServletContext()
							.getRealPath(pathdir);

					// �����ļ���
					File savedir = new File(realpathdir);
					// �����ļ�����
					qPicfile.append(UUID.randomUUID().toString()).append(".")
					.append(ext);					
					qPicUrl.append(pathdir).append("/").append(qPicfile);
					
					// ����BaseForm�ľ�̬������ϣ�������·��������ԭ�ļ���ͼƬ��������
					// ͬʱ���÷���ֵ�����ļ�
					File qfile = BaseForm.saveFile(savedir, qPicfile.toString(),
							formbean.getqPicFile().getFileData());									
					
					File file = new File(timu.getqPicUrl());
					// ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
					if (file.exists() && file.isFile()) {
						if (file.delete()) {
							logger.info("ɾ�������ļ�" + timu.getqPicUrl() + "�ɹ���");
						} else {
							logger.info("ɾ�������ļ�" + timu.getqPicUrl() + "ʧ�ܣ�");
						}
					} else {
						logger.info("ɾ�������ļ�ʧ�ܣ�" + timu.getqPicUrl() + "�����ڣ�");
					}
					timu.setqPicUrl(qPicUrl.toString());
					
					// ʶ������ͼƬ�ϵ�����
					try {
						problemContentText = BaiduOcrUtil.getTimuDesc(BaiduOcrUtil
								.ocr4File(qfile));
					} catch (Exception e) {
						qfile = null;
						qPicfile = null;
						aPicfile = null;
						qPicUrl = null;
						aPicUrl = null;
						problemContentText = null;
						ext = null;
						dateformat = null;
						realpathdir = null;
						savedir = null;
						logger.error("����ٶ�OCRʶ��ͼƬʧ�ܣ�" + e.toString());
						request.setAttribute("message", "��ĿͼƬʶ��ʧ�ܣ������³���");
						request.setAttribute("urladdress",
								SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId());
						return mapping.findForward("message");
					}
					
				} catch (Exception e) {
					logger.error("�������ݴ���" + e.toString());
					request.setAttribute("message", "Sorry,������Ŀ����ʧ��");
					request.setAttribute("urladdress",
							SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId());
					return mapping.findForward("message");
				}				
				
			}
		}
		
		
		if (formbean.getaPicFile().getFileName()!=null&&!"".equals(formbean.getaPicFile().getFileName())) {
			
			if (formbean.getaPicFile().getFileSize() >0) {				
				try {
					String ext = BaseForm.getExt(formbean.getaPicFile());

					// ��ȡ�������ļ�����Ӧ·��

					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MMdd");
					// �����ļ������Ŀ¼
					String pathdir = "/pic/timu/" + dateformat.format(new Date());
					// �õ�ͼƬ����Ŀ¼����ʵ·��
					String realpathdir = request.getSession().getServletContext()
							.getRealPath(pathdir);

					// �����ļ���
					File savedir = new File(realpathdir);
					// �����ļ�����
					qPicfile.append(UUID.randomUUID().toString()).append(".")
					.append(ext);
					aPicfile = aPicfile.append("a_").append(qPicfile);					
					aPicUrl.append(pathdir).append("/").append(aPicfile);
					
					// ����BaseForm�ľ�̬������ϣ�������·��������ԭ�ļ���ͼƬ��������
					// ͬʱ���÷���ֵ�����ļ�
					BaseForm.saveFile(savedir, aPicfile.toString(),
							formbean.getaPicFile().getFileData());					
					
					File file = new File(timu.getaPicUrl());
					// ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
					if (file.exists() && file.isFile()) {
						if (file.delete()) {
							logger.info("ɾ�������ļ�" + timu.getqPicUrl() + "�ɹ���");
						} else {
							logger.info("ɾ�������ļ�" + timu.getqPicUrl() + "ʧ�ܣ�");
						}
					} else {
						logger.info("ɾ�������ļ�ʧ�ܣ�" + timu.getqPicUrl() + "�����ڣ�");
					}
					
					timu.setaPicUrl(aPicUrl.toString());
					
				} catch (Exception e) {
					logger.error("�������ݴ���" + e.toString());
					request.setAttribute("message", "Sorry,������Ŀ����ʧ��");
					request.setAttribute("urladdress",
							SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId());
					return mapping.findForward("message");
				}
			}	
		}
		
		
		timu.setProSourceDesc(formbean.getProSourceDesc());
		timu.setProblemContentText(problemContentText);
		timu.setPubDate(DateFormatutil.getDate(new Date()));
		timu.setSubject(formbean.getSubject());
		timu.setGrade(formbean.getGrade());
		timu.setType(formbean.getType());
		
		try {			
			timuService.update(timu);
			request.setAttribute("message", "��Ŀ���³ɹ���");
			request.setAttribute("urladdress", SiteUrl.readUrl("control.timu.list"));//�����鿴�������Ŀʹ�� "control.timu.view"
			return mapping.findForward("message");
		} catch (Exception e) {
			qPicfile = null;
			aPicfile = null;
			qPicUrl = null;
			aPicUrl = null;
			problemContentText = null;
			logger.error("��Ŀ���ݸ��µ�����ʧ�ܣ�" + e.toString());
			request.setAttribute("message", "��Ŀ����ʧ�ܣ������³���");
			request.setAttribute("urladdress",
					SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId());
			return mapping.findForward("message");
		}
	}
	
	/**
	 * ��Ŀ���/�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("add");
	}

	/**
	 * ��Ŀ���/����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimuForm formbean = (TimuForm) form;
		StringBuffer qPicfile = new StringBuffer(),
				aPicfile = new StringBuffer(), 
				qPicUrl = new StringBuffer(), 
				aPicUrl = new StringBuffer();
		String problemContentText = "";		
		
		/**
		 * ��У��
		 */
		if (formbean.getSubject()==null) {
			request.setAttribute("message", "��ѡ���Ŀ");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getGrade()==null) {
			request.setAttribute("message", "��ѡ���꼶");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getType()==null) {
			request.setAttribute("message", "��ѡ������");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getProSourceDesc()==null||"".equals(formbean.getProSourceDesc().trim())) {
			request.setAttribute("message", "����д��Ŀ��Դ˵��");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getqPicFile().getFileName()==null||"".equals(formbean.getqPicFile().getFileName())) {
			request.setAttribute("message", "��ѡ����ĿͼƬ");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getaPicFile().getFileName()==null||"".equals(formbean.getaPicFile().getFileName())) {
			request.setAttribute("message", "��ѡ����ͼƬ");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		
		if (formbean.getqPicFile()!=null&&formbean.getaPicFile()!=null) {
			if (!(BaseForm.validateImageFileType(formbean.getqPicFile())&&BaseForm.validateImageFileType(formbean.getaPicFile()))) {
				request.setAttribute("message", "Sorry,Ŀǰֻ������.jpg��ʽͼƬ");
				return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
			}
			if (formbean.getqPicFile().getFileSize() > 1024000||formbean.getaPicFile().getFileSize() > 1024000) {
				request.setAttribute("message", "Sorry,ͼƬ���ܴ���1MB");
				return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
			}
			if (formbean.getqPicFile().getFileSize() >0&&formbean.getaPicFile().getFileSize() >0) {				
				try {
					String ext = BaseForm.getExt(formbean.getqPicFile());

					// ��ȡ�������ļ�����Ӧ·��

					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MMdd");
					// �����ļ������Ŀ¼
					String pathdir = "/pic/timu/" + dateformat.format(new Date());
					// �õ�ͼƬ����Ŀ¼����ʵ·��
					String realpathdir = request.getSession().getServletContext()
							.getRealPath(pathdir);

					// �����ļ���
					File savedir = new File(realpathdir);
					// �����ļ�����
					qPicfile.append(UUID.randomUUID().toString()).append(".")
					.append(ext);
					aPicfile = aPicfile.append("a_").append(qPicfile);
					qPicUrl.append(pathdir).append("/").append(qPicfile);
					aPicUrl.append(pathdir).append("/").append(aPicfile);
					
					// ����BaseForm�ľ�̬������ϣ�������·��������ԭ�ļ���ͼƬ��������
					// ͬʱ���÷���ֵ�����ļ�
					File qfile = BaseForm.saveFile(savedir, qPicfile.toString(),
							formbean.getqPicFile().getFileData());
					BaseForm.saveFile(savedir, aPicfile.toString(), formbean
							.getaPicFile().getFileData());					
					
					// ʶ������ͼƬ�ϵ�����
					try {
						problemContentText = BaiduOcrUtil.getTimuDesc(BaiduOcrUtil
								.ocr4File(qfile));
					} catch (Exception e) {
						qfile = null;
						qPicfile = null;
						aPicfile = null;
						qPicUrl = null;
						aPicUrl = null;
						problemContentText = null;
						ext = null;
						dateformat = null;
						realpathdir = null;
						savedir = null;
						logger.error("����ٶ�OCRʶ��ͼƬʧ�ܣ�" + e.toString());
						request.setAttribute("message", "��Ŀʶ��ʧ�ܣ������³���");
						request.setAttribute("urladdress",
								SiteUrl.readUrl("control.timu.add"));
						return mapping.findForward("message");
					}
					
				} catch (Exception e) {
					logger.error("�������ݴ���" + e.toString());
					request.setAttribute("message", "Sorry,���ε�����Ŀʧ��");
					request.setAttribute("urladdress",
							SiteUrl.readUrl("control.timu.add"));
					return mapping.findForward("message");
				}	
				
				Timu timu = new Timu(formbean.getProSourceDesc(), formbean.getType(),
						problemContentText, qPicUrl.toString(), aPicUrl.toString(),
						DateFormatutil.getDate(new Date()), formbean.getGrade(),
						formbean.getSubject());
				try {			
					timuService.save(timu);
					request.setAttribute("message", "����Ŀ����ɹ���");
					request.setAttribute("urladdress", SiteUrl.readUrl("control.timu.list"));//�����鿴�������Ŀʹ�� "control.timu.view"
					return mapping.findForward("message");
				} catch (Exception e) {
					qPicfile = null;
					aPicfile = null;
					qPicUrl = null;
					aPicUrl = null;
					problemContentText = null;
					logger.error("��Ŀ���뱣�浽����ʧ�ܣ�" + e.toString());
					request.setAttribute("message", "��Ŀ����ʧ�ܣ������³���");
					request.setAttribute("urladdress",
							SiteUrl.readUrl("control.timu.add"));
					return mapping.findForward("message");
				}
			}	
		}
		request.setAttribute("message", "����Ŀ����ʧ�ܣ�");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.timu.list"));//�����鿴�������Ŀʹ�� "control.timu.view"
		return mapping.findForward("message");
	}
}

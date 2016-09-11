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
 * 后台题目管理
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-12 上午11:03:16
 *
 */
@Controller("/control/timu/manage")
public class TimuManageAction extends DispatchAction {
	@Resource
	private ITimuService timuService;
	private static Logger logger=Logger.getLogger("TimuManageAction");
	
	/**
	 * 题目修改界面
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
	 * 修改题目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * <br/>后期完善修改点：文件的保存占用服务器硬盘，可删除旧文件
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TimuForm formbean = (TimuForm) form;
		Timu timu = timuService.find(formbean.gettId());
		//重新获取图片存到服务器，识别得到文字，更改该题目的对应属性。
		StringBuffer qPicfile = new StringBuffer(),
				aPicfile = new StringBuffer(), 
				qPicUrl = new StringBuffer(), 
				aPicUrl = new StringBuffer();
		String problemContentText = "";
		
		/**
		 * 表单校验
		 */
		if (formbean.getSubject()==null) {
			request.setAttribute("message", "请选择科目");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getGrade()==null) {
			request.setAttribute("message", "请选择年级");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getType()==null) {
			request.setAttribute("message", "请选择题型");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getProSourceDesc()==null||"".equals(formbean.getProSourceDesc().trim())) {
			request.setAttribute("message", "请填写题目来源说明");
			return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
		}
		if (formbean.getProblemContentText()==null||"".equals(formbean.getProblemContentText().trim())) {
			if (formbean.getqPicFile().getFileName()==null||"".equals(formbean.getqPicFile().getFileName())) {
				request.setAttribute("message", "请填写题目图片识别的文字内容或选择题目图片");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
		}else {
			problemContentText=formbean.getProblemContentText();
		}
		
		if(formbean.getqPicFile().getFileName()!=null&&!"".equals(formbean.getqPicFile().getFileName())) {
			if (!(BaseForm.validateImageFileType(formbean.getqPicFile()))) {
				request.setAttribute("message", "Sorry,目前只允许用.jpg格式图片");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
			if (formbean.getqPicFile().getFileSize() > 1024000) {
				request.setAttribute("message", "Sorry,图片不能大于1MB");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}			
		}	
		
		if (formbean.getaPicFile().getFileName()!=null&&!"".equals(formbean.getaPicFile().getFileName())) {
			if (!(BaseForm.validateImageFileType(formbean.getaPicFile()))) {
				request.setAttribute("message", "Sorry,目前只允许用.jpg格式图片");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
			if (formbean.getaPicFile().getFileSize() > 1024000) {
				request.setAttribute("message", "Sorry,图片不能大于1MB");
				return new ActionForward(SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId(), false);
			}
		}
		
		if(formbean.getqPicFile().getFileName()!=null&&!"".equals(formbean.getqPicFile().getFileName())) {			
			if (formbean.getqPicFile().getFileSize() >0) {				
				try {
					String ext = BaseForm.getExt(formbean.getqPicFile());

					// 获取并保存文件到相应路径

					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MMdd");
					// 构建文件保存的目录
					String pathdir = "/pic/timu/" + dateformat.format(new Date());
					// 得到图片保存目录的真实路径
					String realpathdir = request.getSession().getServletContext()
							.getRealPath(pathdir);

					// 创建文件夹
					File savedir = new File(realpathdir);
					// 构建文件名称
					qPicfile.append(UUID.randomUUID().toString()).append(".")
					.append(ext);					
					qPicUrl.append(pathdir).append("/").append(qPicfile);
					
					// 利用BaseForm的静态方法按希望保存的路径保存了原文件的图片到服务器
					// 同时利用返回值创建文件
					File qfile = BaseForm.saveFile(savedir, qPicfile.toString(),
							formbean.getqPicFile().getFileData());									
					
					File file = new File(timu.getqPicUrl());
					// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
					if (file.exists() && file.isFile()) {
						if (file.delete()) {
							logger.info("删除单个文件" + timu.getqPicUrl() + "成功！");
						} else {
							logger.info("删除单个文件" + timu.getqPicUrl() + "失败！");
						}
					} else {
						logger.info("删除单个文件失败：" + timu.getqPicUrl() + "不存在！");
					}
					timu.setqPicUrl(qPicUrl.toString());
					
					// 识别问题图片上的文字
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
						logger.error("请求百度OCR识别图片失败：" + e.toString());
						request.setAttribute("message", "题目图片识别失败，请重新尝试");
						request.setAttribute("urladdress",
								SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId());
						return mapping.findForward("message");
					}
					
				} catch (Exception e) {
					logger.error("更新数据错误：" + e.toString());
					request.setAttribute("message", "Sorry,本次题目更新失败");
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

					// 获取并保存文件到相应路径

					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MMdd");
					// 构建文件保存的目录
					String pathdir = "/pic/timu/" + dateformat.format(new Date());
					// 得到图片保存目录的真实路径
					String realpathdir = request.getSession().getServletContext()
							.getRealPath(pathdir);

					// 创建文件夹
					File savedir = new File(realpathdir);
					// 构建文件名称
					qPicfile.append(UUID.randomUUID().toString()).append(".")
					.append(ext);
					aPicfile = aPicfile.append("a_").append(qPicfile);					
					aPicUrl.append(pathdir).append("/").append(aPicfile);
					
					// 利用BaseForm的静态方法按希望保存的路径保存了原文件的图片到服务器
					// 同时利用返回值创建文件
					BaseForm.saveFile(savedir, aPicfile.toString(),
							formbean.getaPicFile().getFileData());					
					
					File file = new File(timu.getaPicUrl());
					// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
					if (file.exists() && file.isFile()) {
						if (file.delete()) {
							logger.info("删除单个文件" + timu.getqPicUrl() + "成功！");
						} else {
							logger.info("删除单个文件" + timu.getqPicUrl() + "失败！");
						}
					} else {
						logger.info("删除单个文件失败：" + timu.getqPicUrl() + "不存在！");
					}
					
					timu.setaPicUrl(aPicUrl.toString());
					
				} catch (Exception e) {
					logger.error("更新数据错误：" + e.toString());
					request.setAttribute("message", "Sorry,本次题目更新失败");
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
			request.setAttribute("message", "题目更新成功！");
			request.setAttribute("urladdress", SiteUrl.readUrl("control.timu.list"));//立即查看导入的题目使用 "control.timu.view"
			return mapping.findForward("message");
		} catch (Exception e) {
			qPicfile = null;
			aPicfile = null;
			qPicUrl = null;
			aPicUrl = null;
			problemContentText = null;
			logger.error("题目数据更新到数据失败：" + e.toString());
			request.setAttribute("message", "题目更新失败，请重新尝试");
			request.setAttribute("urladdress",
					SiteUrl.readUrl("control.timu.edit")+"&tId="+timu.gettId());
			return mapping.findForward("message");
		}
	}
	
	/**
	 * 题目添加/导入界面
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
	 * 题目添加/导入
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
		 * 表单校验
		 */
		if (formbean.getSubject()==null) {
			request.setAttribute("message", "请选择科目");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getGrade()==null) {
			request.setAttribute("message", "请选择年级");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getType()==null) {
			request.setAttribute("message", "请选择题型");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getProSourceDesc()==null||"".equals(formbean.getProSourceDesc().trim())) {
			request.setAttribute("message", "请填写题目来源说明");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getqPicFile().getFileName()==null||"".equals(formbean.getqPicFile().getFileName())) {
			request.setAttribute("message", "请选择题目图片");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		if (formbean.getaPicFile().getFileName()==null||"".equals(formbean.getaPicFile().getFileName())) {
			request.setAttribute("message", "请选择解答图片");
			return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
		}
		
		if (formbean.getqPicFile()!=null&&formbean.getaPicFile()!=null) {
			if (!(BaseForm.validateImageFileType(formbean.getqPicFile())&&BaseForm.validateImageFileType(formbean.getaPicFile()))) {
				request.setAttribute("message", "Sorry,目前只允许用.jpg格式图片");
				return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
			}
			if (formbean.getqPicFile().getFileSize() > 1024000||formbean.getaPicFile().getFileSize() > 1024000) {
				request.setAttribute("message", "Sorry,图片不能大于1MB");
				return new ActionForward(SiteUrl.readUrl("control.timu.add"), false);
			}
			if (formbean.getqPicFile().getFileSize() >0&&formbean.getaPicFile().getFileSize() >0) {				
				try {
					String ext = BaseForm.getExt(formbean.getqPicFile());

					// 获取并保存文件到相应路径

					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MMdd");
					// 构建文件保存的目录
					String pathdir = "/pic/timu/" + dateformat.format(new Date());
					// 得到图片保存目录的真实路径
					String realpathdir = request.getSession().getServletContext()
							.getRealPath(pathdir);

					// 创建文件夹
					File savedir = new File(realpathdir);
					// 构建文件名称
					qPicfile.append(UUID.randomUUID().toString()).append(".")
					.append(ext);
					aPicfile = aPicfile.append("a_").append(qPicfile);
					qPicUrl.append(pathdir).append("/").append(qPicfile);
					aPicUrl.append(pathdir).append("/").append(aPicfile);
					
					// 利用BaseForm的静态方法按希望保存的路径保存了原文件的图片到服务器
					// 同时利用返回值创建文件
					File qfile = BaseForm.saveFile(savedir, qPicfile.toString(),
							formbean.getqPicFile().getFileData());
					BaseForm.saveFile(savedir, aPicfile.toString(), formbean
							.getaPicFile().getFileData());					
					
					// 识别问题图片上的文字
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
						logger.error("请求百度OCR识别图片失败：" + e.toString());
						request.setAttribute("message", "题目识别失败，请重新尝试");
						request.setAttribute("urladdress",
								SiteUrl.readUrl("control.timu.add"));
						return mapping.findForward("message");
					}
					
				} catch (Exception e) {
					logger.error("更新数据错误：" + e.toString());
					request.setAttribute("message", "Sorry,本次导入题目失败");
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
					request.setAttribute("message", "新题目导入成功！");
					request.setAttribute("urladdress", SiteUrl.readUrl("control.timu.list"));//立即查看导入的题目使用 "control.timu.view"
					return mapping.findForward("message");
				} catch (Exception e) {
					qPicfile = null;
					aPicfile = null;
					qPicUrl = null;
					aPicUrl = null;
					problemContentText = null;
					logger.error("题目插入保存到数据失败：" + e.toString());
					request.setAttribute("message", "题目导入失败，请重新尝试");
					request.setAttribute("urladdress",
							SiteUrl.readUrl("control.timu.add"));
					return mapping.findForward("message");
				}
			}	
		}
		request.setAttribute("message", "新题目导入失败！");
		request.setAttribute("urladdress", SiteUrl.readUrl("control.timu.list"));//立即查看导入的题目使用 "control.timu.view"
		return mapping.findForward("message");
	}
}

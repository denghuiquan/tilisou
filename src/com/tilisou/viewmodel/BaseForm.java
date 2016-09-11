package com.tilisou.viewmodel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * 公共基础FormBean
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 下午10:35:42
 * 
 */
public class BaseForm extends ActionForm {
	
	private static final long serialVersionUID = 5629266886536604230L;
	
	/** 获取当前页 **/
	private int page;
	/** 设置是否进行查找 **/
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getPage() {
		return page < 1 ? 1 : page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 验证上传文件类型是否属于.jpg图片格式
	 * 
	 * @param formfile
	 * @return
	 */
	public static boolean validateImageFileType(FormFile formfile) {
		if (formfile != null && formfile.getFileSize() > 0) {
			List<String> arrowType = Arrays.asList("image/jpg", "image/jpeg",
					"image/pjpeg");
			List<String> arrowExtension = Arrays.asList("jpg");
			String ext = getExt(formfile);
			return arrowType.contains(formfile.getContentType().toLowerCase())
					&& arrowExtension.contains(ext);
		}
		return true;
	}
	
	/**
	 * 获取FormFile文件的文件名的后缀
	 * 
	 * @param formfile
	 * @return
	 */
	public static String getExt(FormFile formfile) {
		return formfile.getFileName()
				.substring(formfile.getFileName().lastIndexOf('.') + 1)
				.toLowerCase();
	}
	
	/**
	 * 保存文件数据到服务器的指定目录和文件名
	 * 
	 * @param savedir
	 *            存放目录
	 * @param fileName
	 *            文件名称
	 * @param data
	 *            保存的内容
	 * @return 保存的文件
	 * @throws Exception
	 */
	public static File saveFile(File savedir, String fileName, byte[] data)
			throws Exception {
		if (!savedir.exists())
			savedir.mkdirs();// 如果目录不存在就创建
		File file = new File(savedir, fileName);
		FileOutputStream fileoutstream = new FileOutputStream(file);
		fileoutstream.write(data);
		fileoutstream.flush();
		fileoutstream.close();
		return file;
	}
}

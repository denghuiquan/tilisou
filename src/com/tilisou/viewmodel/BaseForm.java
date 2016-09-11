package com.tilisou.viewmodel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * ��������FormBean
 * 
 * @author Administrator��<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-10-8 ����10:35:42
 * 
 */
public class BaseForm extends ActionForm {
	
	private static final long serialVersionUID = 5629266886536604230L;
	
	/** ��ȡ��ǰҳ **/
	private int page;
	/** �����Ƿ���в��� **/
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
	 * ��֤�ϴ��ļ������Ƿ�����.jpgͼƬ��ʽ
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
	 * ��ȡFormFile�ļ����ļ����ĺ�׺
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
	 * �����ļ����ݵ���������ָ��Ŀ¼���ļ���
	 * 
	 * @param savedir
	 *            ���Ŀ¼
	 * @param fileName
	 *            �ļ�����
	 * @param data
	 *            ���������
	 * @return ������ļ�
	 * @throws Exception
	 */
	public static File saveFile(File savedir, String fileName, byte[] data)
			throws Exception {
		if (!savedir.exists())
			savedir.mkdirs();// ���Ŀ¼�����ھʹ���
		File file = new File(savedir, fileName);
		FileOutputStream fileoutstream = new FileOutputStream(file);
		fileoutstream.write(data);
		fileoutstream.flush();
		fileoutstream.close();
		return file;
	}
}

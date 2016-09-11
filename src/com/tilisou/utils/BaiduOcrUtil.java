package com.tilisou.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import sun.misc.BASE64Encoder;

/**
 * 百度OCR Api工具类，主要用于获取百度OCR开放平台返回的识别结果数据
 * 
 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark Deng</a>
 * @time 2015-9-15 下午5:38:01
 * 
 */
public class BaiduOcrUtil {
	private static Logger logger = Logger.getLogger("BaiduOcrUtil");
	// 接口地址 :
	static String httpUrl = "http://apis.baidu.com/apistore/idlocr/ocr";
	// 请求参数：
	static String httpArg = "fromdevice=pc&clientip=10.10.10.0&detecttype=LocateRecognize&languagetype=CHN_ENG&imagetype=1&image=";
	// 自己的应用ApiKey:
	static String myApikey = "31f48cf5c10c1d58cdcf9d38d0ac8283";

	/*public static void main(String[] args) throws Exception {
		File file = new File("d:\\che41.jpg");
		String jsonResult = "";
		try {
			jsonResult = ocr4File(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(jsonResult);
		System.out.println(getTimuDesc(jsonResult));
	}*/
	
	/**
	 * 识别图片文件
	 * 
	 * @param file
	 *            图片文件
	 * @return String 识别平台返回的Json字符串
	 * @throws Exception
	 *             存在连接失败或读写文件失败的异常，抛出以便在用户访问的业务处理中捕获并作出异常处理。
	 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark
	 *         Deng</a>
	 * @time 2015-9-23 下午4:18:31
	 */
	public static String ocr4File(File file) throws Exception {

		String imageBase = encodeImageToBase64(file);
		// 替换掉不必要的字符
		imageBase = imageBase.replaceAll("\r\n", "");
		imageBase = imageBase.replaceAll("\\+", "%2B");
		String jsonResult = request(httpUrl, httpArg + imageBase, myApikey);
		return jsonResult;
	}
	
	/**
	 * 转换Json Unicode编码 为中文编码
	 * 
	 * @param jsonString
	 *            json字符串
	 * @return 中文编码字符串
	 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark
	 *         Deng</a>
	 * @time 2015-9-23 下午4:21:03
	 */
	public static String convertUnicode2Utf(String jsonString) {
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = jsonString.indexOf("\\u", pos)) != -1) {
			sb.append(jsonString.substring(pos, i));
			if (i + 5 < jsonString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(
						jsonString.substring(i + 2, i + 6), 16));
			}
		}
		sb.append(jsonString.substring(pos, jsonString.length() - 1));
		return sb.toString();
	}
	
	/**
	 * 请求百度OCR识别平台Api
	 * 
	 * @param httpUrl
	 *            接口地址：如：http://apis.baidu.com/apistore/idlocr/ocr
	 * @param httpArg
	 *            请求参数：如：fromdevice=pc&clientip=10.10.10.0&detecttype=
	 *            LocateRecognize
	 *            &languagetype=CHN_ENG&imagetype=1&image=aba988abcdacbd908837
	 * @return 平台返回的原始json格式数据
	 * @throws Exception
	 *             可能出现连接失败，读写失败异常，抛出以便调用者捕获处理
	 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark
	 *         Deng</a>
	 * @time 2015-9-23 下午4:37:39
	 */
	public static String request(String httpUrl, String httpArg, String apikey)
			throws Exception {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();

		HttpURLConnection connection;
		URL url;
		try {
			url = new URL(httpUrl);
			connection = (HttpURLConnection) url.openConnection();
			// 请求方法 :POST
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey", apikey/* 您自己的apikey */);
			connection.setDoOutput(true);
			connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
			// 获取连接状态码
			int returnCode = connection.getResponseCode();
			if (returnCode == HttpURLConnection.HTTP_OK) {
				connection.connect();
				InputStream is = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String strRead = null;
				while ((strRead = reader.readLine()) != null) {
					sbf.append(strRead);
					sbf.append("\r\n");
				}
				reader.close();
				result = sbf.toString();
				// System.out.println("连接识别平台成功");
			} else {
				logger.info("BaiduOcrUtil.request()：连接识别平台失败，returnCode：" + returnCode);
				// System.out.println("连接识别平台失败，returnCode：" + returnCode);
				throw new Exception("连接平台失败");
			}
		} catch (Exception e1) {
			logger.info("BaiduOcrUtil.request()：连接识别平台失败");
			throw new Exception("图片文件识别失败");
		}
		return result;
	}
	
	/**
	 * 从平台返回的Json数据中，获取图片识别出来的题目文字描述
	 * 
	 * @param jsonString
	 *            通过baiduOCR Api获取的json格式数据
	 * @return String 完整的题目文字描述
	 * @author Administrator：<a href="mailto:denghuiquan@foxmail.com">Mark
	 *         Deng</a>
	 * @throws Exception
	 * @time 2015-9-23 下午4:25:52
	 */
	public static String getTimuDesc(String jsonString) throws Exception {
		String result = "";
		StringBuffer sbf = new StringBuffer();
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		// 这里其实仍需对返回的error码进行检测，error!=0的话同样应该抛出异常。
		String errno = jsonObj.getString("errNum");
		if (errno.equals("0")) {
			JSONArray jsonArray = jsonObj.getJSONArray("retData");
			if (null != jsonArray && jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					Object obj = jsonArray.get(i);
					JSONObject json = JSONObject.fromObject(obj);
					if (!json.getString("word").isEmpty()) {
						// 先通过字符串的方式得到,转义字符自然会被转化掉
						// System.out.println("word " + json.getString("word"));
						sbf.append(json.getString("word")).append(" ");
					}
				}
				sbf.deleteCharAt(sbf.length() - 1);
			}
			result = sbf.toString();
		} else {
			logger.info("BaiduOcrUtil.getTimuDesc()：识别错误，错误码：" + errno+";错误消息提示："+jsonObj.getString("errMsg"));
			throw new Exception("识别错误，错误码：" + errno+";错误消息提示："+jsonObj.getString("errMsg"));
		}
		return result;
	}	
	
	/**
	 * 将图片进行Base64位编码
	 * 
	 * @param imgUrl
	 *            图片的url路径，如d:\\中文.jpg
	 * @return 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @throws Exception
	 */
	public static String encodeImageToBase64(File imageFile) throws Exception {
		// 其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imageFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			logger.info("BaiduOcrUtil.encodeImageToBase64(File imageFile)：图片编码失败");
			e.printStackTrace();
			throw new Exception("图片编码失败");
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
	
	/**
	 * 将图片进行Base64位编码
	 * 
	 * @param imgUrl
	 *            图片的url路径，如d:\\中文.jpg
	 * @return 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * @throws Exception
	 */
	public static String encodeImageToBase64(byte[] imageFileData) throws Exception {		
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(imageFileData);// 返回Base64编码过的字节数组字符串
	}
}

package org.great.web.controller.test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Synthesizer;
import javax.swing.JPopupMenu.Separator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.great.util.FileUtil;
import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sun.swing.internal.plaf.synth.resources.synth;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Controller
@RequestMapping("/Reception")
public class FileUpload{
	@Autowired
	protected JedisPool jedisPool;

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/uoloadFile.html", method = RequestMethod.POST)
	@ResponseBody
	public synchronized void uoloadFile(HttpServletRequest request, HttpServletResponse response) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("utf-8");
		String savePath = request.getServletContext().getRealPath("");
		savePath = new File(savePath) + "/upload/";
		String fileMd5 = null;
		String chunk = null;
		String fileName = null;
		try {
			List<FileItem> items = sfu.parseRequest(request);
			System.out.println(items.size());
			for (FileItem item : items) {
				// 上传文件的真实名称
				fileName = item.getName();
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					if (fieldName.equals("fileMd5")) {
						try {
							fileMd5 = item.getString("utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					if (fieldName.equals("chunk")) {
						try {
							chunk = item.getString("utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				} else {
					Jedis jedis = null;
					try {
						jedis = jedisPool.getResource();
						File file = new File(savePath + "/" + jedis.get("fileName_" + fileName));
						if (!file.exists()) {
							file.mkdirs();
						}
						// filetype=jedis.get("fileType_" + fileName);
						File chunkFile = new File(savePath + "/" + jedis.get("fileName_" + fileName) + "/" + chunk);
						FileUtil.StreamToFile(chunkFile.getAbsolutePath(), item.getInputStream());
						// item.getInputStream().close();
						// FileUtils.copyInputStreamToFile(item.getInputStream(),
						// chunkFile);
					} catch (Exception e) {
						// e.printStackTrace();
					} finally {
						jedisPool.returnResource(jedis);
					}
				}
			}
		} catch (FileUploadException e) {
			System.out.println("文件传输出错");
			// e.printStackTrace();
		} finally {
		}
	}

	/**
	 * 合并文件
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/mergeFile.html", method = RequestMethod.POST)
	@ResponseBody
	public String mergeFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = request.getParameter("fileName");
		
		// 当前登录用户信息
		// SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
		String folad = "upload";
		String newFilePath = folad + "_" + fileName;
		String savePath = request.getServletContext().getRealPath("");
		savePath = savePath + "\\" + folad + "\\";
		// 文件上传的临时文件保存在项目的temp文件夹下 定时删除
		//savePath = new File(savePath) + "/upload/";
		// 合并文件
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 读取目录里的所有文件
			File f = new File(savePath + "/" + jedis.get("fileName_" + fileName));
			File[] fileArray = f.listFiles(new FileFilter() {
				// 排除目录只要文件
				@Override
				public boolean accept(File pathname) {
					if (pathname.isDirectory()) {
						return false;
					}
					return true;
				}
			});

			// 转成集合，便于排序
			List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));
			Collections.sort(fileList, new Comparator<File>() {
				@Override
				public int compare(File o1, File o2) {
					if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
						return -1;
					}
					return 1;
				}
			});

			// 截取文件名的后缀名
			// 最后一个"."的位置
			int pointIndex = fileName.lastIndexOf(".");
			// 后缀名
			String suffix = fileName.substring(pointIndex);
			// 合并后的文件
			File outputFile = new File(savePath + "/" + jedis.get("fileName_" + fileName) + suffix);
			// 创建文件
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 输出流
			FileChannel outChnnel = new FileOutputStream(outputFile).getChannel();
			// 合并
			FileChannel inChannel;
			for (File file : fileList) {
				inChannel = new FileInputStream(file).getChannel();
				try {
					inChannel.transferTo(0, inChannel.size(), outChnnel);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 删除分片
				file.delete();
			}
			try {
				outChnnel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 清除文件夹
			File tempFile = new File(savePath + "/" + jedis.get("fileName_" + fileName));
			if (tempFile.isDirectory() && tempFile.exists()) {
				tempFile.delete();
			}

			Map<String, String> resultMap = new HashMap<String, String>();
			// 将文件的最后上传时间和生成的文件名返回
			resultMap.put("lastUploadTime", jedis.get("lastUploadTime_" + newFilePath));
			resultMap.put("pathFileName", jedis.get("fileName_" + fileName) + suffix);

			/**************** 清除redis中的相关信息 **********************/
			// 合并成功后删除redis中的进度信息
			jedis.del("jindutiao_" + newFilePath);
			// 合并成功后删除redis中的最后上传时间，只存没上传完成的
			jedis.del("lastUploadTime_" + newFilePath);
			// 合并成功后删除文件名称与该文件上传时生成的存储分片的临时文件夹的名称在redis中的信息 key：上传文件的真实名称
			// value：存储分片的临时文件夹名称（由上传文件的MD5值+时间戳组成）
			// 如果下次再上传同名文件 redis中将存储新的临时文件夹名称 没有上传完成的还要保留在redis中 直到定时任务生效
			jedis.del("fileName_" + fileName);

			Gson gson = new Gson();
			String json = gson.toJson(resultMap);
			System.out.println("合并成功");
			return json;
		} catch (Exception e) {
			System.out.println("合并文件出错");
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
		
		return savePath;
	}

	/**
	 * 检测文件是否存在
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkChunk.html", method = RequestMethod.POST)
	@ResponseBody
	public void checkChunk(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/************************* 检查当前分块是否上传成功 **********************************/
		String fileMd5 = request.getParameter("fileMd5");
		String chunk = request.getParameter("chunk");
		String chunkSize = request.getParameter("chunkSize");
		String jindutiao = request.getParameter("jindutiao");// 文件上传的实时进度
		String param = request.getParameter("param");
		String fileName = request.getParameter("fileName");
		String folad = "uploads";
		String newFilePath = folad + "_" + fileName;
		String savePath = request.getServletContext().getRealPath("");
		savePath = savePath + "\\" + folad + "\\";
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 将当前进度存入redis
			jedis.set("jindutiao_" + newFilePath, jindutiao);

			// 将系统当前时间转换为字符串
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String lastUploadTime = formatter.format(date);
			// 将最后上传时间以字符串形式存入redis
			jedis.set("lastUploadTime_" + newFilePath, lastUploadTime);

			// 自定义文件名： 时间戳（13位）
			String tempFileName = String.valueOf(System.currentTimeMillis());
			if (jedis.get("fileName_" + fileName) == null || "".equals(jedis.get("fileName_" + fileName))) {
				// 将文件名与该文件上传时生成的存储分片的临时文件夹的名称存入redis
				// 文件上传时生成的存储分片的临时文件夹的名称由MD5和时间戳组成
				jedis.set("fileName_" + fileName, fileMd5 + tempFileName);
				jedis.set("fileType_" + fileName, fileName.substring(fileName.lastIndexOf(".")));
			}

			File checkFile = new File(savePath + "/" + jedis.get("fileName_" + fileName) + "/" + chunk);

			response.setContentType("text/html;charset=utf-8");
			// 检查文件是否存在，且大小是否一致
			if (checkFile.exists() && checkFile.length() == Integer.parseInt(chunkSize)) {
				// 上传过
				try {
					response.getWriter().write("{\"ifExist\":1}");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// 没有上传过
				try {
					response.getWriter().write("{\"ifExist\":0}");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	// 当有文件添加进队列时 通过文件名查看该文件是否上传过 上传进度是多少
	@RequestMapping(value = "/selectProgressByFileName.html", method = RequestMethod.POST)
	@ResponseBody
	public String selectProgressByFileName(String fileName) {
		String jindutiao = "";
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (null != fileName && !"".equals(fileName)) {
				String folad = "uploads";
				String newFilePath = folad + "_" + fileName;
				jindutiao = jedis.get("jindutiao_" + newFilePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
		return jindutiao;
	}
}
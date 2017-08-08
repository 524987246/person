package org.great.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import sun.net.TelnetOutputStream;

public class FileUtil {
	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dir
	 *            将要删除的文件目录
	 * @return true 成功 false 失败
	 */
	public static boolean deleteFile(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteFile(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 1.压缩（compression）
	 * 
	 * @param zipFileName
	 * @param inputFile
	 * @throws Exception
	 */
	public static void zipCompression(String zipFileName, String inputFile) throws Exception {
		System.out.println("压缩中...");
		zipFileName = new String(zipFileName.getBytes(), "ISO-8859-1");
		File file = new File(inputFile);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		zip(out, file, file.getName(), bo);
		bo.close();
		out.close(); // 输出流关闭
		System.out.println("压缩完成");
	}

	public static void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) throws Exception { // 方法重载
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
				System.out.println(base + "/");
			}
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
			}
		} else {
			ZipEntry entry = new ZipEntry(base);
			out.putNextEntry(entry); // 创建zip压缩进入点base
			// 防止中文乱码
			out.setEncoding("gbk");
			System.out.println(base);
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // 将字节流写入当前zip目录
			}
			bo.flush();
			bi.close();
			in.close(); // 输入流关闭
		}
	}

	/**
	 * 2.解压（decompression）
	 * 
	 * @param zipFileName
	 *            输入源zip路径
	 * @param inputFile
	 *            输出路径（文件夹目录）
	 * @return
	 */
	public static boolean zipDecompression(String zipFileName, String inputFile) {
		boolean flag = false;
		try {
			// 防止中文乱码
			ZipFile zipFile = new ZipFile(zipFileName, "GBK");
			Enumeration<ZipEntry> e = zipFile.getEntries();
			org.apache.tools.zip.ZipEntry zipEntry = null;
			createDirectory(inputFile, "");
			while (e.hasMoreElements()) {
				zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(inputFile + File.separator + name);
					f.mkdir();
					System.out.println("创建目录：" + inputFile + File.separator + name);
				} else {
					String fileName = zipEntry.getName();
					fileName = fileName.replace('\\', '/');
					System.out.println("解压文件：" + fileName);
					if (fileName.indexOf("/") != -1) {
						createDirectory(inputFile, fileName.substring(0, fileName.lastIndexOf("/")));
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
					}

					File f = new File(inputFile + File.separator + zipEntry.getName());

					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);

					byte[] by = new byte[1024];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
				flag = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("完成");
		return flag;
	}

	/**
	 * 创建目录
	 * 
	 * @param directory
	 *            父目录
	 * @param subDirectory
	 *            子目录
	 */
	private static void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		try {
			if (subDirectory == "" && fl.exists() != true)
				fl.mkdir();
			else if (subDirectory != "") {
				dir = subDirectory.replace('\\', '/').split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (subFile.exists() == false)
						subFile.mkdir();
					directory += File.separator + dir[i];
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * 文件写入
	 * 
	 * @param str
	 *            内容
	 * @param filename
	 *            文件名称
	 * @return 文件的绝对路径
	 */
	public static String writeFile(String str, String filename) {
		try {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 写入文件,如果使用文件名,将会创造出两个文件
			FileWriter fileWritter = new FileWriter(file);

			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(str);
			bufferWritter.flush();
			bufferWritter.close();
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}

	/**
	 * 首字母及下划线后字母大写,并删除下划线
	 * 
	 * @param name
	 * @return
	 */
	public static String setfilenam(String name) {
		int i = 0;
		String temp = "";
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		do {
			i = name.indexOf("_", i + 1);
			if (i == -1) {
				break;
			}
			temp = name.substring(i + 1, i + 2 > name.length() ? name.length() : i + 2);
			temp = temp.toUpperCase();
			name = name.substring(0, i) + temp + name.substring(i + 2 > name.length() ? name.length() : i + 2);

		} while (i != -1);
		return name;
	}

	public static String StreamToFile(String filepath, InputStream inputStream) {
		File file = new File(filepath);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int num = 0;
			long sum=0;
			while (num!=-1){
				out.write(buffer, 0, num);
				num = inputStream.read(buffer);
				sum+=num;
			};
			System.out.println(filepath+"===>"+sum);
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			try {
//				if (inputStream != null) {
//					inputStream.close();
//				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		return file.getAbsolutePath();
	}

	public static long forJava(File f1, File f2) {
		long time = new Date().getTime();
		int length = 2097152;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(f1);
			out = new FileOutputStream(f2);
			byte[] buffer = new byte[length];
			while (true) {
				int ins = in.read(buffer);
				if (ins == -1) {
					in.close();
					out.flush();
					out.close();
					return new Date().getTime() - time;
				} else
					out.write(buffer, 0, ins);
			}
		} catch (IOException e) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new Date().getTime() - time;
	}
}

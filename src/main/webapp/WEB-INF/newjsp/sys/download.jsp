<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page import="java.net.URLEncoder"%>
<%@ page import="org.great.util.FileUtil"%>
<%@ page import="java.io.File"%>
<%
	response.setContentType("application/x-download");//设置为下载application/x-download  
	String filedownload = (String) request.getAttribute("filePath");//即将下载的文件的相对路径  
	String filedisplay = (String) request.getAttribute("fileName");//下载文件时显示的文件保存名称  
	String filenamedisplay = java.net.URLEncoder.encode(filedisplay, "UTF-8");
	response.addHeader("Content-Disposition", "attachment;filename=" + filenamedisplay);

	try {
		RequestDispatcher dis = application.getRequestDispatcher(filedownload);
		if (dis != null) {
			dis.forward(request, response);
		}
		response.flushBuffer();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		out.clear();
		out = pageContext.pushBody();
		File file = new File(filedownload);
		FileUtil.deleteFile(file);
	}
%>
<%@ include file="/WEB-INF/include/newtitlehead.jsp"%>
<script type="text/javascript">
	removeIframe();
</script>

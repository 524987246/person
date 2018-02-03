<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${ctx}/js/jquery-2.2.1.js"></script>
<link rel="stylesheet" href="${ctx}/css/bootstrap.css" media="screen" title="no title" charset="utf-8">
<script type="text/javascript" src="${ctx}/js/bootstrap.js" charset="utf-8">
</script>
<script src="${ctx}/js/cookieUtil.js"></script>
<script src="${ctx}/viewjs/index.js"></script>
<script src="${ctx}/js/public_function.js"></script>
<script type="text/javascript" src="${ctx}/js/this_project_function.js" charset="utf-8">
</script>
<script src="${ctx}/tools/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var basePath='${ctx}';
</script>
<meta id="token" name="${_csrf.token}" />
<meta id="header" name="${_csrf.headerName}" />

<!-- fileinput 插件 开始 -->
<link type="text/css" rel="stylesheet" href="${ctx}/statis/fileinput/css/fileinput.css" />
<script type="text/javascript" src="${ctx}/statis/fileinput/js/fileinput.js"></script>
<%-- <script type="text/javascript" src="${ctx}/statis/fileinput/js/fileinput_locale_zh.js"></script> --%>
<!-- fileinput 插件 结束 -->

<!-- webupload 插件 开始 -->
<link type="text/css" rel="stylesheet" href="${ctx}/statis/webupload/webuploader.css" />
<script type="text/javascript" src="${ctx}/statis/webupload/webuploader.js"></script>
<!-- webupload 插件 结束 -->

<!-- ztree插件   开始 -->
<link href="${ctx}/statis/ztree/css/metroStyle/metroStyle.css" type="text/css" rel="stylesheet" /> 
<script type="text/javascript" src="${ctx}/statis/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="${ctx}/statis/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/statis/ztree/js/jquery.ztree.exhide.js"></script>
<!-- ztree插件  结束 -->

<!-- pagination 分页插件   开始 -->
<link href="${ctx}/statis/pagination/pagination.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/statis/pagination/jquery.pagination.js"></script>
<!-- pagination 分页插件   结束 -->

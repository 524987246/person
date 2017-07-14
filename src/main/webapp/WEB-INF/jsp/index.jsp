<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%@ include file="/WEB-INF/include/titlehead.jsp" %>
  <!--  <link rel="stylesheet" href="${ctx}/viewcss/left.css" media="screen" title="no title" charset="utf-8">
--><script src="${pageContext.request.contextPath}/viewjs/index.js"></script>
<title>主页</title>
<body style="width: 100%;height:100%;background-image:url(${ctx}/images/backimage.png);">
<button class="" data-toggle="modal"
   data-target="#myModal" hidden="hidden" id="dialogText">
</button>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
          <div class="modal-content">
             <div class="modal-header">
                <button type="button" class="close"
                   data-dismiss="modal" aria-hidden="true">
                      &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                  提示
                </h4>
             </div>
             <div class="modal-body" id="message">

             </div>
             <div class="modal-footer">
                <button type="button" class="btn btn-default"
                   data-dismiss="modal">关闭
                </button>
             </div>
          </div>
    </div>
    </div>
</body>
</html>

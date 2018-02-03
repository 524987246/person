
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/include/titlehead.jsp"%>
<!-- 富文本插件 开始 -->
<script type="text/javascript"
	src="${ctx}/statis/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript"
	src="${ctx}/statis/ueditor/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript"
	src="${ctx}/statis/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- 富文本插件 结束 -->
<title>富文本功能实现</title>
</head>
<body>
	<h1>UEditor完整版Demo</h1>
	<script id="container" name="content" type="text/plain" style="width: 800px;height: 1024px;">这里写你的初始化内容</script>
	<script type="text/javascript">
		
	</script>
	<!-- <div class="section">
		<h4>语言切换</h4>

		<div class="details">
			<input type="button" value="zh-cn" onclick="setLanguage(this)">
			<input type="button" value="en" onclick="setLanguage(this)">
		</div>
	</div> -->
	<div class="section">
            <h4>常用API</h4>

            <div id="allbtn" class="details">
                <div id="btns">
                    <div>
                        <button onclick="getAllHtml()">获得整个html的内容</button>
                        <button onclick="getContent()">获得内容</button>
                        <button onclick="getContentTxt()">获得纯文本</button>
                        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
                        <button onclick="hasContent()">判断是否有内容</button>
                       <!--  <button onclick="setFocus()">使编辑器获得焦点</button> -->
                    </div>
                    <!-- <div>
                        <button onclick="getText()">获得当前选中的文本</button>
                        <button onclick="insertHtml()">插入给定的内容</button>
                        <button id="enable" onclick="setEnabled()">可以编辑</button>
                        <button onclick="setDisabled()">不可编辑</button>
                        <button onclick="UE.getEditor('editor').setHide()">隐藏编辑器</button>
                        <button onclick="UE.getEditor('editor').setShow()">显示编辑器</button>
                        <button onclick="UE.getEditor('editor').setHeight(300)">设置编辑器的高度为300</button>
                    </div> -->

                </div>
               <!--  <div>
                    <button type="button" onclick="createEditor()">创建编辑器</button>
                    <button type="button" onclick="deleteEditor()">删除编辑器</button>
                    <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
                    <button onmousedown="setblur(event)">编辑器失去焦点</button>
                    <button onclick="getLocalData()">获取草稿箱内容</button>
                    <button onclick="clearLocalData()">清空草稿箱</button>
                </div> -->
            </div>
        </div>
</body>
<script type="text/javascript" src="${ctx}/viewjs/webUEditor.js"></script>
</html>
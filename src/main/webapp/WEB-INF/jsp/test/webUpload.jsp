
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/include/titlehead.jsp"%>
<script type="text/javascript" src="${ctx}/viewjs/webupload.js"></script>
<title>断点上传文件实现</title>
</head>
<body>
	<!-- 断点续传   start-->
	<!-- 隐藏域 实时保存上传进度 -->
	<input id="jindutiao" type="hidden" />
	<div id="uploader" class="wu-example">
		<label class="text-right"
			style="font-weight:100;float:left;margin-left:15px;width:144px;margin-right:15px;">大文件：</label>
		<div class="btns">
			<div id="picker" class="webuploader-container">
				<div class="webuploader-pick">选择文件</div>
				<div id="rt_rt_1bchdejhrarjdvd11h41eoh1nt1"
					style="position: absolute; top: 0px; left: 0px; width: 88px; height: 35px; overflow: hidden; bottom: auto; right: auto;">
					<input id="file_bp" name="file"
						class="webuploader-element-invisible" type="file" /> <label
						style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></label>
				</div>
			</div>
			<!-- 文件列表：选择文件后在该div显示 -->
			<div id="thelist"
				class="uploader-list list-group-item clearfix ng-hide"
				style="margin-left:160px;"></div>
			<label class="text-right"
				style="font-weight:100;float:left;margin-left:15px;width:144px;margin-right:15px;"></label>
			<button class="btn m-b-xs btn-sm btn-info btn-addon"
				id="startOrStopBtn" style="padding:7px 50px;margin-top:20px;">开始上传</button>
		</div>
	</div>
	<!-- 断点续传   end-->
</body>
</html>
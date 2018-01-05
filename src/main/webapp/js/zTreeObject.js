//ztree工具  封装成对象,便于查询等操作
//TODO 尚未测试
function zTreeUtil(setting, htmlId) {
	var zTree;
	this.setting = setting;
	this.htmlId = htmlId;
	//初始化树形
	this.init = function(array) {
		zTree = $.fn.zTree.init($(this.htmlId), this.setting, array);
	}
	this.setNode = function(id) {
		if (id != null && id != "") {
			var node = zTree.getNodeByParam(this.setting.data.simpleData.idKey, id);
			zTree.selectNode(node);
		}
	}
	this.getNode = function() {
		var nodes = zTree.getSelectedNodes();
		if (nodes == null || nodes.length == 0) {
			return;
		}
		/*		$("#parentId").val(nodes[0].id);
				$("#parentName").val(nodes[0].name);*/
		return nodes;
	}

	this.setCheckedNodes = function(array) {
		if (array != null) {
			var idKey = this.setting.data.simpleData.idKey;
			for (var i = 0; i < array.length; i++) {
				var id = array[i];
				var node = zTree.getNodeByParam(idKey, id);
				zTree.checkNode(node, true, true);
			}
		}
	}
	this.getCheckedNodes = function() {
		var nodes = zTree.getCheckedNodes(true);
		var list = new Array();
		for (var i = 0; i < nodes.length; i++) {
			console.dir(nodes[i].id);
			list.push(nodes[i].id);
		}
		return list;

	}

	this.expandAll = function() {
		zTree.expandAll(true);
	}
	//搜索框功能实现
	var nodeList;
	this.queryByName = function(value) {
		var allNode = zTree.transformToArray(zTree.getNodes());
		;
		zTree.hideNodes(allNode);
		nodeList = zTree.getNodesByParamFuzzy("name", value, null);
		nodeList = zTree.transformToArray(nodeList);
		for (var n in nodeList) {
			findParent(zTree, nodeList[n]);
		}
		zTree.showNodes(nodeList);
		if (value == "") {
			zTree.expandAll(false);
		}
	}
	function findParent(treeObj, node) {
		treeObj.expandNode(node, true, false, false);
		var pNode = node.getParentNode();
		if (pNode != null) {
			nodeList.push(pNode);
			findParent(treeObj, pNode);
		}
	}
}
//ztree工具  封装成对象,便于查询等操作
//TODO 尚未测试
function zTreeUtil(setting, htmlId) {
	var zTreeMenu;
	this.setting = setting;
	this.htmlId = htmlId;
	//初始化树形
	this.init = function(array) {
		zTreeMenu = $.fn.zTree.init($(this.htmlId), this.setting, array);
	}
	this.setNode = function(id) {
		if (id != null && id != "") {
			var node = zTreeMenu.getNodeByParam(this.setting.data.simpleData.idKey, id);
			zTreeMenu.selectNode(node);
		}
	}
	this.getNode = function() {
		var nodes = zTreeMenu.getSelectedNodes();
		if (nodes == null || nodes.length == 0) {
			return;
		}
		/*		$("#parentId").val(nodes[0].id);
				$("#parentName").val(nodes[0].name);*/
		return nodes;
	}
	//搜索框功能实现
	var nodeList;
	this.queryByName = function(value) {
		var allNode = zTreeMenu.transformToArray(zTreeMenu.getNodes());
		;
		zTreeMenu.hideNodes(allNode);
		nodeList = zTreeMenu.getNodesByParamFuzzy("name", value, null);
		nodeList = zTreeMenu.transformToArray(nodeList);
		for (var n in nodeList) {
			findParent(zTreeMenu, nodeList[n]);
		}
		zTreeMenu.showNodes(nodeList);
		if (value == "") {
			zTreeMenu.expandAll(false);
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
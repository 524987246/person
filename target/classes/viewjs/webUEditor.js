var editor = UE.getEditor('container')
function getAllHtml(){
	alert($("#container").html());
}
function getContent(){
	alert(editor.getContent());
}
function getContentTxt(){
	alert(editor.getContentTxt());
}
function getPlainTxt(){
	alert(editor.getPlainTxt());
}
function hasContent(){
	alert(editor.hasContents());
}
function setFocus(){
	alert(editor.setFocus());
}
function getText(){
	alert(editor.getText());
}
function setEnabled(){
	alert(editor.setEnabled());
}
function setDisabled(){
	alert(editor.setDisabled());
}
function insertHtml(){
	editor.insertHtml("插入给定内容");
}
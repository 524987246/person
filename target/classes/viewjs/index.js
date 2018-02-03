/**
 * 提示框
 * 
 * @param str
 *            提示信息
 */
function hint(str) {
	$("#myModalLabel").text("提示");
    $("#message").text(str);
    $("#dialogText").trigger("click");
}
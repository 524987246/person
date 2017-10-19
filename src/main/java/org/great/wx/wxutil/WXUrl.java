package org.great.wx.wxutil;

public class WXUrl {

	// 账户基本信息
	public static final String APPID = "wx59f1afdea7f76a12";
	public static final String APPSECRET = "3d61d68030a9556c2dd9a68fd497222f";

	// 获取access_token
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String ACCESS_TOKEN_URL_TRUE = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET",
			APPSECRET);

	// 获取access_token 与 openId
	public static final String OAUTH_OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String OAUTH_OPENID_URL_TURE = OAUTH_OPENID_URL.replace("APPID", APPID).replace("SECRET",
			APPSECRET);

	// 获取用户列表 一次查询一万条
	public static final String USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	public static final String USER_LIST_URL_TYPE = "GET";

	// 消息推送
	public static final String SEND_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";
	public static final String SEND_MSG_URL_TYPE = "POST";
	
	//获取所有消息模板
	public static final String GET_ALL_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
	public static final String GET_ALL_TEMPLATE_TYPE = "GET";
	
	//发送模板消息推送
	public static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	public static final String SEND_TEMPLATE_MESSAGE_TYPE = "POST";

}

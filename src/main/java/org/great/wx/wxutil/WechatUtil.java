package org.great.wx.wxutil;

import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.great.util.myutil.MyPrintUtil;
import org.great.wx.bean.AccessToken;
import org.great.wx.bean.MessageTemplate;
import org.great.wx.bean.OAuthInfo;
import org.great.wx.bean.SendTemplate;
import org.json.JSONException;
import org.junit.Test;

public class WechatUtil {
	private static AccessToken token;

	/**
	 * 获取accessToken
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getAccessToken() throws ParseException, IOException {
		if (token != null) {
			Long end = System.currentTimeMillis();
			Long begin = token.getTime();
			boolean bo = (end - begin) > (7200 * 1000) ? true : false;
			if (!bo) {
				return token;
			}
		}
		token = new AccessToken();
		String url = WXUrl.ACCESS_TOKEN_URL_TRUE;
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
			token.setTime(System.currentTimeMillis());
		} else {
			token = null;
		}
		return token;
	}

	public static OAuthInfo getOAuthOpenId(String appid, String secret, String code)
			throws ParseException, IOException {
		OAuthInfo oAuthInfo = null;
		String url = WXUrl.OAUTH_OPENID_URL_TURE.replace("CODE", code);
		JSONObject jsonObject = doGetStr(url);
		// oAuthInfo是作者自己把那几个属性参数写在一个类里面了。
		// 如果请求成功
		if (null != jsonObject) {
			try {
				oAuthInfo = new OAuthInfo();
				AccessToken token = new AccessToken();
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				token.setToken(jsonObject.getString("access_token"));
				oAuthInfo.setAccessToken(token);
				oAuthInfo.setRefreshToken(jsonObject.getString("refresh_token"));
				oAuthInfo.setOpenId(jsonObject.getString("openid"));
				oAuthInfo.setScope(jsonObject.getString("scope"));
			} catch (JSONException e) {
				oAuthInfo = null;
			}
		}
		return oAuthInfo;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url, String outStr) throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr, "UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}

	// @Test
	public void systime() throws InterruptedException {
		Long begin = System.currentTimeMillis();
		Thread.sleep(1000);
		Long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	@Test
	public void add() {
		// 测试获取token
		try {
			AccessToken accessToken = getAccessToken();
			String url = WXUrl.GET_ALL_TEMPLATE.replaceAll("ACCESS_TOKEN", accessToken.getToken());
			MessageTemplate messageTemplate = new MessageTemplate();
			messageTemplate.getFirst().setValue("您好，您有新的技术任务");
			messageTemplate.getKeyword1().setValue("张三有新装的技术任务");
			messageTemplate.getKeyword2().setValue("尚未完成");
			messageTemplate.getRemark().setValue("请及时完成");
			SendTemplate temp = new SendTemplate();
			temp.setData(messageTemplate);
			JSONObject jsonStu = JSONObject.fromObject(temp);
			System.out.println(jsonStu.toString());
			url = WXUrl.SEND_TEMPLATE_MESSAGE.replaceAll("ACCESS_TOKEN", accessToken.getToken());
			JSONObject json = doPostStr(url, jsonStu.toString());
			System.out.println(json.toString());

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

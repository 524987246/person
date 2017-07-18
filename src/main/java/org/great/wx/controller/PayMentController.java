package org.great.wx.controller;

import java.io.OutputStream;
import java.util.Hashtable;

import javax.servlet.http.HttpServletResponse;

import org.great.wx.payment.PayMentUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

/**
 * 微信支付
 * 未测试
 * @author xiej
 * @date 2017年7月18日13:57:01
 * @since 1.0
 */
@Controller
@RequestMapping("/WeChat/test")
public class PayMentController {
	/**
	 * 创建二维码
	 */
	@RequestMapping("createQRCode")
	public void createQRCode(String orderId, HttpServletResponse response) {

		// 生成订单
		String orderInfo = PayMentUtil.createOrderInfo(orderId);
		// 调统一下单API
		String code_url = PayMentUtil.httpOrder(orderInfo);
		// 将返回预支付交易链接（code_url）生成二维码图片
		// 这里使用的是zxing <span
		// style="color:#ff0000;"><strong>说明1(见文末)</strong></span>
		try {
			int width = 200;
			int height = 200;
			String format = "png";
			Hashtable hints = new Hashtable();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(code_url, BarcodeFormat.QR_CODE, width, height, hints);
			OutputStream out = null;
			out = response.getOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, format, out);
			out.flush();
			out.close();
		} catch (Exception e) {
		}

	}
}

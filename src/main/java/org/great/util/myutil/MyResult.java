package org.great.util.myutil;

public class MyResult {
	private int errcode;
	private String errmsg;
	private Long msgid;
	private Object object;

	public static MyResult ok() {
		return new MyResult();
	}

	public static MyResult ok(String str) {
		return new MyResult(str);
	}

	public static MyResult ok(Object object) {
		return new MyResult(object);
	}

	public static MyResult error() {
		return new MyResult(500, "未知错误,请联系管理员", 0L);
	}

	public static MyResult error(String str) {
		return new MyResult(500, str, 0L);
	}

	public static MyResult error(int coke, String str) {
		return new MyResult(coke, str, 0L);
	}

	public MyResult() {
		this.errcode = 0;
		this.errmsg = "ok";
		this.msgid = 0L;
	}

	public MyResult(String str) {
		this.errcode = 0;
		this.errmsg = str;
		this.msgid = 0L;
	}

	public MyResult(Object object) {
		this.errcode = 0;
		this.errmsg = "ok";
		this.msgid = 0L;
		this.object = object;
	}

	public MyResult(int errcode, String errmsg, Long msgid) {
		super();
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.msgid = msgid;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Long getMsgid() {
		return msgid;
	}

	public void setMsgid(Long msgid) {
		this.msgid = msgid;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}

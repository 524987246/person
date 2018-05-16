package org.great.util.lucene;

public class DbBaseInfo {
	private String driver;
	private String url;
	private String username;
	private String userpwd;

	public DbBaseInfo() {
	}

	public DbBaseInfo(String driver, String url, String username, String userpwd) {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.userpwd = userpwd;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

}

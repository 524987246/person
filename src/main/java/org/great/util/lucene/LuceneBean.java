package org.great.util.lucene;

public class LuceneBean {
	private String id;
	private int flag;
	private String name;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "LuceneBean [id=" + id + ", flag=" + flag + ", name=" + name + ", content=" + content + "]";
	}

}

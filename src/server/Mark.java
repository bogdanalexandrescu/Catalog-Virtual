package server;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by teo on 16.04.2017.
 */
public class Mark implements Serializable {

	String data;
	int mark;

	public Mark(int mark, String data) {
		this.data = data;
		this.mark = mark;
	}

	public Mark() {

	}

	public String getData() {
		return data;
	}

	@XmlElement
	public void setData(String data) {
		this.data = data;
	}

	public int getMark() {
		return mark;
	}

	@XmlElement
	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Mark{" + "data='" + data + '\'' + ", mark=" + mark + '}';
	}
}

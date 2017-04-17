package server;

import java.io.Serializable;

/**
 * Created by teo on 16.04.2017.
 */
public class Mark implements Serializable {

    String data;
    int mark;


    public Mark(String data, int mark) {
        this.data = data;
        this.mark = mark;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }


    @Override
    public String toString() {
        return "Mark{" +
                "data='" + data + '\'' +
                ", mark=" + mark +
                '}';
    }
}

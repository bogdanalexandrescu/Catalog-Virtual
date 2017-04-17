package server;

import java.io.Serializable;

/**
 * Created by teo on 16.04.2017.
 */
public class Absence implements Serializable {

    String data;


    public Absence(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    @Override
    public String toString() {
        return "Absence{" +
                "data='" + data +
                '}';
    }
}

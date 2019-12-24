package util;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String name;
    private Map<String, Object> parameteres;

    public Request(){
        parameteres = new HashMap<>();
    }


    public Request(String name, Map<String, Object> parameteres) {
        this.name = name;
        this.parameteres = parameteres;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getParameteres() {
        return parameteres;
    }

    public void setParameteres(Map<String, Object> parameteres) {
        this.parameteres = parameteres;
    }
}

package ru.halt.practice.exception;

/**
 * Created by Petr Rudenko on 20.02.2016.
 */
public class ExceptionJSONInfo {
    private String message;
    private String url;

    public ExceptionJSONInfo(){
    }

    public ExceptionJSONInfo(String message, String url){
        this.message = message;
        this.url = url;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}

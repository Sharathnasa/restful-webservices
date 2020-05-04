package com.fxpi.rest.webservices.restfulwebservices.Beans;

public class HelloBean {
    private String message;

    public HelloBean(String hello_world) {
        this.message = hello_world;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

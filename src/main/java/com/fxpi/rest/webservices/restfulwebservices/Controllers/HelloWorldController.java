package com.fxpi.rest.webservices.restfulwebservices.Controllers;

import com.fxpi.rest.webservices.restfulwebservices.Beans.HelloBean;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    // here i'm returning bean
    @GetMapping("/hello-bean")
    public HelloBean helloBean() {
        return new HelloBean("Hello World");
    }

    @GetMapping("/hello-bean/{name}")
    public HelloBean helloBean(@PathVariable String name) {
        return new HelloBean(String.format("Hello %s", name));
    }

    @GetMapping("/hello-bean-inter")
    public String helloBeanInter() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}

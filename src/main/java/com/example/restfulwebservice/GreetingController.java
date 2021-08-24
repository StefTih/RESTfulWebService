package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

//  This code uses Spring @RestController annotation, which marks the class as a controller where every method
//  returns a domain object instead of a view. It is shorthand for including both @Controller and @ResponseBody.

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


//    There are companion annotations for other HTTP verbs(e.g. @PostMapping for POST).
//    There is also a @RequestMapping annotation that they all derive from,
//    and can serve as a synonym (e.g. @RequestMapping(method=GET))

//    @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method.
//    If the name parameter is absent in the request, the defaultValue of World is used.

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}

//  @SpringBootApplication is a convenience annotation that adds all of the following:
//
//  1. @Configuration: Tags the class as a source of bean definitions for the application context.
//
//  2. @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
//  other beans, and various property settings. For example, if spring-webmvc is on the classpath,
//  this annotation flags the application as a web application and activates key behaviors,
//  such as setting up a DispatcherServlet.
//
//  3. @ComponentScan: Tells Spring to look for other components, configurations,
//  and services in the com/example package, letting it find the controllers.
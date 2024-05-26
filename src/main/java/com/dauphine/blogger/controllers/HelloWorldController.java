package com.dauphine.blogger.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@Tag(
        name = "Hello World API",
        description = "My first hello world endpoints"
)
public class HelloWorldController {

    @GetMapping("/hello-world")
    @Operation(summary = "Hello world endpoint", description = "Returns 'Hello World!'")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    @Operation(summary = "Hello by name endpoint", description = "Returns 'Hello {name}' by request parameter")
    public String helloByName(@Parameter(description = "Name to greet") @RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello/{name}")
    @Operation(summary = "Hello by name endpoint", description = "Returns 'Hello {name}' by path variable")
    public String hello(@Parameter(description = "Name to greet") @PathVariable String name) {
        return "Hello " + name;
    }
}

package com.learning.FirstSpringAPI;

import org.springframework.web.bind.annotation.*;

@RestController
//localhost:8080/sample
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/sayhello/{name}")
    public String sayHello(@PathVariable("name")String name, @RequestParam(required = false) String id){

          return "Hey "+name +" " + ((id != null) ? id : "");

    }


}

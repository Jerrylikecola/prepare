package com.jerrylikecola.prepare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaxiang
 * @date 2021/4/19 16:38
 * @description
 */

@RestController()
@RequestMapping(value = "/api/trade")
public class controller {

    @GetMapping(value = "/order-due")
    public String test1(){
        return "ok";
    }

    @GetMapping(value = "/order-due-A-B")
    public String test2(){
        return "ok";
    }
}

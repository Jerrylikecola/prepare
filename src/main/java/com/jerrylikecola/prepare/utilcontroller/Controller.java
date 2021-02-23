package com.jerrylikecola.prepare.utilcontroller;

import com.jerrylikecola.prepare.juc.Pool;
import com.jerrylikecola.prepare.juc.Sleep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaxiang
 * @date 2021/1/13 16:45
 * @description
 */

@RestController
public class Controller {

    @Autowired
    private Pool pool;

    @GetMapping("/add")
    public void add(){
        pool.submit(new Sleep());
    }
}

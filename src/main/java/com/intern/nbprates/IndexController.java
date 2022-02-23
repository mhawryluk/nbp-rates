package com.intern.nbprates;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "Error occured. Wrong url or internal error.";
    }
}
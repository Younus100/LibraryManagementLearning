package com.library.library_management.adminservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibrarienController {

    @GetMapping("/admin/test")
    String test(){
        System.out.println("Admin");
        return "Admin";
    }
}

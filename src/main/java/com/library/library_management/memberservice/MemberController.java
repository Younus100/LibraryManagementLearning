package com.library.library_management.memberservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @GetMapping("/member/test")
    String test(){
        System.out.println("dd");
        return "dd";
    }

}

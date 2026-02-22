package com.nawid.EMS.controller.v2;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/test")
public class testControllerV2 {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String getMessage(){
        return "This is the message";
    }
}

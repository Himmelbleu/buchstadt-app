package com.buchstadt.controller;

import com.buchstadt.annotaion.GlobalUrl;
import com.buchstadt.pojo.Admin;
import com.buchstadt.pojo.User;
import com.buchstadt.pojo.dto.TokenDto;
import com.buchstadt.pojo.vo.SignUpVo;
import com.buchstadt.service.EntryService;
import com.buchstadt.utils.R;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@GlobalUrl("/entry")
public class EntryController {

    @Resource
    private EntryService service;

    @PostMapping("/public/user-signin")
    public R<TokenDto> userSignIn(@RequestBody User user) {
        return service.userSignIn(user);
    }

    @PostMapping("/public/admin-signin")
    public R<TokenDto> adminSignIn(@RequestBody Admin admin) {
        return service.adminSignIn(admin);
    }

    @PostMapping("/public/user-signup")
    public R<Void> userSignup(@Validated @RequestBody SignUpVo vo) {
        return service.userSignUp(vo);
    }

}

package com.myapp.security_user.controller;

import com.myapp.security_user.dto.UserDto;
import com.myapp.security_user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    /**
     * 사용자 등록 html 반환
     * @param model
     * @return
     */
    @GetMapping("/adduser")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-user";
    }

    /**
     * 사용자 등록
     * @param userDto
     * @param result
     * @return
     */
    @PostMapping("/adduser")
    public String register(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.createUser(userDto);
        return "redirect: adduser ? success";
    }
}

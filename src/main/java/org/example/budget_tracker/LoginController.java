// com.example.demo.LoginController
package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value="err",required=false) String err, Model model) {
        model.addAttribute("err", err != null);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {
        if ("demo".equals(username) && "pass123".equals(password)) {
            session.setAttribute("USER", username);
            return "redirect:/ui/expenses";
        }
        return "redirect:/login?err=1";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

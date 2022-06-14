package com.example.springboottask20;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/add")
    public String createUser(@RequestParam(required = false, defaultValue = "", name = "inputName") String inputName,
                             @RequestParam(required = false, defaultValue = "", name = "inputLastName")
                             String inputLastName,
                             @RequestParam(required = false, defaultValue = "0", name = "inputAge") Integer inputAge) {
        if (inputName.isEmpty() || inputName == null ||
                inputLastName.isEmpty() || inputLastName == null ||
                inputAge.intValue() <= 0 || inputAge == null) {
            return "redirect:/err.html";
        }
        User user = new User(inputName, inputLastName, inputAge);
        userRepository.addUser(user);
        return "redirect:/success.html";
        //example /add?inputName=Daniel&inputLastName=Abacki&inputAge=10
    }

    @ResponseBody
    @RequestMapping("/users")
    public String displayUsers() {
        List<User> users = userRepository.getAll();
        String result = "";
        for (User user : users) {
            result += user.getName() + " " + user.getLastName() + " - " + user.getAge() + " lat" + "<br/>";
        }
        return result;
    }
}

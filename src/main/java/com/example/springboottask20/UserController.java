package com.example.springboottask20;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //example /add?inputName=Daniel&inputLastName=Abacki&inputAge=10
    @RequestMapping("/add")
    public String createUser(@RequestParam(defaultValue = "", name = "inputName") String inputName,
                             @RequestParam(defaultValue = "", name = "inputLastName") String inputLastName,
                             @RequestParam(defaultValue = "0", name = "inputAge") Integer inputAge) {
        if (inputName.isEmpty() || inputLastName.isEmpty() || inputAge <= 0) {
            return "redirect:/err.html";
        }
        User user = new User(inputName, inputLastName, inputAge);
        userRepository.addUser(user);
        return "redirect:/success.html";
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

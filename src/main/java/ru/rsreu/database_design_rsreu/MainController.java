package ru.rsreu.database_design_rsreu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rsreu.database_design_rsreu.model.User;
import ru.rsreu.database_design_rsreu.model.UserRoleEnum;
import ru.rsreu.database_design_rsreu.model.UserStatusEnum;
import ru.rsreu.database_design_rsreu.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String get(Model model) {
        userRepository.save(new User(null, UserRoleEnum.USER, UserStatusEnum.ONLINE, "Steve"));
        model.addAttribute("headers", Arrays.stream(User.class.getDeclaredFields()).map(it -> it.getName()).toList());
        model.addAttribute("rows", userRepository.findAll().stream().map(it -> {
            List<String> userDataColumned = new ArrayList<>();
            userDataColumned.add(it.getId().toString());
            userDataColumned.add(it.getUserRoleEnum().toString());
            userDataColumned.add(it.getUserStatusEnum().toString());
            userDataColumned.add(it.getName());
            return userDataColumned;
        }).toList());
        return "home";
    }
}

package ru.rsreu.database_design_rsreu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rsreu.database_design_rsreu.model.User;
import ru.rsreu.database_design_rsreu.model.UserRoleEnum;
import ru.rsreu.database_design_rsreu.model.UserStatusEnum;
import ru.rsreu.database_design_rsreu.repository.UserRepository;

@RestController
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String get() {
        userRepository.save(new User(null, UserRoleEnum.USER, UserStatusEnum.ONLINE, "Steve"));
        return userRepository.findAll().toString();
    }
}

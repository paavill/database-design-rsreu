package ru.rsreu.database_design_rsreu;

import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rsreu.database_design_rsreu.dto.UserByNameDto;
import ru.rsreu.database_design_rsreu.model.User;
import ru.rsreu.database_design_rsreu.model.UserRoleEnum;
import ru.rsreu.database_design_rsreu.model.UserStatusEnum;
import ru.rsreu.database_design_rsreu.repository.UserRepository;

import java.lang.reflect.Field;
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
        prepareModelWithUsersTable(model, userRepository.findAll());
        model.addAttribute("totalCount", userRepository.getCount());
        model.addAttribute("onlineCount", userRepository.getOnlineCount());
        return "home";
    }

    @GetMapping("/getByName")
    public String getByNamePage(Model model) {
        model.addAttribute("userByNameDto", new UserByNameDto());
        return "getByName";
    }

    @PostMapping("/getByName")
    public String getByName(Model model, UserByNameDto userByNameDto) {
        List<User> result;
        if (userByNameDto.getName().isBlank()) {
            result = userRepository.findAll();
        } else {
            result = userRepository.findByName(userByNameDto.getName());
        }
        prepareModelWithUsersTable(model, result);
        return "getByName";
    }

    @GetMapping("/insert")
    public String getInsertPage() {
        return "insert";
    }

    @PostMapping("/insert")
    public String insert(@RequestParam String username) {
        userRepository.insert(username);
        return "redirect:/";
    }

    private void prepareModelWithUsersTable(Model model, List<User> userRepository) {
        model.addAttribute("headers",
                Arrays.stream(User.class.getDeclaredFields()).map(Field::getName).toList());
        model.addAttribute("rows",
                userRepository.stream().map(MainController::getUserString).toList());
    }

    private static List<String> getUserString(User user) {
        List<String> userDataColumned = new ArrayList<>();
        userDataColumned.add(user.getId().toString());
        userDataColumned.add(user.getUserRoleEnum().toString());
        userDataColumned.add(user.getUserStatusEnum().toString());
        userDataColumned.add(user.getName());
        return userDataColumned;
    }
}

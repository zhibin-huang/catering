package me.catering.demo.web.admin;

import me.catering.demo.domain.entity.User;
import me.catering.demo.service.UserService;
import me.catering.demo.web.dto.UserDto;
import me.catering.demo.web.dto.admin.UserCreateDto;
import me.catering.demo.web.dto.admin.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/admin/users")
public class AdminUsersController {

    private final UserService userService;

    @Autowired
    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView listAllUsers() {
        Iterable<User> users = userService.getAllUser();
        return new ModelAndView("admin/users", "users", users);
    }

    @PostMapping(path = "/add")
    public String addUser(@Valid UserCreateDto userCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/users";
        }

        UserDto userDto = new UserDto();
        userDto.setEmail(userCreateDto.getEmail());
        userDto.setPassword(userCreateDto.getPassword());
        userDto.setUsername(userCreateDto.getUsername());

        if (!userService.addUser(userDto)) {
            return "redirect:/admin/users";
        }

        User user = userService.getUserByUsername(userCreateDto.getUsername());
        user.setCredit(userCreateDto.getCredit());
        user.setDeleted(userCreateDto.isDeleted());
        user.setIsAdmin(userCreateDto.isAdmin());

        if (userService.saveUser(user)) {
            return "redirect:/admin/users";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/update")
    public String updateUser(@Valid UserUpdateDto userUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            return "redirect:/admin/users";
        }

        User user = userService.getUserById(userUpdateDto.getUserId());

        user.setUsername(userUpdateDto.getUsername());
        user.setEmail(userUpdateDto.getEmail());
        user.setCredit(userUpdateDto.getCredit());
        user.setPassword(userUpdateDto.getPassword());
        user.setDeleted(userUpdateDto.isDeleted());
        user.setIsAdmin(userUpdateDto.isAdmin());

        if (userService.updateUser(user)) {
            return "redirect:/admin/users";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/delete")
    public String deleteUser(@RequestParam int userId) {
        if (userService.deleteUserByUserId(userId)) {
            return "redirect:/admin/users";
        } else {
            return "error";
        }
    }
}

package web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "list/index";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "list/new";
    }

    @GetMapping("/{id}") //сюда можем поместить любое число и оно вставится в аргументы этого метода
    public String show(@PathVariable("id") long id, Model model) {
        // с помощью этой аннотации мы сможем вытащить id
        model.addAttribute("user", userService.readUserById(id));
        //получим одного человека по id из дао и сервиса и передадим на отображение
        return "list/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.readUserById(id));
        return "list/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(user);
        return "redirect:/user/";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/user/";
    }

}

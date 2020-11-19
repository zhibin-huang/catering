package me.catering.demo.web.customer;

import me.catering.demo.domain.document.UserProfile;
import me.catering.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/user/profile")
public class UserProfileRestController {

    private final UserService userService;

    @Autowired
    public UserProfileRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserProfile getProfile(Principal principal) {
        return userService.getProfileByUsername(principal.getName());
    }

    @GetMapping("/reset")
    public boolean resetAllProfiles() {
        return userService.resetAllProfiles();
    }

    // 获得用户的地址簿
    @GetMapping("/address")
    public List<String> getAddresses(Principal principal) {
        return userService.getProfileByUsername(principal.getName()).getAddresses();
    }
    // 添加收货地址
    @PostMapping("/address")
    public boolean addAddress(@RequestParam String address, Principal principal) {
        return userService.addAddress(address, principal.getName());
    }
    //更新某条收货地址
    @PutMapping("/address")
    public boolean updateAddress(@RequestParam int index, @RequestParam String address, Principal principal) {
        return userService.updateAddress(index, address, principal.getName());
    }
    //删除某条收货地址
    @DeleteMapping("/address/{index}")
    public boolean removeAddress(@PathVariable int index, Principal principal) {
        return userService.removeAddress(index, principal.getName());
    }

    // private profile
    @PutMapping("/profile")
    public boolean updateProfile(@RequestParam String bio, @RequestParam String location, Principal principal) {
        return userService.updateProfilePart(bio, location, principal.getName());
    }

    // subscription
    @PutMapping("/subscription")
    public boolean updateSubscription(@RequestParam boolean newsletters, @RequestParam boolean autoRecharge, Principal principal) {
        return userService.updateSubscriptionPart(newsletters, autoRecharge, principal.getName());
    }
}

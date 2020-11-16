package me.catring.demo.web.image;


import me.catring.demo.service.FoodService;
import me.catring.demo.service.ShopService;
import me.catring.demo.service.StorageService;
import me.catring.demo.service.UserService;
import me.catring.demo.service.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Objects;

@RestController
@RequestMapping("/image/avatar")
public class AvatarRestController {

    private final StorageService storageService;

    private final UserService userService;

    private final FoodService foodService;

    private final ShopService shopService;

    @Autowired
    public AvatarRestController(StorageService storageService, UserService userService,FoodService foodService, ShopService shopService) {
        this.storageService = storageService;
        this.userService = userService;
        this.foodService = foodService;
        this.shopService = shopService;
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename) {

        Resource file = storageService.loadAvatarAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @PostMapping()
    public boolean updateUserAvatar(@RequestParam MultipartFile file, Principal principal) {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/jpg")) {
            return false;
        }


        String newFilename;
        if (file.getOriginalFilename().indexOf(".") > 0) {
            newFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        } else {
            newFilename = file.getOriginalFilename();
        }
        newFilename = newFilename + new Timestamp(System.currentTimeMillis()).getTime() + ".jpeg";

//        String oldAvatarFilename = userService.getAvatarFilenameByUsername(principal.getName());
//        if (!Objects.equals(oldAvatarFilename, "default.jpg")) {
//            storageService.deleteAvatarByFilename(oldAvatarFilename);
//        }

        return userService.updateAvatarFilename(newFilename, principal.getName())
                && storageService.storeAvatarAs(file, newFilename);
    }

    @PostMapping("/food")
    public boolean updateFoodAvatar(@RequestParam MultipartFile file, @RequestParam int foodId) {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/jpg")) {
            return false;
        }

        String newFilename;
        if (file.getOriginalFilename().indexOf(".") > 0) {
            newFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        } else {
            newFilename = file.getOriginalFilename();
        }
        newFilename = newFilename + new Timestamp(System.currentTimeMillis()).getTime() + ".jpeg";

//        String oldAvatarFilename = foodService.getAvatarFilenameByFoodId(foodId);
//        if (!Objects.equals(oldAvatarFilename, "default.jpg")) {
//            storageService.deleteAvatarByFilename(oldAvatarFilename);
//        }
        foodService.updateAvatarFilename(newFilename, foodId);
        return storageService.storeAvatarAs(file, newFilename);
    }

    @PostMapping("/shop")
    public boolean updateAvatar(@RequestParam MultipartFile file, @RequestParam int shopId) {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/jpg")) {
            return false;
        }

        String newFilename;
        if (file.getOriginalFilename().indexOf(".") > 0) {
            newFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));
        } else {
            newFilename = file.getOriginalFilename();
        }
        newFilename = newFilename + new Timestamp(System.currentTimeMillis()).getTime() + ".jpeg";

//        String oldAvatarFilename = shopService.getAvatarFilenameByShopId(shopId);
//        if (!Objects.equals(oldAvatarFilename, "default.jpg")) {
//            storageService.deleteAvatarByFilename(oldAvatarFilename);
//        }
        shopService.updateAvatarFilename(newFilename, shopId);
        return storageService.storeAvatarAs(file, newFilename);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}

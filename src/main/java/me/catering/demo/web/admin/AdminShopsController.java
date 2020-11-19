package me.catering.demo.web.admin;

import me.catering.demo.domain.entity.Shop;
import me.catering.demo.service.ShopService;
import me.catering.demo.web.dto.admin.ShopCreateDto;
import me.catering.demo.web.dto.admin.ShopUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/admin/shops")
public class AdminShopsController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public ModelAndView listAllShops() {
        Iterable<Shop> shops = shopService.getAllShops();
        return new ModelAndView("admin/shops", "shops", shops);
    }

    @PostMapping(path = "/add")
    public String addShop(@Valid ShopCreateDto shopCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/shops";
        }

        Shop shop = new Shop();
        
        shop.setTitle(shopCreateDto.getTitle());
        shop.setCity(shopCreateDto.getCity());
        shop.setAddress(shopCreateDto.getAddress());
        shop.setDistrict(shopCreateDto.getDistrict());
        shop.setLandmark(shopCreateDto.getLandmark());
        shop.setFoodTag(shopCreateDto.getFoodTag());
        shop.setCoverFilename(shopCreateDto.getCoverFilename());
        shop.setDescription(shopCreateDto.getDescription());
        shop.setStarRate(shopCreateDto.getStarRate());
        shopService.addShop(shop);

        return "redirect:/admin/shops";
    }

    @PostMapping(path = "/update")
    public String updateShop(@Valid ShopUpdateDto shopUpdateDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/shops";
        }

        Shop shop = shopService.getShopById(shopUpdateDto.getShopID());

        shop.setTitle(shopUpdateDto.getTitle());
        shop.setCity(shopUpdateDto.getCity());
        shop.setAddress(shopUpdateDto.getAddress());
        shop.setDistrict(shopUpdateDto.getDistrict());
        shop.setLandmark(shopUpdateDto.getLandmark());
        shop.setFoodTag(shopUpdateDto.getFoodTag());
        shop.setCoverFilename(shopUpdateDto.getCoverFilename());
        shop.setDescription(shopUpdateDto.getDescription());
        shop.setStarRate(shopUpdateDto.getStarRate());

        shopService.updateShop(shop);

        return "redirect:/admin/shops";
    }

    @PostMapping(path = "/delete")
    public String deleteShop(@RequestParam int shopId) {
        shopService.deleteShop(shopId);

        return "redirect:/admin/shops";
    }
}

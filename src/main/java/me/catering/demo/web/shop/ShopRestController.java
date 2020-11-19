package me.catering.demo.web.shop;

import me.catering.demo.domain.entity.Shop;
import me.catering.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopRestController {

    private final ShopService shopService;

    @Autowired
    public ShopRestController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public Page<Shop> listAllShops(@RequestParam int page, @RequestParam(defaultValue = "4") int pageSize,
                                   @RequestParam(required = false) String q) {

        if (q == null) {
            return shopService.getAllShops(page, pageSize);
        } else {
            return shopService.getSearchResult(q, page, pageSize);
        }
    }

    @GetMapping("/{shopId}")
    public Shop getShopDetail(@PathVariable int shopId) {
        return shopService.getShopById(shopId);
    }
}

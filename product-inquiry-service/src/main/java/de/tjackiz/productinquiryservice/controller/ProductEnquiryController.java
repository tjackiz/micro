package de.tjackiz.productinquiryservice.controller;

import de.tjackiz.productinquiryservice.beans.ProductEnquiryBean;
import de.tjackiz.productinquiryservice.client.ProductStockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productEnquiry")
public class ProductEnquiryController {

    @Autowired
    private ProductStockClient productStockClient;

    @GetMapping("/getEnquiryOfProduct/name/{name}/availability/{availability}/unit/{unit}")
    public ProductEnquiryBean getEnquiryOfProduct(@PathVariable String name,
                                                  @PathVariable String availability,
                                                  @PathVariable int unit)
    {
        // TODO move logic2service
        ProductEnquiryBean productEnquiryBean = productStockClient.checkProductStock(name, availability);

        double totalPrice = productEnquiryBean.getTotalPrice() * unit;
        double discount = productEnquiryBean.getDiscountOffer();
        double discountPrice = totalPrice - totalPrice / 100;

        return new ProductEnquiryBean(
                productEnquiryBean.getId(),
                name,
                productEnquiryBean.getProductPrice(),
                availability,
                productEnquiryBean.getDiscountOffer(),
                unit,
                discountPrice,
                productEnquiryBean.getPort()
        );
    }
}

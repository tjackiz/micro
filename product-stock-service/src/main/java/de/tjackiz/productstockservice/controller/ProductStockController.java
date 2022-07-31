package de.tjackiz.productstockservice.controller;

import de.tjackiz.productstockservice.beans.ProductStockBean;
import de.tjackiz.productstockservice.entity.ProductStock;
import de.tjackiz.productstockservice.repository.ProductStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productStock")
public class ProductStockController {

    @Autowired
    private Environment environment;

    @Autowired
    private ProductStockRepository productStockRepository;

    @GetMapping("/check-product-stock/productName/{productName}/productAvailability/{productAvailability}")
    public ProductStockBean checkProductStock(@PathVariable String productName,
                                              @PathVariable String productAvailability)
    {
        ProductStock productStock = productStockRepository.findByProductNameAndProductAvailability(productName, productAvailability);

        ProductStockBean productStockBean = new ProductStockBean(productStock.getId(),
                productStock.getProductName(),
                productStock.getProductPrice(),
                productStock.getProductAvailability(),
                productStock.getDiscountOffer(),
                Integer.parseInt(environment.getProperty("local.server.port")));

        return productStockBean;
    }
}

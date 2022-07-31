package de.tjackiz.productinquiryservice.client;

import de.tjackiz.productinquiryservice.beans.ProductEnquiryBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// TODO without loadbalancing or discovery url needs2b defined manually
@FeignClient(name = "product-stock-service", url = "localhost:8800")
public interface ProductStockClient {

    @GetMapping("/productStock/check-product-stock/productName/{productName}/productAvailability/{productAvailability}")
    public ProductEnquiryBean checkProductStock(@PathVariable String productName,
                                                @PathVariable String productAvailability);
}

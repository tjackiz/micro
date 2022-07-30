package de.tjackiz.propertyaccessservice.controller;

import de.tjackiz.propertyaccessservice.beans.PropertyAccessBean;
import de.tjackiz.propertyaccessservice.beans.PropertyAccessValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/access")
public class PropertyFilesAccessController {

    @Autowired
    private PropertyAccessBean propertyAccessBean;

    @GetMapping("/accessPropertyFile")
    public PropertyAccessValue getPropertyAccessValue() {
        refreshActuator();
        return new PropertyAccessValue(propertyAccessBean.getName(), propertyAccessBean.getDescription());
    }

    // @GetMapping("/refresh")
    // TODO move2service
    private void refreshActuator() {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8100/actuator/refresh";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
    }
}

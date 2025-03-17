package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{item}")
    public String shipOrder(@PathVariable String item) {
        System.out.println("Shipping initiated for: " + item);
        

        String response = restTemplate.getForObject("http://localhost:8083/notification/" + item, String.class);
        
        return "Shipping Done → " + response;
    }
}

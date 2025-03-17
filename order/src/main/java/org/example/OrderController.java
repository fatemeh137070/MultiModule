package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{item}")
    public String placeOrder(@PathVariable String item) {
        System.out.println("Order received: " + item);
        

        String response = restTemplate.getForObject("http://localhost:8081/payment/" + item, String.class);
        
        return "Order Processed → " + response;
    }
}

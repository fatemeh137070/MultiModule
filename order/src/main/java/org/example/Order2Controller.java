//package org.example;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@RequestMapping("/order")
//@RequiredArgsConstructor
//public class OrderController {
//    private final RestTemplate restTemplate;
//
//    @GetMapping("/{item}")
//    public String placeOrder(@PathVariable String item) {
//        System.out.println("Order received: " + item);
//        String response = restTemplate.getForObject("http://localhost:8081/payment/" + item, String.class);
//        return "Order Processed → " + response;
//    }
//}

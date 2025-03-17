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
//@RequestMapping("/payment")
//@RequiredArgsConstructor
//public class Payment2Controller {
//    private final RestTemplate restTemplate;
//
//    @GetMapping("/{item}")
//    public String processPayment(@PathVariable String item) {
//        System.out.println("Payment processed for: " + item);
//        String response = restTemplate.getForObject("http://localhost:8082/shipping/" + item, String.class);
//        return "Payment Done → " + response;
//    }
//}

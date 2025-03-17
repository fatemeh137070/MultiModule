package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Payment {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/payment", new PaymentHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Payment Service Running on port 8081");
    }

    static class PaymentHandler implements HttpHandler {
        private final RestTemplate restTemplate = new RestTemplate();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String item = exchange.getRequestURI().getPath().split("/")[2];
            System.out.println("Payment processed for: " + item);

            String shippingResponse = restTemplate.getForObject("http://localhost:8082/shipping/" + item, String.class);


            String response = "Payment Done → " + shippingResponse;
            exchange.sendResponseHeaders(200, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
}

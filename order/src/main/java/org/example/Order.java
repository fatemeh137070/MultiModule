package org.example;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class Order {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/order", new OrderHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Order Service Running on port 8080");
    }

    static class OrderHandler implements HttpHandler {
        private final RestTemplate restTemplate = new RestTemplate();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String item = exchange.getRequestURI().getPath().split("/")[2];
            System.out.println("Order received: " + item);


            String paymentResponse = restTemplate.getForObject("http://localhost:8081/payment/" + item, String.class);
            

            String response = "Order Processed → " + paymentResponse;
            exchange.sendResponseHeaders(200, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
}

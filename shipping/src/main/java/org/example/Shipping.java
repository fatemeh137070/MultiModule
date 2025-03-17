package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Shipping {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8082), 0);
        server.createContext("/shipping", new ShippingHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Shipping Service Running on port 8082");
    }

    static class ShippingHandler implements HttpHandler {
        private final RestTemplate restTemplate = new RestTemplate();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String item = exchange.getRequestURI().getPath().split("/")[2];
            System.out.println("Shipping initiated for: " + item);

            // درخواست به سرویس Notification
            String notificationResponse = restTemplate.getForObject("http://localhost:8083/notification/" + item, String.class);

            // پاسخ به کلاینت
            String response = "Shipping Done → " + notificationResponse;
            exchange.sendResponseHeaders(200, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
}

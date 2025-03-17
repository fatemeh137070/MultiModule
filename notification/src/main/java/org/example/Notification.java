package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Notification {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8083), 0);
        server.createContext("/notification", new NotificationHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Notification Service Running on port 8083");
    }

    static class NotificationHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String item = exchange.getRequestURI().getPath().split("/")[2];
            System.out.println("Notification sent for: " + item);


            String response = "Notification Done → " + item + " received!";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        }
    }
}

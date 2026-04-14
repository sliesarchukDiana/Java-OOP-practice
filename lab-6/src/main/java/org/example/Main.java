package org.example;
import org.example.entity.Client;
import org.example.entity.Restaurant;
import org.example.entity.Waiter;

public class Main {
    static void main() {
        Restaurant restaurant = new Restaurant();

        Thread waiter1 = new Thread(new Waiter("Офіціант 1", restaurant));
        Thread waiter2 = new Thread(new Waiter("Офіціант 2", restaurant));

        waiter1.start();
        waiter2.start();

        Thread[] clients = new Thread[5];
        clients[0] = new Thread(new Client("Роберт", restaurant));
        for (int i = 1; i < 5; i++) {
            clients[i] = new Thread(new Client("Клієнт " + i, restaurant));
        }

        for (Thread client : clients) {
            client.start();
        }

        for (Thread client : clients) {
            try {
                client.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        waiter1.interrupt();
        waiter2.interrupt();
    }
}
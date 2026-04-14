package org.example.entity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class Waiter implements Runnable {
    private final String name;
    private final Restaurant restaurant;

    @Override
    public void run() {
        try {
            while (true) {
                Order order = restaurant.takeOrder();
                log.info("{} прийняв в роботу замовлення від {}", name, order.getClient().getName());

                Thread.sleep(1500);
                log.info("{} подав страву для {}", name, order.getClient().getName());

                Thread.sleep(1000);
                order.getClient().completePayment();
            }
        } catch (InterruptedException e) {
            log.info("{} завершив свою зміну.", name);
            Thread.currentThread().interrupt();
        }
    }
}
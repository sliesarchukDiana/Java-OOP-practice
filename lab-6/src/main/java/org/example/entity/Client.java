package org.example.entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@RequiredArgsConstructor
public class Client implements Runnable {
    @Getter
    private final String name;
    private final Restaurant restaurant;
    private boolean isPaid = false;
    private static final String[] MENU = {
            "Стейк та кава",
            "Паста Карбонара",
            "Салат Цезар та лимонад",
            "Борщ з пампушками",
            "Піца Маргарита",
            "Суші сет",
            "Сирники та зелений чай"
    };

    @Override
    public void run() {
        try {
            String randomMeal = MENU[ThreadLocalRandom.current().nextInt(MENU.length)];
            restaurant.placeOrder(new Order(randomMeal, this));

            Thread.sleep(2000);
            payBill();
        } catch (InterruptedException e) {
            log.error("{} перервано", name, e);
            Thread.currentThread().interrupt();
        }
    }

    private void payBill() throws InterruptedException {
        synchronized (this) {
            log.info("{} просить рахунок.", name);
            while (!isPaid) {
                this.wait();
            }
            log.info("{} успішно оплатив замовлення і покидає заклад.", name);
        }
    }

    public void completePayment() {
        synchronized (this) {
            this.isPaid = true;
            this.notify();
        }
    }
}
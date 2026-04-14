package org.example.entity;
import lombok.extern.log4j.Log4j2;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class Restaurant {
    private final Queue<Order> orderQueue = new LinkedList<>();
    private final ReentrantLock orderLock = new ReentrantLock();

    public void placeOrder(Order order) {
        orderLock.lock();
        try {
            log.info("{} розміщує замовлення: {}", order.getClient().getName(), order.getMeal());

            synchronized (orderQueue) {
                orderQueue.add(order);
                orderQueue.notifyAll();
            }
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("Помилка під час розміщення замовлення", e);
            Thread.currentThread().interrupt();
        } finally {
            orderLock.unlock();
        }
    }

    public Order takeOrder() throws InterruptedException {
        synchronized (orderQueue) {
            while (orderQueue.isEmpty()) {
                orderQueue.wait();
            }
            return orderQueue.poll();
        }
    }
}
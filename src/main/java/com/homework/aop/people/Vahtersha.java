package com.homework.aop.people;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Вахтерша, ведет подсчет посетителей.
 */
public class Vahtersha {
    // список посетителей
    private List<Visitor> visitors = new ArrayList<>();
    private boolean sleep = false;

    public void newVisitor(Visitor visitor) {
        // проверяем, если спит, выбрасываем исключение
        if (isSleeping()) {
            throw new IllegalStateException("Security is sleeping, don't disturb!");
        }
        this.visitors.add(visitor);
    }

    public void leaveVisitor(Visitor visitor) {
        if (isSleeping()) {
            throw new IllegalStateException("Security is sleeping, don't disturb!");
        }
        visitors.remove(visitor);
    }

    public void fallAsleep() {
        sleep = true;
    }

    public void wakeUp() {
        sleep = false;
    }

    public boolean isSleeping() {
        return this.sleep;
    }
}

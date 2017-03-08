package com.homework.aop;

import com.homework.aop.people.Vahtersha;
import com.homework.aop.people.Visitor;
import com.homework.aop.people.VisitorsFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Основной класс приложения, запускающий Spring контейнер и выполняющий простой тестовый сценарий.
 */
public class DormitoryApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        // выполняем примитивный сценарий с 3 посетителями и коротким сном (вызовет исключение)
        Vahtersha vahtersha = context.getBean(Vahtersha.class);
        VisitorsFactory visitorsFactory = context.getBean(VisitorsFactory.class);

        Visitor visitor_1 = visitorsFactory.getVisitor("Ivan", "Ivanov");
        vahtersha.newVisitor(visitor_1);

        Visitor visitor_2 = visitorsFactory.getVisitor("Masha", "Petrova");
        vahtersha.newVisitor(visitor_2);

        vahtersha.leaveVisitor(visitor_1);

        vahtersha.fallAsleep();
        Visitor visitor_3 = visitorsFactory.getVisitor("John", "Smith");
        // throws exception since sleeping
        vahtersha.newVisitor(visitor_3);
        vahtersha.leaveVisitor(visitor_2);

        vahtersha.wakeUp();
        vahtersha.newVisitor(visitor_3);
    }
}

package com.homework.aop.people;

/**
 * Класс фабрика для создания новых объектов посетителей.
 */
public class VisitorsFactory {

    public Visitor getVisitor(String firstName, String lastName) {
        return new Visitor(firstName, lastName);
    }
}

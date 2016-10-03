package com.lee.junit.test.service;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lee.junit.test.entity.Apple;
import com.lee.junit.test.entity.Pen;

@Service
@Scope(scopeName = SCOPE_PROTOTYPE)
public class FooService {

    @Resource(name = "sessionFactoryApple")
    private SessionFactory factoryApple;

    @Resource(name = "sessionFactoryPen")
    private SessionFactory factoryPen;

    public void update() {
        updateAppleById(1);
        updatePenById(1);
    }

    private void updatePenById(int i) {
        Session session = factoryPen.openSession();
        Transaction tx = session.beginTransaction();
        Pen pen = (Pen) session.get(Pen.class, i);
        pen.setOwner("Tony");
        session.update(pen);
        tx.commit();
    }

    private void updateAppleById(int i) {
        Session session = factoryApple.openSession();
        Transaction tx = session.beginTransaction();
        Apple apple = (Apple) session.get(Apple.class, i);
        apple.setOwner("Tony");
        session.update(apple);
        tx.commit();
    }
}

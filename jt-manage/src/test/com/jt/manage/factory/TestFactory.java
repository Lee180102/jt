package com.jt.manage.factory;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

public class TestFactory {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/factory.xml");
        Calendar calendar1 = (Calendar) applicationContext.getBean("calendar1");
        System.out.println(calendar1.getTime());
    }

    @Test
    public void test02(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/factory.xml");
        Calendar calendar1 = (Calendar) applicationContext.getBean("calendar2");
        System.out.println(calendar1.getTime());
    }

    @Test
    public void test03(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/factory.xml");
        Calendar calendar1 = (Calendar) applicationContext.getBean("calendar3");
        System.out.println(calendar1.getTime());
    }
}

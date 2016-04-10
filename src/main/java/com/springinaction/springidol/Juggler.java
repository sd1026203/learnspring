package com.springinaction.springidol;

/**
 * Created by gewei on 16/2/24.
 */
public class Juggler implements Performer {
    private int beanBags = 3;

    public Juggler() {
    }

    public Juggler(int beanBags) {
        this.beanBags = beanBags;
    }

    public void perform() throws PerformanceException {
        System.out.println("JUGGLING " + beanBags + " BEANBAGS");
    }

    public void before() {
        System.out.println("登场");
    }

    public void after() {
        System.out.println("退场");
    }
}

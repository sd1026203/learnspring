package com.springinaction.springidol;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.lang.System.setProperty;

/**
 * Created by gewei on 16/2/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath:spring/spring-idol.xml" })
public class IdolTest {

    @BeforeClass
    public static void setup() {
        setProperty("STANS_SONG", "Total Eclipse of the Heart");
    }

    @Autowired
    ApplicationContext context;

    @Test
    public void testPerformer() throws PerformanceException {
        Performer performer = (Performer)context.getBean("duke");
        performer.perform();
    }

    @Test
    public void testPoeticJuggler() throws PerformanceException {
        PoeticJuggler poeticJuggler = (PoeticJuggler)context.getBean("poeticJuggler");
        poeticJuggler.perform();
    }

    @Test
    public void testInstrumentalist() throws PerformanceException {
        Instrumentalist poeticJuggler = (Instrumentalist)context.getBean("keeny");
        poeticJuggler.perform();
    }

    @Test
    public void test() throws PerformanceException {
        Performer performer = (Performer)context.getBean("duke");
        Performer performer1 = (Performer)context.getBean("duke");
        Assert.assertEquals(performer,performer1);
    }
}


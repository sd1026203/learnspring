//<start id="poeticjuggler_java" /> 
package com.springinaction.springidol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoeticJuggler extends Juggler {
    private Poem poem;

    @Autowired
    public PoeticJuggler(Poem poem) { //<co id="co_injectPoem"/>
        super();
        this.poem = poem;
    }

    public PoeticJuggler(Integer beanBags, Poem poem) { // <co id="co_injectPoemBeanBags"/>
        super(beanBags);
        this.poem = poem;
    }

    public void perform() throws PerformanceException {
        super.perform();
        System.out.println("While reciting...");
        poem.recite();
    }
}
//<end id="poeticjuggler_java" />
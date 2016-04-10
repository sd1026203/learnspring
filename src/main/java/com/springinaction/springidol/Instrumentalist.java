//<start id="instrumentalist_java" /> 
package com.springinaction.springidol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import javax.inject.Named;

public class Instrumentalist implements Performer {
  public Instrumentalist() {
  }

  public void perform() throws PerformanceException {
    System.out.print("Playing " + song + " : ");
    instrument.play();
  }

  private String song;

  public void setSong(String song) { //<co id="co_injectSong"/>
    this.song = song;
  }

  public String getSong() {
    return song;
  }

  public String screamSong() {
    return song;
  }

//  @Autowired
//  @Qualifier("piano")
  @Inject
  @Named("saxophone")
  private Instrument instrument;

  public void setInstrument(Instrument instrument) { //<co id="co_injectInstrument"/>
    this.instrument = instrument;
  }
}
//<end id="instrumentalist_java" />

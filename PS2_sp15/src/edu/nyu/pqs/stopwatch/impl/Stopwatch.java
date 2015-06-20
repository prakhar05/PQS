package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * Description: A thread safe Stopwatch class which allows functionalities of a stop watch including
 * start,lap stop and reset. It implements the IStopwatch class and allows users to make lap
 * measurements and get a list of lap times.
 * 
 * @author pv594
 * @version 1.1
 */

public class Stopwatch implements IStopwatch {

  private List<Long> laptimes = new ArrayList<Long>();
  private final String id;
  private String state;

  // members for intermediate lap calculations
  private long lapstart;
  private long lapstop;
  private long laptime;
  private long pausedlap;

  // member for synchronized blocks
  private Object lock;

  /**
   * Constructor that sets the stopwatch id and initialises the other members including the ones for
   * intermediate lap calculations
   * 
   * @param stopWatchName
   *          : type String - holds the ID of the stopwatch
   */
  Stopwatch(String stopWatchName) {
    state = "initial";
    id = stopWatchName;
    lock = new Object();
    clearLapReadings();
  }

  /**
   * Method that returns the id of the stopwatch of type String
   *
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * Method that starts the stop watch if its in the initial state, throws an exception if the stop
   * watch is already running and resumes the stop watch if the stop watch was stopped. The behavior
   * of the start method after a stop method is that of a resume, where the stopwatch would continue
   * the measurement of lap that was stopped.
   *
   * @throws IllegalStateException
   *           when stop watch is already running
   */
  public void start() {
    synchronized (lock) {
      if (state.equals("initial")) {
        state = "running";
        lapstart = System.nanoTime();
      }
      else if (state.equals("running")) {
        throw new IllegalStateException("Stopwatch is already runnning");
      }
      else if (state.equals("stopped")) {
        state = "running";
        pausedlap = laptimes.get(laptimes.size() - 1);
        laptimes.remove(laptimes.size() - 1);
        lapstart = System.nanoTime();
      }
    }
  }

  /**
   * Method that ends a lap and adds the lap to an ArrayList containing the lap times.
   *
   * @throws IllegalStateException
   *           when the stop watch is not running
   */
  public void lap() {
    synchronized (lock) {
      if (!state.equals("running")) {
        throw new IllegalStateException("Stopwatch is not running");
      }
      else {
        addLapTime();
        lapstart = lapstop;
      }
    }
  }

  /**
   * Method that stops the current lap and adds the lap to the ArrayList of lap times. The behavior
   * of a stop is like a pause button where the lap that was stopped resumes if the stop method call
   * is succeeded by a start method call, and the lap is added if not succeeded by a start call.
   *
   * @throws IllegalStateException
   *           if the stop watch is not running when the stop method is called
   */
  public void stop() {
    synchronized (lock) {
      if (!state.equals("running")) {
        throw new IllegalStateException("Stopwatch is not running");
      }
      else {
        state = "stopped";
        addLapTime();
      }
    }
  }

  /**
   * Method that resets the stop watch. It empties all intermediate lap calculations, deletes the
   * laps from the ArrayList of laptimes and sets the state of the stop to its initial state.
   */
  public void reset() {
    synchronized (lock) {
      if (state.equals("running")) {
        stop();
        clearLapReadings();
      }
      laptimes.clear();
    }
  }

  /**
   * Method that returns a List of lap times in milliseconds
   *
   * @return List of type Long containing the lap times.
   */
  public List<Long> getLapTimes() {
    synchronized (lock) {
      return laptimes;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (!(o == this)) {
      return false;
    }
    if (!(o instanceof Stopwatch)) {
      return false;
    }
    Stopwatch sw = (Stopwatch) o;
    return sw.id.equals(id) && sw.state.equals(state) && sw.lapstart == lapstart
            && sw.lapstop == lapstop && sw.laptime == laptime && sw.pausedlap == pausedlap
            && sw.laptimes.equals(laptimes);
  }

  @Override
  public int hashCode() {
    int result = 23;
    result = 31 * result + (int) (lapstart ^ (lapstart >>> 32));
    result = 31 * result + (int) (lapstop ^ (lapstop >>> 32));
    result = 31 * result + (int) (laptime ^ (laptime >>> 32));
    result = 31 * result + (int) (pausedlap ^ (pausedlap >>> 32));
    result = 31 * result + id.hashCode();
    result = 31 * result + state.hashCode();
    result = 31 * result + laptimes.hashCode();
    return result;
  }

  @Override
  public String toString() {
    String result;
    result = "Stopwatch id :" + id + "\n" + "laptimes: \n";
    for (int i = 0; i < laptimes.size(); i++) {
      result += laptimes.get(i) + " ";
    }
    return result;
  }

  /*
   * Method to clear all the intermediate lap calculation variables and set the stop watch to its
   * initial state
   */
  private void clearLapReadings() {
    lapstart = 0;
    lapstop = 0;
    laptime = 0;
    pausedlap = 0;
    state = "initial";
  }

  /*
   * Method to add the measured lap times into the ArrayList. It also adds the paused lap to the lap
   * time if the stop operation is succeeded by a start operation
   */
  private void addLapTime() {
    lapstop = System.nanoTime();
    laptime = (lapstop - lapstart) / 1000000;
    if (pausedlap != 0) {
      laptime += pausedlap;
      pausedlap = 0;
    }
    laptimes.add(laptime);
  }
}

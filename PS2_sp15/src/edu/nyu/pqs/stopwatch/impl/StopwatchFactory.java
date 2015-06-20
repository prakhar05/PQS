package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects. It maintains
 * references to all created IStopwatch objects and provides a convenient method for getting a list
 * of those objects.
 *
 */
public class StopwatchFactory {

  private static List<IStopwatch> stopWatchList = new ArrayList<IStopwatch>();
  private static Object lock = new Object();

  /**
   * Creates and returns a new IStopwatch object
   * 
   * @param id
   *          The identifier of the new object
   * @return The new IStopwatch object
   * @throws IllegalArgumentException
   *           if <code>id</code> is empty, null, or already taken.
   */
  public static IStopwatch getStopwatch(String id) {
    if (id.equals(null) || id.equals("")) {
      throw new IllegalArgumentException("Parameter is null or empty");
    }
    synchronized (lock) {
      for (int i = 0; i < stopWatchList.size(); i++) {
        if (stopWatchList.get(i).getId() == id) {
          throw new IllegalArgumentException("Stopwatch name already taken");
        }
      }
      IStopwatch stopWatchObject = new Stopwatch(id);
      stopWatchList.add(stopWatchObject);
      return stopWatchObject;
    }
  }

  /**
   * Returns a list of all created stopwatches.
   * 
   * @return a List of all creates IStopwatch objects. Returns an empty list if no IStopwatches have
   *         been created.
   */
  public static List<IStopwatch> getStopwatches() {
    synchronized (lock) {
      return stopWatchList;
    }
  }
}

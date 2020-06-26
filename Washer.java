// File: Washer.java from the package edu.colorado.simulations
// Additional javadoc documentation is available from the Washer link at
//   http://www.cs.colorado.edu/~main/docs/

/******************************************************************************
* An <CODE>Washer</CODE> simulates a simple washing machine.
*
* <b>Java Source Code for this class:</b>
*   <A HREF="../../../../edu/colorado/simulations/Washer.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/simulations/Washer.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version Feb 10, 2016
******************************************************************************/
public class Washer
{
   private int secondsForWash; // Seconds for a single wash
   private int washTimeLeft; 
   private int landingTime;// Seconds until this washer is no longer busy

                      
   /**
   * Initialize a washer.
   * @param s
   *   the number of seconds required for one wash cyle of this washer
   * <b>Postcondition:</b>
   *   This washer has been initialized so that it takes <CODE>s</CODE>
   *   seconds to complete one wash cycle.
   **/
   public Washer(int s,int r)
   {
       secondsForWash = r;
       washTimeLeft =0;
       landingTime=s;
   }


   /**
   * Determine whether this washer is currently busy.
   * @return
   *   <CODE>true</CODE> if this washer is busy (in a wash cycle);
   *   otherwise <CODE>false</CODE>
   **/   
   public boolean isBusy( )
   {
      return (washTimeLeft > 0);
   }
 

   /**
   * Reduce the remaining time in the current wash cycle by one second.
   * <b>Postcondition:</b>
   *   If a car is being washed, then the remaining time in the current
   *   wash cycle has been reduced by one second.
   **/
   public void reduceRemainingTime( )
   {
      if (washTimeLeft > 0)
         washTimeLeft--;
   } 


   /**
   * Start a wash cycle going for this washer.
   * <b>Precondition:</b>
   *   <CODE>isBusy()</CODE> is <CODE>false</CODE>.
   * <b>Postcondition:</b>
   *   This washer has started simulating one wash cycle. Therefore, 
   *   <CODE>isBusy()</CODE> will return <CODE>true</CODE> until the required
   *   number of simulated seonds have passed.
   * @exception IllegalStateException
   *   Indicates that this washer is busy.
   **/
   public void startLanding( )
   {
      if (washTimeLeft > 0)
         throw new IllegalStateException("Washer is already busy.");
      washTimeLeft = landingTime;
   }
   public void startDeparture( )
   {
      if (washTimeLeft > 0)
         throw new IllegalStateException("Washer is already busy.");
      washTimeLeft = secondsForWash;
   }
   
   
}
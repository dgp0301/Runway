import java.util.*;
public class Airport {
	public static void main(String[]args) {
		//Declaring vars
		int landingTime,takeoffTime,crashTime,totalTime,planesCrashed=0;
		double arrivalProb,departureProb,avgLand,avgTO;
		Scanner in = new Scanner(System.in);
		
		//initializing variables
		System.out.print("Amount of minutes to land: ");
		landingTime=in.nextInt();
		System.out.print("Amount of time to take off: ");
		takeoffTime=in.nextInt();
		System.out.print("Probability of arrival during a minute: ");
		arrivalProb=in.nextDouble();
		System.out.print("Average amount of time between planes to land: ");
		avgLand= in.nextDouble();
		System.out.print("Probability of departure during a minute: ");
		departureProb=in.nextDouble();
		System.out.print("Average amount of time between planes to takeoff: ");
		avgTO=in.nextDouble();
		System.out.print("Maximum amount of time in the air before crashing: ");
		crashTime=in.nextInt();
		System.out.print("Total simulation in minutes: ");
		totalTime= in.nextInt();
		
		//create variables for departure and arrival
		Queue<Integer> arrivalTime = new LinkedList<Integer>();
		int next;
		BooleanSource arrival = new BooleanSource(arrivalProb);
		Washer runway = new Washer(landingTime,takeoffTime);
		Averager waitArrival = new Averager();
		int currentSecond;
		
		Queue<Integer> departureTime = new LinkedList<Integer>();
		BooleanSource departure = new BooleanSource(departureProb);
		Averager waitDeparture = new Averager();
		
		//Check preconditions
		if(landingTime <= 0 || takeoffTime <= 0|| arrivalProb < 0|| arrivalProb > 1 ||
				departureProb < 0 || departureProb > 1 || totalTime < 0) {
			throw new IllegalArgumentException("Values out of Range");
		}
		
		//sim pass one second at a time
		for(currentSecond = 0; currentSecond < totalTime; currentSecond++) {
			//check for new plane taking off or landing
			if(arrival.query()) {
				arrivalTime.add(currentSecond);
			}
			if(departure.query()) {
				departureTime.add(currentSecond);
			}
			//Check if plane crashed
			if((runway.isBusy())&&(!arrivalTime.isEmpty())&&
					(currentSecond - arrivalTime.peek() > crashTime)) {
				planesCrashed++;
				next = arrivalTime.remove();
				waitArrival.addNumber(currentSecond - next);
			}
			//check if plane is waiting to land 
			if((!runway.isBusy())&&(!arrivalTime.isEmpty())) {
				next = arrivalTime.remove();
				waitArrival.addNumber(currentSecond-next);
				runway.startLanding();
			}
			//check if plane is waiting to takeoff and no plane is landing
			if((!runway.isBusy())&&(arrivalTime.isEmpty())&&(!departureTime.isEmpty())) {
				next=departureTime.remove();
				waitDeparture.addNumber(currentSecond-next);
				runway.startDeparture();
			}
			runway.reduceRemainingTime();
		}
		System.out.println("Number of planes taken off: "+ waitDeparture.howManyNumbers());
		System.out.println("Number of planes landed: "+ waitArrival.howManyNumbers());
		System.out.println("Number of planes crashed: "+ planesCrashed);
		System.out.printf("Average waiting time for taking off: %.2f minutes\n",(waitDeparture.average()));
		System.out.printf("Average waiting time for landing: %.2f minutes\n",(waitArrival.average()));
		
	}

}

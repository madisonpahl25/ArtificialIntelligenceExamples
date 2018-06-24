//Created by: Madison Pahl

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//checks to see if file was passed as a command line argument
		if (args.length==0) {
			//if no file provided the user will be notified and the system will exit
			System.out.println("Please enter a file as a command line argument");
			System.exit(0);
		}
		//takes in the designated map as a comand line argument
		RobotGrid world=new RobotGrid(args[0]);
		System.out.println("---------------------------------------------------------");
		System.out.println("");
		System.out.println("Good luck robot, here is your challenge: ");
		System.out.println("");
		System.out.println("---------------------------------------------------------");
		//prints the unnavigated world for refrence
		world.print();
		System.out.println("---------------------------------------------------------");


		//creating robot instance with the particular world for each of the searches
		Robot r1 = new Robot(world);
		Robot r2 = new Robot(world);
		Robot r3 = new Robot(world);
		Robot r4 = new Robot(world);
		
		//Solves using Euclidean Distance
		r1.solveBestFirst(1);
		System.out.println("Euclidean Distance: ");
		System.out.println("");
		r1.printSolution();
		r1.reset();

		//Solves using Euclidean Distance & Total Cost
		r2.solveBestFirst(2);
		System.out.println("Euclidean Distance & Total Cost: ");
		System.out.println("");
		r2.printSolution();
		r2.reset();
		
		//Solves using Manhattan Distance
		r3.solveBestFirst(3);
		System.out.println("Manhattan Distance: ");
		System.out.println("");
		r3.printSolution();
		r3.reset();
		
		//Solves Manhattan Distance & Total Cost
		r4.solveBestFirst(4);
		System.out.println("Manhattan Distance & Total Cost: ");
		System.out.println("");
		r4.printSolution();
		r4.reset();
		
		
		
		
		
		

	}

}

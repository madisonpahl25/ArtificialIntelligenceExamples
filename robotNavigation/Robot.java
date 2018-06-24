//Created by: Madison Pahl

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.lang.Math;

public class Robot {
	
	private RobotGrid world;
	private RobotState initial;
	private RobotState goal;
	private RobotState current;
	private LinkedList<RobotState> closed;
	private PriorityQueue<RobotState> area;
	private LinkedList<RobotState> path;
	
	
	//initialization of variables
	public Robot(RobotGrid theWorld) {
		this.world=theWorld;
		this.initial=this.world.getInitial();
		this.goal=this.world.getGoal();
		this.current=this.initial;
		this.closed=new LinkedList<RobotState>();
		this.closed.addLast(this.current);
		this.path=new LinkedList<RobotState>();
		int initialCapacity=4;
		Comparator<RobotState> comparator= new Comparison();
		this.area= new PriorityQueue<RobotState>(initialCapacity, comparator);
			
		
	}

	//Creates new linked list of robot states, and computes the various path costs and distance equations
	//uses the evaluationState function to retrieve the various equations
	public void solveBestFirst(int theFunction) {
		while(this.closed.size()!= this.world.getNumNodes()&& this.current!= this.goal) {
			LinkedList<RobotState> currentsChildren=this.current.getChildren();
			
			for(RobotState theState: currentsChildren) {
				if(this.closed.contains(theState)) continue;
				theState.evaluationStates(theFunction, this.goal, this.path.size());
				this.area.add(theState);
			}
			if(this.area.size()>0 && !this.path.contains(this.current)) {
				this.path.push(this.current);
			}
			if(this.area.size()==0) {
				this.current=this.path.pop();
				continue;
			}
			this.current=this.area.poll();
			this.closed.addLast(this.current);
			this.area.clear();
		}
		if(this.current!=this.goal) {
			System.out.println("Failed in finding the correct path.");
			System.exit(0);
		}
	
		
		
	}

	//printing the grid as well as solutions to the equations specified in the homework
	public void printSolution() {
		for(RobotState theState:this.path) {
			this.world.visited(theState.getRow(), theState.getColumn());
		}
		this.world.print();
		System.out.println("Total Path Cost: " + this.path.size());
		System.out.println("Number of Nodes in Tree: " + this.closed.size());
		System.out.println("");
		System.out.println("---------------------------------------------------------");

		
	}

	//used to reset the world for uses of same world for other equations
	public void reset() {
		this.world.reset();
		this.closed.clear();
		this.area.clear();
		this.path.clear();
		this.current=this.initial;
	}
	
	

}

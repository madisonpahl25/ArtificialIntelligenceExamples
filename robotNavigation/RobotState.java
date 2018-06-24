//Created by: Brandon Lavinsky & Madison Pahl

import java.io.*;
import java.util.*;
import java.lang.Math;
import java.util.LinkedList;

public class RobotState {
	
	private int row;
	  private int column;
	  private double evaluation;
	  private LinkedList<RobotState> children;
	  
	  //initializing the row and column variables
	  public RobotState(int x, int y) {
		  this.row=x;
		  this.column=y;
		  
	  }

	  //cases will be used to determine in which order the functions will be used.
	  public void evaluationStates(int function, RobotState goal, int totalCost) {
		  switch(function) {
		  	//case that sets the evaluation to be Euclidean Distance
		  case 1:this.evaluation=this.euclideanDistance(goal);
		  	break;
		  	//case that sets the evaluation to the Euclidean Distance plus the total path cost
		  case 2: this.evaluation=this.euclideanDistance(goal)+ totalCost+1;
		  	break;
		  	//case that sets the evaluation to Manhattan Distance
		  case 3: this.evaluation=this.manhattanDistance(goal);
		  	break;
		  	//case that sets the evaluation to Manhattan Distance plus the total path cost
		  case 4: this.evaluation=this.manhattanDistance(goal)+totalCost+1;
		  	break;
		  	
		  default: System.out.println("Invalid evaluation function choice.");
		  	System.exit(0);
		  
		  
		  
		  
		  }
	  }
	  //Mathematical equation for euclidean distance
	  public double euclideanDistance(RobotState goal) {
		  int row1= this.row - goal.getRow();
		  int column1=this.column -goal.getColumn();
		  return Math.sqrt( (double)((row1*row1)+(column1*column1)) );
	  }
	  
	  //Mathematical equation for manhattan distance
	  public int manhattanDistance(RobotState goal) {
		  return Math.abs(this.row - goal.getRow()) + Math.abs(this.column - goal.getColumn());
		  }
	  
	  public boolean equal(RobotState altChoice) {
		  if(altChoice.getRow() != this.row || altChoice.getColumn() != this.column) 
		  {
			  
			  return false;
		  }
		  else 
		  {
			  return true;
		  }
			  
		  
	  }
	  
	  //gets the children 
	  public LinkedList<RobotState> getChildren(){
		  return this.children;
	  }
	  //sets the children
	  public void setChildren(LinkedList<RobotState> kids) {
		  this.children=kids;
		  
	  }
	  //gets the row
	  public int getRow() {
		  return this.row;
	  }
	  //gets the column
	  public int getColumn() {
		  return this.column;
	  }
	  //gets the evaluation
	  public double getEvaluation() {
		  return this.evaluation;
	  }
	  //sets the evaluation 
	  public void setEvaluation(double eval) {
		  this.evaluation=eval;
	  }
	  
	

}

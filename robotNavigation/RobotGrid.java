//Created by: Brandon Lavinsky & Madison Pahl

import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class RobotGrid {
	private String filepath;
	private int dimension;
	private RobotState initial;
	private RobotState goal;
	private int numNodes;
	private char[][] display;
	private RobotState[][] nodes;
	
	public RobotGrid(String file) {
		this.filepath=file;
		this.numNodes=0;
		this.parseMapFile(this.filepath);
		this.generateChildren();
		
	}
	public int getDimension() {
		
		return this.dimension;
	}

	//used to read in the file, and retrieve necessary path information
	static String readFile (String path) throws IOException {
		//BufferedReader is used to read the file path
		BufferedReader reader = new BufferedReader(new FileReader (path));
	    String line = null;
	    //StringBuilder is used to create a string from the file that was passed in
	    StringBuilder stringBuilder = new StringBuilder();
	   
	    try {
	    	//read through the lines of the file and create a string for each line using the line.separator property
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(System.getProperty("line.separator"));
	          
	            
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}

	//parsing to the file and conversion to string
	private void parseMapFile (String file) {
		String theInput = "";

		try {
			// Get string of file contents
			theInput = RobotGrid.readFile(file);
		} catch (IOException ioe) {
			System.out.print(ioe.toString());
		}
	//create a String array and make it equal to the contents of the file
	String[] lines= theInput.split(System.getProperty("line.separator"));
	//parses the first line of the file which should be the dimension to an integer
	try{
	this.dimension=Integer.parseInt(lines[0]);
	}catch(Exception e){
		System.out.println("The fist line of you file is not the dimension of the map please fix your input file");
		System.exit(0);

	}

	//Statement to notify that the max dimension of a grid can be 80
	if(this.dimension<=80){
	//set the dimensions of the file passed in
	this.display=new char[this.dimension][this.dimension];
	this.nodes=new RobotState[this.dimension][this.dimension];
	//code to understand the locations of +, i, and g on the map
		for(int y=1; y<lines.length; ++y) {
		for(int x=0; x < lines[y].length(); ++x) {
			char icon=lines[y].charAt(x);
			this.display[y-1][x]=icon;
			if(icon=='+') {
				continue;
			}
			if(icon=='i') {
				this.initial=new RobotState(x, y-1);
				this.nodes[y-1][x]=this.initial;
			}
			else if(icon=='g') {
				this.goal=new RobotState(x, y-1);
				this.nodes[y-1][x]=this.goal;
				
				
			}
			else {
				
				this.nodes[y-1][x]= new RobotState(x,y-1);
			}
			this.numNodes++;
		}
			
		}
	
	//error for exceeding the grid dimension of 80
	}else{

		System.out.println("Dimension cannot exceed 80");
		System.exit(0);

	}

		
		
	}
	

	//counting nodes in the tree
	private void generateChildren() {
		for(int y=0; y<this.display.length; ++y) {
			for(int x=0; x< this.display.length;++x) {
				RobotState robotState=this.nodes[y][x];
				LinkedList<RobotState> children=new LinkedList<RobotState>();
				
				if(robotState==null) {
					continue;
				}
				if(this.onMap(x+1,y)&& this.display[y][x+1]!='+') {
					children.addLast(this.nodes[y][x+1]);
				}
				if(this.onMap(x-1,y)&& this.display[y][x-1]!='+') {
					children.addLast(this.nodes[y][x-1]);
				}
				if(this.onMap(x,y+1)&& this.display[y+1][x]!='+') {
					children.addLast(this.nodes[y+1][x]);
				}
				if(this.onMap(x,y-1)&& this.display[y-1][x]!='+') {
					children.addLast(this.nodes[y-1][x]);
				}
				robotState.setChildren(children);
			}
		}
		
	}
	//checks to see if something is off the map if the map exceeds its specified dimensions
	public boolean onMap(int row, int column) {
		//if before the beginning of the map dimensions return false
		if(row<0 || column<0) {
			return false;
		}
		//if after the end of the map dimensions return false
		if (row >= this.dimension || column >= this.dimension) {
			return false;
		}
		return true;

	}
	//returns the intital state of the robot on the map (the i)
	public RobotState getInitial() {
		return this.initial;
	}
	//returns the goal state of the map (the g)
	public RobotState getGoal() {
		return this.goal;
	}
	//returns the number of nodes 
	public int getNumNodes() {
		return this.numNodes;
	}

	//stating what characters resemble the visited locations where the goal 
	//is where we have already visitied and where the initial state is
	//marks visited spaces as o
	public void visited(int row, int column) {
		if(this.display[column][row]!='g'&& this.display[column][row]!='i') {
			this.display[column][row]='o';
		}
	}

	//States where the robot has been and where it can go. used to reset the world
	public void reset() {
		for(int y=0; y<this.display.length; ++y) {
			for(int x=0; x<this.display.length; ++x){
				if(this.display[y][x]=='o') {
					this.display[y][x]='.';
				}
			}
		}
	}
	//prints the world to the console
	public void print() {
		for(int y=0; y<this.display.length;++y) {
			for(int x=0; x<this.display.length; ++x) {
				System.out.print(this.display[y][x]);
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
}

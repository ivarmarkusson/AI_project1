package prog1;

import java.util.ArrayList;
import java.util.List;


public class Environment {
	public State[][] grid;
	public int length;
	public int width;
	public Coordinate home_Coordinate;
	public String home_orientation;
	public int number_of_dirty_states;
	
	public List<Coordinate> dirtCoord;
	public List<Coordinate> obsticleCoord;
	
	Environment(){
		number_of_dirty_states = 0;
		length = 0;
		width = 0;
		
		dirtCoord = new ArrayList<Coordinate>();
		obsticleCoord = new ArrayList<Coordinate>();
	}

	public void setHome(int xcoord, int ycoord){
		home_Coordinate = new Coordinate(xcoord -1, ycoord -1);
	}
	
	public void setHomeOrientation(String orient){
		home_orientation = orient;
	}
	
	public void setSize(int wid, int len){
		width = wid;
		length = len;
	}
	
	public void setGrid(){
		grid = new State[width][length];
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < length; j++){
				Coordinate Coord = new Coordinate(i,j);
				if(dirtCoord.contains(Coord)){
					grid[i][j] = new State(i, j, true, false, "NONE");
				}
				else if(obsticleCoord.contains(Coord)){
					grid[i][j] = new State(i, j, false, true, "NONE");
				}
				else{
					grid[i][j] = new State(i, j, false, false, "NONE");
				}
			}
		}
	}
	
	public void printGrid(){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < length; j++){
				if(grid[i][j].isdirty){
					System.out.print(" D ");
				}
				else if(grid[i][j].isObsticle){
					System.out.print(" O ");
				}
				else{
					System.out.print(" - ");
				}
			}
			System.out.println("");
		}
	}
	public void insert(int xcoord, int ycoord, boolean dirt, boolean obst){
		if(dirt){
			dirtCoord.add(new Coordinate(xcoord -1, ycoord -1));
			number_of_dirty_states++;
		}
		else{
			obsticleCoord.add(new Coordinate(xcoord -1, ycoord -1));
		}
		
		
		
	}
	
}

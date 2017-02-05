import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;

//package prog1;


public class Environment {
	public State[][] grid;
	public int length;
	public int width;
	public Coordinate home_Coordinate;
	public String home_orientation;
	public int number_of_dirty_states;
	public List<Coordinate> dirtLocation;
	public List<Coordinate> obstLocation;
	
	Environment(){
		number_of_dirty_states = 0;
		length = 0;
		width = 0;
		initGrid(10, 10);
	}
	
	public void setHome(int xcoord, int ycoord){
		home_Coordinate = new Coordinate(xcoord, ycoord);
	}
	
	public void setHomeOrientation(String orient){
		home_orientation = orient;
	}
	
	public void initGrid(int len, int wid){
		length = len;
		width = wid;
		grid = new State[len][wid];
		
		for(int i = 0; i < len; i++){
			for(int j = 0; j < wid; j++){
				grid[i][j] = new State(i,j,false,false,"");
			}
		}
	}
	
	public void setGrid(int len, int wid){
		State[][] newGrid = new State[len][wid];
		
		for(int i = 0; i < len; i++){
			for(int j = 0; j < wid; j++){
				newGrid[i][j] = grid[i][j];
				/*System.out.println("Grid[" + i + "][" + j + "] Dirt: " + grid[i][j].isdirty);
				System.out.println("Grid[" + i + "][" + j + "] Obstacle: " + grid[i][j].isObsticle);
				System.out.println("Grid[" + i + "][" + j + "]: " + grid[i][j].orientation);
				System.out.println("Grid[" + i + "][" + j + "]: " + "(" + grid[i][j].coordinate.x + "," + grid[i][j].coordinate.y + ")");*/
			}
		}
	}
	
	/*public List<String> optimalPath(){
		return bfs(_root, grid);
	}*/
	
	public void insert(int xcord, int ycord, boolean dirt, boolean obst){
		if(dirt){
			number_of_dirty_states++;
		}
		grid[xcord-1][ycord-1].isdirty = dirt;
		grid[xcord-1][ycord-1].isObsticle = obst;
	}
}

package prog1;


public class Environment {
	public State[][] grid;
	public int length;
	public int width;
	public Coordinate home_Coordinate;
	public String home_orientation;
	public int number_of_dirty_states;
	
	Environment(){
		number_of_dirty_states = 0;
		length = 0;
		width = 0;
	}

	public void setHome(int xcoord, int ycoord){
		home_Coordinate = new Coordinate(xcoord, ycoord);
	}
	
	public void setHomeOrientation(String orient){
		home_orientation = orient;
	}
	
	public void setGrid(int len, int wid){
		length = len;
		width = wid;
		grid = new State[len][wid];
		
		for(int i = 0; i < len; i++){
			for(int j = 0; j < wid; j++){
				grid[i][j] = new State(i,j,false,false);
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
		
		grid[xcord][ycord] = new State(xcord, ycord, dirt, obst);
	}
	
}

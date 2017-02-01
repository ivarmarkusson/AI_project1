package prog1;

import java.util.List;

public class Environment {
	public Node _root;
	public State[][] grid;
	public int length;
	public int width;
	public int home_x, home_y;
	public String home_orientation;
	public int number_of_dirty_states;
	
	Environment(){
		number_of_dirty_states = 0;
		length = 0;
		width = 0;
	}
	
	public void createTree(){
		_root.currentState = grid[home_x][home_y];
		createTree(_root);
	}
	
	private void createTree(Node node){
		
	}
	
	private void insertToTree(){
		
	}

	public void setHome(int xcord, int ycord){
		home_x = xcord;
		home_y = ycord;
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

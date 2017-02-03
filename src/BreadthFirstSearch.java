package prog1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch implements Algorithms{

	private Node root;
	private Queue<Node> frontier;
	private Environment environment;
	
	BreadthFirstSearch(Environment initialEnvironment){
		environment = initialEnvironment;
		frontier = new LinkedList<Node>();
		
		int home_x = environment.home_Coordinate.x;
		int home_y = environment.home_Coordinate.y;
		
		State home_State = new State(home_x, home_y, environment.grid[home_x][home_y].isdirty, environment.grid[home_x][home_y].isObsticle);
		root = new Node(home_State);
	}
	
	public Stack<String> search() {
		frontier.add(root);
		
		while(!frontier.isEmpty()){
			Node nextNode = frontier.remove();
			
		}
		
		return null;
	}

}

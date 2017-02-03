package prog1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch implements Algorithms{

	private Node root;
	private Queue<Node> frontier;
	private Environment environment;
	private HashMap<State, Node> visited = new HashMap<State, Node>();
	
	BreadthFirstSearch(Environment initialEnvironment){
		environment = initialEnvironment;
		frontier = new LinkedList<Node>();
		
		int home_x = environment.home_Coordinate.x;
		int home_y = environment.home_Coordinate.y;
		
		State home_State = new State(home_x, home_y, environment.grid[home_x][home_y].isdirty, environment.grid[home_x][home_y].isObsticle, environment.home_orientation);
		root = new Node(home_State);
	}
	
	public Stack<String> search() {
		frontier.add(root);
		
		while(!frontier.isEmpty()){
			Node nextNode = frontier.remove();
			if(!visited.containsKey(nextNode.currentState)){
				//skip this node.
			}
			else{
				if(nextNode.currentState.goalState(environment.home_Coordinate, environment.home_orientation, environment.number_of_dirty_states)){
					return nextNode.path();
				}
				for(String action : nextNode.currentState.legalActions(environment)){
					Node successorNode = new Node(nextNode.currentState.successorState("GO", environment));
					successorNode.actionTo = action;
					frontier.add(successorNode);
				}
			}
		}
		
		return null;
	}

}

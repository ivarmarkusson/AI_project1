package prog1;

import java.util.HashMap;
import java.util.Stack;

public class DepthFirstSearch implements Algorithms{

	private Node root;
	private Stack<Node> frontier;
	private Environment environment;
	private HashMap<State, Node> visited = new HashMap<State, Node>();
	
	DepthFirstSearch(Environment initEnvironment){
		environment = initEnvironment;
		frontier = new Stack<Node>();
		
		int home_x = environment.home_Coordinate.x;
		int home_y = environment.home_Coordinate.y;
		
		State home_State = new State(home_x, home_y, environment.grid[home_x][home_y].isdirty, environment.grid[home_x][home_y].isObsticle, environment.home_orientation);
		root = new Node(home_State);
	}
	
	public Stack<String> search(){
		frontier.push(root);

		while(!frontier.isEmpty()){
			
			Node nextNode = frontier.pop();
			if(visited.containsKey(nextNode.currentState)){
				
			}
			else{
				if(nextNode.currentState.goalState(environment.number_of_dirty_states, environment.home_Coordinate, environment.home_orientation)){
					System.out.println("GOAL!!!");
					return nextNode.path();
				}
				for(String action : nextNode.currentState.legalActions(environment)){
					Node successorNode = new Node(nextNode.currentState.successorState(action, environment));
					successorNode.actionTo = action;
					frontier.push(successorNode);
				}
				visited.put(nextNode.currentState, nextNode);
			}
		}
		return new Stack<String>();
	}
}

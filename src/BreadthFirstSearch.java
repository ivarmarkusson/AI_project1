package prog1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch implements Algorithms{

	private Node root;
	private Queue<Node> frontier;
	private Environment environment;
	private HashMap<State, Node> visited;
	
	BreadthFirstSearch(Environment initialEnvironment){
		environment = initialEnvironment;
		frontier = new LinkedList<Node>();
		
		int home_x = environment.home_Coordinate.x;
		int home_y = environment.home_Coordinate.y;
		
		State home_State = new State(home_x, home_y, environment.grid[home_x][home_y].isdirty, environment.grid[home_x][home_y].isObsticle, environment.home_orientation);
		root = new Node(home_State);
		
		visited = new HashMap<State, Node>();
	}
	
	public Stack<String> search() {
		frontier.add(root);
		
		while(!frontier.isEmpty()){
			Node nextNode = frontier.remove();
			System.out.println("(" + nextNode.currentState.coordinate.x + ", " + nextNode.currentState.coordinate.y + ") " + nextNode.currentState.orientation);
			System.out.println(nextNode.path().toString());
			//System.out.println("number of dirt: " + environment.number_of_dirty_states);
			//System.out.println("number of clean " + nextNode.currentState.cleaned.size());
			
			if(visited.containsKey(nextNode.currentState)){
				//skip this node.
				continue;
			}
			else{
				//loop through legalactions() - childNodes
				for(String action : nextNode.currentState.legalActions(environment)){
					
					State successorState = nextNode.currentState.successorState(action, environment);
					Node successorNode = new Node(successorState);
					successorNode.actionTo = action;
					successorNode.parent = nextNode;
					
					frontier.add(successorNode);
					
					//Check for if goal is reached
					if(successorState.goalState(environment.number_of_dirty_states, environment.home_Coordinate, environment.home_orientation)){
						return successorNode.path();
					}
				}
				visited.put(nextNode.currentState, nextNode);
			}
		}
		
		return new Stack<String>();
	}

}

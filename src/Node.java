//package prog1;

import java.util.Stack;

public class Node {
	
	public Node parent;
	
	public int cost;
	public State currentState;
	public String actionTo;
	
	Node(State state){
		parent = null;
		cost = 0;
		currentState = null;
		actionTo = "";	
		currentState = state;
	}
	
	public Stack<String> path(){
		Stack<String> builder = new Stack<String>();
		Node next = this;
		
		while(next != null){
			builder.push(next.actionTo);
			next = next.parent;
		}
		
		return builder;
	}
	
	
	
}

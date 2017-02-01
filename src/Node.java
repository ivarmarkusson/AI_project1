package prog1;

public class Node {
	Node parent;
	
	Node childRight;
	Node childLeft;
	Node childGo;
	Node childSuck;
	Node childTurnOff;
	
	int cost;
	State currentState;
	
	Node(){
		parent = null;
		childRight = null;
		childLeft = null;
		childGo = null;
		childSuck = null;
		cost = 0;
	}
}

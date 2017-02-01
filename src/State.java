package prog1;

import java.util.List;



public class State {
	
	private enum Orientation {NORTH, EAST, SOUTH, WEST};
	private enum Actions {TURN_ON, TURN_OFF, GO, TURN_LEFT, TURN_RIGHT, SUCK};
	private int x, y;
	private boolean dirty;
	private Orientation orientation;
	private int number_of_dirty_states;
	private int home_x, home_y;
	
	private List<String> legalActions() {
		List<String> actions;
		
		if (this.orientation.equals("NORTH")) {
			
		}
		else if (this.orientation.equals("EAST")) {
			
		}
		else if (this.orientation.equals("SOUTH")) {
					
		}
		else{
			
		}
		return actions;
	}
	
	State successorState(Action) {
		return null;
	}
	
	private boolean goalState() {
		return number_of_dirty_states == 0 && home_x == x && home_y == y; 
	}
}


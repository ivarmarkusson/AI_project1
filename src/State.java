package prog1;

import java.util.List;



public class State {
	
	private enum Orientation {NORTH, EAST, SOUTH, WEST};
	private enum Actions {TURN_ON, TURN_OFF, GO, TURN_LEFT, TURN_RIGHT, SUCK};
	
	private int x, y;
	private boolean isdirty;
	private boolean isObsticle;
	private Orientation orientation;
	
	
	State(int xcord, int ycord, boolean dirt, boolean obst){
		x = xcord;
		y = ycord;
		isdirty = dirt;
		isObsticle = obst;
	}
	
	private List<String> legalActions() {
		List<String> actions = null;
		
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
	
	State successorState(String action) {
		return null;
	}
	
	//TODO: and is home!
	private boolean goalState() {
		return number_of_dirty_states == 0; 
	}
}


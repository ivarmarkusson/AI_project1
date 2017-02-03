package prog1;

import java.util.ArrayList;
import java.util.List;



public class State {
	
	//private enum Orientation {NORTH, EAST, SOUTH, WEST};
	//private enum Actions {TURN_ON, TURN_OFF, GO, TURN_LEFT, TURN_RIGHT, SUCK};
	
	public Coordinate coordinate;
	public boolean isdirty;
	public boolean isObsticle; // TODO: should be in environment, there is no state in a obsticle. 
	public String orientation;
	
	
	State(int x, int y, boolean dirt, boolean obst){
		coordinate = new Coordinate(x, y);
		isdirty = dirt;
		isObsticle = obst;
	}
	
	
	
	private List<String> legalActions(Environment environment) {
		List<String> actions = new ArrayList<String>();
		
		if(this.isdirty){
			actions.add("SUCK");
		}
		
		Coordinate turn_right = this.turn(environment, this.orientation, true);
		Coordinate turn_left = this.turn(environment, this.orientation, false);
		Coordinate go = this.coordinate.forward(this.orientation);
		
		if(checkAction(turn_right, environment)){
			actions.add("TURN_RIGHT");
		}
		if(checkAction(turn_left, environment)){
			actions.add("TURN_LEFT");
		}
		if(checkAction(go, environment)){
			actions.add("GO");
		}
			
		return actions;
	}
	
	private boolean checkAction(Coordinate coordinate, Environment environment){
		if(coordinate.x > environment.width || coordinate.y > environment.length || environment.width < 0 || environment.length < 0){
			return false;
		}
		else if(!environment.grid[coordinate.x][coordinate.y].isObsticle){
			return true;
		}
		return false;
	}
	
	private Coordinate turn(Environment environment, String orientation, boolean right){
		Coordinate next = new Coordinate(this.coordinate.x, this.coordinate.y);
		String newOrientation = "";
		
		if(right){
			if(orientation == "NORTH"){
				newOrientation = "EAST";
			}
			else if(orientation == "SOUTH"){
				newOrientation = "WEST";
			}
			else if(orientation == "EAST"){
				newOrientation = "SOUTH";
			}
			else if(orientation == "WEST"){
				newOrientation = "NORTH";
			}
		}
		else{
			if(orientation == "NORTH"){
				newOrientation = "WEST";
			}
			else if(orientation == "SOUTH"){
				newOrientation = "EAST";
			}
			else if(orientation == "EAST"){
				newOrientation = "NORTH";
			}
			else if(orientation == "WEST"){
				newOrientation = "SOUTH";
			}
		}
		
		next.forward(newOrientation);
		
		return next;
	}
	
	//TODO:
	State successorState(String action) {
		return null;
	}
	
	//TODO: and is home!
	private boolean goalState(Coordinate init, String initOrientation, int dirtLeft) {
		
		return this.orientation.equals(initOrientation) && this.coordinate.x == init.x && this.coordinate.y == init.y && dirtLeft == 0; 
	}
}


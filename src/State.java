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
	
	
	State(int x, int y, boolean dirt, boolean obst, String orient){
		coordinate = new Coordinate(x, y);
		isdirty = dirt;
		isObsticle = obst;
		orientation = orient;		
	}
	
	
	
	public List<String> legalActions(Environment environment) {
		List<String> actions = new ArrayList<String>();
		
		if(this.isdirty){
			actions.add("SUCK");
		}
		
		State turn_right = this.turn(environment, this.orientation, true);
		turn_right.coordinate.forward(turn_right.orientation);
		State turn_left = this.turn(environment, this.orientation, false);
		turn_left.coordinate.forward(turn_left.orientation);
		Coordinate go = this.coordinate.forward(this.orientation);
		
		if(checkAction(turn_right.coordinate, environment)){
			actions.add("TURN_RIGHT");
		}
		if(checkAction(turn_left.coordinate, environment)){
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
	
	private State turn(Environment environment, String orientation, boolean right){
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
		
		State result = new State(next.x, next.y, environment.grid[next.x][next.y].isdirty, environment.grid[next.x][next.y].isObsticle, newOrientation);
		return result;
	}
	
	//TODO:
	State successorState(String action, Environment environment) {
		State successorState = new State(this.coordinate.x, this.coordinate.y, false, false, this.orientation);
		
		if (action == "GO"){
			successorState.coordinate = this.coordinate.forward(this.orientation);
			successorState.isdirty = environment.grid[successorState.coordinate.x][successorState.coordinate.y].isdirty;			
		}
		else if (action == "TURN_RIGHT"){
			successorState = this.turn(environment, action, true);
		}
		else if (action == "TURN_Left"){
			successorState = this.turn(environment, action, false);
		}
		else if (action == "SUCK"){
			environment.number_of_dirty_states--;
		}
		return successorState;
	}
	
	
	//TODO: and is home!
	public boolean goalState(Coordinate init, String initOrientation, int dirtLeft) {
		
		return this.orientation.equals(initOrientation) && this.coordinate.x == init.x && this.coordinate.y == init.y && dirtLeft == 0; 
	}
	public int hashCode(){
		return (this.coordinate.hashCode() * 1221) ^ (this.orientation.hashCode() * 4512321);
	}
}


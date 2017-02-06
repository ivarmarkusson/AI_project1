package prog1;

import java.util.ArrayList;
import java.util.List;



public class State {
	public Coordinate coordinate;
	public boolean isdirty;
	public boolean isObsticle; 
	public String orientation;
	public List<Coordinate> cleaned;
	
	State(int x, int y, boolean dirt, boolean obst, String orient){
		cleaned = new ArrayList<Coordinate>();
		coordinate = new Coordinate(x, y);
		isdirty = dirt;
		isObsticle = obst;
		orientation = orient;
	}
	
	public List<String> legalActions(Environment environment) {
		List<String> actions = new ArrayList<String>();
		
		if(this.isdirty && !this.cleaned.contains(this.coordinate)){
			actions.add("SUCK");
		}
		
		State turn_right = this.turn(environment, this.orientation, true);
		turn_right.coordinate = turn_right.coordinate.forward(turn_right.orientation);
		
		State turn_left = this.turn(environment, this.orientation, false);
		turn_left.coordinate = turn_left.coordinate.forward(turn_left.orientation);
		
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
		if(coordinate.x >= environment.width || coordinate.y >= environment.length || coordinate.x < 0 || coordinate.y < 0){
			return false;
		}
		else if(environment.grid[coordinate.x][coordinate.y].isObsticle){
			return false;
		}
		return true;
	}
	
	private State turn(Environment environment, String orientation, boolean right){
		String newOrientation = "NONE";
		
		if(right){
			if(orientation.equals("NORTH")){
				newOrientation = "EAST";
			}
			else if(orientation.equals("SOUTH")){
				newOrientation = "WEST";
			}
			else if(orientation.equals("EAST")){
				newOrientation = "SOUTH";
			}
			else if(orientation.equals("WEST")){
				newOrientation = "NORTH";
			}
		}
		else{
			if(orientation.equals("NORTH")){
				newOrientation = "WEST";
			}
			else if(orientation.equals("SOUTH")){
				newOrientation = "EAST";
			}
			else if(orientation.equals("EAST")){
				newOrientation = "NORTH";
			}
			else if(orientation.equals("WEST")){
				newOrientation = "SOUTH";
			}
		}
		
		State result = new State(this.coordinate.x, this.coordinate.y, this.isdirty, this.isObsticle, newOrientation);
		result.cleaned = this.cleaned;
		return result;
	}
	
	State successorState(String action, Environment environment) {
		State successorState = new State(this.coordinate.x, this.coordinate.y, this.isdirty, this.isObsticle, this.orientation);
		successorState.cleaned = this.cleaned;
		
		if (action.equals("GO")){
			successorState.coordinate = this.coordinate.forward(this.orientation);
			successorState.isdirty = environment.grid[successorState.coordinate.x][successorState.coordinate.y].isdirty;
		}
		else if (action.equals("TURN_RIGHT")){
			successorState = this.turn(environment, this.orientation, true);
		}
		else if (action.equals("TURN_LEFT")){
			successorState = this.turn(environment, this.orientation, false);
		}
		else if (action.equals("SUCK")){
			if(this.cleaned.contains(this.coordinate) == false){
				successorState.cleaned.add(this.coordinate);
			}
		}
		return successorState;
	}
	
	
	public boolean goalState(int number_of_dirt, Coordinate home_coord, String orientation){
		if(this.cleaned.size() == number_of_dirt && this.coordinate.equals(home_coord)){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		else if(!State.class.isAssignableFrom(obj.getClass())){
	        return false;
	    }
		
		State comparison = (State) obj;
		
		if(this.coordinate.equals(comparison.coordinate) && this.orientation.equals(comparison.orientation)){
			if(this.cleaned.size() != comparison.cleaned.size()){
				return false;
			}
			for(Coordinate coord : this.cleaned){
				if(comparison.cleaned.contains(coord) == false){
					return false;
				}
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return (this.coordinate.hashCode() * 1221) ^ (this.orientation.hashCode() * 4512321);
	}
}


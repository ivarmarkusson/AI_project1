package prog1;

public class Coordinate {
	
	Coordinate(int _x, int _y){
		x = _x;
		y = _y;
	}
	
	public int x;
	public int y;
	
	public Coordinate forward(String orientation){
		Coordinate next = new Coordinate(this.x, this.y);
		
		if(orientation == "NORTH"){
			next.y++;
		}
		else if(orientation == "SOUTH"){
			next.y--;
		}
		else if(orientation == "EAST"){
			next.x++;
		}
		else if(orientation == "WEST"){
			next.x--;
		}
		
		return next;
	}
	
	public int hashCode(){
		return (this.x*21143) ^ (this.y*45127);
	}
	
}

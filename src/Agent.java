package prog1;

import java.util.Collection;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agent
{
	private Stack<String> _path;
	private Environment environment;
	
    public void init(Collection<String> percepts){
	    _path = new Stack<String>();
    	_path.push("TURN_OFF");
	    environment = new Environment();
	    
		Pattern perceptNamePattern = Pattern.compile("\\(\\s*([^\\s]+).*");
		for (String percept:percepts) {
			Matcher perceptNameMatcher = perceptNamePattern.matcher(percept);
			if (perceptNameMatcher.matches()) {
				String perceptName = perceptNameMatcher.group(1);
				if (perceptName.equals("HOME")) {
					Matcher m = Pattern.compile("\\(\\s*HOME\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						//TODO: Initialize init state
						environment.setHome(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
					}
				}
				else if (perceptName.equals("SIZE")) {
					Matcher m = Pattern.compile("\\(\\s*SIZE\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						//TODO: Initialize size of the environment
						environment.setGrid(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
					}
				}
				else if (perceptName.equals("ORIENTATION")) {
					Matcher m = Pattern.compile("\\(\\s*ORIENTATION\\s+(NORTH|SOUTH|EAST|WEST)\\s*\\)").matcher(percept);
					if (m.matches()) {
						//TODO: Initialize the orientation of the agent at initial state
						environment.setHomeOrientation(m.group(1));
					}
				}
				else if (perceptName.equals("AT")) {
					Matcher m = Pattern.compile("\\(\\s*AT\\s+(DIRT|OBSTACLE)\\s+([0-9]+)\\s+([0-9]+)\\s*\\)").matcher(percept);
					if (m.matches()) {
						//TODO: Initialize dirt or obstacle for this position in the grid
						if (m.group(1) == "DIRT"){
							environment.insert(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), true, false);
						}
						else if (m.group(1) == "OBSTACLE"){
							environment.insert(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), false, true);							
						}
					}
				}
				else {
					System.out.println("other percept:" + percept);
				}
			} else {
				System.err.println("strange percept that does not match pattern: " + percept);
			}
		}
		_path.push("TURN_ON");
    }
    
    public String nextAction(Collection<String> percepts){
    	if(!_path.empty())
    		return _path.pop();
    	return null; //should not happen
    }
}

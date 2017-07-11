/**
 * An abstract class that holds a mission's information.
 * Will provide mission details for MissionSelect stage
 * and help link the mission's stage. 
 * TODO link mission stage and make missions loadable saved files?
 * @author Harrison Lee
 */
public class Mission {

	String name;
	String desc;
	
	public Mission(String msnName, String msnDesc) {
		name = msnName;
		desc = msnDesc;
	}

	
	/** Get methods **/
	public String getName()	{ return name;	}
	public String getDesc()	{ return desc;	}
}

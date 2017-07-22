package gameplayElements;
/**
 * An abstract class that holds a mission's information.
 * Will provide mission details for MissionSelect stage
 * and help link the mission's stage. 
 * TODO link mission stage and make missions loadable saved files?
 * @author Harrison Lee
 */
public class Mission {

	private String name;
	private String desc;
	private int id;
	
	public Mission(String msnName, String msnDesc, int IDNum) {
		name = msnName;
		desc = msnDesc;
		id = IDNum;
	}

	
	/** Get methods **/
	public String getName()	{ return name;	}
	public String getDesc()	{ return desc;	}
	public int getID()		{ return id;	}
}

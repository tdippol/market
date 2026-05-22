package com.axiante.tm1.json.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Thread {
	Integer ID;
	String Type;
	String Name;
	String Context;
	String State;
	String Function;
	String ObjectType;
	String ObjectName;
	Integer RLocks;
	Integer IXLocks;
	Integer WLocks;
	String ElapsedTime;
	String WaitTime;
	String Info;
	Session[] Session;

	class Session {
		Integer ID;
	}
}

package org.technicalpi.util;

public class Preset {
	public String name;
	public String presetString;
	
	public Preset(String presetString)
	{
		this.presetString = presetString;
		this.name = presetString.split("/")[0];
	}
}

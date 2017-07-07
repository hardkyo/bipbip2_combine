package com.kitri.factory;

import com.kitri.maproute.action.MapRouteListAction;

public class MapRouteActionFactory {
	
	private static MapRouteListAction mapRouteListAction;
	
	static{
		mapRouteListAction = new MapRouteListAction();
	}

	public static MapRouteListAction getMapRouteListAction() {
		return mapRouteListAction;
	}
	

}

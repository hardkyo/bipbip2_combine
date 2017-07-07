package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.map.action.*;


public class MapActionFactory {
	private static Action mapWriteAction;
	private static Action mapViewAction;
	private static Action mapListAction;
	private static Action mapModifyAction;
	private static Action mapDeleteAction;
	private static Action mapCallAction;
	
	static {
		mapWriteAction = new MapWriteAction();
		mapViewAction = new MapViewAction();
		mapListAction = new MapListAction();
		mapModifyAction = new MapModifyAction();
		mapDeleteAction = new MapDeleteAction();
		mapCallAction = new MapCallAction();
	}

	public static Action getMapCallAction() {
		return mapCallAction;
	}

	public static Action getMapWriteAction() {
		return mapWriteAction;
	}
   
	public static Action getMapViewAction() {
		return mapViewAction;
	}

	public static Action getMapListAction() {
		return mapListAction;
	}

	public static Action getMapModifyAction() {
		return mapModifyAction;
	}

	public static Action getMapDeleteAction() {
		return mapDeleteAction;
	}

	
}

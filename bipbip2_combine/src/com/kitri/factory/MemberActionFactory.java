package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.member.action.DeleteAction;
import com.kitri.member.action.IdcheckAction;
import com.kitri.member.action.LoginAction;
import com.kitri.member.action.ModifyAction;
import com.kitri.member.action.MvmodifyAction;
import com.kitri.member.action.RegisterAction;

public class MemberActionFactory {
	//教臂沛栏肺 咀记按眉 积己
	private static Action registerAction;
	private static Action loginAction;
	private static Action modifyAction;
	private static Action mvmodifyAction;
	private static Action deleteAction;
	private static Action idcheckAction;
	
	static{
		registerAction = new RegisterAction();
		loginAction = new LoginAction();
		modifyAction = new ModifyAction();
		mvmodifyAction = new MvmodifyAction();
		deleteAction = new DeleteAction();
		idcheckAction = new IdcheckAction();
	}

	public static Action getIdcheckAction() {
		return idcheckAction;
	}

	public static Action getDeleteAction() {
		return deleteAction;
	}

	public static Action getMvmodifyAction() {
		return mvmodifyAction;
	}

	public static Action getRegisterAction() {
		return registerAction;
	}

	public static Action getLoginAction() {
		return loginAction;
	}

	public static Action getModifyAction() {
		return modifyAction;
	}

	
	
}

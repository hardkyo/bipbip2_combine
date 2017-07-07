package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.freeboard.action.*;
import com.kitri.memo.action.*;


public class FreeBoardActionFactory {
	private static Action freeBoardWriteAction;
	private static Action freeBoardListAction;
	private static Action freeBoardViewAction;
	private static Action freeBoardDeleteAction;
	
	private static Action freeBoardModifyAction;
	private static Action freeBoardMoveModifyAction;
	
	private static Action freeBoardMoveReplyAction;
	private static Action freeBoardReplyAction;
	
	private static Action freeBoardHotListAction;
	
	private static Action memoWriteAction;
	private static Action memoListAction;
	private static Action memoModifyAction;
	private static Action memoDeleteAction;
	
	private static Action freeBoardUpAction;
	private static Action freeboardDownAction;
	
	static{
		freeBoardWriteAction = new FreeBoardWriteAction();
		freeBoardListAction = new FreeBoardListAction();
		freeBoardViewAction	= new FreeBoardViewAction();
		freeBoardDeleteAction = new FreeBoardDeleteAction();
		
		freeBoardMoveModifyAction = new FreeBoardMoveModifyAction();
		freeBoardModifyAction = new FreeBoardModifyAction();
		
		freeBoardMoveReplyAction = new FreeBoardMoveReplyAction();
		freeBoardReplyAction = new FreeBoardReplyAction();
		
		freeBoardHotListAction = new FreeBoardHotListAction();
		
		memoWriteAction = new MemoWriteAction();
		memoListAction = new MemoListAction();
		memoModifyAction = new MemoModifyAction();
		memoDeleteAction = new  MemoDeleteAction();
		
		freeBoardUpAction = new FreeBoardUpAction ();
		freeboardDownAction = new FreeBoardDownAction ();
		
	}
	
	
	
	public static Action getFreeBoardUpAction() {
		return freeBoardUpAction;
	}

	public static Action getFreeboardDownAction() {
		return freeboardDownAction;
	}

	public static Action getMemoWriteAction() {
		return memoWriteAction;
	}

	public static Action getMemoListAction() {
		return memoListAction;
	}

	public static Action getMemoModifyAction() {
		return memoModifyAction;
	}

	public static Action getMemoDeleteAction() {
		return memoDeleteAction;
	}

	public static Action getFreeBoardHotListAction() {
		return freeBoardHotListAction;
	}

	public static Action getFreeBoardMoveReplyAction() {
		return freeBoardMoveReplyAction;
	}

	public static Action getFreeBoardReplyAction() {
		return freeBoardReplyAction;
	}

	public static Action getFreeBoardMoveModifyAction() {
		return freeBoardMoveModifyAction;
	}

	public static Action getFreeBoardDeleteAction() {
		return freeBoardDeleteAction;
	}

	public static Action getFreeBoardModifyAction() {
		return freeBoardModifyAction;
	}

	public static Action getFreeBoardViewAction() {
		return freeBoardViewAction;
	}

	public static Action getFreeBoardWriteAction() {
		return freeBoardWriteAction;
	}

	public static Action getFreeBoardListAction() {
		return freeBoardListAction;
	}
}

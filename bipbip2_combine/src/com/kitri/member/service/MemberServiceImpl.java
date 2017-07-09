package com.kitri.member.service;

import java.util.HashMap;
import java.util.Map;


import com.kitri.member.dao.MemberDao;
import com.kitri.member.dao.MemberDaoImpl;
import com.kitri.member.model.MemberDto;

public class MemberServiceImpl implements MemberService {

	private static MemberService memberService;
	
	static {
		
		memberService = new MemberServiceImpl();
	}
	
	private MemberServiceImpl() {}
	
	
	
	public static MemberService getMemberService() {
		return memberService;
	}



	@Override
	public int register(MemberDto memberDto) {
		return MemberDaoImpl.getMemberDao().register(memberDto);
	}

	@Override
	public MemberDto login(String id, String pass) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pass);
		return MemberDaoImpl.getMemberDao().login(map);
	}

	@Override
	public MemberDto logout(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modify(MemberDto memberDto2) {
//		System.out.println(memberDto2.getEmail());
		return MemberDaoImpl.getMemberDao().modify(memberDto2);
		
	}

	@Override
	public int delete(String id) {
		return MemberDaoImpl.getMemberDao().delete(id);
	}



	@Override
	public MemberDto getMember(String id) {
		return MemberDaoImpl.getMemberDao().getMember(id);
	}



	@Override
	public int idCheck(String id) {
//		System.out.println(id);
		return MemberDaoImpl.getMemberDao().idCheck(id);
	}

}

package com.kitri.member.dao;

import java.util.Map;

import com.kitri.member.model.MemberDto;

public interface MemberDao {

	int register(MemberDto memberDto);
	MemberDto login(Map<String, String> map);
	MemberDto logout(String id);
	int modify(MemberDto memberDto2);
	int delete(String id);
	MemberDto getMember(String id);
	int idCheck(String id);
	
	
}

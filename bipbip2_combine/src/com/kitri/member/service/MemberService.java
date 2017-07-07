package com.kitri.member.service;

import com.kitri.member.model.MemberDto;

public interface MemberService {
	
	int register(MemberDto memberDto);
	MemberDto login(String id, String pass);
	MemberDto logout(String id);
	int modify(MemberDto memberDto2);
	int delete(String id);
	MemberDto getMember(String id);
	int idCheck(String id);
}

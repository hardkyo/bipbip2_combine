package com.kitri.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.action.Action;
import com.kitri.member.model.MemberDto;
import com.kitri.member.service.MemberService;
import com.kitri.member.service.MemberServiceImpl;

public class RegisterAction implements Action {

//	1. data get (
//    request(제목, 내용, 게시판번호), 
//    session(이름, 아이디, 이메일)
//    db (글번호)
//2. logic (service >> dao) insert
// 1의 data를 이용하여  Dto만들어 service에 전송.
//3. 2의 결과에 따라 view page 결정.
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDto memberDto = new MemberDto();
		
		String path = "/index.jsp";
		memberDto.setId(request.getParameter("signid"));
		memberDto.setName(request.getParameter("signname"));
		memberDto.setPass(request.getParameter("signpass"));
		memberDto.setPhone(request.getParameter("phone"));
		memberDto.setEmail(request.getParameter("email"));
		memberDto.setAddr1(request.getParameter("address1"));
		memberDto.setAddr2(request.getParameter("address2"));
		
//		System.out.println("제발찍혀라");
//		System.out.println(request.getParameter("signid"));
//		System.out.println(request.getParameter("signname"));
//		System.out.println(request.getParameter("signpass"));
//		System.out.println(request.getParameter("email"));
//		System.out.println(request.getParameter("phone"));
//		System.out.println(request.getParameter("address1"));
//		System.out.println(request.getParameter("address2"));
		
		int cnt = MemberServiceImpl.getMemberService().register(memberDto);
//		System.out.println(cnt);
		if (cnt != 0) {
			path = "/member/joinok.jsp";
			request.setAttribute("userInfo", memberDto);
		}else {
			path = "/member/joinfail.jsp";
		}
		
		return path;
	}

}

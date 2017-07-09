package com.kitri.freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

import com.kitri.action.Action;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.freeboard.service.FreeBoardServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.NumberCheck;

public class FreeBoardWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int count = 0;
		String path = "/board/freeboard.jsp";
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
//		System.out.println("memberDto >>>>>>." + memberDto.getEmail());
		if (memberDto != null) {
			int seq = CommonServiceImpl.getCommonService().getNextSeq();

			FreeBoardDto dto = new FreeBoardDto();
			dto.setSeq(seq);
			dto.setId(memberDto.getId());
			dto.setName(memberDto.getName());
			dto.setEmail(memberDto.getEmail());
			dto.setSubject(request.getParameter("subject"));
			dto.setContent(request.getParameter("content"));
			dto.setBcode(NumberCheck.nullToOne(request.getParameter("bcode")));
			dto.setRef(seq);
//			System.out.println("seq from commondao after dto"+dto.getSeq());

//			System.out.println("writeaction + \t" + dto.getSubject() + "\n---" + dto.getContent());
			count = FreeBoardServiceImpl.getService().freeBoardWrite(dto);
			
			if (count != 0) {
				JOptionPane.showMessageDialog(null, "성공입니다.");
				request.setAttribute("seq", seq + "");
			} else {
				JOptionPane.showMessageDialog(null, "실패입니다.");
			}
			path = "/board/freeboard.jsp";
		}
		return path;
	}

}

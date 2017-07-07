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

public class FreeBoardReplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
		
		String path = "/board/freeboard.jsp";
		if(memberDto != null) {//로그인 했다면.
			int seq = CommonServiceImpl.getCommonService().getNextSeq();//글번호 얻기
			
			FreeBoardDto freeboardDto = new FreeBoardDto();
			freeboardDto.setSeq(seq);
			freeboardDto.setId(memberDto.getId());
			freeboardDto.setName(memberDto.getName());
			freeboardDto.setEmail(memberDto.getEmail());
			freeboardDto.setSubject(request.getParameter("subject"));
			freeboardDto.setContent(request.getParameter("content"));
			freeboardDto.setBcode(NumberCheck.nullToZero(request.getParameter("bcode")));
			freeboardDto.setPseq(Integer.parseInt(request.getParameter("pseq")));
						
			int cnt = FreeBoardServiceImpl.getService().replyArticle(freeboardDto);
			if(cnt != 0) {
				request.setAttribute("seq", seq + "");
				JOptionPane.showMessageDialog(null, "답변작성 성공");
			} else {
				JOptionPane.showMessageDialog(null, "답변작성 실패");
			}
		}
		return path;
	}

}

package com.kitri.memo.action;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.xml.bind.*;

import com.kitri.action.Action;
import com.kitri.member.model.MemberDto;
import com.kitri.memo.model.MemoDto;
import com.kitri.memo.model.MemoListXMLDto;
import com.kitri.memo.service.MemoServiceImpl;


public class MemoWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
		if (memberDto != null) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String mcontent = request.getParameter("mcontent");
			
			MemoDto memoDto = new MemoDto ();
			memoDto.setSeq(seq);
			memoDto.setId(memberDto.getId());
			memoDto.setMcontent(mcontent);
			memoDto.setName(memberDto.getName());
			
			//write
			int count = MemoServiceImpl.getMemoServiceImpl().writeMemo(memoDto);
			
			//list
			List<MemoDto> list = MemoServiceImpl.getMemoServiceImpl().listMemo(seq);
			
			MemoListXMLDto xml = new MemoListXMLDto();
			xml.setStatus(count == 1 ? Boolean.TRUE : Boolean.FALSE);
			xml.setList(list);
			
			response.setContentType("application/xml;charset=UTF-8");
			PrintWriter out = response.getWriter();

			JAXBContext context = null;
			Marshaller marshal = null;
			
			try {
				context = JAXBContext.newInstance(MemoListXMLDto.class);
				marshal = context.createMarshaller();
				marshal.setProperty(Marshaller.JAXB_ENCODING, "euc-kr");
				marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshal.marshal(xml, out);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

}

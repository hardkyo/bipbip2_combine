package com.kitri.memo.action;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.xml.bind.*;

import com.kitri.action.Action;
import com.kitri.memo.model.MemoDto;
import com.kitri.memo.model.MemoListXMLDto;
import com.kitri.memo.service.MemoServiceImpl;

public class MemoListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		List<MemoDto> list = MemoServiceImpl.getMemoServiceImpl().listMemo(seq);
		
		MemoListXMLDto xml = new MemoListXMLDto();
		xml.setStatus(list != null ? Boolean.TRUE : Boolean.FALSE);
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
		return null;
	}

}

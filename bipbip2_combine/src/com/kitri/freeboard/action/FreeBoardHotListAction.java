package com.kitri.freeboard.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.*;

import com.kitri.action.Action;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.freeboard.model.HotListXMLDto;
import com.kitri.freeboard.service.FreeBoardServiceImpl;

public class FreeBoardHotListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<FreeBoardDto> list = FreeBoardServiceImpl.getService().getHotList();
		
		HotListXMLDto xml = new HotListXMLDto();
		xml.setStatus(list != null ? Boolean.TRUE : Boolean.FALSE);
		xml.setList(list);
		
		response.setContentType("application/xml;charset=UTF-8");
		PrintWriter out = response.getWriter();

		JAXBContext context = null;
		Marshaller marshal = null;
		
		try {
			context = JAXBContext.newInstance(HotListXMLDto.class);
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

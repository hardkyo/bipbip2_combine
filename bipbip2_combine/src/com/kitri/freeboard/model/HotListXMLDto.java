package com.kitri.freeboard.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hotlist") // <hotlist> </hotlist>
public class HotListXMLDto {
	
	private boolean status;
	private List<FreeBoardDto> list;
	
	public boolean isStatus() {
		return status;
	}
	
	@XmlElement // <status></status>
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public List<FreeBoardDto> getList() {
		return list;
	}
	
	@XmlElement(name = "listhot") // <listhot></listhot>
	public void setList(List<FreeBoardDto> list) {
		this.list = list;
	}	
	

}

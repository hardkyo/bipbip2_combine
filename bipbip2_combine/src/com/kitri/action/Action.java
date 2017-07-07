package com.kitri.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public interface Action {
	String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

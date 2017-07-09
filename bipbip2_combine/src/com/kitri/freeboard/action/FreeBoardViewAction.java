package com.kitri.freeboard.action;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.NumberCheck;

public class FreeBoardViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String root = request.getContextPath();

		String path = "";
		path = "/index.jsp";

		HttpSession session = request.getSession();
		MemberDto loginInfo = (MemberDto) session.getAttribute("loginInfo");

		int seq = NumberCheck.nullToZero(request.getParameter("seq"));

		/*
		 * if (loginInfo != null && loginInfo.getCheckhit() == 0) {
		 * CommonServiceImpl.getCommonService().callCommonDaoUpdateHit(seq,
		 * loginInfo); MemberDto newLoginInfo =
		 * CommonServiceImpl.getCommonService().updateLoginInfo(loginInfo);
		 * session.setAttribute("loginInfo", newLoginInfo); }
		 */
		FreeBoardDto dto = null;

		Cookie[] cookies = request.getCookies();
		String key = "";
		String value = "";
		int token = 0;
		int tokenSize = 0;

		Cookie hitCheck = null;
		
		if (seq != 0) {
			path = "/board/freeboardview.jsp";
			if (loginInfo != null) {
				if (cookies != null && cookies.length >= 1) {
					for (Cookie c : cookies) {
						key = c.getName();
						if (key.equals(loginInfo.getId())) {
							value = c.getValue();
							StringTokenizer seqToken = new StringTokenizer(value, "|");
							tokenSize = seqToken.countTokens();
							for (int i = 0; i < tokenSize; i++) {
								token = Integer.parseInt(seqToken.nextToken());
								if (token == seq) {
									dto = CommonServiceImpl.getCommonService().getArticle(seq);
									request.setAttribute("article", dto);
									return path;
								}
							}
							hitCheck = new Cookie(loginInfo.getId(), value + seq + "|");
							hitCheck.setPath(root);
							////
							hitCheck.setMaxAge(hitCheck.getMaxAge());
							response.addCookie(hitCheck);
							///////
							System.out.println(hitCheck.getMaxAge());

							CommonServiceImpl.getCommonService().updateHit(seq);

							dto = CommonServiceImpl.getCommonService().getArticle(seq);
							request.setAttribute("article", dto);
							return path;
						}
					}
				}
				hitCheck = new Cookie(loginInfo.getId(), seq + "|");
				hitCheck.setPath(root);
				hitCheck.setMaxAge(60 * 60 * 24);
				response.addCookie(hitCheck);

				CommonServiceImpl.getCommonService().updateHit(seq);

				dto = CommonServiceImpl.getCommonService().getArticle(seq);
				request.setAttribute("article", dto);
				return path;
			} else {

			}
		}
		dto =CommonServiceImpl.getCommonService().getArticle(seq);
		request.setAttribute("article", dto);
		return path;
	}





}

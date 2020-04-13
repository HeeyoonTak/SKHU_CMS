<%@ tag description="pagination" pageEncoding="UTF-8"%>
<%@ tag import="java.util.ArrayList"%>
<%@ attribute name="queryStringSemName" type="java.lang.String"
	required="true"%>
<%!
private class Page {
		int page;
		String label;

		Page(int page, String label) {
			this.page = page;
			this.label = label;
		}
	}%>
<%

	String sem_name = (String) jspContext.getAttribute("queryStringSemName");
	
	String queryString = request.getQueryString();
	if (queryString == null)
		queryString = sem_name + "=@@@";
	else if (queryString.matches(".*" + sem_name + "=[0-9]+.*"))
		queryString = queryString.replaceAll(sem_name + "=[0-9]+", sem_name + "=@@@");
	else
		queryString = queryString + "&" + sem_name + "=@@@";
	
	String url = request.getAttribute("javax.servlet.forward.request_uri") + "?" + queryString;
	
%>

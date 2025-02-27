package com.cts.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.xhtmlrenderer.pdf.ITextRenderer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class PdfGenerator {
	
	
	public static void generatePdfFromJsp(HttpServletResponse response,String htmlContent,String fileName) throws Exception{
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(htmlContent);
		renderer.layout();
		
		response.setContentType("application/pdf");
		response.setHeader("Clontent-Disposition","attachment; filename="+fileName);
		ServletOutputStream outputStream = response.getOutputStream();
		renderer.createPDF(outputStream);
		outputStream.close();
		
	}
	
	public static String renderJsptoHtml(HttpServletResponse response,HttpServletRequest request,String jspPath) throws Exception {
		StringWriter stringWriter = new StringWriter();
		
		HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
			private final PrintWriter writer = new PrintWriter(stringWriter);
			
			@Override
			public PrintWriter getWriter() {
				return writer;
			}
		};
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
		dispatcher.include(request, responseWrapper);
		return stringWriter.toString();
	}
}

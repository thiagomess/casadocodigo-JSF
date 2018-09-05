package br.com.alura.loja.servlet;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.loja.infra.FileSaver;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getRequestURI().split("/file")[1];
		
		Path arquivoFonte = Paths.get(FileSaver.SERVER_PATH + "/" + path);
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor("file:" + arquivoFonte);
		
		resp.reset();
		resp.setContentType(contentType);
		resp.setHeader("Content-Length", String.valueOf(Files.size(arquivoFonte)));
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + arquivoFonte.getFileName().toString() + "\"");//Se usar o "attachment;" antes do "filename"
																															//disponibiliza o arquivo para download
//		resp.setHeader("Content-Disposition", "filename=\"" + arquivoFonte.getFileName().toString() + "\""); 
																								
		FileSaver.transfer(arquivoFonte, resp.getOutputStream());
	}

}

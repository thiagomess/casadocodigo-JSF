package br.com.alura.loja.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.text.Normalizer;

import javax.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "/alura/casadocodigo/";

	// Serve para salvar o arquivo no Sistema Operacional
	public String write(Part arquivo, String path) {

		String relativePath = path + "/" + arquivo.getSubmittedFileName();

		// normaliza o nome do arquivo caso tenha caracteres especiais ou acentos
		String relativePathNormalizer = Normalizer.normalize(relativePath, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "");
		try {
			arquivo.write(SERVER_PATH + "/" + relativePathNormalizer + "/");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return relativePathNormalizer;
	}

	//Remove um arquivo da pasta no SO
	public void remove(String path) {

		File file = new File(SERVER_PATH + "/" + path);
		try {
			if (file.delete()) {
				System.out.println(file.getName() + " foi deletado");
			} else {
				System.out.println("A operação de delete falhou");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	// Pega o caminho do arquivo e pega o arquivo no SO para enviar para servlet
	public static void transfer(Path arquivoFonte, OutputStream outputStream) {

		try {
			FileInputStream input = new FileInputStream(arquivoFonte.toFile());
			try (ReadableByteChannel inputChannel = Channels.newChannel(input);
					WritableByteChannel outputChanel = Channels.newChannel(outputStream)) {
				ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);

				while (inputChannel.read(buffer) != -1) {
					buffer.flip();
					outputChanel.write(buffer);
					buffer.clear();
				}

			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

}

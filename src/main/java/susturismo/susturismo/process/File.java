package susturismo.susturismo.process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class File {

    static public String saveDocInFolder(byte[] decodedBytes, String nome) {

        String file = "https://www.susturismo.com/imgsAdd";
       // String file = "C:\\docsSS";
        java.io.File diretorio = new java.io.File(file); // ajfilho é uma pasta!
        if (!diretorio.exists())
            diretorio.mkdirs();

        String caminho = file + "/" + nome;
        try {
            Files.write(Paths.get(caminho), decodedBytes);
            return caminho;
        } catch (IOException e) {
            return null;
        }

    }


    static public String encodeFileToBase64Binary(java.io.File file)
            throws IOException {

        byte[] bytes = loadFile(file);
        byte[] encoded = Base64.getEncoder().encode(bytes);
        String encodedString = new String(encoded);

        return encodedString;
    }

    static public byte[] loadFile(java.io.File file) throws IOException {
        byte[] bytes;
        try (InputStream is = new FileInputStream(file)) {
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                throw new IOException("File to large " + file.getName());
            }
            bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
        }
        return bytes;
    }

}

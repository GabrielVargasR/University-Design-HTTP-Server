package resource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FileResource implements Resource {
    private File file;
    private String mimeType;

    public FileResource(File file, String mimeType) {
        this.file = file;
        this.mimeType = mimeType;
    }

    @Override
    public Long getSize() {
        long fileLength = (long) this.file.length();
        return fileLength;
    }

    @Override
    //https://www.geeksforgeeks.org/implement-how-to-load-file-as-inputstream-in-java/
    public InputStream getContent() throws IOException {
        FileInputStream fileInputStream= new FileInputStream(this.file);
        return fileInputStream;
    }

    @Override
    public String getMimeType() {
       return this.mimeType;
    }

    @Override
    public String getLastModifiedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(this.file.lastModified());
    }
}
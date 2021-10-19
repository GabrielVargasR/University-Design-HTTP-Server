package resource;

import requests.Method;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public interface Resource {
    Long getSize();
    InputStream getContent() throws IOException;
    String getMimeType();
    String getLastModifiedDate();
}

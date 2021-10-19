package resource;

import requests.Request;
import requests.RequestImpl;
import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class ResourceFactory {
    public ResourceFactory(){
    }

    public Resource getInstance(File file, String mimeType) {
        return new FileResource(file,mimeType);
    }
}
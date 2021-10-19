package resource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MimeManagerImpl implements MimeManager{
    Map<String, String > mapMimeExtensions;

    public MimeManagerImpl(File mimeFile) throws IOException {
        mapMimeExtensions = parseMimeExtensions(mimeFile);
    }

    public String getMimeType(String resourceExtension){
        return mapMimeExtensions.get(resourceExtension);
    }


    private Map<String, String> parseMimeExtensions(File mimeExtensionsFilePath) throws IOException {
        HashMap<String, String> mapMimeExtensions = new HashMap<String, String>();
        Scanner sc = new Scanner(mimeExtensionsFilePath);

        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(" ", 2);
            String mimeExtensionKey = parts[0];
            String mimeExtensionValue = parts[1];
            mapMimeExtensions.put(mimeExtensionKey, mimeExtensionValue);
        }
        return mapMimeExtensions;
    }
    
}

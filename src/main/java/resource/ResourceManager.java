package resource;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface ResourceManager {
    <Optional>Resource getResource(String resource) throws IOException;
    Resource getPrivateResource(String path);
}

package resource;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FileResourceManager implements ResourceManager{
    private ResourceFactory resourceFactory;
    private MimeManager mimeManager;
    private final File directory;

    public FileResourceManager(ResourceFactory resourceFactory, MimeManager mimeManager, File directory){
        this.resourceFactory = resourceFactory;
        this.mimeManager = mimeManager;
        this.directory = directory;
    }


    @Override
    public Resource getResource(String path) throws IOException{
        String extension = getExtension(path);
        String mimeType = this.mimeManager.getMimeType(extension);
        Optional<File> file =  validateFile(path);
        Resource resource = this.resourceFactory.getInstance(file.get(), mimeType);
        return resource;
    }

    @Override
    public Resource getPrivateResource(String path) {
        String extension = getExtension(path);
        String mimeType = this.mimeManager.getMimeType(extension);
        Resource resource = this.resourceFactory.getInstance(new File(path), mimeType);
        return resource;
    }


    private String getExtension(String resource) {
        String[] resourceExtension = resource.split("\\.");
        String fileExtension = "." + resourceExtension[1];
        return fileExtension;
    }

    private Optional<File> validateFile(String requestPath) throws IOException{
        String path = this.directory.getAbsolutePath() + requestPath;
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new IOException();
        }
        return Optional.of(file);
    }
}


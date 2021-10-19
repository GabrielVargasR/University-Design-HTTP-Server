package method.response.errorResponse;
import java.io.*;
import java.net.URL;

import method.response.HTTPStatus;
import method.response.Response;
import method.response.ResponseFactory;
import resource.Resource;
import resource.ResourceManager;

public class BadRequestResponseHandler implements ErrorResponseHandler{

    @Override
    public Response handleError(ResponseFactory responseFactory, ResourceManager resourceManager) {

        Resource resource =   resourceManager.getPrivateResource("src/main/resources/400.html");

        try {
            return responseFactory.getInstance(resource, HTTPStatus.BAD_REQUEST,"HTTP/1.1",3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

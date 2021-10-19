package method.response.errorResponse;
import method.response.HTTPStatus;
import method.response.Response;
import method.response.ResponseFactory;
import resource.Resource;
import resource.ResourceManager;

import java.io.*;

public class NotImplementedResponseHandler implements ErrorResponseHandler{

    @Override
    public Response handleError(ResponseFactory responseFactory, ResourceManager resourceManager) {

        Resource resource =  resourceManager.getPrivateResource("src/main/resources/501.html");

        try {
            return responseFactory.getInstance(resource, HTTPStatus.NOT_IMPLEMENTED,"HTTP/1.1",3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

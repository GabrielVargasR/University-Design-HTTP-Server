package method.response.errorResponse;

import java.io.*;

import method.response.HTTPStatus;
import method.response.Response;
import method.response.ResponseFactory;
import resource.Resource;
import resource.ResourceManager;

public class InternalServerErrorResponseHandler implements ErrorResponseHandler {

    @Override
    public Response handleError(ResponseFactory responseFactory, ResourceManager resourceManager) {

        Resource resource =  resourceManager.getPrivateResource("src/main/resources/500.html");

        try {
            return responseFactory.getInstance(resource, HTTPStatus.INTERNAL_SERVER_ERROR,"HTTP/1.1",3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

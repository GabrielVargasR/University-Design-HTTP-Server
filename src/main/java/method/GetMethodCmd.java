package method;

import method.response.Response;
import method.response.ResponseFactory;
import method.response.errorResponse.ErrorResponseHandler;
import requests.Request;
import resource.Resource;
import resource.ResourceManager;

import java.io.IOException;

import static method.response.HTTPStatus.OK;

public class GetMethodCmd extends MethodCmd{

    public GetMethodCmd(ResourceManager resourceManager, ResponseFactory responseFactory, ErrorResponseHandler errorHandler){
        this.responseFactory = responseFactory;
        this.resourceManager = resourceManager;
        this.errorHandler = errorHandler;
    }

    @Override
    public Response exec(Request request) throws RuntimeException{
        try {
            Resource resource = this.resourceManager.getResource(request.getPath());
            return this.responseFactory.getInstance(resource, OK, request.getProtocolVersion(),1);
        } catch (IOException e) {
            return this.errorHandler.handleError(responseFactory,resourceManager);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

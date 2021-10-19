package method;

import method.response.Response;
import method.response.ResponseFactory;
import method.response.errorResponse.ErrorResponseHandler;
import requests.Request;
import resource.ResourceManager;


public class DefaultMethodCmd extends MethodCmd{
    public DefaultMethodCmd(ResourceManager resourceManager, ResponseFactory responseFactory, ErrorResponseHandler errorHandler){
        this.responseFactory = responseFactory;
        this.resourceManager = resourceManager;
        this.errorHandler = errorHandler;
    }

    @Override
    public Response exec(Request request) throws RuntimeException{
        try{
            return this.errorHandler.handleError(responseFactory,resourceManager);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
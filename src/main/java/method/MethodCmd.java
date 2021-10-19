package method;

import method.response.Response;
import method.response.ResponseFactory;
import method.response.errorResponse.ErrorResponseHandler;
import requests.Request;
import resource.ResourceManager;

public abstract class MethodCmd {
    protected ResourceManager resourceManager;
    protected ResponseFactory responseFactory;
    protected ErrorResponseHandler errorHandler;

    public abstract Response exec(Request request) throws RuntimeException;
}

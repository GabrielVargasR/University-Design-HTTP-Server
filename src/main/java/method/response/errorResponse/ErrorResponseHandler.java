package method.response.errorResponse;
import method.response.Response;
import method.response.ResponseFactory;
import resource.Resource;
import resource.ResourceManager;

public interface ErrorResponseHandler{
        public Response handleError(ResponseFactory responseFactory,ResourceManager resourceManager);
}

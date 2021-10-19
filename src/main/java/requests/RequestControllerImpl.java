package requests;

import logger.Logger;
import method.response.Response;
import method.response.ResponseFactory;
import method.response.errorResponse.BadRequestResponseHandler;
import method.response.errorResponse.ErrorResponseHandler;
import method.response.errorResponse.InternalServerErrorResponseHandler;
import resource.ResourceManager;

import java.io.IOException;

public class RequestControllerImpl implements RequestController {

    private final Logger logger;
    private final RequestParser parser;
    private final RequestProcessor processor;
    private final ErrorResponseHandler badRequestHandler;
    private final ErrorResponseHandler internalErrorHandler;
    private final ResponseFactory factory;
    private final ResourceManager manager;

    public RequestControllerImpl(Logger logger, RequestParser parser, RequestProcessor processor, ErrorResponseHandler internalServerError, ErrorResponseHandler badRequest, ResponseFactory factory, ResourceManager manager) {
        this.logger = logger;
        this.parser = parser;
        this.processor = processor;
        this.badRequestHandler = badRequest;
        this.internalErrorHandler = internalServerError;
        this.factory = factory;
        this.manager = manager;
    }

    @Override
    public Response process(String message) {
        try {
            Request request = this.parser.parse(message);
            this.logger.log(request);
            Response response = this.processor.process(request);
            this.logger.log(response);
            return response;
        } catch (BadRequestException e) {
            return this.badRequestHandler.handleError(factory, manager);
        } catch (RuntimeException e) {
            return this.internalErrorHandler.handleError(factory, manager);
        }
    }


}

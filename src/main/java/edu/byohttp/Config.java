package edu.byohttp;

import logger.ConsoleLogger;
import logger.Logger;
import method.DefaultMethodCmd;
import method.GetMethodCmd;
import method.HeadMethodCmd;
import method.MethodCmd;
import method.response.Response;
import method.response.ResponseFactory;
import method.response.errorResponse.*;
import requests.*;
import resource.*;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private File resourceDirectory;
    private File mimeTypes;

    public Config(File directoryPath, File typesPath) {
        this.resourceDirectory = directoryPath;
        this.mimeTypes = typesPath;
    }

    public SocketMessageRunnable getSocketMessageRunnable(Socket socket) throws IOException {
        return new SocketMessageRunnable(socket, this.getRequestController());
    }

    private RequestController getRequestController() throws IOException {
        return new RequestControllerImpl(this.getLogger(),this.getRequestParser(),this.getRequestProcessor(),this.getInternalServerErrorResponseHandler(),this.getBadRequestResponseHandler(), this.getResponseFactory(), this.getResourceManager());
    }
     
    private Logger getLogger() {
        return new ConsoleLogger();
    }

    public RequestParser getRequestParser() {
        return new RequestParserImpl(this.getRequestFactory());
    }

    private RequestFactory getRequestFactory() {
        return new RequestFactory();
    }

    private RequestProcessor getRequestProcessor() throws IOException {
        return new RequestProcessorImpl(this.getMethodCmds());
    }

    private Map<Method, MethodCmd> getMethodCmds() throws IOException {
        Map<Method,MethodCmd> methods=new HashMap<Method,MethodCmd>();
        methods.put(Method.GET,new GetMethodCmd(this.getResourceManager(),this.getResponseFactory(),new NotFoundResponseHandler()));
        methods.put(Method.HEAD,new HeadMethodCmd(this.getResourceManager(),this.getResponseFactory(),new NotFoundResponseHandler()));
        methods.put(Method.PUT,new DefaultMethodCmd(this.getResourceManager(),this.getResponseFactory(),new NotImplementedResponseHandler()));
        methods.put(Method.POST,new DefaultMethodCmd(this.getResourceManager(),this.getResponseFactory(),new NotImplementedResponseHandler()));
        return methods;
    }

    private ResponseFactory getResponseFactory(){
        return new ResponseFactory();
    }

    private ResourceManager getResourceManager() throws IOException {
        return new FileResourceManager(this.getResourceFactory(),this.getMimeManager(), this.resourceDirectory);
    }

    private ResourceFactory getResourceFactory(){
        return new ResourceFactory();
    }

    private MimeManager getMimeManager() throws IOException {
        return new MimeManagerImpl(this.mimeTypes);
    }

    private ErrorResponseHandler getInternalServerErrorResponseHandler() throws IOException {
        return new InternalServerErrorResponseHandler();
    }

    private ErrorResponseHandler getBadRequestResponseHandler() throws IOException {
        return new BadRequestResponseHandler();
    }

}

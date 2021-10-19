package requests;

import method.MethodCmd;
import method.response.Response;

import java.util.Map;

public class RequestProcessorImpl implements RequestProcessor {

    private final Map<Method,MethodCmd> methods;


    public RequestProcessorImpl(Map<Method, MethodCmd> methods){
        this.methods=methods; }

    @Override
    public Response process(Request request) throws RuntimeException{
        try {
            MethodCmd methodCmd = this.methods.get(request.getMethod());
            return methodCmd.exec(request);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}

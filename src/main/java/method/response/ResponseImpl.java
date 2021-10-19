package method.response;

import java.io.InputStream;
import java.util.Map;

public class ResponseImpl implements Response{
    private final Map<String, String> headers;
    private final String protocolVersion;
    private final HTTPStatus status;
    private InputStream body;

    public ResponseImpl(String protocolVersion,HTTPStatus status,Map<String,String> headers)  {
        this.headers=headers;
        this.protocolVersion=protocolVersion;
        this.status=status;
    }
    public ResponseImpl(String protocolVersion,HTTPStatus status,Map<String,String> headers,InputStream body)  {
        this.headers=headers;
        this.protocolVersion=protocolVersion;
        this.status=status;
        this.body=body;
    }

    public String getHeader(String headerKey){
        return this.headers.get(headerKey);
    }

    public String getProtocolVersion(){
        return this.protocolVersion;
    }

    public HTTPStatus getStatus(){
        return this.status;
    }

    public InputStream getBody(){
        return this.body;
    }

    @Override
    public String toString() {
        String message=this.protocolVersion + " " +this.status.getCode() +" "+this.status.getMessage()+"\n";
        for(String key:headers.keySet()){
            message+=key+": "+headers.get(key)+"\n";
        }
        message+="\n";
        return message;
    }
}

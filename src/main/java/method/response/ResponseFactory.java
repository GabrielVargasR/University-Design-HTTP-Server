package method.response;

import resource.Resource;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseFactory {

    public ResponseFactory(){}

    public Response getInstance(Resource resource, HTTPStatus status, String protocolVersion,Integer responseType) throws IOException {
        Map<String,String> headers=new HashMap<>();
        Date date= Calendar.getInstance().getTime();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        headers.put("Server", "byohttp/0.0.1");
        headers.put("Date", dateFormat.format(date));
        headers.put("Content-Type", resource.getMimeType());
        headers.put("Content-Length", Long.toString(resource.getSize()));

        if(responseType!=3) {
            headers.put("Last-Modified",resource.getLastModifiedDate());
            headers.put("Connection","keep-alive");
            headers.put("Accept-Range","bytes");
        } else {
            headers.put("Connection","keep-alive");
        }

        if(responseType!=2) {
            return new ResponseImpl(protocolVersion,status,headers,resource.getContent());
        }
        else {
            return new ResponseImpl(protocolVersion,status,headers);
        }
    }
}

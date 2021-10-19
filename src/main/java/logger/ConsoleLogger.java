package logger;

import method.response.Response;
import requests.Request;

import java.util.Date;

public class ConsoleLogger implements Logger  {

    public ConsoleLogger (){
    }

    @Override
    public void log(Request request)
    {
        System.out.println("\nDate:"+getDate()+"\nRequest: "+request.toString()+"\n");
    }
    @Override
    public  void log(Response response)
    {
        System.out.println("\nDate:"+getDate()+"\nResponse: "+response.toString()+"\n");
    }

    private Date getDate(){
        return new Date();
    }
}
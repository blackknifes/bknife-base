package com.bknife.base.net.todo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public interface HttpRequest {
    public static enum Method {
        GET,
        POST,
        PUT,
        DELETE,
        OPTION
    }

    public URL getURL();

    public void setURL(String url) throws MalformedURLException;

    public Method getMethod();

    public void setMethod(Method method);

    public void addHeader(String name, Object value);

    public void setParams(Map<String, Object> params);

    public void setGZip(boolean gzip);
    
    public void setBody(String body);
    public void setBody(byte[] body);
    public void setBody(byte[] body, int offset, int length);
    public void setBody(InputStream inputStream);

    public HttpResponse execute();

    public OutputStream getOutputStream() throws IOException;
}

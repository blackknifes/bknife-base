package com.bknife.base.net.todo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public interface HttpResponse {

    public Collection<String> getUrls();

    public String getMimeType();

    public String getCharset();

    public long getContentLength();

    public String getHeader(String name);

    public Map<String, String> getHeaders();

    public Collection<HttpHeader> getRawHeaders();

    public Map<String, HttpCookie> getCookies();

    public HttpCookie getCookie(String name);

    public InputStream getInputStream() throws IOException;
}

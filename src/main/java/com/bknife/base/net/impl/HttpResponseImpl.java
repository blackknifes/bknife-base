package com.bknife.base.net.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bknife.base.net.HttpCookie;
import com.bknife.base.net.HttpHeader;
import com.bknife.base.net.HttpResponse;

public class HttpResponseImpl implements HttpResponse {
    private Collection<String> urls;
    private String mimetype;
    private String charset;
    private long contentLength;
    private List<HttpHeader> rawHeaders = new ArrayList<HttpHeader>();
    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, HttpCookie> cookies = new HashMap<String, HttpCookie>();
    private InputStream inputStream;

    @Override
    public Collection<String> getUrls() {
        return urls;
    }

    @Override
    public String getMimeType() {
        return mimetype;
    }

    @Override
    public String getCharset() {
        return charset;
    }

    @Override
    public long getContentLength() {
        return contentLength;
    }

    @Override
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public Collection<HttpHeader> getRawHeaders() {
        return rawHeaders;
    }

    @Override
    public Map<String, HttpCookie> getCookies() {
        return cookies;
    }

    @Override
    public HttpCookie getCookie(String name) {
        return cookies.get(name);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }
}

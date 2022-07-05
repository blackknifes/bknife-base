package com.bknife.base.net.todo.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import com.bknife.base.net.todo.HttpHeader;
import com.bknife.base.net.todo.HttpRequest;
import com.bknife.base.net.todo.HttpResponse;

//TODO: implement http
public class HttpRequestImpl implements HttpRequest {
    private Socket socket;
    private URL url;
    private Method method = Method.GET;
    private List<HttpHeader> headers = new ArrayList<HttpHeader>();
    private InputStream bodyInputStream;
    private Map<String, Object> params;
    private boolean gzip;

    public HttpRequestImpl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String protocol = url.getProtocol();
        if ("https".equals(protocol) || "wss".equals(protocol))
            socket = SSLSocketFactory.getDefault().createSocket();
        else
            socket = new Socket();

        socket.bind(new InetSocketAddress(0));
        int port = url.getPort();
        if (port == -1)
            port = url.getDefaultPort();
        if (port == -1)
            throw new IOException("Unkown port");

        socket.connect(new InetSocketAddress(url.getHost(), port), 5000);
    }

    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public void setURL(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public void addHeader(String name, Object value) {
        headers.add(new HttpHeader(name, value.toString()));
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public void setBody(String body) {
        setBody(body.getBytes());
    }

    @Override
    public void setBody(byte[] body) {
        bodyInputStream = new ByteArrayInputStream(body);
    }

    @Override
    public void setBody(byte[] body, int offset, int length) {
        setBody(new ByteArrayInputStream(body, offset, length));
    }

    @Override
    public void setBody(InputStream inputStream) {
        bodyInputStream = inputStream;
    }

    @Override
    public HttpResponse execute() {
        return null;
    }

    @Override
    public void setGZip(boolean gzip) {
        this.gzip = gzip;
    }
}

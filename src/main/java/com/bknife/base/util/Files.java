package com.bknife.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class Files {
    public static void write(File file, byte[] data, int offset, int length) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data, offset, length);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void write(File file, byte[] data) throws IOException {
        write(file, data, 0, data.length);
    }

    public static void write(String file, byte[] data, int offset, int length) throws IOException {
        write(new File(file), data, offset, length);
    }

    public static void write(String file, byte[] data) throws IOException {
        write(file, data, 0, data.length);
    }

    public static void write(File file, String str, String encoding) throws IOException {
        write(file, str.getBytes(encoding));
    }

    public static void write(File file, String str, Charset charset) throws IOException {
        write(file, str.getBytes(charset));
    }

    public static void write(File file, String str) throws IOException {
        write(file, str.getBytes());
    }

    public static void write(String file, String str, String encoding) throws IOException {
        write(file, str.getBytes(encoding));
    }

    public static void write(String file, String str, Charset charset) throws IOException {
        write(file, str.getBytes(charset));
    }

    public static void write(String file, String str) throws IOException {
        write(file, str.getBytes());
    }

    public static byte[] readBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            int len = (int) file.length();
            byte[] data = new byte[len];
            fis.read(data);
            return data;
        } catch (IOException e) {
            throw e;
        }
    }

    public static byte[] readBytes(String file) throws IOException {
        return readBytes(new File(file));
    }

    public static String readString(File file, Charset charset) throws IOException {
        return new String(readBytes(file), charset);
    }

    public static String readString(File file, String encoding) throws IOException {
        return new String(readBytes(file), encoding);
    }

    public static String readString(File file) throws IOException {
        return new String(readBytes(file));
    }

    public static String readString(String file, Charset charset) throws IOException {
        return new String(readBytes(file), charset);
    }

    public static String readString(String file, String encoding) throws IOException {
        return new String(readBytes(file), encoding);
    }

    public static String readString(String file) throws IOException {
        return new String(readBytes(file));
    }
}

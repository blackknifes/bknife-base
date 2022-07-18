package com.bknife.base.json;

public interface JsonReader {
    /**
     * 跳过空白字符
     */
    public void skipSpace();

    /**
     * 取出下移一个字符，并且索引下移
     * 
     * @return
     */
    public void back();

    /**
     * 取出下移一个字符，并且索引下移
     * 
     * @return
     */
    public char next();

    /**
     * 是否到达文件结尾
     * 
     * @return
     */
    public boolean isEof();

    /**
     * 获取当前行数
     * 
     * @return
     */
    public int linenum();

    /**
     * 获取当前列数
     * 
     * @return
     */
    public int offset();

    /**
     * 重置状态
     */
    public void reset();
}

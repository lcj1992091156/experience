package com.ssl.tools;


import java.io.Serializable;

/**
 * *                     "page": "1",
 * *                 "positionX": "x轴的距离",
 * *                 "positionY": "y轴的距离",
 * *                 "width": "长",
 * *                 "height": "宽",
 * *                 "font":"字体，ttf格式",
 * *                 "fontSize": "字体大小",
 * *                 "name":"表单域的名称",
 * *                 "value":"表单域的值，可选"
 */
public class PDFModel implements Serializable {
    /**
     * "fontSize": "字体大小",
     */
    private Integer fontSize;
    /**
     * "height": "宽",
     */
    private float height;
    /**
     * "width": "长",
     */
    private float width;
    /**
     * "name":"表单域的名称",
     */
    private String name;

    private Integer page;

    private float positionX;

    private float positionY;
    /**
     * "value":"表单域的值，可选"
     */
    private String value;

    private String font;

    public Integer getFontSize() {
        return fontSize;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public Integer getPage() {
        return page;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public String getValue() {
        return value;
    }

    public String getFont() {
        return font;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFont(String font) {
        this.font = font;
    }
}

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
    private String fontSize;
    /**
     * "height": "宽",
     */
    private String height;
    /**
     * "width": "长",
     */
    private String width;
    /**
     * "name":"表单域的名称",
     */
    private String name;

    private String page;

    private String positionX;

    private String positionY;
    /**
     * "value":"表单域的值，可选"
     */
    private String value;
    private String font;

    public String getFontSize() {
        return fontSize;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public String getName() {
        return name;
    }

    public String getPage() {
        return page;
    }

    public String getPositionX() {
        return positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public String getValue() {
        return value;
    }

    public String getFont() {
        return font;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFont(String font) {
        this.font = font;
    }
}

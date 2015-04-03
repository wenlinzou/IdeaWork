package com.bean;

import java.awt.*;

/**
 * Created by Pet on 2015-04-02.
 * 验证码
 */
public class IDCode {
    private int height;
    private int width;
    private Graphics g;
    private Color backColor;
    private Color borderColor;
    private Color lineColor;
    private Color fontColor;
    private Font wordFont;

    public IDCode() {
    }

    public IDCode(int height, int width, Graphics g, Color backColor, Color borderColor, Color lineColor, Color fontColor, Font wordFont) {
        this.height = height;
        this.width = width;
        this.g = g;
        this.backColor = backColor;
        this.borderColor = borderColor;
        this.lineColor = lineColor;
        this.fontColor = fontColor;
        this.wordFont = wordFont;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public Color getBackColor() {
        return backColor;
    }

    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public Font getWordFont() {
        return wordFont;
    }

    public void setWordFont(Font wordFont) {
        this.wordFont = wordFont;
    }
}

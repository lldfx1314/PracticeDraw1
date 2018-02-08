package com.hencoder.hencoderpracticedraw1.model;

/**
 * Created by Administrator on 2018/2/7.
 *
 * @author Administrator
 */
public class PieChartData {

    private String title;

    private int color;

    private int value;

    public PieChartData(String title, int color, int value) {
        this.title = title;
        this.color = color;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

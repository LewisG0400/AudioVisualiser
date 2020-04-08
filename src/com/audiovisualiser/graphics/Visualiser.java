package com.audiovisualiser.graphics;

import processing.core.PApplet;

public class Visualiser {
    private int bands, sampleSize;
    private int step;
    private double scaling, smoothing = 0.1;
    private int r = 255, g = 255, b = 255;

    private PApplet parent;

    private double[] lastData, plotData;

    public Visualiser(PApplet parent, int sampleSize, int bands) {
        this.parent = parent;

        this.bands  = bands;
        this.sampleSize = sampleSize;
        this.step = (sampleSize / 2) / bands;
        System.out.println(parent.height);

    }

    public void start() {
        this.scaling = ((float)parent.width / (float)bands);

        this.lastData = new double[bands];
        this.plotData = new double[bands];

        parent.fill(r, g, b);
    }

    public void setBands(int bands) {
        this.bands = bands;

        System.out.println(bands);

        this.scaling = ((float)parent.width / (float)bands);
    }

    public void setColour(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setRed(int red) {
        System.out.println("Red: " + red);
        this.r = red;
    }

    public void setGreen(int green) {
        this.g = green;
    }

    public void setBlue(int blue) {
        this.b = blue;
    }

    public void render(double[] data) {
        double dataAvg;
        parent.fill(r, g, b);
        for(int i = 0; i < data.length - step; i += step) {
            dataAvg = 0;
            for(int j = i; j < i + step; j ++) {
                dataAvg += data[j];
            }
            dataAvg /= step;

            if(dataAvg < plotData[i / step]) {
                plotData[i / step] -= 1;
            } else {
                plotData[i / step] = smoothing * plotData[i / step] + (1 - smoothing) * dataAvg;
            }
        }

        for(int i = 0; i < bands; i ++) {
            parent.rect((float)(i * scaling), parent.height, (float)(scaling), (float)-data[i]);
        }
    }

}

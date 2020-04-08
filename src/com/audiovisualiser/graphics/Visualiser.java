package com.audiovisualiser.graphics;

import processing.core.PApplet;

public class Visualiser {
    private int bands, sampleSize;
    private int step;
    private double scaling, smoothing = 0.1;

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
    }

    public void render(double[] data) {
        double dataAvg;
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

package com.audiovisualiser;

import com.audiovisualiser.audio.AudioProcessor;
import com.audiovisualiser.graphics.Visualiser;
import com.audiovisualiser.audio.AudioListener;
import com.audiovisualiser.graphics.Controls;
import g4p_controls.G4P;
import g4p_controls.GControlMode;
import g4p_controls.GEvent;
import g4p_controls.GSlider2D;
import processing.core.*;

import java.util.Arrays;

public class Main extends PApplet {

    public static AudioListener listener;
    public static Visualiser visualiser;
    public static Controls controls;

    private static final int SAMPLE_RATE = 48000, SAMPLE_SIZE = 512;
    private static byte[] data;
    private static double[] transformedData;

    private int clamp(int x, int small, int large) {
        return max(small, min(x, large));
    }

    public void settings() {
        size(1200, 800);
    }

    public void update() {
        listener.readData(data);
        AudioProcessor.FFT(data, transformedData);
    }

    public void draw() {
        update();

        background(0);

        visualiser.render(Arrays.copyOfRange(transformedData, 0, SAMPLE_SIZE / 2));
       // red.draw();
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Visualiser"};
        Main sketch = new Main();

        listener = new AudioListener(SAMPLE_RATE, SAMPLE_SIZE);
        visualiser = new Visualiser(sketch, SAMPLE_SIZE, 128);
        controls = new Controls(sketch, visualiser);

        data = new byte[SAMPLE_SIZE];
        transformedData = new double[SAMPLE_SIZE];

        PApplet.runSketch(processingArgs, sketch);

        visualiser.start();
        controls.setupUI();
        visualiser.setColour(127, 127, 127);
    }
}

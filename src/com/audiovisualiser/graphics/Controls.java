package com.audiovisualiser.graphics;

import g4p_controls.*;
import processing.core.PApplet;

public class Controls {
    private PApplet parent;
    private Visualiser visualiser;

    private GLabel redLabel, greenLabel, blueLabel;
    private GSlider red, green, blue;

    private GLabel bandLabel;
    private GSlider bandSlider;

    public Controls(PApplet parent, Visualiser visualiser) {
        this.parent = parent;
        this.visualiser = visualiser;

        G4P.setCtrlMode(GControlMode.CORNER);
        G4P.setGlobalColorScheme(4);
        G4P.setDisplayFont("Verdana", G4P.PLAIN, 20);
    }

    public void setupUI() {
        redLabel = new GLabel(parent, 25, 5, 255, 25, "Red:");
        red = new GSlider(parent, 25, 25, 255, 50, 20);
        red.addEventHandler(this, "setRed");

        greenLabel = new GLabel(parent, 25, 60, 255, 25, "Green:");
        green = new GSlider(parent, 25, 80, 255, 50, 20);
        green.addEventHandler(this, "setGreen");

        blueLabel = new GLabel(parent, 25, 120, 255, 25, "Blue:");
        blue = new GSlider(parent, 25, 135, 255, 50, 20);
        blue.addEventHandler(this, "setBlue");

        bandLabel = new GLabel(parent, parent.width / 2 - 100, 5, 200, 25, "Bands:");
        bandSlider = new GSlider(parent,parent.width / 2 - 100, 25, 200, 50, 20);
        bandSlider.addEventHandler(this, "setBands");
    }

    public void setRed(GSlider slider, GEvent event) {
        visualiser.setRed((int)(slider.getValueF() * 255));
    }

    public void setGreen(GSlider slider, GEvent event) {
        visualiser.setGreen((int)(slider.getValueF() * 255));
    }

    public void setBlue(GSlider slider, GEvent event) {
        visualiser.setBlue((int)(slider.getValueF() * 255));
    }

    public void setBands(GSlider slider, GEvent event) {
        visualiser.setBands((int)(slider.getValueF() * 100));
    }
}

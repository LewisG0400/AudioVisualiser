package com.audiovisualiser.audio;

import javax.sound.sampled.*;

public class AudioListener {
    private int sampleRate;

    private TargetDataLine line;
    private int bufferSize, sampleSize;

    public AudioListener(int sampleRate, int sampleSize) {
        this.sampleRate = sampleRate;

        AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open();
            line.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.bufferSize = (int) format.getSampleRate() * format.getFrameSize();
        this.sampleSize = sampleSize;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void readData(byte[] data) {
        int numRead = line.read(data, 0, sampleSize);
    }
}

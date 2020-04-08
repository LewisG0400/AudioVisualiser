package com.audiovisualiser.audio;

import static org.apache.commons.math3.transform.FastFourierTransformer.*;
import static org.apache.commons.math3.transform.TransformType.*;
import static org.apache.commons.math3.transform.DftNormalization.*;

public class AudioProcessor {

    /*static class Complex {
        public byte real, imag;

        public Complex(byte real, byte imag) {
            this.real = real;
            this.imag = imag;
        }

        public Complex add(Complex other) {
            return new Complex((byte)(this.real + other.real), (byte)(this.imag + other.imag));
        }

        public Complex sub(Complex other) {
            return new Complex((byte)(this.real - other.real), (byte)(this.imag - other.imag));
        }

        public Complex mul(Complex other) {
            return new Complex((byte)(this.real * other.real + this.imag * other.imag), (byte)(this.real * other.imag + other.real * this.imag));
        }

        public Complex conjugate() {
            return new Complex(real, (byte)-imag);
        }

        public byte modulus() {
            return (byte)Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
        }

        public static Complex getFromPolar(double r, double theta) {
            return new Complex((byte)(r * Math.cos(theta)), (byte)(r * Math.sin(theta)));
        }
    }

    public static int bitReverse(int n, int bits) {
        int reversedN = n;
        int count = bits - 1;

        n >>= 1;
        while (n > 0) {
            reversedN = (reversedN << 1) | (n & 1);
            count--;
            n >>= 1;
        }

        return ((reversedN << count) & ((1 << bits) - 1));
    }

    private static Complex[] FFT(Complex[] data) {
        int n = data.length;
        if(n == 1) return new Complex[] {data[0]};

        Complex[] even = new Complex[n / 2];
        Complex[] odd = new Complex[n / 2];

        for(int i = 0; i < n / 2; i ++) {
            even[i] = data[2 * i];
            odd[i] = data[2 * i + 1];
        }

        Complex[] newEven = FFT(even);
        Complex[] newOdd = FFT(odd);

        Complex[] out = new Complex[n];
        for(int k = 0; k < n / 2; k ++) {
            double exp = -2 * Math.PI * k / 2;
            Complex w = new Complex((byte)Math.cos(exp), (byte)Math.sin(exp));
            out[k] = newEven[k].add(w.mul(newOdd[k]));
            out[k + n / 2] = newEven[k].sub(w.mul(newOdd[k]));
        }
        return out;
    }

    public static void FFT(byte[] inData, byte[] outData) {
        int n = inData.length;
        Complex[] complexData = new Complex[n];

        for(int i = 0; i < n; i ++) {
            complexData[i] = new Complex(inData[i], (byte) 0);
        }

        complexData = FFT(complexData);

        for(int i = 0; i < n; i ++) {
            outData[i] = (byte)(complexData[i].modulus());
        }
    }

    private static void DFT(Complex[] inData, Complex[] outData) {
        int n = inData.length;

        for(int k = 0; k < n; k ++) {
            byte realSum = 0;
            byte imagSum = 0;

            for(int l = 0; l < n; l ++) {
                double angle = 2 * Math.PI * l * k / n;
                realSum += inData[l].real * Math.cos(angle) + inData[l].imag * Math.sin(angle);
                imagSum += -inData[l].real * Math.sin(angle) + inData[l].imag * Math.cos(angle);
            }
            outData[k].real = realSum;
            outData[k].imag = imagSum;
        }
    }*/

    public static void FFT(byte[] inData, double[] outData) {
        double[][] d = new double[2][inData.length];

        for(int i = 0; i < inData.length; i ++) {
            d[0][i] = inData[i];
            d[1][i] = 0;
        }

        transformInPlace(d, STANDARD, FORWARD);

        for(int i = 0; i < inData.length; i ++) {
            outData[i] = Math.pow(d[0][i], 2) + Math.pow(d[1][i], 2);
            outData[i] = 10 * Math.log10(outData[i]);
        }
    }
}

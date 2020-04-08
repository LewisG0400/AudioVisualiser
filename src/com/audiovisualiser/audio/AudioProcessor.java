package com.audiovisualiser.audio;

import static org.apache.commons.math3.transform.FastFourierTransformer.*;
import static org.apache.commons.math3.transform.TransformType.*;
import static org.apache.commons.math3.transform.DftNormalization.*;

public class AudioProcessor {

    public static void FFT(byte[] inData, double[] outData) {
        double[][] d = new double[2][inData.length];

        for(int i = 0; i < inData.length; i ++) {
            d[0][i] = inData[i];
            d[1][i] = 0;
        }

        transformInPlace(d, STANDARD, FORWARD);

        for(int i = 0; i < inData.length; i ++) {
            outData[i] = Math.pow(d[0][i], 2) + Math.pow(d[1][i], 2);
            outData[i] = 10 * Math.log(outData[i]);
        }
    }
}

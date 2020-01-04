package org.lib.framework;

import java.util.Arrays;

public class MedianFilter {
    int window_size;
    double[] window;
    int idx = 0;
    public MedianFilter(int window_size) {
        window = new double[window_size];
        this.window_size = window_size;
    }

    public void insert(double value) {
        window[idx%window_size] = value;
        idx++;
        Arrays.sort(window);
    }

    public double get() {
        return window[window_size / 2];
    }
}
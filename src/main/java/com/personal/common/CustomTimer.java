package com.personal.common;

public class CustomTimer {
    private boolean isStarted;
    private boolean isEnded;
    private long start;
    private long end;

    private CustomTimer() {}

    public static CustomTimer create() {
        return new CustomTimer();
    }

    public static CustomTimer create(boolean started) {
        CustomTimer timer = new CustomTimer();
        timer.start();
        return timer;
    }

    public long start() {
        this.start = System.currentTimeMillis();
        this.isStarted = true;
        return this.start;
    }

    public long end() {
        if (!this.isStarted) {
            return 0;
        }
        this.end = System.currentTimeMillis();
        this.isEnded = true;
        return this.end;
    }

    public long elapsed() {
        this.end();
        return this.end - this.start;
    }

    public String elapsedFormatted() {
        elapsed();
        return elaped("%d ms");
    }

    public String elaped(String format) {
        return String.format(format, this.end - this.start);
    }
}

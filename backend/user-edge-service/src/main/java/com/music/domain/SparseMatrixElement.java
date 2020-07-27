package com.music.domain;

public class SparseMatrixElement {

    int r;
    int c;
    int num = 1;

    public SparseMatrixElement(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return r + "\t" + c + "\t" + num;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public SparseMatrixElement(int r, int c, int num) {
        this.r = r;
        this.c = c;
        this.num = num;
    }

    public SparseMatrixElement() {
    }
}

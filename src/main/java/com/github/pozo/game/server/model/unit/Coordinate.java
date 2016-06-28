package com.github.pozo.game.server.model.unit;

/**
 * Created by pozo on 2016.05.25..
 */
public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getDistance(Coordinate coordinate) {
        double dx = this.x - coordinate.x;
        double dy = this.y - coordinate.y;

        return Math.sqrt(dx * dx + dy * dy);
    }
    public double getAngleY(Coordinate coordinate) {
        double angle = Math.atan2(coordinate.y - y, coordinate.x - x);

        if(angle < 0){

            angle += 2*Math.PI;
        }

        return angle;

    }
    public Coordinate normalize() {
        double newX = 0;
        double newY = 0;
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        if (length != 0) {
            newX = this.x / length;
            newY = this.y / length;
        }
        return new Coordinate(newX, newY);
    }
    public Coordinate sub(Coordinate v1) {
        return new Coordinate(this.x - v1.x, this.y - v1.y);
    }

    public Coordinate add(Coordinate v1) {
        return new Coordinate(this.x + v1.x, this.y + v1.y);
    }
    public Coordinate multiply(double i) {
        return new Coordinate(this.x * i, this.y * i);
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public String toJSON() {
        return "{" +
                "\"x\":" + x +
                ",\"y\":" + y +
                "}";
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

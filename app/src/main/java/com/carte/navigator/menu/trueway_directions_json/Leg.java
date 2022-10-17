package com.carte.navigator.menu.trueway_directions_json;

import java.util.ArrayList;
import java.util.List;

public class Leg {
    private int distance;
    private int duration;
    private int start_point_index;
    private StartPoint start_point;
    private int end_point_index;
    private EndPoint end_point;
    private Bounds bounds;
    private List<Step> steps;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStart_point_index() {
        return start_point_index;
    }

    public void setStart_point_index(int start_point_index) {
        this.start_point_index = start_point_index;
    }

    public StartPoint getStart_point() {
        return start_point;
    }

    public void setStart_point(StartPoint start_point) {
        this.start_point = start_point;
    }

    public int getEnd_point_index() {
        return end_point_index;
    }

    public void setEnd_point_index(int end_point_index) {
        this.end_point_index = end_point_index;
    }

    public EndPoint getEnd_point() {
        return end_point;
    }

    public void setEnd_point(EndPoint end_point) {
        this.end_point = end_point;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}

package dev.mattson.intersectionlightcontroller.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Objects;

@Entity
@Builder
@Table(name = "intersection")
public class Intersection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intersection_id")
    private int intersectionId;

    @Column(name = "ns_light")
    private LightState NSLight;

    @Column(name = "ew_light")
    private LightState EWLight;

    @Column(name = "power")
    private boolean power;

    @Column(name = "ns_light_red")
    private int NSLightRed;

    @Column(name = "ns_light_yellow")
    private int NSLightYellow;

    @Column(name = "ns_light_green")
    private int NSLightGreen;

    @Column(name = "ew_light_red")
    private int EWLightRed;

    @Column(name = "ew_light_yellow")
    private int EWLightYellow;

    @Column(name = "ew_light_green")
    private int EWLightGreen;

    public Intersection() {
    }

    public Intersection(int intersectionId, LightState NSLight, LightState EWLight, boolean power, int NSLightRed, int NSLightYellow, int NSLightGreen, int EWLightRed, int EWLightYellow, int EWLightGreen) {
        this.intersectionId = intersectionId;
        this.NSLight = NSLight;
        this.EWLight = EWLight;
        this.power = power;
        this.NSLightRed = NSLightRed;
        this.NSLightYellow = NSLightYellow;
        this.NSLightGreen = NSLightGreen;
        this.EWLightRed = EWLightRed;
        this.EWLightYellow = EWLightYellow;
        this.EWLightGreen = EWLightGreen;
    }

    public int getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(int intersectionId) {
        this.intersectionId = intersectionId;
    }

    public LightState getNSLight() {
        return NSLight;
    }

    public void setNSLight(LightState NSLight) {
        this.NSLight = NSLight;
    }

    public LightState getEWLight() {
        return EWLight;
    }

    public void setEWLight(LightState EWLight) {
        this.EWLight = EWLight;
    }

    public boolean isPower() {
        return power;
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public int getNSLightRed() {
        return NSLightRed;
    }

    public void setNSLightRed(int NSLightRed) {
        this.NSLightRed = NSLightRed;
    }

    public int getNSLightYellow() {
        return NSLightYellow;
    }

    public void setNSLightYellow(int NSLightYellow) {
        this.NSLightYellow = NSLightYellow;
    }

    public int getNSLightGreen() {
        return NSLightGreen;
    }

    public void setNSLightGreen(int NSLightGreen) {
        this.NSLightGreen = NSLightGreen;
    }

    public int getEWLightRed() {
        return EWLightRed;
    }

    public void setEWLightRed(int EWLightRed) {
        this.EWLightRed = EWLightRed;
    }

    public int getEWLightYellow() {
        return EWLightYellow;
    }

    public void setEWLightYellow(int EWLightYellow) {
        this.EWLightYellow = EWLightYellow;
    }

    public int getEWLightGreen() {
        return EWLightGreen;
    }

    public void setEWLightGreen(int EWLightGreen) {
        this.EWLightGreen = EWLightGreen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return intersectionId == that.intersectionId && power == that.power && NSLightRed == that.NSLightRed && NSLightYellow == that.NSLightYellow && NSLightGreen == that.NSLightGreen && EWLightRed == that.EWLightRed && EWLightYellow == that.EWLightYellow && EWLightGreen == that.EWLightGreen && NSLight == that.NSLight && EWLight == that.EWLight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(intersectionId, NSLight, EWLight, power, NSLightRed, NSLightYellow, NSLightGreen, EWLightRed, EWLightYellow, EWLightGreen);
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "intersectionId=" + intersectionId +
                ", NSLight=" + NSLight +
                ", EWLight=" + EWLight +
                ", power=" + power +
                ", NSLightRed=" + NSLightRed +
                ", NSLightYellow=" + NSLightYellow +
                ", NSLightGreen=" + NSLightGreen +
                ", EWLightRed=" + EWLightRed +
                ", EWLightYellow=" + EWLightYellow +
                ", EWLightGreen=" + EWLightGreen +
                '}';
    }
}

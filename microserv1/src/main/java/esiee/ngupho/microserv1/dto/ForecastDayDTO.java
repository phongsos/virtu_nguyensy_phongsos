package esiee.ngupho.microserv1.dto;

import java.time.LocalDate;

public class ForecastDayDTO
{
    private String location;
    private LocalDate date;
    private int maxTemperature;
    private int minTemperature;
    private float humidity;
    private int chanceOfRain;
    private int chanceOfSnow;
    private String text;

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public void setChanceOfRain(int chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public int getChanceOfSnow() {
        return chanceOfSnow;
    }

    public void setChanceOfSnow(int chanceOfSnow) {
        this.chanceOfSnow = chanceOfSnow;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ForecastDayDTO{" +
                "location='" + location + '\'' +
                ", date=" + date +
                ", maxTemperature=" + maxTemperature +
                ", minTemperature=" + minTemperature +
                ", humidity=" + humidity +
                ", chanceOfRain=" + chanceOfRain +
                ", chanceOfSnow=" + chanceOfSnow +
                ", text='" + text + '\'' +
                '}';
    }

}

package esiee.ngupho.microserv1.dto;

public class CurrentWeatherDTO {
    private String location;
    private double temperature;
    private int humidity;
    private double wind;
    private String text;

    // Getters and Setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Display
    @java.lang.Override
    public java.lang.String toString() {
        return "CurrentWeatherDTO{" +
                "location='" + location + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", wind=" + wind +
                ", text='" + text + '\'' +
                '}';
    }
}
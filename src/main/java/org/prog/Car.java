package org.prog;

public class Car implements ITransport {

    private String color;
    public int milage = 0;

    public void goTo() {
        milage += 10;
        goTo("somewhere");
    }

    public void goTo(String destination) {
        goTo("current city", destination);
    }

    public void goTo(String from, String destination) {
        goTo(from, destination, "nowhere");
    }

    public void goTo(String from, String destination, String passingThrough) {
        System.out.println("Car is going from " + from + " to "
                + destination + " stopping at " + passingThrough);
    }

    public void turn(String direction) {
        System.out.println(color + " car turns " + direction);
    }

    public void setColor(String newColor) {
        if (newColor != null) {
            color = newColor;
        }
    }

    public String getColor() {
        return color;
    }
}

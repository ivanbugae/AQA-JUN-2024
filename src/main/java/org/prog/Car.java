package org.prog;

public class Car implements ITransport {

    private String color;
    public int milage = 0;
    @Override
    public String toString() {
        return "This car is " + color + " and its current milage is " + milage;
    }
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

    @Override
    public void stopAt(String stop) {

    }

    public void setColor(String newColor) {
        if (newColor != null) {
            color = newColor;
        }
    }

    public String getColor() {
        return color;
    }


@Override
public boolean equals(Object o) {
    if (o instanceof Car) {
        Car obj = (Car) o;
        return this.color.equals(obj.color) && Integer.toString(this.milage).equals(Integer.toString(obj.milage));
    }
    return false;
}

@Override
public int hashCode() {
    return (color + milage).hashCode();
}
}
package org.prog;

public class Chair {

    public String color;
    public String wood;

    @Override
    public String toString() {
        return "This chair is " + color + " and made of " + wood;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Chair) {
            Chair obj = (Chair) o;
            return this.color.equals(obj.color) && this.wood.equals(obj.wood);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (color + wood).hashCode();
    }
}


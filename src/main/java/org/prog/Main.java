package org.prog;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * car1 == car2
     * #############################################
     * #############################################
     * #############################################
     * ############[Car : car1 | color:Red]#########
     * #############################################
     * #############################################
     * #############################################
     * #############################################
     * #############################################
     * ###[Car : car2 | color:Red]##################
     * ############################10###############
     *
     * @param args
     */


    public static void main(String[] args) {
//        Car car1 = new Car();
//        Truck truck1 = new Truck();
//        BigTruck bigTruck = new BigTruck();
//        BiggestTruck biggestTruck = new BiggestTruck();
//
//        car1.setColor("Red");
//        truck1.setColor("white");
//        bigTruck.setColor("black");
//        biggestTruck.setColor("yellow");
//
//        car1.goTo();
//        car1.goTo("Kyiv");
//        car1.goTo("Odessa", "Lviv");
//        car1.goTo("Uzhorod", "Kharkiv", "Dnipro");
//
        Chair chairOne = new Chair();
        Chair chairTwo = new Chair();
        chairOne.color = "white";
        chairTwo.color = "white";
        chairOne.wood = "oak";
        chairTwo.wood = "oak";
        isChairWhite(chairOne);
        System.out.println(chairOne);
        System.out.println(chairTwo);
        System.out.println(chairOne.equals(chairTwo));
        System.out.println(chairOne.hashCode());
        System.out.println(chairTwo.hashCode());

        String s1 = "s1";
        String s2 = "";
        String s3 = null;

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        Car car1= new Car();
        car1.setColor("White");
        car1.milage=10;
        Car car2 =new Car();
        car2.setColor("Black");
        car2.milage=20;
        Car car3 =new Car();
        car3.setColor("White");
        car3.milage=30;
        Car car4 =new Car();
        car4.setColor("Black");
        car4.milage=40;
        Truck truck1 = new Truck();
        truck1.setColor("White");
        truck1.milage=50;
        Truck truck2 = new Truck();
        truck2.setColor("Black");
        truck2.milage=60;
        BigTruck bigTruck = new BigTruck();
        bigTruck.setColor("White");
        bigTruck.milage=70;
        BigTruck bigTruck2= new BigTruck();
        bigTruck2.setColor("Black");
        bigTruck2.milage=80;
        BiggestTruck biggestTruck = new BiggestTruck();
        biggestTruck.setColor("White");
        biggestTruck.milage=90;
        BiggestTruck biggestTruck2 = new BiggestTruck();
        biggestTruck2.setColor("Black");
        biggestTruck2.milage=100;
        List<String> sList = new ArrayList<>();
        sList.add(car1.getColor()+" car and its milage is "+car1.milage);
        sList.add(car2.getColor()+" car and its milage is "+car2.milage);
        sList.add(car3.getColor()+" car and its milage is "+car3.milage);
        sList.add(car4.getColor()+" car and its milage is "+car4.milage);
        sList.add(truck1.getColor()+" truck and its milage is "+truck1.milage);
        sList.add(truck2.getColor()+" truck and its milage is "+truck2.milage);
        sList.add(bigTruck.getColor()+" big truck and its milage is "+bigTruck.milage);
        sList.add(bigTruck2.getColor()+" big truck and its milage is "+bigTruck2.milage);
        sList.add(biggestTruck.getColor()+" biggest truck and its milage is "+biggestTruck.milage);
        sList.add(biggestTruck2.getColor()+" biggest truck and its milage is "+biggestTruck2.milage);
        for (int i = 0; i < sList.size(); i++) {
            System.out.println(sList.get(i));
        }
    }

    public static void isChairWhite(Chair chair) {
        if ("white".equals(chair.color)) {
            System.out.println("this chair is white");
        } else {
            System.out.println("this chair is not white");
        }
    }

    public static void transport(ITransport iTransport) {
        iTransport.goTo();
        iTransport.turn("left");
        iTransport.turn("right");
    }
}

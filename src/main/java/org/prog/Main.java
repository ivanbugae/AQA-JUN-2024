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

        Car car2 =new Car();


        Car car3 =new Car();

        Car car4 =new Car();

        Truck truck1 = new Truck();

        Truck truck2 = new Truck();

        BigTruck bigTruck = new BigTruck();
        BigTruck bigTruck2= new BigTruck();

        BiggestTruck biggestTruck = new BiggestTruck();

        BiggestTruck biggestTruck2 = new BiggestTruck();

        List<Car> sList = new ArrayList<>();
        sList.add(car1);
        sList.add(car2);
        sList.add(car3);
        sList.add(car4);
        sList.add(truck1);
        sList.add(truck2);
        sList.add(bigTruck);
        sList.add(bigTruck2);
        sList.add(biggestTruck);
        sList.add(biggestTruck2);
        for (int i = 0; i < sList.size(); i++) {
if(i%2==1){sList.get(i).setColor("Black");}
else{sList.get(i).setColor("White");}
            sList.get(i).milage=i*10+10;
System.out.println(sList.get(i).getColor()+" "+sList.get(i).milage);
}
}   public static void isChairWhite(Chair chair) {
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

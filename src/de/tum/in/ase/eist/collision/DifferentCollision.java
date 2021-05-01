package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.Car;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

public class DifferentCollision extends Collision{

    private final boolean crash;
    private final Map<Car,Car> crashes = new HashMap();
    private final int maxAmountOfCrashes = 2;
    private int amountOfCrashes = 0;

    public DifferentCollision(Car car1, Car car2) {
        super(car1, car2);
        this.crash = detectCollision();
    }

    @Override
    public Car evaluate() {

        // In this different collision implementation it requires the collision between two cars
        // to be already in the map. This means that the two cars need to hit (following the "right before left" rule)
        // twice to have a winner.

        Point2D p1 = this.car1.getPosition();
        Point2D p2 = this.car2.getPosition();

        Car winnerCar = null;
        if (p1.getX() < p2.getX()) {
            if(!crashes.containsKey(car1) || !crashes.containsValue(car2)){
                crashes.put(car1,car2);
            } else if(crashes.containsKey(car1) && crashes.containsValue(car2)){
                winnerCar = this.car2;
                crashes.remove(car1, car2);
            }
        } else {
            if(!crashes.containsKey(car2) || !crashes.containsValue(car1)){
                crashes.put(car2, car1);
            } else if(crashes.containsKey(car2) && crashes.containsValue(car1)){
                winnerCar = this.car1;
                crashes.remove(car2, car1);
            }
        }
        return winnerCar;
    }

}

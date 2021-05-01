package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class RandomTurnCar extends Car{

    private static final String FAST_CAR_IMAGE_FILE = "blue-car-hp-left-bmw.gif";

    private static final int MIN_SPEED_FAST_CAR = 2;
    private static final int MAX_SPEED_FAST_CAR = 10;

    public RandomTurnCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_FAST_CAR);
        setMaxSpeed(MAX_SPEED_FAST_CAR);
        setRandomSpeed();
        setIconLocation(FAST_CAR_IMAGE_FILE);

    }

    @Override
    public void drive(Dimension2D gameBoardSize) {
        if (super.isCrunched()) {
            return;
        }
        double maxX = gameBoardSize.getWidth();
        double maxY = gameBoardSize.getHeight();
        // calculate delta between old coordinates and new ones based on speed and
        // direction
        double deltaX = super.getSpeed() * Math.sin(Math.toRadians(super.getDirection()));
        double deltaY = super.getSpeed() * Math.cos(Math.toRadians(super.getDirection()));
        double newX = super.getPosition().getX() + deltaX;
        double newY = super.getPosition().getY() + deltaY;

        // calculate position in case the boarder of the game board has been reached
        if (newX < 0) {
            newX = -newX;
            super.setDirection((MAX_ANGLE - super.getDirection()) * calculateRandomInt(0,1));
        } else if (newX + super.getSize().getWidth() > maxX) {
            newX = 2 * maxX - newX - 2 * super.getSize().getWidth();
            super.setDirection((MAX_ANGLE - super.getDirection()) * calculateRandomInt(0,1));
        }

        if (newY < 0) {
            newY = -newY;
            super.setDirection((HALF_ANGLE - super.getDirection())* calculateRandomInt(0,1));
            if (super.getDirection() < 0) {
                super.setDirection((HALF_ANGLE + super.getDirection())* calculateRandomInt(0,1));
            }
        } else if (newY + super.getSize().getHeight() > maxY) {
            newY = 2 * maxY - newY - 2 * super.getSize().getHeight();
            super.setDirection((HALF_ANGLE - super.getDirection())* calculateRandomInt(0,1));
            if (super.getDirection() < 0) {
                super.setDirection((HALF_ANGLE + super.getDirection())* calculateRandomInt(0,1));
            }
        }
        // set coordinates
        super.setPosition(newX, newY);

        // the car turns randomly once it hits the wall

    }

}
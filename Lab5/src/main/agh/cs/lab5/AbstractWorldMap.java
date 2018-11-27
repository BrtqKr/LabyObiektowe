package agh.cs.lab5;

import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap
{
    protected List<Car> cars;
    public boolean place(Car car)
    {
        if (canMoveTo(car.getPosition()))
        {
            this.cars.add(car);
            return true;
        }
        else return false;
    }
    public boolean isOccupied(Position position) {
        for (int i = 0; i < this.cars.size(); i++) {
            Car actualCar = cars.get(i);
            if (position.equals(actualCar.getPosition()))
                return true;
        }
        return false;
    }
    public Object objectAt(Position position) {
        for (Car carIter : cars) {
            if (carIter.getPosition().equals(position))
                return carIter;
        }
        return null;
    }
    public void run(MoveDirection[] directions)  {
        for (int i = 0; i < directions.length; i++) {
            Car actualCar = cars.get(i % this.cars.size());
            actualCar.move(directions[i]);
            System.out.print(toString());
        }
    }

}

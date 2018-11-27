package agh.cs.lab6;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap
{

    protected LinkedHashMap<Position,IObject> hashObjects=new LinkedHashMap<>();
    protected List<Car>cars=new ArrayList<>();
    public boolean place(Car car)
    {
        if (canMoveTo(car.getPosition()))
        {
            this.hashObjects.put(car.getPosition(),car);
            this.cars.add(car);
            return true;
        }
        throw new IllegalArgumentException(car.getPosition().toString()+ " is already occupied");
    }
    public boolean isOccupied(Position position)
    {
        return objectAt(position)!=null;

    }
    public Object objectAt(Position position) {
        return hashObjects.get(position);
    }
    public void run(MoveDirection[] directions)
    {
        for (int i = 0; i < directions.length; i++) {
            Car actualCar = cars.get(i % this.cars.size());
            Position before=actualCar.getPosition();
            actualCar.move(directions[i]);
            Position after=actualCar.getPosition();
            if(!before.equals(after))
            {
                hashObjects.remove(before);
                hashObjects.put(after,actualCar);
            }
            System.out.print(toString());
        }
    }

}

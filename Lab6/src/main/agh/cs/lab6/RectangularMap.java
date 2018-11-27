package agh.cs.lab6;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int height;
    private List<Car> cars;


    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.cars = new ArrayList<>();
    }

    public boolean canMoveTo(Position position) {
        return (position.getX() >= 0 && position.getX() < this.width && position.getY() >= 0 && position.getY() < this.height && !isOccupied(position));
    }

    /*public boolean place(Car car) {
        if (canMoveTo(car.getPosition())) {
            this.cars.add(car);
            return true;
        } else return false;
    }
    */
    /*public void run(MoveDirection[] directions) throws InterruptedException {
        for (int i = 0; i < directions.length; i++) {
            Car actualCar = cars.get(i % this.cars.size());
            actualCar.move(directions[i]);
            System.out.print(toString());
        }
    }
    /*
    public boolean isOccupied(Position position) {
        for (int i = 0; i < this.cars.size(); i++) {
            Car actualCar = cars.get(i);
            if (position.equals(actualCar.getPosition()))
                return true;
        }
        return false;
    }
    */

    /*public Object objectAt(Position position) {
        for (int i = 0; i < this.cars.size(); i++) {
            Car actualCar = cars.get(i);
            if (actualCar.getPosition().equals(position))
                return actualCar;
        }
        return null;
    }
    */
    public int getWidth()
    {
        return this.width;
    }
    public int getHeight()
    {
        return this.height;
    }
    public String toString() {
        return new MapVisualizer(this).draw(new Position(0, 0), new Position(this.width - 1, this.height - 1));
    }
}

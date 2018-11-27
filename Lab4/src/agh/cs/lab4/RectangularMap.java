package agh.cs.lab4;
import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    private List<Car> cars;


    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.cars = new ArrayList<>();
    }
    Position low_limit=new Position(1,1);
    Position uplimit=new Position(this.width-1,this.height-1);
    //to na dole trzeba za pomocą funkcji z position smaller, larger, upperRight, loweLeft i tych punktów wyżej
    public boolean canMoveTo(Position position) {
        return (position.getX() >= 0 && position.getX() < this.width && position.getY() >= 0 && position.getY() < this.height && !isOccupied(position));
    }

    public boolean place(Car car) {
        if (canMoveTo(car.getPosition())) {
            this.cars.add(car);
            return true;
        } else return false;
    }

    public void run(MoveDirection[] directions) throws InterruptedException {
        for (int i = 0; i < directions.length; i++) {
            Car actualCar = cars.get(i % this.cars.size());
            actualCar.move(directions[i]);
            System.out.print(toString());
        }
    }

    public boolean isOccupied(Position position) {
        for (Car cariter:cars) {
            if (position.equals(cariter.getPosition()))
                return true;
        }
        return false;
    }

    public Object objectAt(Position position) {
        for (Car cariter: cars) {
            if (cariter.getPosition().equals(position))
                return cariter;
        }
        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(new Position(0, 0), new Position(this.width - 1, this.height - 1));
    }
}

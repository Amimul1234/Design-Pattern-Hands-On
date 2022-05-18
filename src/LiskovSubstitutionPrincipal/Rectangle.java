package LiskovSubstitutionPrincipal;

public class Rectangle {

    private int width, height;

    public int getWidth() {
        return width;
    }

    public void setWidth( int width ) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public int getAres() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Square extends Rectangle {

    public Square() {

    }

    public Square( int size ) {
        setWidth(size);
        setHeight(size);
    }

    @Override
    public void setWidth( int width ) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight( int height ) {
        super.setHeight(height);
        super.setWidth(height);
    }

    public boolean isSquare() {
        return getWidth() == getHeight();
    }

}

class Demo {
    static void useIt( Rectangle rectangle ) {
        int width = rectangle.getWidth();
        rectangle.setHeight(10);

        System.out.println(
                "Expected area of " + (width * 10) +
                        ", got " + rectangle.getAres()
        );
    }

    public static void main( String[] args ) {

        Rectangle rectangle = new Rectangle();

        rectangle.setWidth(2);
        rectangle.setHeight(3);

        useIt(rectangle);

        Rectangle square = new Square();

        square.setWidth(5);
        useIt(square);
    }
}

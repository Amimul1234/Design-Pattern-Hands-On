package OCP;

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {

    public String name;
    public Color color;
    public Size size;

    public Product( String name, Color color, Size size ) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

}

class ProductFilter {

    public Stream<Product> filterByColor( List<Product> productList, Color color ) {
        return productList.stream().filter(product -> product.color == color);
    }

    public Stream<Product> filterBySize( List<Product> productList, Size size ) {
        return productList.stream().filter(product -> product.size == size);
    }

    public Stream<Product> filterBySizeAndColor( List<Product> productList, Size size, Color color ) {
        return productList.stream().filter(product -> product.size == size && product.color == color);
    }

}

interface Specification<T> {
    boolean isSatisfied( T item );
}

interface Filter<T> {
    Stream<T> filter( List<T> items, Specification<T> spec );
}

class ColorSpecification implements Specification<Product> {

    public Color color;

    public ColorSpecification( Color color ) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied( Product item ) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product> {

    public Size size;

    public SizeSpecification( Size size ) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied( Product item ) {
        return item.size == size;
    }
}

class AndSpecification<T> implements Specification<T> {

    private Specification<T> first, second;

    public AndSpecification( Specification<T> first, Specification<T> second ) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied( T item ) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class BetterFilter implements Filter<Product> {
    @Override
    public Stream<Product> filter( List<Product> items, Specification<Product> spec ) {
        return items.stream().filter(spec::isSatisfied);
    }
}


public class Demo {

    public static void main( String[] args ) {

        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.HUGE);

        List<Product> productList = List.of(apple, tree, house);

        ProductFilter productFilter = new ProductFilter();
        System.out.println("Green products (old): ");

        productFilter.filterByColor(productList, Color.GREEN)
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        BetterFilter betterFilter = new BetterFilter();

        System.out.println();
        System.out.println("Green products (new): ");

        betterFilter.filter(productList, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("Large blue items: ");
        betterFilter.filter(productList, new AndSpecification<>(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.HUGE)
        )).forEach(p -> System.out.println(" - " + p.name + " is green"));
    }

}

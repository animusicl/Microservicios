package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

import static labs.pm.data.Rating.*;

public abstract class Product implements Rateable <Product> {

    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

    /*
    Product() { // constructor vacio con default values.
        this(0, "no name", BigDecimal.ZERO);
    }
    */


    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
    Product(int id, String name, BigDecimal price) {
        this(id, name,price, NOT_RATED);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    public LocalDate getBestBefore(){
        return LocalDate.now();
    }

    //public abstract Product applyRating(Rating newRating);

    @Override
    public String toString() {
        return id +
                " " + name +
                " " + price +
                " " + getDiscount() +
                " " + rating.getStars();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                price.equals(product.price) &&
                rating == product.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, rating);
    }
}

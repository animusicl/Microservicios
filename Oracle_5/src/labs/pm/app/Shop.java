package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Shop {

    public static void main(String[] args) {
        ProductManager pm = new ProductManager(Locale.UK);
        pm.createProduct(101,"Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        pm.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
        pm.reviewProduct(101, Rating.TWO_STAR, "Rather weak tea");
        pm.reviewProduct(101, Rating.FOUR_STAR, "Fine tea");
        pm.reviewProduct(101, Rating.FOUR_STAR, "Good tea");
        pm.reviewProduct(101, Rating.FIVE_STAR, "Perfect tea");
        pm.reviewProduct(101, Rating.THREE_STAR, "Just add some lemon");
        //pm.printProductReport(101);

        pm.createProduct(102, "Coffee", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        pm.reviewProduct(102, Rating.THREE_STAR, "Coffee was ok");
        pm.reviewProduct(102, Rating.ONE_STAR, "Where is the milk?!");
        pm.reviewProduct(102, Rating.FIVE_STAR, "It's perfect with ten spoons of sugar!");
        //pm.printProductReport(102);

        pm.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.NOT_RATED, LocalDate.now().plusDays(2));
        pm.reviewProduct(103, Rating.FIVE_STAR, "Very nice cake");
        pm.reviewProduct(103, Rating.FOUR_STAR, "It good, but I've expected more chocolate");
        pm.reviewProduct(103, Rating.FIVE_STAR, "This cake is perfect!");
        //pm.printProductReport(103);

        Product p4 = pm.createProduct(104, "Cookie", BigDecimal.valueOf(2.99), Rating.NOT_RATED, LocalDate.now());
        p4 = pm.reviewProduct(p4, Rating.THREE_STAR, "Just another cookie");
        p4 = pm.reviewProduct(p4, Rating.THREE_STAR, "Ok");
        //pm.printProductReport(p4);

        Product p5 = pm.createProduct(105, "Hot Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED);
        p5 = pm.reviewProduct(p5, Rating.FOUR_STAR, "Tasty!");
        p5 = pm.reviewProduct(p5, Rating.FOUR_STAR, "Not bad at all");
        //pm.printProductReport(p5);

        Product p6 = pm.createProduct(106, "Chocolate", BigDecimal.valueOf(2.50), Rating.NOT_RATED, LocalDate.now().plusDays(3));
        p6 = pm.reviewProduct(p6, Rating.TWO_STAR, "Too sweet!");
        p6 = pm.reviewProduct(p6, Rating.THREE_STAR, "Better then cookie");
        p6 = pm.reviewProduct(p6, Rating.TWO_STAR, "Too bitter");
        p6 = pm.reviewProduct(p6, Rating.ONE_STAR, "I don't get it!");
        //pm.printProductReport(p6);

        Comparator <Product> ratingSorter = (p1,p2) -> p2.getRating().ordinal() - p1.getRating().ordinal(); //de mayor a menor
        Comparator <Product> priceSorter = (p1, p2) -> p2.getPrice().compareTo(p1.getPrice());
        pm.printProducts(ratingSorter.thenComparing(priceSorter)); 



        // Product p2 = pm.createProduct(102,"Coffee", BigDecimal.valueOf(2.99), Rating.FOUR_STAR);
       // Product p3 = pm.createProduct(103,"Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        //Product p4 = pm.createProduct(104,"Cookie", BigDecimal.valueOf(0.99), Rating.TWO_STAR, LocalDate.now());
        //p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "Nice hot cup of tea");
        //pm.printProductReport();

        //System.out.printf("%d %s %s %s%n", p1.getId(), p1.getName(), p1.getPrice(), p1.getRating().getStars());
        //System.out.printf("%d %s %s %s%n", p2.getId(), p2.getName(), p2.getPrice(), p2.getRating().getStars());
        //System.out.printf("%d %s %s %s%n",p3.getId(), p3.getName(), p3.getPrice(), p3.getRating().getStars());
        //System.out.printf("%d %s %s %s%n",p4.getId(), p4.getName(), p4.getPrice(), p4.getRating().getStars());

        //System.out.println(p1.toString());
        //System.out.println(p2.toString());
        //System.out.println(p3.toString());
        //System.out.println(p4.toString());

    }
}

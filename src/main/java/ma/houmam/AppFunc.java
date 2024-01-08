package ma.houmam;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AppFunc {
    public static void main(String[] args) {
        Consumer<String> consumer = (input) -> {
            System.out.println(input);
        };
        consumer.accept("Hello");
        Supplier<String> supplier = () -> {
            return "qwertz";
        };
        System.out.println(supplier.get());
        Function<Integer, Integer> function = a -> {
            return a * 5;
        };
        System.out.println(function.apply(4));
        Predicate<String> predicate = a -> {
           return a.equals("qwertz");
        };
        System.out.println(predicate.test("qwertz"));

    }
}

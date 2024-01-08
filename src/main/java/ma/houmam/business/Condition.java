package ma.houmam.business;
@FunctionalInterface
public interface Condition<T> {
    boolean test(T t);
}

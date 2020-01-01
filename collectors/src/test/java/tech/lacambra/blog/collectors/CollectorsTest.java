package tech.lacambra.blog.collectors;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectorsTest {

  @Test
  void rangeTest() {

    Range r = Stream.of(1, 20, 3, 5, 9, -1).collect(new RangeCollector());

    assertEquals(new Range(-1, 20), r);
  }

  @Test
  void listTest() {

    Collection<Integer> r = Stream.of(1, 20, 3, 5, 9, -1).collect(new ListCollector<>());
    Collection<String> s = Stream.of(1, 20, 3, 5, 9, -1).map(String::valueOf).collect(new ListCollector<>());

    assertEquals(new Range(-1, 20), r);
  }
}

class ListCollector<T> implements Collector<T, List<T>, Collection<T>> {

  @Override
  public Supplier<List<T>> supplier() {
    return ArrayList::new;
  }

  @Override
  public BiConsumer<List<T>, T> accumulator() {
    return List::add;
  }

  @Override
  public BinaryOperator<List<T>> combiner() {
    return (l1, l2) -> {
      List<T> arr = new ArrayList<>(l1);
      arr.addAll(l2);
      return arr;
    };
  }

  @Override
  public Function<List<T>, Collection<T>> finisher() {
    return ArrayList::new;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }
}

class RangeCollector implements Collector<Integer, RangeAccumulator, Range> {

  @Override
  public Supplier<RangeAccumulator> supplier() {
    return RangeAccumulator::new;
  }

  @Override
  public BiConsumer<RangeAccumulator, Integer> accumulator() {
    return RangeAccumulator::add;
  }

  @Override
  public BinaryOperator<RangeAccumulator> combiner() {
    return RangeAccumulator::combine;
  }

  @Override
  public Function<RangeAccumulator, Range> finisher() {
    return r -> new Range(r.min, r.max);
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }
}

class Range {
  int max;
  int min;

  public Range(int max, int min) {
    this.max = max;
    this.min = min;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Range range = (Range) o;

    if (max != range.max) return false;
    return min == range.min;
  }

  @Override
  public int hashCode() {
    int result = max;
    result = 31 * result + min;
    return result;
  }

  @Override
  public String toString() {
    return "Range{" +
        "max=" + max +
        ", min=" + min +
        '}';
  }
}

class RangeAccumulator {

  int max = Integer.MIN_VALUE;
  int min = Integer.MAX_VALUE;

  public void add(Integer i) {

    if (max < i) {
      max = i;
    }

    if (min > i) {
      min = i;
    }

  }

  public RangeAccumulator combine(RangeAccumulator acc) {

    add(acc.max);
    add(acc.min);

    return this;

  }
}
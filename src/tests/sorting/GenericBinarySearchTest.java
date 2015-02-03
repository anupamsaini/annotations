package tests.sorting;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import code.sorting.GenericBinarySearch;

import java.util.LinkedList;
import java.util.List;

/**
 * Unit tests for {@link GenericBinarySearch}.
 *
 * @author Anupam Saini
 */
public class GenericBinarySearchTest {

  private List<Person> persons;
  private GenericBinarySearch<Person> instanceUnderTest;

  static class Person implements Comparable<Person> {
    private final int id;

    Person(int id) {
      this.id = id;
    }

    @Override
    public int compareTo(Person other) {
      if (this.id > other.id) {
        return 1;
      } else if (this.id < other.id) {
        return -1;
      }
      return 0;
    }

  }

  @Before
  public void setUp() {
    persons = new LinkedList<Person>();
    instanceUnderTest = new GenericBinarySearch<Person>();
  }

  @Test
  public void emptyInput_doesNotFindValue_passes() {
    assertEquals(-1, instanceUnderTest.search(persons, new Person(121)));
  }

  @Test
  public void singleValueInput_searchesSuccessfully() {
    persons.add(new Person(1));
    assertEquals(0, instanceUnderTest.search(persons, new Person(1)));
  }

  @Test
  public void singleValueInput_doesNotFindValue() {
    persons.add(new Person(10101));
    assertEquals(-1, instanceUnderTest.search(persons, new Person(1)));
  }

  @Test
  public void arraySizedTwoInput_searchesSuccessfully() {
    persons.add(new Person(99));
    persons.add(new Person(1011));
    assertEquals(0, instanceUnderTest.search(persons, new Person(99)));
  }

  @Test
  public void arraySizedTwoInput_doesNotFindValue() {
    persons.add(new Person(99));
    persons.add(new Person(1011));
    assertEquals(-1, instanceUnderTest.search(persons, new Person(100)));
  }

  @Test
  public void arraySizedThreeInput_searchesSuccessfully() {
    addElementsToList(99,1011,34987);
    assertEquals(1, instanceUnderTest.search(persons, new Person(1011)));
  }

  @Test
  public void arraySizedThreeInput_doesNotFindValue() {
    addElementsToList(99,1011,34987);
    assertEquals(-1, instanceUnderTest.search(persons, new Person(19)));
  }

  @Test
  public void valueLessThanSmallestValueInArray_doesNotFindValue() {
    addElementsToList(4, 5, 6, 7, 78, 89, 90, 1091, 3458, 54545, 478236, 287236);
    assertEquals(-1, instanceUnderTest.search(persons, new Person(1)));
  }

  @Test
  public void valueGreaterThanLargestValueInArray_doesNotFindValue() {
    addElementsToList(4, 5, 6, 7, 78, 89, 90, 1091, 3458, 54545, 478236, 287236);
    assertEquals(-1, instanceUnderTest.search(persons, new Person(999999999)));
  }

  @Test
  public void allNegativeValuesInArray_findsValue() {
    addElementsToList(-8,-7,-6, -5, -4, -3, -2, -1, 0);
    assertEquals(7, instanceUnderTest.search(persons, new Person(-1)));
  }

  @Test
  public void allValuesEqualInArray_returnsIndexOfFirstMatchedValue() {
    addElementsToList(4, 4, 4, 4, 4, 4);
    assertEquals(2, instanceUnderTest.search(persons, new Person(4)));
  }

  @Test
  public void iteratesAllValuesInArray_returnsIndexOfAllValuesInArray() {
    addElementsToList(23, 47,52,129,141,171,186,205,264,295,320,343,353,484,496,785,793,826,833,981);
    for (int i = 0; i < persons.size(); i++) {
      assertEquals(i, instanceUnderTest.search(persons, persons.get(i)));
    }
  }

  private void addElementsToList(int ... input) {
    for(int value: input) {
      persons.add(new Person(value));
    }
  }
}

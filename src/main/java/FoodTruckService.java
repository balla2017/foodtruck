import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Stores our truck objects and prints them to the user. Encapsulates a child class for the
 * individual foodtruck objects, which implements comparator so that we can easily alphabetize them
 * and insert them in the proper place in the truckList using Java's Collection.binarySearch.
 */
public class FoodTruckService {
  List<FoodTruck> truckList = new ArrayList<>();
  int listIndex = 0;

  /**
   * Build a new food truck and insert to the the list
   *
   * @param name
   * @param address
   */
  public void truckBuilder(String name, String address) {
    FoodTruck foodTruck = new FoodTruck(name, address);
    insertTruck(foodTruck);
  }

  /**
   * insert the new one to existed truck list according to alphabetical order
   *
   * @param newTruck
   */
  public void insertTruck(FoodTruck newTruck) {
    int index = Collections.binarySearch(truckList, newTruck);
    if (index < 0) {
      index = (index * -1) - 1;
    }

    truckList.add(index, newTruck);
  }

  /** print 10 items at one time after that re-get the user input to decide to continue or not */
  public void printList() {
    System.out.println("NAME  |  ADDRESS");
    for (int i = listIndex; i < listIndex + 10 && i < truckList.size(); i++) {
      FoodTruck truck = truckList.get(i);
      System.out.println(truck.getName() + " | " + truck.getAddress());
    }
    listIndex += 10;

    // check if we has print it out.
    if (listIndex >= truckList.size() - 1) {
      System.out.println("\nYou've reached the end of the list. Exiting...");
    } else {
      getUserInput();
    }
  }

  /** get user's input, "exit" to exit, or continue. */
  public void getUserInput() {
    System.out.println("Hit enter to see more results or exit to end program.");
    Scanner in = new Scanner(System.in);
    String userInput = in.nextLine();
    if (!userInput.equalsIgnoreCase("exit")) {
      printList();
    } else {
      System.out.println("Exiting...");
    }
  }
}

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FoodTruck implements Comparable<FoodTruck> {
  private String address;
  private String name;

  /**
   * Implementing comparator here so that we can sort trucks alphabetically based on their name
   * value. (Instead I could have used Socrata's Order by soSQL option and then response list would
   * come back alphabetized, realized this while reading doc on soSQL at the last minute.)
   */
  @Override
  public int compareTo(FoodTruck otherTruck) {
    return this.getName().compareToIgnoreCase(otherTruck.getName());
  }
}

package com.gvraams.seat_allocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gvraams.seat_allocator.abstract_classes.TypeBasedArrangement;
import com.gvraams.seat_allocator.abstract_classes.TypewiseSeatPopulator;

public class EntryPoint {
  public static List<SeatGroup> seatingArrangement(int[][] seats, int noOfPassengers) {
    List<SeatGroup> seatGroups = new ArrayList<>();
    int numberOfGroups = seats.length;

    for (int index = 0; index < numberOfGroups; index++) {
      int[] seatDetails = seats[index];
      int columns = seatDetails[0];
      int rows = seatDetails[1];
      boolean isFirst = index == 0;
      boolean isLast = index == numberOfGroups - 1;

      SeatGroup seatGroup = new SeatGroup(columns, rows, isFirst, isLast);
      seatGroups.add(seatGroup);
    }

    TypewiseSeatPopulator populator = new LeftToRightSeatPopulator(seatGroups);
    populator.populateSeats();

    TypeBasedArrangement arrangement = new AisleFirstArrangement(noOfPassengers, populator);
    arrangement.arrange();

    return populator.getSeatGroups();
  }

/**
 * Sample input
 *
1
4
3 2
4 3
2 3
3 4
30
 */
  public static void main(String[] args) {
    List<List<SeatGroup>> results = new ArrayList<>();

    try (Scanner scanner = new Scanner(System.in)) {
      int numberOfTestCases = Integer.parseInt(scanner.nextLine());

      for (int tc = 0; tc < numberOfTestCases; tc++) {
        // Get seat groups' information
        int totalGroups = Integer.parseInt(scanner.nextLine());
        int[][] seats = new int[totalGroups][2];

        for (int i = 0; i < totalGroups; i++) {
          String string = scanner.nextLine();
          String[] arr = string.split(" ");
          int columns = Integer.parseInt(arr[0]);
          int rows = Integer.parseInt(arr[1]);
          seats[i] = new int[] { columns, rows };
        }

        // Get total number of passengers
        int numberOfPassengers = scanner.nextInt();

        // Solve the problem and add to result
        results.add(seatingArrangement(seats, numberOfPassengers));
      }
    } catch (Exception e) {
      // Do nothing
    } finally {
      for (List<SeatGroup> result : results) {
        System.out.println(result);
      }
    }
  }
}

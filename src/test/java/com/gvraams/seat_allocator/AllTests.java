package com.gvraams.seat_allocator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gvraams.seat_allocator.abstract_classes.TypeBasedArrangement;
import com.gvraams.seat_allocator.abstract_classes.TypewiseSeatPopulator;
import com.gvraams.seat_allocator.interfaces.SeatTypes;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
  public Seat seatFactory(String type, int r, int c, int occupiedBy) {
    Seat seat = new Seat(type, 0, 0);
    seat.setOccupiedBy(String.valueOf(occupiedBy));
    return seat;
  }

  @Test
  public void testOne() {
    List<SeatGroup> seatGroups = new ArrayList<>();
    SeatGroup seatGroup = new SeatGroup(5, 5, true, true);
    seatGroups.add(seatGroup);

    TypewiseSeatPopulator populator = new LeftToRightSeatPopulator(seatGroups);
    populator.populateSeats();

    TypeBasedArrangement arrangement = new AisleFirstArrangement(30, populator);
    arrangement.arrange();

    Seat seat00 = seatFactory(SeatTypes.WINDOW, 0, 0, 1);
    Seat seat01 = seatFactory(SeatTypes.MIDDLE, 0, 1, 11);
    Seat seat02 = seatFactory(SeatTypes.MIDDLE, 0, 2, 12);
    Seat seat03 = seatFactory(SeatTypes.MIDDLE, 0, 3, 13);
    Seat seat04 = seatFactory(SeatTypes.WINDOW, 0, 4, 2);

    List<Seat> seatGroup0 = new ArrayList<>(
      Arrays.asList(
        seat00, seat01, seat02, seat03, seat04
      )
    );


    Seat seat10 = seatFactory(SeatTypes.WINDOW, 1, 0, 3);
    Seat seat11 = seatFactory(SeatTypes.MIDDLE, 1, 1, 14);
    Seat seat12 = seatFactory(SeatTypes.MIDDLE, 1, 2, 15);
    Seat seat13 = seatFactory(SeatTypes.MIDDLE, 1, 3, 16);
    Seat seat14 = seatFactory(SeatTypes.WINDOW, 1, 4, 4);

    List<Seat> seatGroup1 = new ArrayList<>(
      Arrays.asList(
        seat10, seat11, seat12, seat13, seat14
      )
    );

    Seat seat20 = seatFactory(SeatTypes.WINDOW, 2, 0, 5);
    Seat seat21 = seatFactory(SeatTypes.MIDDLE, 2, 1, 17);
    Seat seat22 = seatFactory(SeatTypes.MIDDLE, 2, 2, 18);
    Seat seat23 = seatFactory(SeatTypes.MIDDLE, 2, 3, 19);
    Seat seat24 = seatFactory(SeatTypes.WINDOW, 2, 4, 6);

    List<Seat> seatGroup2 = new ArrayList<>(
      Arrays.asList(
        seat20, seat21, seat22, seat23, seat24
      )
    );

    Seat seat30 = seatFactory(SeatTypes.WINDOW, 3, 0, 7);
    Seat seat31 = seatFactory(SeatTypes.MIDDLE, 3, 1, 20);
    Seat seat32 = seatFactory(SeatTypes.MIDDLE, 3, 2, 21);
    Seat seat33 = seatFactory(SeatTypes.MIDDLE, 3, 3, 22);
    Seat seat34 = seatFactory(SeatTypes.WINDOW, 3, 4, 8);

    List<Seat> seatGroup3 = new ArrayList<>(
      Arrays.asList(
        seat30, seat31, seat32, seat33, seat34
      )
    );

    Seat seat40 = seatFactory(SeatTypes.WINDOW, 4, 0, 9);
    Seat seat41 = seatFactory(SeatTypes.MIDDLE, 4, 1, 23);
    Seat seat42 = seatFactory(SeatTypes.MIDDLE, 4, 2, 24);
    Seat seat43 = seatFactory(SeatTypes.MIDDLE, 4, 3, 25);
    Seat seat44 = seatFactory(SeatTypes.WINDOW, 4, 4, 10);

    List<Seat> seatGroup4 = new ArrayList<>(
      Arrays.asList(
        seat40, seat41, seat42, seat43, seat44
      )
    );

    List<List<Seat>> seatGroupsList = new ArrayList<>(
      Arrays.asList(
        seatGroup0, seatGroup1, seatGroup2, seatGroup3, seatGroup4
      )
    );

    assertEquals(populator.getSeatGroups().get(0).getRowsOfSeats().toString(), seatGroupsList.toString());
  }
}

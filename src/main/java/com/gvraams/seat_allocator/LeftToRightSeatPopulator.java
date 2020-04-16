package com.gvraams.seat_allocator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.gvraams.seat_allocator.abstract_classes.TypewiseSeatPopulator;

public class LeftToRightSeatPopulator extends TypewiseSeatPopulator {
  public LeftToRightSeatPopulator(List<SeatGroup> seatGroups) {
    super(seatGroups);
  }

  public int getMaxRows(List<SeatGroup> seatGroups) {
    int maxRows = -1;

    for (SeatGroup group : seatGroups) {
      int count = group.getRowCount();

      if (maxRows < count) {
        maxRows = count;
      }
    }

    return maxRows;
  }

  @Override
  public void populateSeats() {
    List<String> types = SeatAllocatorUtil.getAllTypes();
    List<SeatGroup> seatGroups = getSeatGroups();
    int maxRows = getMaxRows(getSeatGroups());

    for (String type : types) {
      int currentRow = 0;
      int seatGroupIdx = 0;

      while (currentRow < maxRows) {
        for(seatGroupIdx = 0; seatGroupIdx < seatGroups.size(); seatGroupIdx++) {
          SeatGroup seatGroup = seatGroups.get(seatGroupIdx);
          List<List<Seat>> rowOfSeats = seatGroup.getRowsOfSeats();

          if (currentRow >= rowOfSeats.size()) {
            continue;
          }

          List<Seat> oneRowOfSeats = rowOfSeats.get(currentRow);
          Predicate<Seat> seats = seat -> seat.getType() == type;

          List<Seat> matchingSeats = oneRowOfSeats.stream().filter(seats).
            collect(Collectors.toList());

          for (Seat seat : matchingSeats) {
            List<Integer> seatValue = new ArrayList<>(
              Arrays.asList(
                seatGroupIdx, seat.getRowIndex(), seat.getColumnIndex()
              )
            );
            addSeat(type, seatValue);
          }
        }
        currentRow += 1;
      }
    }
  }
}

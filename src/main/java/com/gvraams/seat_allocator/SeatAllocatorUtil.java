package com.gvraams.seat_allocator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gvraams.seat_allocator.interfaces.SeatTypes;

public class SeatAllocatorUtil {
  public static List<String> getAllTypes() {
    return new ArrayList<>(
      Arrays.asList(
          SeatTypes.AISLE, SeatTypes.WINDOW, SeatTypes.MIDDLE
      )
    );
  }
}

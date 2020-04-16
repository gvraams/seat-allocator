package com.gvraams.seat_allocator;

import java.util.ArrayList;
import java.util.Arrays;

import com.gvraams.seat_allocator.abstract_classes.TypeBasedArrangement;
import com.gvraams.seat_allocator.abstract_classes.TypewiseSeatPopulator;
import com.gvraams.seat_allocator.interfaces.SeatTypes;

public class AisleFirstArrangement extends TypeBasedArrangement {
  public AisleFirstArrangement(int numberOfPassengers, TypewiseSeatPopulator populator) {
    super(numberOfPassengers, populator);
    setOrder();
  }

  @Override
  public void setOrder() {
    m_order = new ArrayList<>(
      Arrays.asList(
        SeatTypes.AISLE, SeatTypes.WINDOW, SeatTypes.MIDDLE
      )
    );
  }
}

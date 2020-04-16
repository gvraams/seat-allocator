package com.gvraams.seat_allocator.abstract_classes;

import java.util.List;

import com.gvraams.seat_allocator.Seat;
import com.gvraams.seat_allocator.SeatAllocatorUtil;
import com.gvraams.seat_allocator.SeatGroup;

public abstract class TypeBasedArrangement {
  public List<String> m_order;

  final int m_numberOfPassengers;
  int m_seatTypeIndex;
  int m_indexOfSeatDetails;
  boolean m_exhausted;
  final TypewiseSeatPopulator m_populator;

  public void setCurrentSeatType(int index) {
    m_seatTypeIndex = index;
  }

  public int getSeatTypeIndex() {
    return m_seatTypeIndex;
  }

  public void setIndexOfSeatDetails(int index) {
    m_indexOfSeatDetails = index;
  }

  public int getIndexOfSeatDetails() {
    return m_indexOfSeatDetails;
  }

  public int getNumberOfPassengers() {
    return m_numberOfPassengers;
  }

  public List<String> getOrder() {
    return m_order;
  }

  public void setExhausted(boolean exhausted) {
    m_exhausted = exhausted;
  }

  public boolean isExhausted() {
    return m_exhausted;
  }

  public TypeBasedArrangement(int numberOfPassengers, TypewiseSeatPopulator populator) {
    m_numberOfPassengers = numberOfPassengers;
    m_exhausted = false;
    m_seatTypeIndex = 0;
    m_indexOfSeatDetails = 0;
    m_populator = populator;
  }

  public abstract void setOrder();

  public void updateSeat(List<Integer> seatDetails, int passengerId) {
    int groupIndex = seatDetails.get(0);
    int seatRowIndex = seatDetails.get(1);
    int seatColumnIndex = seatDetails.get(2);

    SeatGroup seatGroup = m_populator.getSeatGroups().get(groupIndex);
    Seat seat = seatGroup.getRowsOfSeats().get(seatRowIndex).get(seatColumnIndex);
    seat.setOccupiedBy(String.valueOf(passengerId));
  }

  public void arrange() {
    int iterations = 1;
    List<String> seatTypes = SeatAllocatorUtil.getAllTypes();

    while (iterations <= m_numberOfPassengers) {
      if (m_seatTypeIndex > seatTypes.size() - 1) {
        break;
      }

      try {
        String seatType = seatTypes.get(m_seatTypeIndex);
        List<Integer> seatDetails = m_populator.getSeatDetails(seatType, m_indexOfSeatDetails);
        updateSeat(seatDetails, iterations);
        m_indexOfSeatDetails += 1;
        iterations += 1;
      } catch (Exception e) {
        m_indexOfSeatDetails = 0;
        m_seatTypeIndex += 1;
      }
    }
  }
}

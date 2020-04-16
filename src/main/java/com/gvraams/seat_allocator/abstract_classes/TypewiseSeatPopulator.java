package com.gvraams.seat_allocator.abstract_classes;

import java.util.ArrayList;
import java.util.List;

import com.gvraams.seat_allocator.SeatGroup;
import com.gvraams.seat_allocator.interfaces.SeatTypes;

public abstract class TypewiseSeatPopulator {
  List<List<Integer>> m_aisleSeats, m_windowSeats, m_middleSeats;

  public List<SeatGroup> m_seatGroups;

  public List<List<Integer>> getAisleSeats() {
    return m_aisleSeats;
  }

  public List<List<Integer>> getWindowSeats() {
    return m_windowSeats;
  }

  public List<List<Integer>> getMiddleSeats() {
    return m_middleSeats;
  }

  public List<SeatGroup> getSeatGroups() {
    return m_seatGroups;
  }

  public TypewiseSeatPopulator(List<SeatGroup> seatGroups) {
    m_seatGroups = seatGroups;
    m_aisleSeats = new ArrayList<>();
    m_windowSeats = new ArrayList<>();
    m_middleSeats = new ArrayList<>();
  }

  public void addSeat(String type, List<Integer> value) {
    if (type == SeatTypes.AISLE) {
      m_aisleSeats.add(value);
    } else if (type == SeatTypes.WINDOW) {
      m_windowSeats.add(value);
    } else {
      m_middleSeats.add(value);
    }
  }

  public List<Integer> getSeatDetails(String type, int index) {
    if (type == SeatTypes.AISLE) {
      return m_aisleSeats.get(index);
    } else if (type == SeatTypes.WINDOW) {
      return m_windowSeats.get(index);
    } else {
      return m_middleSeats.get(index);
    }
  }

  public abstract void populateSeats();
}

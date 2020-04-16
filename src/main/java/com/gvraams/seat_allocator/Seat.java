package com.gvraams.seat_allocator;

import com.gvraams.seat_allocator.interfaces.SeatTypes;

public class Seat {
  final String m_type;
  final int m_rowIndex, m_columnIndex;
  String m_occupiedBy;

  public String getType() {
    return m_type;
  }

  public String getOccupiedBy() {
    return m_occupiedBy;
  }

  public void setOccupiedBy(String occupiedBy) {
    m_occupiedBy = occupiedBy;
  }

  public int getRowIndex() {
    return m_rowIndex;
  }

  public int getColumnIndex() {
    return m_columnIndex;
  }

  public Seat(String type, int rowIndex, int colIndex) {
    m_type        = type;
    m_rowIndex    = rowIndex;
    m_columnIndex = colIndex;
    m_occupiedBy  = "--";
  }

  @Override
  public String toString() {
    String prefix = null;

    switch (m_type) {
      case SeatTypes.AISLE: {
        prefix = "A";
        break;
      }
      case SeatTypes.WINDOW: {
        prefix = "W";
        break;
      }
      default: {
        prefix = "M";
        break;
      }
    }

    return prefix + m_occupiedBy;
  }
}

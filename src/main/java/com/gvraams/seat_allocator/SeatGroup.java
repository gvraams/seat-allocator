package com.gvraams.seat_allocator;

import java.util.ArrayList;
import java.util.List;

import com.gvraams.seat_allocator.interfaces.SeatTypes;

public class SeatGroup {
  final int m_rows, m_columns;
  final boolean m_isFirst, m_isLast;
  List<List<Seat>> m_rowsOfSeats;

  public int getRowCount() {
    return m_rows;
  }

  public int getColumnCount() {
    return m_columns;
  }

  public boolean isFirst() {
    return m_isFirst;
  }

  public boolean isLast() {
    return m_isLast;
  }

  public List<List<Seat>> getRowsOfSeats() {
    return m_rowsOfSeats;
  }

  public SeatGroup(int columns, int rows, boolean isFirst, boolean isLast) {
    m_rows = rows;
    m_columns = columns;
    m_isFirst = isFirst;
    m_isLast = isLast;
    createSeats();
  }

  public void createSeats() {
    m_rowsOfSeats = new ArrayList<>();

    for (int row_idx = 0; row_idx < m_rows; row_idx++) {
      List<Seat> oneRowOfSeats = new ArrayList<Seat>();
      for (int col_idx = 0; col_idx < m_columns; col_idx++) {
        String type = null;

        if (col_idx == 0 || col_idx == m_columns - 1) {
          if (col_idx == 0) {
            type = m_isFirst ? SeatTypes.WINDOW : SeatTypes.AISLE;
          }
          if (col_idx == m_columns - 1) {
            type = m_isLast ? SeatTypes.WINDOW : SeatTypes.AISLE;
          } 
        } else {
          type = SeatTypes.MIDDLE;
        }

        Seat seat = new Seat(type, row_idx, col_idx);
        oneRowOfSeats.add(seat);
      }

      m_rowsOfSeats.add(oneRowOfSeats);
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (List<Seat> rowOfSeats : m_rowsOfSeats) {
      stringBuilder.append("\n    " + rowOfSeats.toString());
    }
    stringBuilder.append("\n");
    return stringBuilder.toString();
  }
}

package com.railway.ticket.management.system.domain;

import com.railway.ticket.management.system.domain.enums.TicketStatus;
import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private int userId;
    private int scheduleId;
    private int coachId;
    private String seatNumber;
    private LocalDateTime bookingTime;
    private float totalAmount;
    private TicketStatus status;
    private LocalDateTime actualReturnTimestamp;
    private float refundAmount;

    public Ticket(int id, int userId, int scheduleId, int coachId, String seatNumber,
                  LocalDateTime bookingTime, float totalAmount, TicketStatus status,
                  LocalDateTime actualReturnTimestamp, float refundAmount) {
        setId(id);
        setUserId(userId);
        setScheduleId(scheduleId);
        setCoachId(coachId);
        setSeatNumber(seatNumber);
        setBookingTime(bookingTime);
        setTotalAmount(totalAmount);
        setStatus(status);
        setActualReturnTimestamp(actualReturnTimestamp);
        setRefundAmount(refundAmount);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    public int getCoachId() { return coachId; }
    public void setCoachId(int coachId) { this.coachId = coachId; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    public LocalDateTime getActualReturnTimestamp() { return actualReturnTimestamp; }
    public void setActualReturnTimestamp(LocalDateTime actualReturnTimestamp) { this.actualReturnTimestamp = actualReturnTimestamp; }
    public float getRefundAmount() { return refundAmount; }
    public void setRefundAmount(float refundAmount) { this.refundAmount = refundAmount; }
}

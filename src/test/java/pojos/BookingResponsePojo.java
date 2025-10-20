package pojos;

public class BookingResponsePojo {

    private Integer bookingid;
    private   BookingPojo booking;


    public Integer getBookingid() {
        return bookingid;
    }


    public BookingPojo getBooking() {
        return booking;
    }


    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}

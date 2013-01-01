package net.mklew.hotelms.inhouse.web.dto;

import net.mklew.hotelms.domain.booking.reservation.Reservation;
import net.mklew.hotelms.inhouse.web.dto.dates.DateParser;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 12/24/12
 *        time 3:48 PM
 */
@XmlRootElement(name = "reservationdto")
public class ReservationDto
{
    private String reservationId;
    private String reservationType;
    private String checkin;
    private String checkout;
    private String numberOfAdults;
    private String numberOfChildren;
    private String roomType;
    private String roomName;
    private String roomExtraBed;
    private String rateType;
    private String reservationStatus;

    @JsonIgnore
    private transient DateTime checkinDate;
    @JsonIgnore
    private transient DateTime checkoutDate;

    public static ReservationDto fromReservationForm(MultivaluedMap<String, String> formParams)
    {
        ReservationDto dto = new ReservationDto();
        // required
        dto.reservationType = formParams.getFirst("reservationType");
        dto.checkin = formParams.getFirst("checkin");
        dto.checkout = formParams.getFirst("checkout");
        dto.numberOfAdults = formParams.getFirst("numberOfAdults");
        dto.numberOfChildren = formParams.getFirst("numberOfChildren");
        dto.roomType = formParams.getFirst("roomType");
        dto.roomName = formParams.getFirst("roomName");
        dto.roomExtraBed = formParams.getFirst("roomExtraBed");
        dto.rateType = formParams.getFirst("rateType");
        // optional
        // none so far
        dto.checkinDate = DateParser.fromString(formParams.getFirst("checkin"));
        dto.checkoutDate = DateParser.fromString(formParams.getFirst("checkout"));

        return dto;
    }

    public static ReservationDto fromReservation(Reservation reservation)
    {
        ReservationDto dto = new ReservationDto();

        dto.reservationId = reservation.getReservationId().getPrintableId();
        dto.reservationType = reservation.getReservationType().getName();
        dto.checkin = DateParser.fromDate(reservation.getCheckIn());
        dto.checkout = DateParser.fromDate(reservation.getCheckOut());
        dto.numberOfAdults = String.valueOf(reservation.getNumberOfAdults());
        dto.numberOfChildren = String.valueOf(reservation.getNumberOfChildren());
        dto.roomType = reservation.getRoom().roomTypeName();
        dto.roomName = reservation.getRoom().fullRoomName();
        dto.roomExtraBed = String.valueOf(reservation.getExtraBeds());
        // TODO refactor rateType into rateName, changes in markup are needed
        dto.rateType = reservation.getRate().getRateName();
        dto.reservationStatus = reservation.getReservationStatus().getName();

        return dto;
    }

    public String getReservationType()
    {
        return reservationType;
    }


    public String getCheckin()
    {
        return checkin;
    }

    public String getCheckout()
    {
        return checkout;
    }

    public String getNumberOfAdults()
    {
        return numberOfAdults;
    }

    public String getNumberOfChildren()
    {
        return numberOfChildren;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public String getRoomExtraBed()
    {
        return roomExtraBed;
    }

    public String getRateType()
    {
        return rateType;
    }

    public DateTime getCheckinDate()
    {
        return checkinDate;
    }

    public DateTime getCheckoutDate()
    {
        return checkoutDate;
    }

    public String getReservationId()
    {
        return reservationId;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public String getReservationStatus()
    {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus)
    {
        this.reservationStatus = reservationStatus;
    }

    public void validateRequired()
    {
        notEmpty(reservationType);
        notEmpty(checkin);
        notEmpty(checkout);
        notEmpty(numberOfAdults);
        notEmpty(numberOfChildren);
        notEmpty(roomType);
        notEmpty(roomName);
        notEmpty(roomExtraBed);
        notEmpty(rateType);
    }

    private static void notEmpty(String string)
    {
        if (string == null || "".equals(string))
        {
            throw new IllegalArgumentException("Parameter cannot be null nor empty");
        }
    }

    public void init()
    {
        checkinDate = DateParser.fromString(checkin);
        checkoutDate = DateParser.fromString(checkout);
    }

    @JsonAnySetter
    public void handleUnknown(String key, Object value)
    {
        // ignore
    }
}

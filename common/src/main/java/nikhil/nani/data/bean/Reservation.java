package nikhil.nani.data.bean;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Reservation implements ReconRecord
{
    private static final String TRUE = "true";

    private String firstName;
    private String lastName;
    private String destination;
    private TransportType transportType;
    private String travelingFrom;
    private int numberOfPeopleInReservation;
    private boolean airlineReservation;
    private String airlineName;
    private boolean hotelReservation;
    private String hotelName;
    private boolean carReservation;
    private String carRental;
    private String promoCode;
    private PaymentType paymentType;
    @JsonIgnore
    private ReservationKey key;

    public Reservation()
    {
    }

    public Reservation(String firstName, String lastName, String destination, TransportType transportType, String travelingFrom, int numberOfPeopleInReservation, boolean airlineReservation, String airlineName, boolean hotelReservation, String hotelName, boolean carReservation, String carRental, String promoCode, PaymentType paymentType)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.destination = destination;
        this.transportType = transportType;
        this.travelingFrom = travelingFrom;
        this.numberOfPeopleInReservation = numberOfPeopleInReservation;
        this.airlineReservation = airlineReservation;
        this.airlineName = airlineName;
        this.hotelReservation = hotelReservation;
        this.hotelName = hotelName;
        this.carReservation = carReservation;
        this.carRental = carRental;
        this.promoCode = promoCode;
        this.paymentType = paymentType;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDestination()
    {
        return this.destination;
    }

    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    public TransportType getTransportType()
    {
        return this.transportType;
    }

    public void setTransportType(TransportType transportType)
    {
        this.transportType = transportType;
    }

    public String getTravelingFrom()
    {
        return this.travelingFrom;
    }

    public void setTravelingFrom(String travelingFrom)
    {
        this.travelingFrom = travelingFrom;
    }

    public int getNumberOfPeopleInReservation()
    {
        return this.numberOfPeopleInReservation;
    }

    public void setNumberOfPeopleInReservation(int numberOfPeopleInReservation)
    {
        this.numberOfPeopleInReservation = numberOfPeopleInReservation;
    }

    public boolean isAirlineReservation()
    {
        return this.airlineReservation;
    }

    public void setAirlineReservation(boolean airlineReservation)
    {
        this.airlineReservation = airlineReservation;
    }

    public String getAirlineName()
    {
        return this.airlineName;
    }

    public void setAirlineName(String airlineName)
    {
        this.airlineName = airlineName;
    }

    public boolean isHotelReservation()
    {
        return this.hotelReservation;
    }

    public void setHotelReservation(boolean hotelReservation)
    {
        this.hotelReservation = hotelReservation;
    }

    public String getHotelName()
    {
        return this.hotelName;
    }

    public void setHotelName(String hotelName)
    {
        this.hotelName = hotelName;
    }

    public boolean isCarReservation()
    {
        return this.carReservation;
    }

    public void setCarReservation(boolean carReservation)
    {
        this.carReservation = carReservation;
    }

    public String getCarRental()
    {
        return this.carRental;
    }

    public void setCarRental(String carRental)
    {
        this.carRental = carRental;
    }

    public String getPromoCode()
    {
        return this.promoCode;
    }

    public void setPromoCode(String promoCode)
    {
        this.promoCode = promoCode;
    }

    public PaymentType getPaymentType()
    {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType paymentType)
    {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }

        Reservation that = (Reservation) o;

        if (this.numberOfPeopleInReservation != that.numberOfPeopleInReservation)
        {
            return false;
        }
        if (this.airlineReservation != that.airlineReservation)
        {
            return false;
        }
        if (this.hotelReservation != that.hotelReservation)
        {
            return false;
        }
        if (this.carReservation != that.carReservation)
        {
            return false;
        }
        if (this.firstName != null ? !this.firstName.equals(that.firstName) : that.firstName != null)
        {
            return false;
        }
        if (this.lastName != null ? !this.lastName.equals(that.lastName) : that.lastName != null)
        {
            return false;
        }
        if (this.destination != null ? !this.destination.equals(that.destination) : that.destination != null)
        {
            return false;
        }
        if (this.transportType != that.transportType)
        {
            return false;
        }
        if (this.travelingFrom != null ? !this.travelingFrom.equals(that.travelingFrom) : that.travelingFrom != null)
        {
            return false;
        }
        if (this.airlineName != null ? !this.airlineName.equals(that.airlineName) : that.airlineName != null)
        {
            return false;
        }
        if (this.hotelName != null ? !this.hotelName.equals(that.hotelName) : that.hotelName != null)
        {
            return false;
        }
        if (this.carRental != null ? !this.carRental.equals(that.carRental) : that.carRental != null)
        {
            return false;
        }
        if (this.promoCode != null ? !this.promoCode.equals(that.promoCode) : that.promoCode != null)
        {
            return false;
        }
        return this.paymentType == that.paymentType;
    }

    @Override
    public int hashCode()
    {
        int result = this.firstName != null ? this.firstName.hashCode() : 0;
        result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
        result = 31 * result + (this.destination != null ? this.destination.hashCode() : 0);
        result = 31 * result + (this.transportType != null ? this.transportType.hashCode() : 0);
        result = 31 * result + (this.travelingFrom != null ? this.travelingFrom.hashCode() : 0);
        result = 31 * result + this.numberOfPeopleInReservation;
        result = 31 * result + (this.airlineReservation ? 1 : 0);
        result = 31 * result + (this.airlineName != null ? this.airlineName.hashCode() : 0);
        result = 31 * result + (this.hotelReservation ? 1 : 0);
        result = 31 * result + (this.hotelName != null ? this.hotelName.hashCode() : 0);
        result = 31 * result + (this.carReservation ? 1 : 0);
        result = 31 * result + (this.carRental != null ? this.carRental.hashCode() : 0);
        result = 31 * result + (this.promoCode != null ? this.promoCode.hashCode() : 0);
        result = 31 * result + (this.paymentType != null ? this.paymentType.hashCode() : 0);
        return result;
    }

    @Override
    public ReservationKey getKey()
    {
        if (Objects.isNull(this.key))
        {
            this.key = new ReservationKey(
                    this.firstName,
                    this.lastName,
                    this.destination,
                    this.transportType);
        }
        return this.key;
    }

    @Override
    public String getRecordString()
    {
        return this.firstName + ',' +
                this.lastName + ',' +
                this.destination + ',' +
                this.transportType.name() + ',' +
                this.travelingFrom + ',' +
                this.numberOfPeopleInReservation + ',' +
                this.airlineReservation + ',' +
                this.airlineName + ',' +
                this.hotelReservation + ',' +
                this.hotelName + ',' +
                this.carReservation + ',' +
                this.carRental + ',' +
                this.promoCode + ',' +
                this.paymentType.name();
    }

    public static Reservation getReservation(String[] split)
    {
        return new Reservation(
                split[0], // firstName
                split[1], // lastName
                split[2], // destination
                TransportType.getTransportType(split[3]), // transportType
                split[4], // travelingFrom
                Integer.parseInt(split[5]), // numberOfPeopleInReservation
                TRUE.equalsIgnoreCase(split[6]), // airlineReservation
                split[7], // airlineName
                TRUE.equalsIgnoreCase(split[8]), // hotelReservation
                split[9], // hotelName
                TRUE.equalsIgnoreCase(split[10]), // carReservation
                split[11], // carRental
                split[12], // promoCode
                PaymentType.getPaymentType(split[13]) // paymentType
        );
    }
}

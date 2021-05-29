package nikhil.nani.data.bean;

public class ReservationKey
{
    private final String firstName;
    private final String lastName;
    private final String destination;
    private final TransportType transportType;

    public ReservationKey(String firstName, String lastName, String destination, TransportType transportType)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.destination = destination;
        this.transportType = transportType;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getDestination()
    {
        return this.destination;
    }

    public TransportType getTransportType()
    {
        return this.transportType;
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

        ReservationKey that = (ReservationKey) o;

        if (!this.firstName.equals(that.firstName))
        {
            return false;
        }
        if (!this.lastName.equals(that.lastName))
        {
            return false;
        }
        if (!this.destination.equals(that.destination))
        {
            return false;
        }
        return this.transportType == that.transportType;
    }

    @Override
    public int hashCode()
    {
        int result = this.firstName.hashCode();
        result = 31 * result + this.lastName.hashCode();
        result = 31 * result + this.destination.hashCode();
        result = 31 * result + this.transportType.hashCode();
        return result;
    }
}

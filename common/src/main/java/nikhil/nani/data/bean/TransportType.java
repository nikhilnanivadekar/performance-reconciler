package nikhil.nani.data.bean;

public enum TransportType
{
    LAND,
    WATER,
    AIR;

    public static TransportType getTransportType(String transportType)
    {
        switch (transportType)
        {
            case "LAND":
                return LAND;
            case "WATER":
                return WATER;
            case "AIR":
                return AIR;
            default:
                throw new IllegalArgumentException("Unsupported TransportType:" + transportType);
        }
    }
}


public class TreasurePathComplete {

    private int pathID;

    private int userID;
    private String message;
    private long timeCreated;

    private Double[] pathPoints;

    public TreasurePathComplete() {

    }

    public int getPathID() {
        return pathID;
    }

    public int getUserID() {
        return userID;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public Double[] getPathPoints() {
        return pathPoints;
    }

    public void setPathID(int pathID) {
        this.pathID = pathID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * The path points will be sorted lat,lng,lat,lng,lat,lng and so on.
     * They will be re-paired up back on the device after being de-json-ed.
     */
    public void setPathPoints(Double[] pathPoints) {
        this.pathPoints = pathPoints;
    }
}

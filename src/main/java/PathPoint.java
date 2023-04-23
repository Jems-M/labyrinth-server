import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pathPoints")
public class PathPoint {

    @DatabaseField
    private int pathID;

    @DatabaseField
    private int pointNumber;

    @DatabaseField
    private double latitude;

    @DatabaseField
    private double longitude;

    public PathPoint() {

    }

    public int getPathID() {
        return pathID;
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setPathID(int pathID) {
        this.pathID = pathID;
    }

    public void setPointNumber(int pointNumber) {
        this.pointNumber = pointNumber;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}

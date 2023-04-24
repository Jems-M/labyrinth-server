import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pathPoints")
public class PathPoint {

    @DatabaseField
    private long pathID;

    @DatabaseField
    private long pointNumber;

    @DatabaseField
    private double latitude;

    @DatabaseField
    private double longitude;

    public PathPoint() {

    }

    public long getPathID() {
        return pathID;
    }

    public long getPointNumber() {
        return pointNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setPathID(long pathID) {
        this.pathID = pathID;
    }

    public void setPointNumber(long pointNumber) {
        this.pointNumber = pointNumber;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "treasurePaths")
public class TreasurePath {
    @DatabaseField
    private long pathID;

    @DatabaseField
    private long userID;

    @DatabaseField
    private String message;

    @DatabaseField
    private long timeCreated;

    public TreasurePath() {

    }

    public long getPathID() {
        return pathID;
    }

    public long getUserID() {
        return userID;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setPathID(long pathID) {
        this.pathID = pathID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }


}

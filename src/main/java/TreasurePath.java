import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "treasurePaths")
public class TreasurePath {
    @DatabaseField(generatedId = true)
    private long pathID;

    @DatabaseField
    private int userID;

    @DatabaseField
    private String message;

    @DatabaseField
    private long timeCreated;

    public TreasurePath() {

    }

    public long getPathID() {
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

    public void setPathID(long pathID) {
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


}

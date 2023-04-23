import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.SQLException;
import java.util.ArrayList;

import static spark.Spark.get;
import static spark.Spark.post;


public class Main {
    static ObjectMapper objectMapper = new ObjectMapper();

    static Dotenv dotenv = Dotenv.load();
    static String databaseURL = dotenv.get("DB_CONNECTION_STRING");

    static ConnectionSource connectionSource;

    static Dao<PathPoint, Integer> pathPointDao;

    static Dao<TreasurePath, Integer> treasurePathDao;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionSource = new JdbcConnectionSource(databaseURL);
            pathPointDao = DaoManager.createDao(connectionSource, PathPoint.class);
            treasurePathDao = DaoManager.createDao(connectionSource, TreasurePath.class);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Main() {

    }

    public static void main(String[] args) {
        get(("/ping"), (request, response) -> "pong");

        post(("/newTreasurePath"), (request, response) -> {
            long pathID = Long.parseLong(request.queryParams("pathID"));
            int userID = Integer.parseInt(request.queryParams("userID"));
            String message = request.body().substring(0, 280);

            TreasurePath treasurePath = new TreasurePath();
            treasurePath.setPathID(pathID);
            treasurePath.setUserID(userID);
            treasurePath.setMessage(message);
            treasurePath.setTimeCreated(System.currentTimeMillis() / 1000L);

            treasurePathDao.create(treasurePath);
            response.status(201);
            System.out.println("Request received");

            return response.status();
        });

        post(("/newPathPoint"), (request, response) -> {
            int pathID = Integer.parseInt(request.queryParams("pathID"));
            int pointNumber = Integer.parseInt(request.queryParams("pointNumber"));
            double latitude = Double.parseDouble(request.queryParams("latitude"));
            double longitude = Double.parseDouble(request.queryParams("longitude"));

            PathPoint pathPoint = new PathPoint();
            pathPoint.setPathID(pathID);
            pathPoint.setPointNumber(pointNumber);
            pathPoint.setLatitude(latitude);
            pathPoint.setLongitude(longitude);

            pathPointDao.create(pathPoint);
            response.status(201);
            System.out.println("Request received");

            return response.status();
        });

        get(("/getPaths"), (request, response) -> {
            ArrayList<TreasurePath> allTreasurePaths = new ArrayList<>();

            // get the paths and points out of the DAOs because they're a bit finnicky with how you use them
            for (TreasurePath treasurePath : treasurePathDao) {
                allTreasurePaths.add(treasurePath);
            }

            ArrayList<PathPoint> allPathPoints = new ArrayList<>();
            for (PathPoint pathPoint : pathPointDao) {
                allPathPoints.add(pathPoint);
            }

            // build new TreasurePathComplete objects out of the TreasurePath and PathPoint objects
            ArrayList<TreasurePathComplete> allTreasurePathComplete = new ArrayList<>();
            for (TreasurePath treasurePath : allTreasurePaths) {
                TreasurePathComplete treasurePathComplete = new TreasurePathComplete();
                treasurePathComplete.setPathID(treasurePath.getPathID());
                treasurePathComplete.setUserID(treasurePath.getUserID());
                treasurePathComplete.setMessage(treasurePath.getMessage());
                treasurePathComplete.setTimeCreated(treasurePath.getTimeCreated());

                ArrayList<Double> pathPointsTemp = new ArrayList<>();

                for (PathPoint pathPoint : allPathPoints) {
                    if (treasurePath.getPathID() == pathPoint.getPathID()) {
                        // this point belongs to this path, add it to the points
                        pathPointsTemp.add(pathPoint.getLatitude());
                        pathPointsTemp.add(pathPoint.getLongitude());
                    }
                }
                treasurePathComplete.setPathPoints(pathPointsTemp.toArray(new Double[0]));
                allTreasurePathComplete.add(treasurePathComplete);
            }

            String json = objectMapper.writeValueAsString(allTreasurePathComplete);

            response.status(201);

            return json;
        });

    }

}

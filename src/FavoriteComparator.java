import java.util.Comparator;

public class FavoriteComparator implements Comparator<Route> {

    @Override
    public int compare(Route route1, Route route2) {
        int distanceComparison = Double.compare(route1.getDistance(), route2.getDistance());

        if (distanceComparison != 0) {
            return distanceComparison;
        }
        return Integer.compare(route2.getPopularity(), route1.getPopularity());
    }
}

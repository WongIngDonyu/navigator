import java.util.Comparator;

public class TopComparator implements Comparator<Route> {

    @Override
    public int compare(Route route1, Route route2) {
        int popularityComparison = Integer.compare(route2.getPopularity(), route1.getPopularity());
        if (popularityComparison != 0) {
            return popularityComparison;
        }
        int distanceComparison = Double.compare(route1.getDistance(), route2.getDistance());
        if (distanceComparison != 0) {
            return distanceComparison;
        }
        return Integer.compare(route1.getLocationPoints().size(), route2.getLocationPoints().size());
    }
}


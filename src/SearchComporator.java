import java.util.Comparator;

public class SearchComporator implements Comparator<Route> {

    private String startPoint;
    private String endPoint;

    public SearchComporator(String startPoint, String endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public int compare(Route route1, Route route2) {
        boolean isFavorite1 = route1.isFavorite();
        boolean isFavorite2 = route2.isFavorite();
        if (isFavorite1 && !isFavorite2) {
            return -1;
        } else if (!isFavorite1 && isFavorite2) {
            return 1;
        }
        int distanceComparison = Integer.compare(calculateDistance(route1), calculateDistance(route2));
        if (distanceComparison == 0) {
            return Integer.compare(route2.getPopularity(), route1.getPopularity());
        }
        return distanceComparison;
    }

    private int calculateDistance(Route route) {
        MyList<String> routePoints = route.getLocationPoints();
        if (routePoints == null || routePoints.size() < 2 || startPoint == null || endPoint == null) {
            return 0;
        }
        int startIndex = routePoints.indexOf(startPoint);
        int endIndex = routePoints.indexOf(endPoint);
        if (startIndex == -1 || endIndex == -1) {
            return 0;
        }
        return Math.abs(endIndex - startIndex);
    }
}

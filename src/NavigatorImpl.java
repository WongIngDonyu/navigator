import java.util.*;
public class NavigatorImpl implements Navigator{
    private TreeSetImpl<Route> routes;

    public NavigatorImpl() {
        this.routes = new TreeSetImpl<>();
    }

    @Override
    public void addRoute(Route route) {
        if (hasDuplicateRoute(route)) {
            System.out.println("Маршрут (" +route.getId()+ ") с таким расстоянием и начальной/конечной точкой уже существует.");
        } else {
            routes.add(route);
        }
    }

    private boolean hasDuplicateRoute(Route newRoute) {
        for (Route existingRoute : routes.inOrderTraversal()) {
            if (newRoute.pointsEqual(existingRoute) &&
                    Objects.equals(newRoute.getDistance(), existingRoute.getDistance())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeRoute(String routeId) {
        if (routeId == null) {
            throw new IllegalArgumentException("Null routeId");
        }
        Route route = new Route(routeId,0,0,true,null);
        Route route2 = new Route(routeId,0,0,false,null);
        routes.remove(route);
        routes.remove(route2);
    }

    @Override
    public boolean contains(Route route) {
        return routes.contains(route);
    }
    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        Route route = new Route(null, 0, 0, false, null);
        route.setId(routeId);
        Route foundRoute = routes.find(route);
        if (foundRoute == null) {
            Route route2 = new Route(null, 0, 0, true, null);
            route2.setId(routeId);
            foundRoute = routes.find(route2);
        }
        return foundRoute;
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = getRoute(routeId);
        if (route != null) {
            route.setPopularity(route.getPopularity()+1);
        } else {
            throw new NoSuchElementException("Маршрут с идентификатором " + routeId + " не найден.");
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        TreeSetImpl<Route> newTree = new TreeSetImpl<>(new SearchComporator(startPoint, endPoint));
        for (Route route : routes.inOrderTraversal()) {
            if (route.getLocationPoints().indexOf(startPoint)< route.getLocationPoints().indexOf(endPoint)
                    && route.getLocationPoints().contains(startPoint) && route.getLocationPoints().contains(endPoint)) {
                newTree.add(route);
            }
        }
        if (newTree.isEmpty()) {
            return  new TreeSetImpl<Route>().inOrderTraversal();
        }
        return newTree.inOrderTraversal();
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        TreeSetImpl<Route> newTree = new TreeSetImpl<>(new FavoriteComparator());
        for (Route route : routes.inOrderTraversal()) {
            if (route.isFavorite() && route.getLocationPoints().contains(destinationPoint)) {
                if (route.getLocationPoints().indexOf(destinationPoint) > 0) {
                    newTree.add(route);
                }
            }
        }
        if (newTree.isEmpty()) {
            return  new TreeSetImpl<Route>().inOrderTraversal();
        }
        return newTree.inOrderTraversal();
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        TreeSetImpl<Route> newTree = new TreeSetImpl<>(new TopComparator());
        for (Route route : routes.inOrderTraversal()) {
            newTree.add(route);

        }

        if (newTree.isEmpty()) {
            return  new TreeSetImpl<Route>().inOrderTraversal();
        }
        newTree.setTraversalLimit(5);
        return newTree.inOrderTraversal();
    }
}

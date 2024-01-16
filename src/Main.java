import java.util.Scanner;

public class Main {
    private static final NavigatorImpl navigator = new NavigatorImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;


    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.add("Москва");
        myList.add("Петербург");
        MyList myList2 = new MyList();
        myList2.add("Тула");
        myList2.add("Липецк");
        myList2.add("Воронеж");
        MyList myList3 = new MyList();
        myList3.add("Тула");
        myList3.add("Воронеж");
        myList3.add("Москва");
        myList3.add("Липецк");
        MyList myList4 = new MyList();
        myList4.add("Петербург");
        myList4.add("Москва");
        navigator.addRoute(new Route("Route_A", 12, 1, false, myList));
        navigator.addRoute(new Route("Route_B", 23, 42, true, myList2));
        navigator.addRoute(new Route("Route_C", 45, 73, false, myList3));
        navigator.addRoute(new Route("Route_D", 67, 84, true, myList4));
        navigator.addRoute(new Route("Route_E", 89, 128, false, myList3));
        navigator.addRoute(new Route("Route_F", 100, 521, true, myList3));
        navigator.addRoute(new Route("Route_G", 98, 523, false, myList));
        navigator.addRoute(new Route("Route_H", 87, 199, true, myList2));
        navigator.addRoute(new Route("Route_I", 76, 862, false, myList3));
        navigator.addRoute(new Route("Route_J", 65, 6753, true, myList3));
        navigator.addRoute(new Route("Route_K", 42, 86, false, myList4));
        navigator.addRoute(new Route("Route_L", 21, 5231, true, myList3));
        navigator.addRoute(new Route("Route_M", 32, 0, false, myList));
        navigator.addRoute(new Route("Route_N", 92, 873, true, myList2));
        navigator.addRoute(new Route("Route_O", 233, 76, false, myList3));
        navigator.addRoute(new Route("Route_P", 254, 543, true, myList4));
        navigator.addRoute(new Route("Route_Q", 325, 923, false, myList3));
        navigator.addRoute(new Route("Route_R", 2541, 90, true, myList3));
        navigator.addRoute(new Route("Route_S", 753, 987, false, myList));
        navigator.addRoute(new Route("Route_T", 931, 65215, true, myList2));
        navigator.addRoute(new Route("Route_U", 9872, 521, false, myList3));
        navigator.addRoute(new Route("Route_V", 2664, 785, true, myList3));
        navigator.addRoute(new Route("Route_W", 231, 4634, false, myList4));
        navigator.addRoute(new Route("Route_X", 632, 5321, true, myList3));
        do {
            int choice = mainMenu();
            switch (choice) {
                case 1 -> routeList();
                case 2 -> createRoute();
                case 3 -> deleteRoute();
                case 4 -> searchById();
                case 5 -> findByStartAndEnd();
                case 6 -> chooseRote();
                case 7 -> showFavorite();
                case 8 -> top5();
                case 9 -> isRunning = false;
            }

        } while (isRunning);
    }

    private static void top5() {
        System.out.println("Топ 5 маршрутов");
        for (Route route : navigator.getTop3Routes()) {
            System.out.println(route);
        }
    }

    private static void showFavorite() {
        System.out.print("Введите точку назначения: ");
        String destination = scanner.next();

        System.out.println("Избранные маршруты");
        for (Route favorite : navigator.getFavoriteRoutes(destination)) {
            System.out.println(favorite);
        }
    }

    private static void findByStartAndEnd() {
        System.out.print("Введите начальную точку маршрута: ");
        String startPoint = scanner.next();
        System.out.print("Введите конечную точку маршрута: ");
        String endPoint = scanner.next();
        System.out.println("Найденные маршруты");
        Iterable<Route> routes = navigator.searchRoutes(startPoint, endPoint);
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    private static void searchById() {
        System.out.println("Введите нужную информацию");
        System.out.print("Введите id: ");
        String id = scanner.next();
        Route route = new Route();
        route.setId(id);
        Route foundRoute = navigator.getRoute(id);
        if (foundRoute == null) {
            System.out.println("Маршрут не найден");
        } else {
            System.out.println("Маршрут найден");
            System.out.println(foundRoute);
        }
    }


    private static void chooseRote() {
        System.out.print("Введите id маршрута: ");
        String id = scanner.next();
        navigator.chooseRoute(id);
    }

    private static void deleteRoute() {
        System.out.print("Введите id маршрута для удаленя: ");
        navigator.removeRoute(scanner.next());
    }

    private static void createRoute() {
        System.out.print("Введите id маршрута: ");
        String id = scanner.next();

        System.out.print("Введите расстояние маршрута: ");
        double distance = scanner.nextDouble();

        System.out.print("Введите количество точек маршрута: ");
        int size = scanner.nextInt();

        MyList<String> locationPoints = new MyList<>();
        for (int i = 0; i < size; i++) {
            int number = i + 1;
            System.out.print("Введите название " + number + "-го города: ");
            locationPoints.add(scanner.next());
        }
        System.out.print("Введите true или false если маршрут избранный: ");
        boolean favorite = scanner.nextBoolean();
        Route route = new Route(
                id,
                distance,
                0,
                favorite,
                locationPoints
        );

        navigator.addRoute(route);

        System.out.println("Создан следующий маршрут:");
        System.out.println(route);
    }


    private static int mainMenu() {
        String menu =
                """
                        Добро пожаловать в навигатор
                        1. Количество маршрутов
                        2. Создание маршрута
                        3. Удаление маршрута
                        4. Поиск маршрута по ID
                        5. Поиск маршрута по начальной и конечной точке
                        6. Выбор маршрута.
                        7. Любимые маршруты
                        8. Топ 5 маршрутов
                        9. Выход
                        """;

        System.out.println(menu);
        System.out.print("Выберите пункт: ");

        return scanner.nextInt();
    }

    private static void routeList() {
        System.out.println("Общее количество маршрутов: " + navigator.size());
    }
}

//понять в каком виде их хранить изначально. Тип сначало избранные а потом уже по вводу или как ?

/*@Override
    public int compareTo(Route o) {
        if (o == null || o.getId() == null) {
            return 1; // Consider this route greater than a null or a route with a null id
        }

        if (this.getId() == null) {
            return -1; // Consider this route less than a route with a non-null id
        }

        return this.getId().compareTo(o.getId());
    }*/

/*
public static void main(String[] args) {
        Navigator navigator = new NavigatorImpl();
        navigator.addRoute(new Route("1", 100.0, 3, false, Arrays.asList("CityA", "CityB", "CityC")));
        navigator.addRoute(new Route("2", 150.0, 2, true, Arrays.asList("CityA", "CityD", "CityE")));
        navigator.addRoute(new Route("3", 120.0, 4, false, Arrays.asList("CityA", "CityC", "CityD", "CityE")));

        // Вызываем метод searchRoutes
        Iterable<Route> searchResults = navigator.searchRoutes("CityA", "CityD");

        // Выводим результаты в консоль
        System.out.println("Search Results:");
        for (Route route : searchResults) {
            System.out.println(route);
        }
    }
 */

/*
        navigator.addRoute(new Route("Route A", 100, 3, false, myList));
        navigator.addRoute(new Route("Route B", 99, 3, false, myList));
        navigator.addRoute(new Route("Route C", 10422, 312321, false, myList));
        navigator.addRoute(new Route("Route D", 102, 3, false, myList));
        navigator.addRoute(new Route("Route E", 102, 3, false, myList));
        navigator.addRoute(new Route("Route F", 1100, 444, false, myList));
        navigator.addRoute(new Route("Route G", 10320, 321313, false, myList));
        navigator.addRoute(new Route("Route H", 100, 3, false, myList));
        navigator.addRoute(new Route("Route I", 100, 3, false, myList));
        navigator.addRoute(new Route("Route J", 100, 3, false, myList));
        navigator.addRoute(new Route("Route K", 100, 3, false, myList));
        navigator.addRoute(new Route("Route L", 100, 3, false, myList));
        navigator.addRoute(new Route("Route M", 100, 3, false, myList));
        navigator.addRoute(new Route("Route M2", 1032130, 3, false, myList));
        navigator.addRoute(new Route("Route 424", 102, 3, false, myList));
        navigator.addRoute(new Route("Route A", 100222, 3, false, myList));
 */

/*
        navigator.addRoute(new Route("Route A", 32, 1, true, myList));
        navigator.addRoute(new Route("Route B", 99, 54, false, myList2));
        navigator.addRoute(new Route("Route C", 99, 54, true, myList3));
        navigator.addRoute(new Route("Route D", 102, 54, false, myList));
        navigator.addRoute(new Route("Route E", 44, 100, true, myList));
        navigator.addRoute(new Route("Route F", 1235, 10, false, myList));
        navigator.addRoute(new Route("Route G", 443, 99, true, myList));
        navigator.addRoute(new Route("Route Z", 322, 939, true, myList4));
        navigator.addRoute(new Route("Route Z2", 31, 51, true, myList4));
        navigator.addRoute(new Route("Route Z3", 322, 999, true, myList4));
        navigator.addRoute(new Route("Route Z4", 93, 24, true, myList4));
 */

/*
        navigator.addRoute(new Route("Route A", 32, 1, true, myList));
        navigator.addRoute(new Route("Route B", 99, 54, false, myList2));
        navigator.addRoute(new Route("Route C", 99, 54, true, myList3));
        navigator.addRoute(new Route("Route D", 102, 54, false, myList));
        navigator.addRoute(new Route("Route E", 44, 100, true, myList));
        navigator.addRoute(new Route("Route F", 1235, 10, false, myList));
        navigator.addRoute(new Route("Route G", 443, 99, true, myList));
        navigator.addRoute(new Route("Route Z", 322, 939, true, myList4));
        navigator.addRoute(new Route("Route Z2", 31, 51, true, myList4));
        navigator.addRoute(new Route("Route Z3", 322, 999, true, myList4));
        navigator.addRoute(new Route("Route Z4", 93, 24, true, myList4));
        navigator.addRoute(new Route("Route Z33123", 322, 999, true, myList4));
        navigator.addRoute(new Route("Route фыв", 93, 24, true, myList4));
        navigator.addRoute(new Route("Route Я", 32, 1, true, myList));
 */
/*
public class Main {
    public static void main(String[] args) {
        Navigator navigator = new NavigatorImpl();
        MyList myList = new MyList();
        myList.add("City1");
        myList.add("City2");
        MyList myList2 = new MyList();
        myList2.add("City1");
        myList2.add("City3");
        myList2.add("City2");
        MyList myList3 = new MyList();
        myList3.add("City1");
        myList3.add("City3");
        myList3.add("City2");
        myList3.add("City4");
        MyList myList4 = new MyList();
        myList4.add("City2");
        myList4.add("City1");
        navigator.addRoute(new Route("Route A", 32, 1, false, myList));
        navigator.addRoute(new Route("Route B", 99, 54, true, myList2));
        navigator.addRoute(new Route("Route Cя", 991, 54, false, myList3));
        navigator.addRoute(new Route("Route C", 99, 54, true, myList3));
        navigator.addRoute(new Route("Route Z", 992, 54, false, myList3));
        navigator.addRoute(new Route("Route ZZ", 993, 54, true, myList3));
        navigator.addRoute(new Route("Route Aя", 11, 1, false, myList));
        navigator.addRoute(new Route("Route Bя", 12, 54, true, myList2));
        navigator.addRoute(new Route("Route Cяч", 13, 54, false, myList3));
        navigator.addRoute(new Route("Route Cя", 14, 54, true, myList3));
        navigator.addRoute(new Route("Route Zя", 15, 54, false, myList3));
        navigator.addRoute(new Route("Route ZZя", 16, 54, true, myList3));
        navigator.addRoute(new Route("Route Aф", 21, 1, false, myList));
        navigator.addRoute(new Route("Route Bф", 22, 54, true, myList2));
        navigator.addRoute(new Route("Route Cяф", 23, 54, false, myList3));
        navigator.addRoute(new Route("Route Cф", 24, 54, true, myList3));
        navigator.addRoute(new Route("Route Zф", 25, 54, false, myList3));
        navigator.addRoute(new Route("Route ZZф", 26, 54, true, myList3));
        navigator.addRoute(new Route("Route Aв", 41, 1, false, myList));
        navigator.addRoute(new Route("Route Bв", 42, 54, true, myList2));
        navigator.addRoute(new Route("Route Cяв", 43, 54, false, myList3));
        navigator.addRoute(new Route("Route Cв", 44, 54, true, myList3));
        navigator.addRoute(new Route("Route Zв", 45, 54, false, myList3));
        navigator.addRoute(new Route("Route ZZв", 46, 54, true, myList3));
        navigator.printBeautifulTree();
        TreeSetImpl<Integer> treeSet =new TreeSetImpl<>();
        treeSet.add(6);
        treeSet.add(1);
        treeSet.add(7);
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(5);
        treeSet.add(8);
        treeSet.printBeautifulTree();



        Iterable<Route> searchRoutesResult = navigator.searchRoutes("City1", "City4");
        System.out.println("Search Results:");
        for (Route route : searchRoutesResult) {
            System.out.println(route);
        }
        Iterable<Route> favoriteRoutesResult = navigator.getFavoriteRoutes("City3");
        System.out.println("Search Results City3 :");
        for (Route route : favoriteRoutesResult) {
            System.out.println(route);
        }
        Iterable<Route> top3RoutesResult = navigator.getTop3Routes();
        System.out.println("Top 5:");
        for (Route route : top3RoutesResult) {
            System.out.println(route);
        }
        Iterable<Integer> ds = treeSet.inOrderTraversal();
        for (Integer i : ds) {
            System.out.println(i);
        }
    }
}
 */
package findShortestPath;

import com.example.openov.Utilz.Node;

import java.util.*;

import static com.example.openov.Utilz.Constants.Trajecten.*;

public class RouteFinder {
    private List<String> allStations = new ArrayList<>();;
    private Map<String, Node> graph;

    private static class PathInfo {
        private String station;
        private int travelTime;

        public PathInfo(String station, int travelTime) {
            this.station = station;
            this.travelTime = travelTime;
        }

        public String getStation() {
            return station;
        }

        public int getTravelTime() {
            return travelTime;
        }
    }

    public void retrieveStations(String[] array){
        for (String station : array){
            if (!allStations.contains(station)){
                allStations.add(station);
            }
        }
        Collections.sort(allStations);
    }


    public List<PathInfo> findShortestPath(String startingPoint, String endingPoint){
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousStops = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt((Node node) -> distances.getOrDefault(node.getNodeName(), Integer.MAX_VALUE)));


        for (Node node : graph.values()) {
            if (node.getNodeName().equals(startingPoint)){
                distances.put(startingPoint, 0);
            } else {
                distances.put(node.getNodeName(), 1000);
            }
            priorityQueue.add(node);
        }

        // Dijkstra's algorithm
        while (!priorityQueue.isEmpty()){
            Node currentNode = priorityQueue.poll();

            if (visited.contains(currentNode.getNodeName())) {
                continue;  // Skip if already visited
            }
            visited.add(currentNode.getNodeName());

//            System.out.println("Processing node: " + currentNode.getNodeName());

            for (Map.Entry<Node, Integer> neighborEntry : currentNode.getNeighbors().entrySet()){
                Node neighborNode = neighborEntry.getKey();
                int newDistance = distances.get(currentNode.getNodeName())+ neighborEntry.getValue();

//                System.out.println("Neighbor: " + neighborNode.getNodeName() + ", Distance: " + newDistance);


                if (newDistance < distances.get(neighborNode.getNodeName())){
                    distances.put(neighborNode.getNodeName(), newDistance);
                    previousStops.put(neighborNode.getNodeName(), currentNode.getNodeName());

                    // update the queue to look for the nearest stations
                    priorityQueue.remove(neighborNode);
                    priorityQueue.add(neighborNode);

                    if (currentNode.getNodeName().equals(endingPoint)) {
                        // Found the destination, stop processing and empty the priority queue
                        priorityQueue.clear();
                        break;
                    }


                }
            }
        }

        // reconstruct path
        List<PathInfo> path = new ArrayList<>();
        for (String stop = endingPoint; stop != null; stop = previousStops.get(stop)){
            String previousStop = previousStops.get(stop);
            int travelTime = distances.get(stop) - distances.getOrDefault(previousStop, 0);
            path.add(new PathInfo(stop, travelTime));
        }
        Collections.reverse(path);

        return path;
    }


//    public String RouteSearcher(String departure, String destination){
//        String finalText = "";
//        Graph grapher = new Graph();
//        graph = grapher.getGraph();
//
//        List<PathInfo> shortestPath = findShortestPath(departure, destination);
//
//        int time = 0;
//
//        for (PathInfo path : shortestPath){
//            finalText += path.getStation() + " - Time: " + path.getTravelTime() + "\n";
//            time += path.getTravelTime();
//        }
//
//        finalText += "Total time: " + time;
//        return finalText;
//    }
    public ArrayList<Object[]> RouteSearcher(String departure, String destination) {
        Graph grapher = new Graph();
        graph = grapher.getGraph();

        List<PathInfo> shortestPath = findShortestPath(departure, destination);

        int totalTime = 0;

        ArrayList<Object[]> result = new ArrayList<>();

        for (PathInfo path : shortestPath) {
            String station = path.getStation();
            int travelTime = path.getTravelTime();

            Object[] pathInfoArray = {station, travelTime};
            result.add(pathInfoArray);

            totalTime += travelTime;
        }

        // Add the total time at the end
        result.add(new Object[]{"Total time", totalTime});

        return result;
    }
}

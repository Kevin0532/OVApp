package findShortestPath;

import com.example.openov.Utilz.Node;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String, Node> graph;

    public Graph(){
        this.graph = new HashMap<>();

        createStations();

//        trainTest();

    }

    public void createStations(){
        Node shertogenbosch = new Node("'s-Hertogenbosch");
        Node amersfoort_centraal = new Node("Amersfoort Centraal");
        Node amersfoort_schothorst = new Node("Amersfoort Schothorst");
        Node amsterdam_amstel = new Node("Amsterdam Amstel");
        Node amsterdam_bijlmer_arena = new Node("Amsterdam Bijlmer Arena");
        Node amsterdam_centraal = new Node("Amsterdam Centraal");
        Node amsterdam_muiderpoort = new Node("Amsterdam Muiderpoort");
        Node amsterdam_science_park = new Node("Amsterdam Science Park");
        Node amsterdam_sloterdijk = new Node("Amsterdam Sloterdijk");
        Node baarn = new Node("Baarn");
        Node bilthoven = new Node("Bilthoven");
        Node bovenkarspel_flora = new Node("Bovenkarspel Flora");
        Node bovenkarspel_grootebroek = new Node("Bovenkarspel-Grootebroek");
        Node bussum_zuid = new Node("Bussum Zuid");
        Node den_dolder = new Node("Den Dolder");
        Node den_haag_centraal = new Node("Den Haag Centraal");
        Node diemen = new Node("Diemen");
        Node dordrecht = new Node("Dordrecht");
        Node eindhoven_centraal = new Node("Eindhoven Centraal");
        Node enkhuizen = new Node("Enkhuizen");
        Node gouda = new Node("Gouda");
        Node heerenveen = new Node("Heerenveen");
        Node hilversum = new Node("Hilversum");
        Node hilversum_media_park = new Node("Hilversum Media Park");
        Node hoogkarspel = new Node("Hoogkarspel");
        Node hoorn = new Node("Hoorn");
        Node hoorn_kersenboogerd = new Node("Hoorn Kersenboogerd");
        Node leeuwarden = new Node("Leeuwarden");
        Node maastricht = new Node("Maastricht");
        Node meppel = new Node("Meppel");
        Node naarden_bussum = new Node("Naarden Bussum");
        Node roermond = new Node("Roermond");
        Node rotterdam_alexander = new Node("Rotterdam Alexander");
        Node rotterdam_blaak = new Node("Rotterdam Blaak");
        Node rotterdam_centraal = new Node("Rotterdam Centraal");
        Node sittard = new Node("Sittard");
        Node soest = new Node("Soest");
        Node soest_zuid = new Node("Soest Zuid");
        Node soestdijk = new Node("Soestdijk");
        Node steenwijk = new Node("Steenwijk");
        Node utrecht_centraal = new Node("Utrecht Centraal");
        Node utrecht_overvecht = new Node("Utrecht Overvecht");
        Node weert = new Node("Weert");
        Node weesp = new Node("Weesp");
        Node zwolle = new Node("Zwolle");

        //      Traject 1
        amersfoort_centraal.addNeighbor(baarn, 7);
        baarn.addNeighbor(hilversum, 6);
        hilversum.addNeighbor(hilversum_media_park, 2);
        hilversum_media_park.addNeighbor(bussum_zuid, 4);
        bussum_zuid.addNeighbor(naarden_bussum, 3);
        naarden_bussum.addNeighbor(weesp, 6);
        weesp.addNeighbor(diemen, 5);
        diemen.addNeighbor(amsterdam_science_park, 2);
        amsterdam_science_park.addNeighbor(amsterdam_muiderpoort, 3);
        amsterdam_muiderpoort.addNeighbor(amsterdam_centraal, 6);
//      Traject 2
        dordrecht.addNeighbor(rotterdam_blaak, 13);
        rotterdam_blaak.addNeighbor(rotterdam_centraal, 3);
        rotterdam_centraal.addNeighbor(rotterdam_alexander, 8);
        rotterdam_alexander.addNeighbor(gouda, 10);
        gouda.addNeighbor(utrecht_centraal, 18);
        utrecht_centraal.addNeighbor(amersfoort_centraal, 13);
        amersfoort_centraal.addNeighbor(zwolle, 35);
        zwolle.addNeighbor(meppel, 15);
        meppel.addNeighbor(steenwijk, 9);
        steenwijk.addNeighbor(heerenveen, 13);
        heerenveen.addNeighbor(leeuwarden, 17);
//      Traject 3
        baarn.addNeighbor(soestdijk, 4);
        soestdijk.addNeighbor(soest, 3);
        soest.addNeighbor(soest_zuid, 2);
        soest_zuid.addNeighbor(den_dolder, 6);
        den_dolder.addNeighbor(bilthoven, 3);
        bilthoven.addNeighbor(utrecht_overvecht, 5);
        utrecht_overvecht.addNeighbor(utrecht_centraal, 4);
//      Traject 4
        maastricht.addNeighbor(sittard, 15);
        sittard.addNeighbor(roermond, 14);
        roermond.addNeighbor(weert, 14);
        weert.addNeighbor(eindhoven_centraal, 16);
        eindhoven_centraal.addNeighbor(shertogenbosch, 18);
        shertogenbosch.addNeighbor(utrecht_centraal, 26);
        utrecht_centraal.addNeighbor(amsterdam_bijlmer_arena, 16);
        amsterdam_bijlmer_arena.addNeighbor(amsterdam_amstel, 5);
        amsterdam_amstel.addNeighbor(amsterdam_centraal, 8);
        amsterdam_centraal.addNeighbor(amsterdam_sloterdijk, 6);
        amsterdam_sloterdijk.addNeighbor(hoorn, 26);
        hoorn.addNeighbor(hoorn_kersenboogerd, 4);
        hoorn_kersenboogerd.addNeighbor(hoogkarspel, 7);
        hoogkarspel.addNeighbor(bovenkarspel_grootebroek, 5);
        bovenkarspel_grootebroek.addNeighbor(bovenkarspel_flora, 1); // Is eigenlijk 2, aangepast voor test
        bovenkarspel_flora.addNeighbor(enkhuizen, 3); // Is eigenlijk 4, aangepast voor test
//      Traject 5
        den_haag_centraal.addNeighbor(gouda, 18);
        //gouda.addNeighbor(utrecht_centraal, 18);  staat er al in
        //utrecht_centraal.addNeighbor(amersfoort_centraal, 13); staat er ook al in
        amersfoort_centraal.addNeighbor(amersfoort_schothorst, 4);

        addNode(shertogenbosch);
        addNode(amersfoort_centraal);
        addNode(amersfoort_schothorst);
        addNode(amsterdam_amstel);
        addNode(amsterdam_bijlmer_arena);
        addNode(amsterdam_centraal);
        addNode(amsterdam_muiderpoort);
        addNode(amsterdam_science_park);
        addNode(amsterdam_sloterdijk);
        addNode(baarn);
        addNode(bilthoven);
        addNode(bovenkarspel_flora);
        addNode(bovenkarspel_grootebroek);
        addNode(bussum_zuid);
        addNode(den_dolder);
        addNode(den_haag_centraal);
        addNode(diemen);
        addNode(dordrecht);
        addNode(eindhoven_centraal);
        addNode(enkhuizen);
        addNode(gouda);
        addNode(heerenveen);
        addNode(hilversum);
        addNode(hilversum_media_park);
        addNode(hoogkarspel);
        addNode(hoorn);
        addNode(hoorn_kersenboogerd);
        addNode(leeuwarden);
        addNode(maastricht);
        addNode(meppel);
        addNode(naarden_bussum);
        addNode(roermond);
        addNode(rotterdam_alexander);
        addNode(rotterdam_blaak);
        addNode(rotterdam_centraal);
        addNode(sittard);
        addNode(soest);
        addNode(soest_zuid);
        addNode(soestdijk);
        addNode(steenwijk);
        addNode(utrecht_centraal);
        addNode(utrecht_overvecht);
        addNode(weert);
        addNode(weesp);
        addNode(zwolle);
    }

    public void addNode(Node node) {
        graph.put(node.getNodeName(), node);
    }

    public Map<String, Node> getGraph() {
        return graph;
    }

}

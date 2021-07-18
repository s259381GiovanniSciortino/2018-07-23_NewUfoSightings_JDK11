package it.polito.tdp.newufosightings.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.newufosightings.db.Edge;
import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;

public class Model {
	Graph<State, DefaultWeightedEdge> grafo;
	Map<String, State> stateIdMap;
	
	public String doCreaGrafo(int anni, String shape) {
		NewUfoSightingsDAO dao= new NewUfoSightingsDAO();
		stateIdMap = new HashMap<>();
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.loadAllStates());
		for(State s: grafo.vertexSet())
			stateIdMap.put(s.getId(), s);
		List<Edge> archi = new ArrayList<>(dao.getEdge());
		for(Edge e: archi) {
			int peso = dao.getPeso(e.getStateID1(), anni, shape) + dao.getPeso(e.getStateID2(), anni, shape);
			if(peso>0) 
				Graphs.addEdge(grafo, stateIdMap.get(e.getStateID1()), stateIdMap.get(e.getStateID2()), peso);
		} 
		String result = "";
		if(this.grafo==null) {
			result ="Grafo non creato";
			return result;
		}
		result = "Grafo creato con :\n# "+this.grafo.vertexSet().size()+" VERTICI\n# "+this.grafo.edgeSet().size()+" ARCHI\n\n";
		for(State s: grafo.vertexSet()) {
			result+="STATO: "+ s+" con somma dei pesi degli archi adiacenti: ";
			int peso = 0;
			for(State st: Graphs.neighborListOf(grafo, s)) {
				peso+=grafo.getEdgeWeight(grafo.getEdge(s, st));
			}
			result+=peso+"\n";
		}
		return result;
	}
	
	
	public String doSimulator(int t1,int alfa,int anni, String shape) {
		Simulator sim = new Simulator(t1,alfa,this.grafo,this.stateIdMap);
		sim.init(anni, shape);
		String msg = sim.run();
		return msg;
		
	}
	
	public List<String> doSelezionaAnno(int anno){
		NewUfoSightingsDAO dao= new NewUfoSightingsDAO();
		return dao.getShapes(anno);
	}
}

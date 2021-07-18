package it.polito.tdp.newufosightings.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;
import it.polito.tdp.newufosightings.model.Event.Type;

public class Simulator {
	Map<String,Double> DefconLevel;
	PriorityQueue<Event> eventiSimulazione;
	Graph<State, DefaultWeightedEdge> grafo;
	Map<String, State> stateIdMap;
	
	int reset;
	int alfa;
	public Simulator(int t1,int alfa,Graph<State, DefaultWeightedEdge> grafo,Map<String, State> stateIdMap) {
		this.grafo = grafo;
		this.reset=t1;
		this.alfa=alfa;
		this.stateIdMap=stateIdMap;
	}
	
	public void init(int anni, String shape) {
		DefconLevel = new HashMap<>();
		NewUfoSightingsDAO dao= new NewUfoSightingsDAO();
		eventiSimulazione = new PriorityQueue<>(dao.getEventi(anni, shape));
		LocalDateTime dateTime = LocalDateTime.of(anni, 01, 01, 00,00,00);
		int volte =365/reset;
		for(int i=0;i<volte;i++) {
			dateTime = dateTime.plusDays(reset);
			if(dateTime.getYear()==anni)
				eventiSimulazione.add(new Event(null,dateTime,Type.CESSA_ALLERTA));
		}
		for(State s: grafo.vertexSet()) {
			DefconLevel.put(s.getId(), 5.0);
		}
		
	}
	public String run() {
		while(!eventiSimulazione.isEmpty()) {
			Event evento = eventiSimulazione.poll();
			System.out.println(evento);
			switch(evento.getType()) {
				case CESSA_ALLERTA:
					for(String str: DefconLevel.keySet()) {
						DefconLevel.put(str, DefconLevel.get(str)+1);
						if(DefconLevel.get(str)>5)
							DefconLevel.put(str, 5.0);
					}
					break;
				case AVVISTAMENTO:
					DefconLevel.put(evento.getState(), DefconLevel.get(evento.getState())-1);
					if(DefconLevel.get(evento.getState())<1)
						DefconLevel.put(evento.getState(), 1.0);
					for(State s: Graphs.neighborListOf(grafo, stateIdMap.get(evento.getState()))) {
						double rand= Math.random()*100;
						if(rand<alfa) {
							DefconLevel.put(s.getId(), DefconLevel.get(s.getId())-0.5);
							if(DefconLevel.get(s.getId())<1)
								DefconLevel.put(s.getId(), 1.0);
						}
					}
					break;
				
			}
		}
		String result = "\nSimulazione avvenuta con successo!!!!\nIl livello di DEFCON finale per ciascun stato è :\n";
		for(String s: DefconLevel.keySet())
			result+=stateIdMap.get(s)+ " | "+DefconLevel.get(s)+"\n";
		return result;
	}
	
}

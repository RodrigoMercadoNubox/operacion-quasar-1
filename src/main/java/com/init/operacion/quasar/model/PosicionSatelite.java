package com.init.operacion.quasar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PosicionSatelite {

	private List<Satelite> satellites;

    public void setSatellites(List<Satelite> satellites) {
        this.satellites = satellites;
    }

    public List<Satelite> getSatellites() {
        return satellites;
    }

    public List<List<String>> getMessages(){
        List<List<String>> messages = new ArrayList<List<String>>();
        for(Satelite s : satellites){
            messages.add(s.getMessage());
        }
        return  messages;
    }

}

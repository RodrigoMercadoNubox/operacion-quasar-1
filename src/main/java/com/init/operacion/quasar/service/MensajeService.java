package com.init.operacion.quasar.service;

import org.springframework.stereotype.Service;

import com.init.operacion.quasar.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MensajeService {

    public List<String> ObtenerOracionMensaje(List<List<String>> msgList){

        List<String> lstOracion = new ArrayList<String>();
        for( List<String> msg : msgList){
        	lstOracion = Stream.concat(lstOracion.stream(), msg.stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        lstOracion.remove("");
        return lstOracion;
    }

    public void eliminarDesfasaje(List<List<String>> lstMensaje, int tamanoDesfase){

        int t = 0;
        for(int i = 0; i < lstMensaje.size(); i++){
            t = lstMensaje.get(i).size();
            lstMensaje.set(i, lstMensaje.get(i).subList(t-tamanoDesfase, t));
        }
    }
    
    public String MensajeCompletoDeAuxilio(List<List<String>> lstMensaje){

        String fraseAuxilio = "";
        for(List<String> m : lstMensaje){

            if(m.size()>0 && !m.get(0).equals("")){
            	fraseAuxilio = (m.size() == 1) ? m.get(0) : m.get(0) + " ";
            	lstMensaje.stream().forEach( s -> s.remove(0));
                return  fraseAuxilio + MensajeCompletoDeAuxilio(lstMensaje);
            }
        }
        return "";
    }

    public String getMessage(List<List<String>> lstMensaje) throws MensajeExcepcion {

        List<String> msgOracion = ObtenerOracionMensaje(lstMensaje);
        if(!ValidarTamanoMensajeAuxilio(lstMensaje, msgOracion.size()))
            throw new MensajeExcepcion("La cantidad de mensajes no es valida, necesitas ingresar más mensajes");
        
        eliminarDesfasaje(lstMensaje,msgOracion.size());
        String mensajeAuxilio = MensajeCompletoDeAuxilio(lstMensaje);
        if(!ValidarMensajeAuxilio(msgOracion,mensajeAuxilio))
            throw new MensajeExcepcion("No es posible determinar el mensaje de auxilio");

        return mensajeAuxilio;
    }

    public boolean ValidarTamanoMensajeAuxilio(List<List<String>> mensajes, int tamano){
        for(List<String> m : mensajes){
            if(m.size() < tamano){
                return false;
            }
        }
        return true;
    }

    public boolean ValidarMensajeAuxilio(List<String> msgOracionCompleta, String mensaje){
        List<String> lstMensaje = Arrays.stream(mensaje.split(" ")).collect(Collectors.toList());
        Collections.sort(msgOracionCompleta);
        Collections.sort(lstMensaje);
        return Arrays.equals(msgOracionCompleta.toArray(), lstMensaje.toArray());
    }
   
    public String getMessageSplit(List<String> lstMensaje)
    {
    	lstMensaje = lstMensaje.stream().distinct().collect(Collectors.toList());
    	lstMensaje.remove("");
    	 Collections.sort(lstMensaje);
    	return String.join(" ", lstMensaje);
    	
    }
    public boolean ValidarOracionMensajeSplit(List<String> oracion, String mensaje){
        List<String> lstMensaje = Arrays.stream(mensaje.split(" ")).collect(Collectors.toList());
        Collections.sort(oracion);
        Collections.sort(lstMensaje);
        return Arrays.equals(oracion.toArray(), lstMensaje.toArray());
    }

}

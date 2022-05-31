package com.init.operacion.quasar.controller;

import com.init.operacion.quasar.model.*;
import com.init.operacion.quasar.exceptions.MensajeExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController	
public class InteligenciaRebeldeController {

    @Autowired
    private InteligenciaRebeldeInterface intelligenceService;

    @Autowired
    private InteligenciaRebeldeInterface intelligenceService2;
    @PostMapping(value="/servers/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topSecret(RequestEntity<PosicionSatelite> requestEntitySatelite){
    
        try {
            return ResponseEntity.status(HttpStatus.OK).body(intelligenceService.ObtenerNaveEspacial(requestEntitySatelite));
        }catch (MensajeExcepcion e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    
    @RequestMapping(value="/servers/{id}/{name}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity topsecret_split(@PathVariable("name") String name, RequestEntity<Satelite> requestEntitySatelite){
        try {
        	
            return ResponseEntity.status(HttpStatus.OK).body(intelligenceService2.ObtenerNaveEspacialSplit(requestEntitySatelite , name));
        }catch (MensajeExcepcion e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
    
 
    
}

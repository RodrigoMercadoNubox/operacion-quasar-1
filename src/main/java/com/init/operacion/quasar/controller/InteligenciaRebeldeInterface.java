package com.init.operacion.quasar.controller;

import com.init.operacion.quasar.model.*;
import  com.init.operacion.quasar.exceptions.*;
import org.springframework.http.RequestEntity;

public interface InteligenciaRebeldeInterface {
    public PortaCarga ObtenerNaveEspacial(RequestEntity requestEntity) throws MensajeExcepcion;
    public PortaCarga ObtenerNaveEspacialSplit(RequestEntity requestEntity, String name) throws MensajeExcepcion;
}

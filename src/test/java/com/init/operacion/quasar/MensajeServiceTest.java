package com.init.operacion.quasar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.init.operacion.quasar.exceptions.MensajeExcepcion;
import com.init.operacion.quasar.service.MensajeService;

@SpringBootTest
public class MensajeServiceTest {

	 @Autowired
	    private MensajeService mensajeService;
	    List<List<String>> lstMensaje = new ArrayList<List<String>>();
	    
	    @Test
	    public void getMessage3Satelites() throws MensajeExcepcion {
	        String [] msg1 = {"este", "", "", "mensaje", ""};
	        String [] msg2 = {"", "es", "", "", "secreto"};
	        String [] msg3 = {"este", "", "un", "", ""};
	        lstMensaje.add(Arrays.stream(msg1).collect(Collectors.toList()));
	        lstMensaje.add(Arrays.stream(msg2).collect(Collectors.toList()));
	        lstMensaje.add(Arrays.stream(msg3).collect(Collectors.toList()));
	        
	        assertEquals(mensajeService.getMessage(lstMensaje),"este es un mensaje secreto");
	    }

	    @Test
	    public void getMessage3SatelitesError(){
	        String [] msg1 = {"este", "", "", "mensaje", ""};
	        String [] msg2 = {"", "es", "", "", "secreto"};
	        String [] msg3 = {"este", "", "un", "", "",""};
	        lstMensaje.add(Arrays.stream(msg1).collect(Collectors.toList()));
	        lstMensaje.add(Arrays.stream(msg2).collect(Collectors.toList()));
	        lstMensaje.add(Arrays.stream(msg3).collect(Collectors.toList()));
	        try {
	            mensajeService.getMessage(lstMensaje);
	        }catch (MensajeExcepcion e){
	        	assertEquals("No se puede conocer el mensaje",e.getMessage());
	        }
	    }

	    @Test
	    public void getMessage4Satelites() throws MensajeExcepcion {
	        String [] msg1 = {"este", "", "", "mensaje", ""};
	        String [] msg2 = {"", "es", "", "", "secreto"};
	        String [] msg3 = {"este", "", "un", "", ""};
	        String [] msg4 = {"", "", "un", "", "secreto"};
	        lstMensaje.add(Arrays.stream(msg1).collect(Collectors.toList()));
	        lstMensaje.add(Arrays.stream(msg2).collect(Collectors.toList()));
	        lstMensaje.add(Arrays.stream(msg3).collect(Collectors.toList()));
	        lstMensaje.add(Arrays.stream(msg4).collect(Collectors.toList()));

	        assertEquals(mensajeService.getMessage(lstMensaje),"este es un mensaje secreto");
	    }
}

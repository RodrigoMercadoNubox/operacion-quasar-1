package com.init.operacion.quasar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.init.operacion.quasar.service.LocalizacionService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocalizacionServiceTest {
	
	@Autowired
	private LocalizacionService localizacionService;
	
	 @Test
	    public void ObtenerLocalizacionValoresMinimos(){
	        double[][] positions = new double[][]{{1.0}, {2.0}, {3.0}};
	        double[] distances = new double[]{1.1, 0.1, 0.9};
	        double[] expectedPosition = new double[]{2.1};
	        double[] calculatedPosition = localizacionService.getLocation(positions, distances);
	        for (int i = 0; i < calculatedPosition.length; i++) {
	            assertEquals(expectedPosition[i], calculatedPosition[i]);
	        }
	    }

	    @Test
	    public void ObtenerLocalizacionValoresOriginales() throws Exception {
	        double[][] positions = new double[][]{{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
	        double[] distances = new double[]{100.0, 115.5, 142.7};
	        double[] expectedPosition = new double[]{-58.315252587138595, -69.55141837312165};
	        double[] calculatedPosition = localizacionService.getLocation(positions, distances);
	        for (int i = 0; i < calculatedPosition.length; i++) {
	            assertEquals(expectedPosition[i], calculatedPosition[i]);
	        }
	    }

}

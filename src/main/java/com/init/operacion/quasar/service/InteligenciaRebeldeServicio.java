package com.init.operacion.quasar.service;

import com.init.operacion.quasar.controller.InteligenciaRebeldeInterface;
import com.init.operacion.quasar.model.*;
import com.init.operacion.quasar.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InteligenciaRebeldeServicio implements InteligenciaRebeldeInterface {

	static double[][] posicionesxy = new double[][] { { -500, -200 }, { 100, -100 }, { 500, 100 } };
	static String errorMsg = "Debes ingresar más de un mensaje";
	static String errorDistancias = "Debe ingresar una distancia para cada satelite";
	static String errorDistancia = "Debe ingresar una distancia";
	@Autowired
	MensajeService mensajeService;

	@Autowired
	LocalizacionService localizacionService;

	@Override
	public PortaCarga ObtenerNaveEspacial(RequestEntity requestEntity) throws MensajeExcepcion {

		PosicionSatelite satelliteWrapper = (PosicionSatelite) requestEntity.getBody();
		if (satelliteWrapper.getMessages().size() < 2)
			throw new MensajeExcepcion(errorMsg);
		double[] resultDistancias = ObtenerDistancia(satelliteWrapper.getSatellites());

		if (resultDistancias.length < 2)
			throw new MensajeExcepcion(errorDistancias);

		double[] points = localizacionService.getLocation(posicionesxy, resultDistancias);

		return new Transportador(new Posicion(points), mensajeService.getMessage(satelliteWrapper.getMessages()));
	}

	@Override
	public PortaCarga ObtenerNaveEspacialSplit(RequestEntity requestEntity, String name) throws MensajeExcepcion {

		Satelite satelite = (Satelite) requestEntity.getBody();
		satelite.setName(name.toLowerCase());

		double[] resultDistancias = new double[] { (satelite.getDistance()), 0 };

		if (satelite.getMessage().size() < 1)
			throw new MensajeExcepcion(errorMsg);

		if (resultDistancias.length == 0)
			throw new MensajeExcepcion(errorDistancia);
		if (satelite.getName().length() == 0)
			throw new MensajeExcepcion("Debse ingresar un nombre de satelite");

		double[] points = localizacionService.getLocation(ObtenerPosicionPorNombre(satelite.getName()),
				resultDistancias);

		return new Transportador(new Posicion(points), mensajeService.getMessageSplit(satelite.getMessage()));
	}

	public double[][] ObtenerPosicionPorNombre(String name) throws MensajeExcepcion {
		switch (name) {
		case "kenobi":
			return new double[][] { { -500, -200 }, { 0, 0 } };
		case "skywalker":
			return new double[][] { { 100, -100 }, { 0, 0 } };
		case "sato":
			return new double[][] { { 500, 100 }, { 0, 0 } };
		}
		throw new MensajeExcepcion("Debe ingresar un nombre de satelite valido (kenobi,skywalker, sato)");
	}

	public double[] ObtenerDistancia(List<Satelite> satelites) {

		double[] distancia1 = new double[satelites.size()];
		int con = 0;
		satelites.stream().forEach((p) -> distancia1[con + 1] = p.getDistance());

		double[] distancias = new double[satelites.size()];
		for (int i = 0; i < satelites.size(); i++) {
			distancias[i] = satelites.get(i).getDistance();
		}
		return distancias;
	}

}

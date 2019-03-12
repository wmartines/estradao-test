package br.estadao.quality.util;

import java.util.Random;

public class EstadaoUtils {
	
	/**
	 * Metodo responsavel por gerar um numero aleatorio
	 * 
	 * @return
	 */
	public static Integer generateRandomNumber() {

		return new Random().nextInt(1000000000) + 1000000000;
	}

}

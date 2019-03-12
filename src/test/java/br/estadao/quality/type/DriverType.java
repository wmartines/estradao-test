
package br.estadao.quality.type;

import br.estadao.quality.constraint.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Enum DriverType.
 */
public enum DriverType {

	/** The firefox. */
	FIREFOX  (Constants.ACTIVE) , 
	
	/** The chrome. */
	CHROME   (Constants.INACTIVE),
	
	/** The explorer. */
	EXPLORER (Constants.INACTIVE),
	
	/** The opera. */
	OPERA    (Constants.INACTIVE),
	
	/** The not found. */
	NOT_FOUND(Constants.INACTIVE);

	/** The active. */
	private boolean active;

	/**
	 * Instantiates a new driver type.
	 *
	 * @param active the active
	 */
	DriverType(boolean active) {
		this.active = active;
	}

	/**
	 * Metodo responsavel por retornar o driver ativo
	 * anotado com true.
	 *
	 * @return the active driver
	 */
	public static DriverType getActiveDriver() {
		for (DriverType driver : DriverType.values()) {
			if (driver.active){
				return driver;
			}
		}
		return DriverType.NOT_FOUND;
	}
}
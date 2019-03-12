package br.estadao.quality.type;

// TODO: Auto-generated Javadoc
/**
 * The Enum AuthenticationUserType.
 */
public enum AuthenticationUserType {
	
	/** The ex subscriber. */
	EX_SUBSCRIBER("xxxxxxx@estadao.com.br", "3s7@DA0"),
	
	/** The ex subscriber. */
	EX_SUBSCRIBER2("xxxxx@testando.com", "123456");
	
	/** The user. */
	private final String user;
	
	/** The password. */
	private final String password;

	/**
	 * Instantiates a new authentication user type.
	 *
	 * @param user the user
	 * @param password the password
	 */
	AuthenticationUserType(String user , String password) {
		this.user = user;
		this.password = password;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
}

package br.estadao.quality.type;

// TODO: Auto-generated Javadoc
/**
 * The Enum EnvironmentType.
 */
public enum EnvironmentType {
	
	/** The estadao homolog. */
	ESTADAO_HOMOLOG("https://www.estadao-hml.com.br"),
	
	/** The estadao prod. */
	ESTADAO_PROD("https://www.estadao.com.br");
	
	/** The url. */
	private final String url;

	/**
	 * Instantiates a new environment type.
	 *
	 * @param url the url
	 */
	EnvironmentType(String url) {
		this.url = url;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
}

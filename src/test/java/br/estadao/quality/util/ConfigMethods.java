package br.estadao.quality.util;

import org.openqa.selenium.WebDriver;

import br.estadao.quality.type.EnvironmentType;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigMethods.
 */
public class ConfigMethods {
	
	/** The driver. */
	private static WebDriver driver;
	
	/**
	 * Sets the up.
	 *
	 * @param env the env
	 * @return the web driver
	 */
	public static WebDriver setUp(EnvironmentType env) {
		driver = new Driver().active(env.getUrl());
		
		return driver;
	}
	
	/**
	 * Tear down.
	 */
	public static void tearDown() {
		driver.quit();
	}

}

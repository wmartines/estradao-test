/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.estadao.quality.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.estadao.quality.type.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	private static final Logger LOG = LoggerFactory.getLogger(Driver.class);
	
private final int time = 30;
	
	/**
	 * Metodo responsavel por inciar o driver ativo
	 * 
	 * @param url URL para ser carregada
	 * @return driver ativo
	 */
	public WebDriver active(String url) {
		
		// Efetua a consulta do driver ativo
		DriverType activeDriver = DriverType.getActiveDriver();
		
		// Verifica se driver ativo  Firefox
		if(activeDriver.equals(DriverType.FIREFOX)){
			
			// Inicia driver firefox
			return firefoxDriver(url);
		}
		
		// Verifica se driver ativo  Chrome
		else if(activeDriver.equals(DriverType.CHROME)){
			
			// Inicia driver Chrome
			return chromeDriver(url);
		}
		
		// Verifica se driver ativo IExplorer
		else if(activeDriver.equals(DriverType.EXPLORER)){
			
			// Inicia driver IExplorer
			return explorerDriver(url);
		}
		
		// Inicia driver firefox por default
		return firefoxDriver(url);
	}

	/**
	 * Metodo responsavel por inciar o driver do navegador firefox
	 * 
	 * @param url URL para ser carregada
	 * @return driver do navegador firefox
	 */
	private WebDriver firefoxDriver(String url) {
		
		WebDriver driver = null;
		try {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);			
			driver.get(url);
						
		} catch (Exception e) {
			LOG.error("[Driver] Erro ao iniciar driver firefox: "+ e.getCause());
		}
		return driver;
	}
	
	/**
	 * Metodo responsavel por inciar o driver do navegador chrome
	 * 
	 * @param url URL para ser carregada
	 * @return driver do navegador chrome
	 */
	private WebDriver chromeDriver(String url) {
		WebDriver driver = null;
		try {
		
			System.setProperty("webdriver.chrome.driver","/home/wilson/sistemas/project-leroy/java/estadao-test/src/test/resources/driver/chromedriver");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			driver.get(url);
			driver.manage().window().setSize(new Dimension(1366,768));
			
			
		} catch (Exception e) {
			
			LOG.error("[Driver] Erro ao iniciar driver chrome: "+ e.getCause());
		}
		return driver;
	}
	
	/**
	 * Metodo responsavel por inciar o driver do navegador explorer
	 * 
	 * @param url URL para ser carregada
	 * @return driver do navegador explorer
	 */
	private WebDriver explorerDriver(String url) {
		WebDriver driver = null;
		try {
		
			
		} catch (Exception e) {
			LOG.error("[Driver] Erro ao iniciar driver explorer: "+ e.getCause());
		}
		return driver;
	}
}

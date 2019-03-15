package br.estadao.quality.util;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ActionMethods {

	private FluentWait<WebDriver> wait;
	
	private WebDriver driver ;
	
	private static final Logger LOG = LoggerFactory.getLogger(ActionMethods.class);

	/**
	 * Construtor passando driver instanciado
	 * 
	 * @param WebDriver driver
	 */
	@SuppressWarnings("deprecation")
	public ActionMethods(WebDriver driver) {
		
		this.driver = driver;

		this.wait = new FluentWait<WebDriver>(driver)

				// FluentWait com timeout de 20 segundos,
				.withTimeout(20, TimeUnit.SECONDS)

				// Intervalo de polling de 1 segundo,
				.pollingEvery(1, TimeUnit.SECONDS)

				// Ignorando as exceções para aplicações que usam muito AJAX
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
	}
	
	/**
	 * Metodo insere texto no campo
	 * 
	 * @param By locator
	 * @param String value
	 * 
	 */
	public void input(By locator, String value) {

		try {

			// Aguarda elemento ser exibido e limpa o campo
			waitLoadPage();
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			element.clear();
			element.sendKeys(value);

		} catch (Exception e) {
			LOG.error("[ActionMethods] Erro ao inserir texto: "+value+", em: "+locator+" ," + e.getCause());
		}
	}
	
	/**
	 * Metodo clica em um elemento
	 * 
	 * @param By locator
	 * @param String value
	 * 
	 */
	public void clickWithReload(By locator) {

		try {

			// Aguarda elemento ser exibido e clica
			waitLoadPage();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			System.out.println("elemento disponivel para click?: "+element.isEnabled());
			Optional.ofNullable(element)
					.ifPresent(WebElement->{
						
						WebElement.click();
			});
			waitLoadPage();

		} catch (Exception e) {
			LOG.error("[ActionMethods] Erro ao clicar em: "+locator+" ," + e.getCause());
		}
	}
	
	/**
	 * Metodo verifica se elemento esta presente
	 *
	 * @param By locator
	 */
	public boolean isElementPresent(By locator) {
		
		boolean present = false;
		try {
			
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			present = driver.findElements(locator).size() > 0;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			LOG.error("[ActionMethods] Erro aguardar elemento: "+ locator +", estar presente: "+ e.getCause());
		}
		
		return present;
	}
	
	/**
	 * Metodo executa scroll down
	 */
	public void scrollDown() {
		
		try {
			
			// Scroll
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", "");
		} catch (Exception e) {
			LOG.error("[ActionMethods] Error to scroll down: " + e.getCause());
		}
	}
	
	/**
	 * Metodo responsavel por aguardar a pagina carregar
	 * 
	 */
	public void waitLoadPage() {
		
        try {
        	
        	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    		Thread.sleep(1000);
            ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                        }
                    };
        	
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable e) {
        	LOG.error("[ActionMethods] error wait for page loaded: "+ e.getCause());
        }
	}
	
	/**
	 * Metodo retorna para pagina anterior
	 * 
	 */
	public void previousPage() {
		
		driver.navigate().back();
		waitLoadPage();
	}
	
	public void switchToModal(String frameId) {

		try {
			driver.switchTo().frame(frameId);
		} catch (Exception e) {
			LOG.error("[ActionMethods] erro ao encontrar modal: " + e.getCause());
		}
	}
	
	/**
	 * Abre home page pelo link
	 * 
	 */
	public void openPage(String url) {
		driver.get(url);
		waitLoadPage();
	}
	
	public void switchToMainPage() {
		
		waitLoadPage();
		driver.switchTo().defaultContent();
	}
}

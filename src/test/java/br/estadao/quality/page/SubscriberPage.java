package br.estadao.quality.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.estadao.quality.util.ActionMethods;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class SubscriberPage {
	
	/** The action. */
	private ActionMethods action;
	
	/** The driver. */
	private WebDriver driver;
	
	/**
	 * Inicializa home page.
	 *
	 * @param driver the driver
	 */
	public SubscriberPage(WebDriver driver) {
		this.action = new ActionMethods(driver);
		this.driver = driver;
	}
	
	/**
	 * Click entry.
	 */
	public void clickEntry() {
		openSubscriberPage();
		action.switchToModal("paywall-iframe-content");
		action.clickWithReload(By.xpath("//b[contains(text(),'entre aqui para ler a matéria')]"));
	}

	
	/**
	 * Abre homepage.
	 */
	public void openSubscriberPage() {
		action.openPage("https://economia.estadao.com.br/noticias/geral,por-que-nao-tres-em-uma,70002648617");
	}

}

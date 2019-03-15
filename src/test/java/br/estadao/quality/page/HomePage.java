package br.estadao.quality.page;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.estadao.quality.util.ActionMethods;

// TODO: Auto-generated Javadoc
/**
 * The Class HomePage.
 */
public class HomePage {
	
	/** The action. */
	private ActionMethods action;
	
	/** The driver. */
	private WebDriver driver;
	
	/**
	 * Inicializa home page.
	 *
	 * @param driver the driver
	 */
	public HomePage(WebDriver driver) {
		this.action = new ActionMethods(driver);
		this.driver = driver;
	}

	/**
	 * Seleciona mais de uma noticia de acordo com a quantidade informada.
	 *
	 * @param newsQty the news qty
	 */
	public void selectFreeNews(int newsQty) {

		boolean isButtonPresent = false;

		// seleciona varias noticias de acordo com a quantidade informada
		for (int i = 1; isButtonPresent == false ; i++) {
			
			selectNewsAccordingToIndex(i);
			
			// verifica se o modal com o botao foi exibido para sair do loop
			isButtonPresent = signInModal();
			
			if(false == isButtonPresent) {
				backPage();
			}
		}
	}
	
	/**
	 * Método seleciona noticia de acordo com index.
	 *
	 * @param index the index
	 */
	public void selectNewsAccordingToIndex(int index) {
		
		action.switchToMainPage();
		
		//closeAdvertising();k'
		closeAlertModal();
		
		// seleciona noticia de acordo com index
		action.scrollDown();
		boolean isnewsPresent = action.isElementPresent(By.xpath("(.//h3/a)["+index+"]"));
		Optional.ofNullable(isnewsPresent)
				.filter(news -> news.equals(true))
				.map(news -> {
					action.clickWithReload(By.xpath("(.//h3/a)["+index+"]"));
					System.out.println("noticia: "+index);
					return true;
				}).orElse(false);
	}
	
	/**
	 * Fecha modal de alerta.
	 * 
	 */
	public void closeAlertModal() {
		
		boolean isModalPresent = action.isElementPresent(By.xpath(".//*[@class='push-body nao-assinante']"));
		
		Optional.ofNullable(isModalPresent)
				.filter(modal -> modal.equals(true))
				.map(modal -> {
					action.clickWithReload(By.xpath(".//input[@id='welcome-deny']"));
					return true;
				}).orElse(false);
	}

	/**
	 * Sign in modal.
	 *
	 * @return true, if successful
	 */
	public boolean signInModal() {
	
	action.switchToModal("paywall-iframe-content");
	boolean isButtonPresent = action.isElementPresent(By.xpath("//a[contains(@class,'btn email')]"));

	return	Optional.ofNullable(isButtonPresent)
				.filter(modal -> modal.equals(true))
				.map(modal -> {
					action.clickWithReload(By.xpath("//a[contains(@class,'btn email')]"));
					
					return true;
				}).orElseGet(()->{ 
					return false;
					});
	}
	
	public boolean closeAdvertising() {
		
		boolean isAdvertisingPresent = action.isElementPresent(By.xpath(""));
		
		return	Optional.ofNullable(isAdvertisingPresent)
					.filter(modal -> modal.equals(true))
					.map(modal -> {
						
						action.clickWithReload(By.xpath(""));
					
						return true;
					}).orElseGet(()->{ 
						return false;
						});
	}
	
	/**
	 * Método retorna para pagina anterior.
	 */
	public void backPage() {
		action.previousPage();
	}
	
	/**
	 * Abre homepage.
	 */
	public void openHomePage() {
		action.openPage("https://www.estadao.com.br");
	}

}

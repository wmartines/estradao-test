package br.estadao.quality.page;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.estadao.quality.param.SignUpParam;
import br.estadao.quality.type.AuthenticationUserType;
import br.estadao.quality.util.ActionMethods;

// TODO: Auto-generated Javadoc
/**
 * The Class SignInPage.
 */
public class SignInPage {
	
	/** The action. */
	private ActionMethods action;
	
	/** The driver. */
	private WebDriver driver;
	
	/**
	 * Construtor.
	 *
	 * @param driver the driver
	 */
	public SignInPage(WebDriver driver) {
		
		this.action = new ActionMethods(driver);
		this.driver = driver;
	}
	
	/**
	 * Metodo clica no botao cadastro.
	 */
	public void clickSignUpButton() {
		
		//.//*[@href='cadastrar']
		action.clickWithReload(By.xpath(".//a[contains(text(), 'Cadastre-se')]"));
	}
	
	/**
	 * Metodo clica no botao cadastro.
	 */
	public void finishSignUp() {
		
		action.clickWithReload(By.xpath("//input[@value='Cadastrar']"));
	}
	
	/**
	 * Metodo preenche formulario de cadastro.
	 *
	 * @param param the param
	 */
	public void populateSignUpForm(SignUpParam param) {
		
		Optional.ofNullable(param)
				.ifPresent(parameter ->{
			action.input(By.xpath("//input[@placeholder='Nome']"), parameter.getName());
			action.input(By.xpath("//input[@placeholder='Sobrenome']"), parameter.getSurname());
			action.input(By.xpath("//input[@placeholder='CPF']"), parameter.getDocumentId());
			action.input(By.xpath("//input[@placeholder='E-mail']"), parameter.getEmail());
			action.input(By.xpath("//input[@placeholder='Senha']"), parameter.getPassword());
		});
	}
	
	/**
	 * Efetua cadastro.
	 *
	 * @param param the param
	 */
	public void signUp(SignUpParam param) {
		driver.switchTo().defaultContent();
		clickSignUpButton();
		populateSignUpForm(param);
		finishSignUp();
	}
	
	/**
	 * Metodo efetua login.
	 *
	 * @param user the user
	 */
	public void login(AuthenticationUserType user) {
		
		// retporna para o frame principal caso esteja fora
		driver.switchTo().defaultContent();
		
		// popula os campos e clica no botao entrar
		action.input(By.xpath("//input[@placeholder='Usuário']"), user.getUser());
		action.input(By.xpath("//input[@placeholder='Senha']"), user.getPassword());
		action.clickWithReload(By.xpath("//input[@value='Entrar']"));
	}

}

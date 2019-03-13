package br.estadao.quality.test;

import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import br.estadao.quality.page.HomePage;
import br.estadao.quality.page.SignInPage;
import br.estadao.quality.param.SignUpParam;
import br.estadao.quality.type.AuthenticationUserType;
import br.estadao.quality.type.EnvironmentType;
import br.estadao.quality.util.ConfigMethods;
import br.estadao.quality.util.EstadaoUtils;
import br.estadao.quality.page.SubscriberPage;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadaoTest.
 */
public class EstadaoTest {
	
	/** The driver. */
	private WebDriver driver;
	
	/** The home page. */
	private HomePage homePage;
	
	/** The sign in page. */
	private SignInPage signInPage ;
	
	/** The subscriber page. */
	private SubscriberPage subscriberPage;
	
	/**
	 * Setup test.
	 */
	@BeforeClass
	public void setUp() {
		
		driver = ConfigMethods.setUp(EnvironmentType.ESTADAO_PROD);
		homePage = new HomePage(driver);
		signInPage = new SignInPage(driver);
		subscriberPage = new SubscriberPage(driver);
	}
	
	/**
	 * Tear down.
	 */
	@AfterClass
	public void tearDown() {
		ConfigMethods.tearDown();
	}
	
	/**
	 * Teste seleciona o limite de noticias gratuitas.
	 */
	@Test
	public void registerNewUser() {
		
		String randomID = UUID.randomUUID().toString();
		
		SignUpParam signUpParam = new SignUpParam();
		signUpParam.setName(randomID);
		signUpParam.setSurname(randomID);
		signUpParam.setDocumentId(EstadaoUtils.generateRandomNumber().toString()+"9");
		signUpParam.setEmail(randomID+"@automation.com");
		signUpParam.setPassword("123456");
		
		homePage.selectFreeNews(5);
		signInPage.signUp(signUpParam);
		homePage.openHomePage();
		Assert.assertTrue(driver.getPageSource().contains(signUpParam.getName()));
	}
	
	/**
	 * Teste HardPaywall com ex assinante.
	 */
	@Test
	public void HardPaywall() {
		
		homePage.selectFreeNews(5);
		signInPage.login(AuthenticationUserType.EX_SUBSCRIBER);
		
	}
	
	/**
	 * Teste HardPaywall com ex assinante.
	 */
	@Test
	public void subscriberContent() {
		
		subscriberPage.clickEntry();
		signInPage.login(AuthenticationUserType.EX_SUBSCRIBER2);
		
	}
}

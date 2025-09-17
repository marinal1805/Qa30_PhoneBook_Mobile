package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class AddNewContactTests extends AppiumConfig {


    @BeforeClass
    public void preCondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("tets@gmail.com")
                        .password("Mm12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
    }

    @Test
    public void createNewContactSuccess() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("Simon")
                .lastName("Wow" + i)
                .email("wow" + i + "@gmail.com")
                .phone("6789456" + i)
                .address("Haifa")
                .description("The best friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByName(contact.getName(), contact.getLastName());
    }


    @Test
    public void createNewContactSuccessReq() {

    }

    @Test
    public void createNewContactWithEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Wow")
                .email("wow@gmail.com")
                .phone("678945622222")
                .address("Haifa")
                .description("The best friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageHasText("{name=must not be blank}");

    }

    @AfterClass
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }

}
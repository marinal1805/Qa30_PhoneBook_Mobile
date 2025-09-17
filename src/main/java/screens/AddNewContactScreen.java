package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen {
    public AddNewContactScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    AndroidElement nameEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    AndroidElement lastNameEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement emailEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    AndroidElement phoneEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    AndroidElement addressEditText;

    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    AndroidElement descriptionEditText;

    @FindBy(xpath = "//*[@text='CREATE']")
    AndroidElement createButton;


    public AddNewContactScreen fillContactForm(Contact contact) {
        should(nameEditText, 5);
        type(nameEditText, contact.getName());
        type(lastNameEditText, contact.getLastName());
        type(emailEditText, contact.getEmail());
        type(phoneEditText, contact.getPhone());
        type(addressEditText, contact.getAddress());
        type(descriptionEditText, contact.getDescription());
        return this;
    }

    public ContactListScreen submitContactForm() {
        createButton.click();
        return new ContactListScreen(driver);
    }

    public AddNewContactScreen submitContactFormNegative() {
        createButton.click();
        return this;
    }

    public AddNewContactScreen isErrorMessageHasText(String text) {
        checkAlertText(text);
        return this;
    }

}
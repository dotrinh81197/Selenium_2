package com.auto.page.imp.ios;

import com.auto.model.UserModel;
import com.auto.page.ILoginPage;
import com.auto.utils.ExecutionContext;
import com.logigear.element.MobileElement;
import io.qameta.allure.Step;

public class IOSLoginPage implements ILoginPage {

    private final MobileElement emailTextBox = new MobileElement("accid=email");


    @Step("Login to System")
    @Override
    public void login(UserModel user) {
        emailTextBox.setValue(user.getUsername());
        ExecutionContext.setUser(user);
    }
}

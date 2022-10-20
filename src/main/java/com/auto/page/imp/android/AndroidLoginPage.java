package com.auto.page.imp.android;

import com.auto.model.UserModel;
import com.auto.page.ILoginPage;
import com.auto.utils.ExecutionContext;
import com.logigear.element.MobileElement;
import io.qameta.allure.Step;

public class AndroidLoginPage implements ILoginPage {

    private final MobileElement emailTextBox = new MobileElement("id=com.demo.beta.staging:id/email");

    @Step("Login to System")
    @Override
    public void login(UserModel user) {
        emailTextBox.enter(user.getUsername());
        ExecutionContext.setUser(user);
    }
}

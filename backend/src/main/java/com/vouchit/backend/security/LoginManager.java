package com.vouchit.backend.security;


import com.vouchit.backend.model.enums.ClientType;
import com.vouchit.backend.service.client.ClientService;
import com.vouchit.backend.service.client.CustomerServiceImpl;
import com.vouchit.backend.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.vouchit.backend.model.enums.ClientType.COMPANY;
import static com.vouchit.backend.model.enums.ClientType.CUSTOMER;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
// singleton class - only one instance of this class will be created
public class LoginManager {

    private boolean isLoggedIn = false;
    private LoginManager loginManager;

    private LoginManager() {
    }

    public LoginManager getInstance() {
        if (loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }


    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


    public ClientService login(String email, String password, ClientType clientType) {
        switch (clientType) {
//            case COMPANY:
//                return new CompanyServiceImpl();
//            case CUSTOMER:
//                return new CustomerServiceImpl();
            default:
                return null;
        }

    }

    // bean of password encoder
}

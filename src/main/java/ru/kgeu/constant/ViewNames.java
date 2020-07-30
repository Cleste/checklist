package ru.kgeu.constant;

public interface ViewNames {
    interface Main {
        String VIEW_NAME = "main";
        String PAGE_URL = "/";
    }

    interface Students {
        String VIEW_NAME = "students";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
    }
    interface Notify {
        String PAGE_URL = Main.PAGE_URL + "notify";
    }

    interface User {
        String VIEW_NAME = "users";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
    }

    interface AccountActivation {
        String VIEW_NAME = "activation";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
    }

    interface CheckList {
        String VIEW_NAME = "checklist";
        String SINGLE_VIEW_NAME = "checklist_single";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
    }

    interface Indebtedness {
        String VIEW_NAME = "indebtedness";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
    }

    interface Login {
        String VIEW_NAME = "login";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
        String SUCCESSFULLY_LOGOUT_PAGE_URL = PAGE_URL + "?logout=true";
    }

    interface Logout {
        String PAGE_URL = Main.PAGE_URL + "logout";
    }
}

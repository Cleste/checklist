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
    interface User {
        String VIEW_NAME = "users";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
    }
    interface Login {
        String VIEW_NAME = "login";
        String PAGE_URL = Main.PAGE_URL + VIEW_NAME;
    }
    interface Logout {
        String PAGE_URL = Main.PAGE_URL + "logout";
    }
}

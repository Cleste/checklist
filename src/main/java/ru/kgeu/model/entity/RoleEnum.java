package ru.kgeu.model.entity;

public enum RoleEnum {
    ROLE_DEAN("Деканат"),
    ROLE_GRADUATING_DEPARTMENT("Выпускающая кафедра"),
    ROLE_ACCOUNTANT("Бухгалтерия"),
    ROLE_DORMITORY_WORKER("Общежитие"),
    ROLE_INSTITUTE_WORKER("Институт"),
    ROLE_CPT("ЦПТ"),
    ROLE_OMP_KB("ОМПиКБ");

    private String name;

    RoleEnum(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

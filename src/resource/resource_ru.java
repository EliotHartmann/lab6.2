package resource;

import java.util.ListResourceBundle;

public class resource_ru extends ListResourceBundle{

    private static final Object[][] contents = {
            {"Language", "Русский"},
            {"Server", "Сервер"},
            {"Name", "Имя"},
            {"Age", "Возраст"},
            {"Collection control", "Управление коллекцией"},
            {"YELLOW", "Желтый"},
            {"GREEN", "Зеленый"},
            {"BLUE", "Синий"},
            {"ADD", "Добавить"},
            {"help", "Помощь"},
            {"info", "Инфо"},
            {"save", "Сохранить"},
            {"load", "Загрузить"},
            {"login", "Логин"},
            {"ban", "Забанить"}
    };


    public Object[][] getContents(){
        return contents;
    }
}

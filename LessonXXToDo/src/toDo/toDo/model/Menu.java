package toDo.toDo.model;

public enum Menu {

    LIST(1), ADD(2), EDIT(3), FIND(4), REMOVE(5), SORT(6), EXIT(7);

    private int menuItem;

    Menu(int menuItem) {
        this.menuItem = menuItem;
    }

    public static void printMenu() {
        Menu[] menu = Menu.values();
        System.out.println("=============================================================================");
        for (int i = 0; i < Menu.values().length; i++) {
            System.out.print((menu[i].menuItem) + " - " + menu[i] + " | ");
        }
        System.out.println(" ");
        System.out.println("=============================================================================");
    }

    public int getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(int menuItem) {
        this.menuItem = menuItem;
    }
}
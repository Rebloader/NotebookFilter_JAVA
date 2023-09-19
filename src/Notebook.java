import java.util.*;

public class Notebook {
    private String model;
    private int ramSizeGB;
    private int storageGB;
    private String os;
    private String color;

    public Notebook(String model, int ramSizeGB, int storageGB, String os, String color) {
        this.model = model;
        this.ramSizeGB = ramSizeGB;
        this.storageGB = storageGB;
        this.os = os;
        this.color = color;
    }

    public void displayInfo() {
        System.out.println("Модель: " + model);
        System.out.println("ОЗУ: " + ramSizeGB + " ГБ");
        System.out.println("Объем ЖД: " + storageGB + " ГБ");
        System.out.println("Операционная система: " + os);
        System.out.println("Цвет: " + color);
    }

    public String getModel() {
        return model;
    }

    public int getRamSizeGB() {
        return ramSizeGB;
    }

    public int getStorageGB() {
        return storageGB;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    public static List<Notebook> filterNotebooks(List<Notebook> notebooks, Map<Integer, Object> filterCriteria) {
        List<Notebook> filteredNotebooks = new ArrayList<>();

        for (Notebook notebook : notebooks) {
            boolean meetsCriteria = true;

            for (Map.Entry<Integer, Object> entry : filterCriteria.entrySet()) {
                int criterion = entry.getKey();
                Object criterionValue = entry.getValue();

                switch (criterion) {
                    case 1:
                        int minRamSize = (int) criterionValue;
                        if (notebook.getRamSizeGB() < minRamSize) {
                            meetsCriteria = false;
                        }
                        break;
                    case 2:
                        int minStorageSize = (int) criterionValue;
                        if (notebook.getStorageGB() < minStorageSize) {
                            meetsCriteria = false;
                        }
                        break;
                    case 3:
                        String requiredOs = (String) criterionValue;
                        if (!requiredOs.isEmpty() && !notebook.getOs().equalsIgnoreCase(requiredOs)) {
                            meetsCriteria = false;
                        }
                        break;
                    case 4:
                        String requiredColor = (String) criterionValue;
                        if (!requiredColor.isEmpty() && !notebook.getColor().equalsIgnoreCase(requiredColor)) {
                            meetsCriteria = false;
                        }
                        break;
                }

                if (!meetsCriteria) {
                    break;
                }
            }

            if (meetsCriteria) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }

    public static void main(String[] args) {
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("HP Pavilion", 8, 512, "Windows 10", "Grey"));
        notebooks.add(new Notebook("Dell Inspiron", 16, 256, "Linux", "Black"));
        notebooks.add(new Notebook("Asus ZenBook", 16, 512, "Windows 11", "Silver"));
        notebooks.add(new Notebook("Lenovo ThinkPad", 8, 256, "Windows 10", "Black"));
        notebooks.add(new Notebook("MacBook Air", 8, 256, "macOS", "Silver"));
        notebooks.add(new Notebook("MacBook Pro", 16, 512, "macOS", "White"));
        notebooks.add(new Notebook("Dell XPS", 32, 1024, "Linux", "Black"));
        notebooks.add(new Notebook("Asus ROG Strix", 32, 1024, "Linux", "Black"));
        notebooks.add(new Notebook("Huawei MateBook", 8, 256, "Windows 11", "Grey"));
        notebooks.add(new Notebook("Lenovo Legion", 16, 512, "Windows 10", "White"));

        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> filterCriteria = new HashMap<>();
        boolean continueFiltering = true;

        while (continueFiltering) {
            System.out.println("Выберите критерий фильтрации (0 - завершить ввод):");
            System.out.println("1 - Минимальный объем ОЗУ (ГБ)");
            System.out.println("2 - Минимальный объем ЖД (ГБ)");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");

            int criterion = scanner.nextInt();
            scanner.nextLine();

            if (criterion == 0) {
                continueFiltering = false;
            } else if (criterion >= 1 && criterion <= 4) {
                Object criterionValue;

                if (criterion == 1 || criterion == 2) {
                    System.out.print("Введите минимальное значение: ");
                    criterionValue = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.print("Введите требуемое значение: ");
                    criterionValue = scanner.nextLine();
                }

                filterCriteria.put(criterion, criterionValue);
            } else {
                System.out.println("Неверный номер критерия. Попробуйте еще раз.");
            }
        }

        List<Notebook> filteredNotebooks = filterNotebooks(notebooks, filterCriteria);

        System.out.println("Ноутбуки, удовлетворяющие заданным критериям:");
        for (Notebook notebook : filteredNotebooks) {
            notebook.displayInfo();
            System.out.println();
        }
    }
}

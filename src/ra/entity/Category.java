package ra.entity;

import ra.bussiness.ICategory;

import java.io.Serializable;
import java.util.Scanner;

import static ra.presentation.CategoryPresentation.listCategory;

public class Category implements ICategory, Serializable {
    private int id;
    private String name;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.id = inputId(scanner);
        this.name = inputName(scanner);
        this.description = inputDescription(scanner);
        this.status = inputStatus(scanner);
    }

    public int inputId(Scanner scanner) {
        do {
            System.out.println("Nhập mã danh mục:");
            try {
                int id = Integer.parseInt(scanner.nextLine());

                if (id > 0) {
                    boolean isId = false;
                    for (Category category : listCategory) {
                        if (category.getId() == id) {
                            isId = true;
                            break;
                        }
                    }

                    if (isId) {
                        System.out.println("Mã danh mục đã tồn tại! vui lòng nhập lại");
                    } else {
                        return id;
                    }
                } else {
                    System.out.println("Mã danh mục có giá trị lớn hơn 0");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập lại kiểu số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (true);
    }

    public String inputName(Scanner scanner) {
        do {
            System.out.println("Tên danh mục:");
            String name = scanner.nextLine();

            if (name.length() >= 6 && name.length() <= 30) {
                boolean isName = false;
                for (Category category : listCategory) {
                    if (category.getName().equals(name)) {
                        isName = true;
                        break;
                    }
                }

                if (isName) {
                    System.out.println("Tên danh mục đã tồn tại! vui lòng nhập lại");
                } else {
                    return name;
                }
            } else {
                System.out.println("Tên danh mục có từ 6-30 kí tự");
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        do {
            System.out.println("Mô tả danh mục:");
            String description = scanner.nextLine();

            if (description.trim().isEmpty()) {
                System.out.println("không được bỏ trống mô tả!");
            } else {
                return description;
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        do {
            System.out.println("Trạng thái danh mục:");
            String status = scanner.nextLine();

            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.out.println("Trạng thái danh mục chỉ nhận true/false!");
            }
        } while (true);
    }
    public void updateData(Scanner scanner) {
        boolean isExitUpdate = true;
        do {
            System.out.println("---------------Update-------------");
            System.out.println("1. Update id");
            System.out.println("2. Update name");
            System.out.println("3. Update description");
            System.out.println("4. Update status");
            System.out.println("5. Exit");
            System.out.println("lựa chon của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        this.id = inputId(scanner);
                        break;
                    case 2:
                        this.name = inputName(scanner);
                        break;
                    case 3:
                        this.description = inputDescription(scanner);
                        break;
                    case 4:
                        this.status = inputStatus(scanner);
                        break;
                    case 5:
                        isExitUpdate = false;
                        break;
                    default:
                        System.out.println("Nhập lựa chọn từ 1-5");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhâp số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        } while (isExitUpdate);
    }
    @Override
    public void displayData() {
        System.out.printf("mã danh mục: %d - tên danh mục: %s - mô tả danh mục: %s - trạng thái danh mục: %s\n",
                this.id, this.name, this.description, this.status ? "Hoạt động" : "Không hoạt động");
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

}

package ra.presentation;

import ra.bussinessIpm.CategoryIpm;
import ra.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryPresentation {
    public static List<Category> listCategory = new ArrayList<>();

    public static void categoryMenu(Scanner scanner) {
        listCategory = CategoryIpm.readCategoryFromFile();
        boolean isExitCategory = true;
        do {
            System.out.println("-------- QUẢN LÝ DANH MỤC------\n" +
                    "1. Thêm mới danh mục\n" +
                    "2. Cập nhật danh mục\n" +
                    "3. Xóa danh mục\n" +
                    "4. Tìm kiếm danh mục theo tên danh mục\n" +
                    "5. Thống kê số lượng sp đang có trong danh mục\n" +
                    "6. Quay lại");
            System.out.println("Lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        CategoryIpm.addCategory(scanner);
                        CategoryIpm.writeCategorytoFile();
                        break;
                    case 2:
                        CategoryIpm.updateCategory(scanner);
                        CategoryIpm.writeCategorytoFile();
                        break;
                    case 3:
                        CategoryIpm.deleteCategory(scanner);
                        CategoryIpm.writeCategorytoFile();
                        break;
                    case 4:
                        CategoryIpm.searchCategory(scanner);
                        break;
                    case 5:
                        CategoryIpm.quantityProduct();
                        break;
                    case 6:
                        isExitCategory = false;
                        break;
                    default:
                        System.out.println("vui lòng lựa chon 1 - 6!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        } while (isExitCategory);
    }
}

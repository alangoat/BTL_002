package ra.presentation;

import ra.bussinessIpm.ProductIpm;
import ra.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductPresentation {
    public static List<Product> listProduct = new ArrayList<>();
    public static void productMenu(Scanner scanner) {
        listProduct = ProductIpm.readProductFromFile();
        boolean isExitProduct = true;
        do {
            System.out.println("-------- QUẢN LÝ SẢN PHẨM ----------\n" +
                    "1. Thêm mới sản phẩm\n" +
                    "2. Cập nhật sản phẩm\n" +
                    "3. Xóa sản phẩm\n" +
                    "4. Hiển thị sản phẩm theo tên A-Z\n" +
                    "5. Hiển thị sản phẩm theo lợi nhuận từ cao-thấp\n" +
                    "6. Tìm kiếm sản phẩm\n" +
                    "7. Quay lại");
            System.out.println("Lựa chọn của bạn:");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        ProductIpm.addProduct(scanner);
                        ProductIpm.writeProducttoFile();
                        break;
                    case 2:
                        ProductIpm.updateProduct(scanner);
                        ProductIpm.writeProducttoFile();
                        break;
                    case 3:
                        ProductIpm.deleteProduct(scanner);
                        ProductIpm.writeProducttoFile();
                        break;
                    case 4:
                        ProductIpm.softProductByName();
                        break;
                    case 5:
                        ProductIpm.softProductByProfit();
                        break;
                    case 6:
                        ProductIpm.searchProduct(scanner);
                        break;
                    case 7:
                        isExitProduct = false;
                        break;
                    default:
                        System.out.println("vui lòng lựa chon 1 - 7!");
                }
            } catch (NumberFormatException e) {
                System.err.println("vui lòng nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }while (isExitProduct);
    }
}

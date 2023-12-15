package ra.bussinessIpm;

import ra.entity.Category;
import ra.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.ProductPresentation.listProduct;

public class ProductIpm {
    public static void addProduct(Scanner scanner) {
        boolean isExist = true;
        int numAdd = 0;
        System.out.println("Nhập số lượng sản phẩm bạn muốn thêm:");
        do {
            try {
                numAdd = Integer.parseInt(scanner.nextLine());
                isExist = false;
            } catch (NumberFormatException e) {
                System.err.println("Nhập số nguyên!");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        } while (isExist);

        for (int i = 0; i < numAdd; i++) {
            Product product = new Product();
            product.inputData(scanner);
            listProduct.add(product);
        }
    }
    public static void updateProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật:");
        String updateId = scanner.nextLine();
        boolean isUpdate = false;

        for (Product product : listProduct) {
            if (product.getId().equals(updateId)) {
                product.updateData(scanner);
                isUpdate = true;
                break;
            }
        }

        if (!isUpdate) {
            System.out.println("Không tồn tại mã sản phẩm!");
        }
    }
    public static void deleteProduct(Scanner scanner) {
        System.out.println("Mã danh mục cần xóa:");
        String deleteId = scanner.nextLine();
        boolean isDelete = false;

        for (Product product : listProduct) {
            if (product.getId().equals(deleteId)) {
                listProduct.remove(product);
                isDelete = true;
                System.out.println("Xóa danh mục thành công!");
                break;
            }
        }

        if (!isDelete) {
            System.out.println("Không tồn tại mã sản phẩm!");
        }
    }
    public static void softProductByName() {
        listProduct.stream().sorted(Comparator.comparing(Product::getName)).forEach(System.out::println);
    }
    public static void softProductByProfit() {
        listProduct.stream().sorted(Comparator.comparing(Product::getProfit).reversed()).forEach(System.out::println);
    }
    public static void searchProduct(Scanner scanner) {
        System.out.println("Tên sản phẩm tìm kiếm:");
        String searchName = scanner.nextLine();

        listProduct.stream().filter(product -> product.getName().contains(searchName)).forEach(System.out::println);
    }
    public static List<Product> readProductFromFile() {
        List<Product> listProductRead = null;
        File file = new File("products.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listProductRead = (List<Product>) ois.readObject();
        }catch (Exception ex){
            listProductRead = new ArrayList<>();
        }finally {
            if (fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois!=null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listProductRead;
    }
    public static void writeProducttoFile() {
        File file = new File("products.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listProduct);
            oos.flush();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

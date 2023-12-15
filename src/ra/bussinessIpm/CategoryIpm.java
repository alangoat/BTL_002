package ra.bussinessIpm;

import ra.entity.Category;
import ra.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.presentation.CategoryPresentation.listCategory;
import static ra.presentation.ProductPresentation.listProduct;

public class CategoryIpm {
    public static void addCategory(Scanner scanner) {
        boolean isExist = true;
        int numAdd = 0;
        System.out.println("Nhập số lượng danh mục muốn thêm:");
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
            Category category = new Category();
            category.inputData(scanner);
            listCategory.add(category);
        }
    }

    public static void updateCategory(Scanner scanner) {
        System.out.println("Nhập mã danh mục cần cập nhật:");
        int updateId = Integer.parseInt(scanner.nextLine());
        boolean isUpdate = false;

        for (Category category : listCategory) {
            if (category.getId() == updateId) {
                category.updateData(scanner);
                isUpdate = true;
                break;
            }
        }

        if (!isUpdate) {
            System.out.println("Không tồn tại mã danh mục!");
        }
    }



    public static void deleteCategory(Scanner scanner) {
        System.out.println("Mã sản phẩm cần xóa:");
        int deleteId = Integer.parseInt(scanner.nextLine());
        boolean isDelete = false;

        for (Category category : listCategory) {
            if (category.getId() == deleteId) {
                listCategory.remove(category);
                isDelete = true;
                System.out.println("Xóa sản phẩm thành công!");
                break;
            }
        }
        if (!isDelete) {
            System.out.println("Không tồn tại mã sản phẩm!");
        }
    }

    public static void searchCategory(Scanner scanner) {
        System.out.println("Tên danh mục tìm kiếm:");
        String searchName = scanner.nextLine();

        listCategory.stream().filter(category -> category.getName().contains(searchName)).forEach(System.out::println);
    }

    public static void quantityProduct() {
        for (Category category: listCategory) {
            long countCatagory = listProduct.stream().filter(product -> product.getCategoryId()==category.getId()).count();
            System.out.printf("danh mục %s có %d sản phẩm\n", category.getName(), countCatagory);
        }
    }

    public static List<Category> readCategoryFromFile() {
        List<Category> listCategoryRead = null;
        File file = new File("categories.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listCategoryRead = (List<Category>) ois.readObject();
        }catch (Exception ex){
            listCategoryRead = new ArrayList<>();
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
        return listCategoryRead;
    }
    public static void writeCategorytoFile() {
        File file = new File("categories.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listCategory);
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

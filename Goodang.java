import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Goodang {
    private static LocalDate now = LocalDate.now();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private static String tanggalwaktu = now.format(formatter);
    private static Scanner sc = new Scanner(System.in);
    private static String[] goodsName = new String[100];
    private static int[] goodsStock = new int[100];
    private static String nama;
    private static int itemCount = 0;
    private static int jumlah;
    private static int index = 0;
    private static boolean input = true;
 
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        center("Goodang");
        center("Halo, harap teliti dalam memasukkan nama dan jumlah barang");
        center("Selamat bekerja ><");
        System.out.println("____________________________________________________________");      
        masukMenu();
    
        System.out.println("____________________________________________________________");
        center("Terima kasih telah menggunakan Goodang");
        center("Semangat bekerja ><");
        System.out.println("____________________________________________________________");
    }
    private static void masukMenu() {
        while (input) {
            System.out.printf("Menu\n1. Pengelolaan Gudang\n2. Lihat Stok\n3. Exit\nEnter menu : ");
            int menu = sc.nextInt();
            sc.nextLine();
            System.out.println();
            if (menu == 1) {
                boolean operasi = true;
                while (operasi) {
                    System.out.println("Masukkan nama barang atau klik X untuk exit: ");
                    nama = sc.nextLine();
                    if (nama.equalsIgnoreCase("X")) {
                        operasi = false;
                        break;
                    } else if (nama.isEmpty()) {
                        continue;
                    }
                    boolean inputJumlah = true;
                    while (inputJumlah) {
                        System.out.println("Masukkan jumlah barang: ");
                        String jumlahBarang = sc.nextLine();
                        try {
                            if (Integer.parseInt(jumlahBarang) <= 0) {
                                System.out.println("Masukkan jumlah!");
                                continue;
                            } else if (Integer.parseInt(jumlahBarang) > 0) {
                                jumlah = Integer.parseInt(jumlahBarang);
                                break;
                            }                              
                        } catch (Exception e) {
                            System.out.println("Input jumlah harus berupa angka!");
                            continue;
                        }
                    }
                    System.out.println();
                        operasi(); 
                }
            } else if (menu == 2) {
                view();
                continue;
            } else if (menu == 3){
                input = false;
                break;
            }
        }         
    }
    private static void operasi() {
        System.out.printf("Stok\n1. Masuk\n2. Keluar\n");
        index = findItemIndex(goodsName, itemCount, nama);
        boolean op = true;
        while (op) {
            System.out.println("Klik 1 untuk memasukkan barang atau 2 untuk mengeluarkan barang: ");
            String operasi = sc.next();
            sc.nextLine();
            System.out.println();
                if (operasi.equals("1")) { 
                    increase(index);
                    System.out.println();
                    break;
                } else if (operasi.equals("2")) {
                    decrease(index);
                    System.out.println();
                    break;  
                } else if (!operasi.equals("1") && !operasi.equals("2")) {
                    continue;
                }            
        }
                return;
    }
    private static int findItemIndex(String[] goodsName, int itemCount, String name) {
        for (int i = 0; i < itemCount; i++) {
            if (goodsName[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
    private static int increase(int x) {
        if (index != -1) {
            goodsStock[index] += jumlah;
            if (jumlah == 1) {
                System.out.println(tanggalwaktu);
                System.out.println(jumlah + " pc " + goodsName[itemCount-1] + " telah ditambahkan ke dalam Gudang");
            } else {
                System.out.println(tanggalwaktu);
                System.out.println(jumlah + " pcs " + goodsName[itemCount-1] + " telah ditambahkan ke dalam Gudang");
            }
        } else {
            if (itemCount < 100) {
                goodsName[itemCount] = nama;
                goodsStock[itemCount] = jumlah;
                itemCount++;
                if (jumlah == 1) {
                    System.out.println(tanggalwaktu);
                    System.out.println(jumlah + " pc " + goodsName[itemCount-1] + " telah ditambahkan ke dalam Gudang");
                } else {
                    System.out.println(tanggalwaktu);
                    System.out.println(jumlah + " pcs " + goodsName[itemCount-1] + " telah ditambahkan ke dalam Gudang");
                }
            } else {
                System.out.println("Gudang penuh! Tidak dapat menambahkan barang baru.\n");
            }
        }
        return x;
    }
    private static int decrease(int x) {
        index = findItemIndex(goodsName, itemCount, nama);
            if (index != -1) {
                goodsStock[index] -= jumlah;
                if (jumlah == 1) {
                    System.out.println(tanggalwaktu);
                    System.out.println(jumlah + " pc " + goodsName[itemCount-1] + " telah dikurangkan dari dalam Gudang");
                } else {
                    System.out.println(tanggalwaktu);
                    System.out.println(jumlah + " pcs " + goodsName[itemCount-1] + " telah dikurangkan dari dalam Gudang");
                }
            } else {
                System.out.println("Tidak ada barang yang dapat dikurangkan");
            }
        return x;
    }
    private static void view() {
        System.out.println(tanggalwaktu);
        System.out.println("Daftar Stok Barang:");
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%-10s: %s\n", goodsName[i], goodsStock[i]);
        }
        System.out.println();
        boolean view = true;
        while (view) {
            System.out.println("Klik O untuk melihat stok atau X untuk exit");
            String exit = sc.nextLine();           
                if (exit.equalsIgnoreCase("O")) {
                    view();
                    break;
                } else if (exit.equalsIgnoreCase("X")) {
                    break;
                } else {
                    continue;
                }
        }
    }
    private static void center(String text) {
        int tengah = (60 - text.length()) / 2;       
        for (int i = 0; i < tengah; i++) {
            System.out.print(" ");
        }        
        System.out.println(text);
    }
}

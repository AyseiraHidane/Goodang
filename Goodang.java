
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*; // yg J J ini semua komponen dari java swing

public class Goodang {
    private ArrayList<String> namabarang = new ArrayList<>();
    private ArrayList<Integer> stokbarang = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Goodang::new);
    }

    public Goodang() {
        JFrame frame = new JFrame("Goodang"); //ini buat yg atas tuh
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // kalo ngeclose, dia berhentiin aplikasi secara penuh
        // JFrame.EXIT_ON_CLOSE: nutup aplikasi sepenuhnya
        // JFrame.DISPOSE_ON_CLOSE: nutup jendela tetapi tetap menjalankan aplikasi
        // JFrame.DO_NOTHING_ON_CLOSE: tidak melakukan apa-apa saat jendela ditutup, memungkinkan pengembang untuk menangani penutupan secara manual
        // JFrame.HIDE_ON_CLOSE: menyembunyikan jendela tetapi tidak menutup aplikasi
        frame.setSize(480, 800); // ukuran ui
        frame.setLayout(new BorderLayout()); // buat membagi jd 5 area

        JTextArea areaoutput = new JTextArea(); // ini area yg di tengah tu bang messi, jadi buat anu... apa namanya, ngeluarin output dr input yg kau masukkan td
        areaoutput.setEditable(false); // biar gabisa diedit-edit, soalnya bisa ngerusak jumlah di dalemnya

        JPanel panelinput = new JPanel(); // ini panel input di atas bang
        panelinput.setLayout(new GridLayout(3, 2)); // 3 baris 2 kolom, btw ini nanti ubah ya jadi 5 baris 2 kolom // nama dan jumlahnya atas bawah, add dan subtractnya dijejerin

        JTextField inputnama = new JTextField();
        JTextField inputjumlah = new JTextField();

        JButton addbut = new JButton("Tambah Stok");
        JButton subtract = new JButton("Kurangi Stok");
        JButton view = new JButton("Lihat Stok");

        panelinput.add(new JLabel("Nama Barang:"));
        panelinput.add(inputnama);
        panelinput.add(new JLabel("Jumlah:"));
        panelinput.add(inputjumlah);
        panelinput.add(addbut);
        panelinput.add(subtract);

        frame.add(panelinput, BorderLayout.NORTH);
        frame.add(new JScrollPane(areaoutput), BorderLayout.CENTER); // area yang nampilin outputnya ditaroh di tengah
        frame.add(view, BorderLayout.SOUTH);

        addbut.addActionListener(e -> {
            String nama = inputnama.getText();
            int jumlah = inputjumlah(inputjumlah, areaoutput);
            if (jumlah > 0) {
                int index = namabarang.indexOf(nama);
                if (index != -1) {
                    stokbarang.set(index, stokbarang.get(index) + jumlah);
                } else {
                    namabarang.add(nama);
                    stokbarang.add(jumlah);
                }
                areaoutput.append("Stok " + jumlah + " ditambahkan untuk " + nama + ".\n"); // WARN! ubah ini jadi 2 kolom, nama, jumlah, dan keterangan (add or sub)
            }
            inputnama.setText("");
            inputjumlah.setText("");
        });

        subtract.addActionListener(e -> {
            String nama = inputnama.getText();
            int jumlah = inputjumlah(inputjumlah, areaoutput);
            if (jumlah > 0) {
                int index = namabarang.indexOf(nama);
                if (index != -1) {
                    if (stokbarang.get(index) >= jumlah) {
                        stokbarang.set(index, stokbarang.get(index) - jumlah);
                        areaoutput.append("Stok " + jumlah + " dikurangi untuk " + nama + ".\n");
                    } else {
                        areaoutput.append("Stok tidak cukup untuk " + nama + ".\n");
                    }
                } else {
                    areaoutput.append("Nama barang tidak ditemukan.\n");
                }
            }
            inputnama.setText("");
            inputjumlah.setText("");
        });

        view.addActionListener(e -> {
            areaoutput.append("\nDaftar Stok Barang:\n");
            for (int i = 0; i < namabarang.size(); i++) {
                areaoutput.append(namabarang.get(i) + ": " + stokbarang.get(i) + "\n");
            }
        });

        frame.setVisible(true);
    }

    private int inputjumlah(JTextField inputjumlah, JTextArea areaoutput) {
        int jumlah = -1;
        while (jumlah < 0) {
            try {
                jumlah = Integer.parseInt(inputjumlah.getText());
                if (jumlah <= 0) {
                    areaoutput.append("Jumlah harus lebih dari 0.\n");
                }
            } catch (NumberFormatException ex) {
                areaoutput.append("Jumlah harus berupa angka.\n");
            }
           
            if (jumlah < 0) {
                inputjumlah.setText(JOptionPane.showInputDialog("Masukkan jumlah barang:"));
            }
        }
        return jumlah;
    }
}

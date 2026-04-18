import java.util.Scanner;

public class InputManager {

    private Scanner scanner;
    private Helper  helper;

    public InputManager(Scanner scanner) {
        this.scanner = scanner;
        this.helper  = new Helper(scanner);
    }

    public String bacaString() {
        return helper.bacaString();
    }

    public int bacaInt(String prompt) {
        return helper.bacaInt(prompt);
    }

    public int bacaIntOpsional(String prompt, int defaultNilai) {
        return helper.bacaIntOpsional(prompt, defaultNilai);
    }

    public double bacaDouble(String prompt) {
        return helper.bacaDouble(prompt);
    }

    public double bacaDoubleOpsional(String prompt, double defaultNilai) {
        return helper.bacaDoubleOpsional(prompt, defaultNilai);
    }

    public String bacaRaw() {
        return helper.bacaRaw();
    }

    private static class Helper {

        private Scanner scanner;

        Helper(Scanner scanner) {
            this.scanner = scanner;
        }

        String bacaString() {
            while (true) {
                String hasil = scanner.nextLine().trim();
                if (!hasil.isEmpty()) {
                    return hasil;
                }
                System.out.print("  [!] Tidak boleh kosong, coba lagi: ");
            }
        }

        int bacaInt(String prompt) {
            while (true) {
                System.out.print(prompt);
                String masukan = scanner.nextLine().trim();
                try {
                    return Integer.parseInt(masukan);
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Harus angka bulat. Coba lagi.");
                }
            }
        }

        int bacaIntOpsional(String prompt, int defaultNilai) {
            while (true) {
                System.out.print(prompt);
                String masukan = scanner.nextLine().trim();
                if (masukan.isEmpty()) return defaultNilai;
                try {
                    return Integer.parseInt(masukan);
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Harus angka bulat. Coba lagi.");
                }
            }
        }

        double bacaDouble(String prompt) {
            while (true) {
                System.out.print(prompt);
                String masukan = scanner.nextLine().trim();
                try {
                    return Double.parseDouble(masukan);
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Harus angka. Coba lagi.");
                }
            }
        }

        double bacaDoubleOpsional(String prompt, double defaultNilai) {
            while (true) {
                System.out.print(prompt);
                String masukan = scanner.nextLine().trim();
                if (masukan.isEmpty()) return defaultNilai;
                try {
                    return Double.parseDouble(masukan);
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Harus angka. Coba lagi.");
                }
            }
        }

        String bacaRaw() {
            return scanner.nextLine().trim();
        }
    }
}
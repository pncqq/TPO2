import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Service s = new Service("United Kingdom");
        String weatherJson = s.getWeather("Whitchurch");
        System.out.println(weatherJson);

        Double rate1 = s.getRateFor("USD");
        System.out.printf("Aktualny kurs dla podanej waluty to: %s%n", rate1);
        Double rate2 = s.getNBPRate();
        System.out.printf("Aktualny kurs podanej waluty do PLN to: %s złotych%n", rate2);

        //GUI
        //InvokeLater nie pokaże GUI,
        //dopóki wszystkie elementy sie nie zaladuja
        SwingUtilities.invokeLater(() -> new GUI(s));
    }
}
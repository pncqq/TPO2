import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main {
    public static void main(String[] args) {
        Service s = new Service("Poland");
        String weatherJson = s.getWeather("Warsaw");
        System.out.println(weatherJson);
//        Double rate1 = s.getRateFor("USD");
//        Double rate2 = s.getNBPRate();


        //GUI
//        // Tworzenie okna aplikacji
//        JFrame frame = new JFrame("Pogoda i kurs waluty");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 200);
//        frame.setLocationRelativeTo(null);
//
//        // Tworzenie panelu z polami tekstowymi i etykietami
//        JPanel panel = new JPanel();
//        JLabel cityLabel = new JLabel("Miasto:");
//        JTextField cityField = new JTextField(20);
//        JLabel countryLabel = new JLabel("Kraj:");
//        JTextField countryField = new JTextField(20);
//        JLabel currencyLabel = new JLabel("Waluta:");
//        JTextField currencyField = new JTextField(3);
//        panel.add(cityLabel);
//        panel.add(cityField);
//        panel.add(countryLabel);
//        panel.add(countryField);
//        panel.add(currencyLabel);
//        panel.add(currencyField);
//
//        // Tworzenie przycisku do pobierania informacji
//        JButton button = new JButton("Pobierz informacje");
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String city = cityField.getText();
//                String country = countryField.getText();
//                String currency = currencyField.getText();
//                String weather = getWeather(city, country);
//                double exchangeRate = getExchangeRate(currency, country);
//                double plnRate = getPLNRate(currency, country);
//                String wikiPage = getWikiPage(city);
//
//                // Tworzenie etykiet z pobranymi informacjami
//                JLabel weatherLabel = new JLabel(weather);
//                JLabel exchangeRateLabel = new JLabel("Kurs wymiany: " + exchangeRate);
//                JLabel plnRateLabel = new JLabel("Kurs NBP: " + plnRate);
//                JLabel wikiLabel = new JLabel("<html><a href=\"" + wikiPage + "\">Link do strony wiki</a></html>");
//
//                // Tworzenie panelu z wynikami
//                JPanel resultPanel = new JPanel();
//                resultPanel.add(weatherLabel);
//                resultPanel.add(exchangeRateLabel);
//                resultPanel.add(plnRateLabel);
//                resultPanel.add(wikiLabel);
//
//                // Dodanie panelu z wynikami do okna aplikacji
//                frame.getContentPane().add(resultPanel, BorderLayout.CENTER);
//                frame.pack();
//            }
//        });
//        panel.add(button);
//
//        // Dodanie panelu z polami tekstowymi i etykietami do okna aplikacji
//        frame.getContentPane().add(panel, BorderLayout.NORTH);
//
//        // Wyświetlenie okna aplikacji
//        frame.setVisible(true);
    }


}
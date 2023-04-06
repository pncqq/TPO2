/**
 *
 * @author Michalski Filip S24916
 *
 */

package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;

public class GUI extends JFrame {
    private Service service;
    private String city;
    private String country;
    private String currency;
    private JButton getWeatherButton;
    private JLabel temperatureVal;
    private JPanel mainPanel;
    private JButton getExchangeRateButton;
    private JLabel exchangeRate;
    private JButton getNBPRate;
    private JLabel nbpRate;
    private JPanel webPanel;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField currencyField;
    private JButton getWiki;
    private final JFrame jFrame;
    private final JFXPanel jfxPanel = new JFXPanel();

    public GUI() {
        jFrame = this;
        jFrame.setTitle("Aplikacja webowa:)");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        webPanel.add(jfxPanel);
        this.add(mainPanel);
        this.pack();


        //Przyciski
        getWeatherButton.addActionListener(e -> {
            //Inicjalizacja z pól tekstowych
            city = cityField.getText();
            country = countryField.getText();
            currency = currencyField.getText();

            //Tworzenie service
            service = new Service(country);

            temperatureVal.setText(service.getWeather(city));
            jFrame.pack();
        });

        getExchangeRateButton.addActionListener(e -> {
            exchangeRate.setText(String.format("Aktualny kurs dla podanej waluty to: %s", service.getRateFor(currency)));
            jFrame.pack();
        });

        getNBPRate.addActionListener(e -> {
            nbpRate.setText(String.format("Aktualny kurs podanej waluty do PLN to: %s złotych\n", service.getNBPRate()));
            jFrame.pack();
        });

        getWiki.addActionListener(e -> {
            Platform.runLater(this::createJFXContent);
            jFrame.pack();
        });
    }

    private void createJFXContent() {
        WebView webView = new WebView();
        webView.getEngine().load("https://en.wikipedia.org/wiki/" + city);
        Scene scene = new Scene(webView);
        jfxPanel.setScene(scene);
    }
}


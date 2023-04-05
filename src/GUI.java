import javax.swing.*;

public class GUI extends JFrame {
    private JButton getWeatherButton;
    private JLabel temperatureVal;
    private JPanel mainPanel;
    private JButton getExchangeRateButton;
    private JLabel exchangeRate;
    private JButton getNBPRate;
    private JLabel nbpRate;
    private JFrame jFrame;

    public GUI(Service service) {
        jFrame = this;
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(mainPanel);
        this.pack();

        getWeatherButton.addActionListener(e -> {
            temperatureVal.setText(service.getWeather("Whitchurch"));
            jFrame.pack();
        });

        getExchangeRateButton.addActionListener(e -> {
            exchangeRate.setText(String.format("Aktualny kurs dla podanej waluty to: %s", service.getRateFor("USD")));
            jFrame.pack();
        });

        getNBPRate.addActionListener(e -> {
            nbpRate.setText(String.format("Aktualny kurs podanej waluty do PLN to: %s złotych\n", service.getNBPRate()));
            jFrame.pack();
        });
    }
}


/**
 *
 * @author Michalski Filip S24916
 *
 */

package zad1;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Service s = new Service("Sweden");

        String weatherJson = s.getWeather("Stockholm");
        System.out.println(weatherJson);

        Double rate1 = s.getRateFor("USD");
        System.out.printf("Aktualny kurs dla podanej waluty to: %s%n", rate1);

        Double rate2 = s.getNBPRate();
        System.out.printf("Aktualny kurs podanej waluty do PLN to: %s złotych%n", rate2);


        //GUI
        //InvokeLater nie pokaże GUI,
        //dopóki wszystkie elementy sie nie zaladuja
        SwingUtilities.invokeLater(GUI::new);
    }


}
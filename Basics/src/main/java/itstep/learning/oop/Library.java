package itstep.learning.oop;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Literature> funds;   // List - interface for collection

    public Library() {
        funds = new ArrayList<>();    // <> - diamond operator
    }

    public void add(Literature literature) {
        funds.add(literature);
    }

    public void printFunds() {

        if(funds.size() == 0) {
            System.out.println("Funds are empty");
        }
        else {

            for(Literature literature : funds){
                System.out.println(literature);
            }
        }
    }

    public void showPrinted() {

        for(Literature literature : funds){

            if(literature instanceof Printed){
                System.out.println(literature.toString());
            }
        }
    }

    public void playAll(){

        for(Literature literature : funds){

            if(literature instanceof Playable){
                ((Playable) literature).play();
            }
        }
    }

    public void showPresentable() {

        for(Literature literature : funds){

            if(literature instanceof Presentable){
                System.out.println(literature.toString());
            }
        }
    }
}

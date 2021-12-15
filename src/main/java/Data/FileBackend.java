package Data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FileBackend {

    private final String filename;
    private final Random random = new Random();
    private final ArrayList<String> values = new ArrayList<>();


    public FileBackend(String filename){
        this.filename = filename;
    }



    public String load(){
        try {
            File fil = new File(filename);

            fil.createNewFile();
            Scanner read = new Scanner(fil);

            while(read.hasNext()){

                values.add(read.nextLine());

            }
            read.close();
        }catch (IOException ex){

            System.out.println(ex.getMessage());

        }
        String loadRandomItems;

        if(!ListIsEmpty()){
            loadRandomItems = values.get(random.nextInt(values.size()));
            return  loadRandomItems;
        }
        else{

            return "File dont not have any items to add. Please add some items to fil '" + filename + "' ";
        }
    }

    public boolean ListIsEmpty(){

        return values.isEmpty();
    }





}

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        SimpleDateFormat RDF = new SimpleDateFormat("yyyy.MM.dd");

        System.out.println("Hello and welcome!");

        Set<Employee> employees = new TreeSet<>(Comparator.comparing(Employee::getCode));




        Scanner  input = new Scanner(System.in);
        boolean folytat = true;
        boolean bovit;
        int muvelet = -1;

        while(folytat){

            muvelet = choose(input);


            if(muvelet == 0){
                folytat = false;
            }
            else if (muvelet == 1) {
                employees = Beolvas();
                Employee st = eemployeeFelvetel(RDF);

                if(st == null){
                    //System.out.println("A kód már használatban van");
                    continue;
                }

                bovit = employees.add(st);
                if(bovit == false){
                    System.out.println("A kód már használatban van");
                }

                Kiir(employees,-1);


            }
            else if (muvelet == 2) {
                employees = Beolvas();

                for(Employee e  : employees){
                    System.out.println(e);
                }



            }
            else if (muvelet == 3) {
                employees = Beolvas();
                int kod = kodBeovlas(input);


                Kiir(employees,kod);
                employees = Beolvas();

            }

        }

        input.close();



    }

    public static int choose(Scanner input){

        System.out.println("Exit (0)");
        System.out.println("Insert employee(1)");
        System.out.println("List employees(2)");
        System.out.println("Delete employee(3)");
        System.out.println("Enter a number:");
        return Integer.parseInt(input.nextLine());
    }

    public static Employee eemployeeFelvetel(SimpleDateFormat RDF){

        //int x = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("Adja meg a dolgozó nevét:");
        String nev = input.nextLine();
        if(nev.length()>30){
            System.out.println("Hibas input");
            return null;
        }


        System.out.println("Adja meg a dolgozó kódját:");
        int kod = Integer.parseInt(input.nextLine());
        if(kod<0 || kod>999){
            System.out.println("Hibás input");
            return null;
        }

        System.out.println("Adja meg a dolgozó születési évét:");
        String date = input.nextLine();
        boolean b = DAteFormatChecker(date,RDF);

        if(date.length()>16 || b == false){
            System.out.println("Hibás input");
            return null;
        }


        System.out.println("Adja meg a dolgozó fizetését:");
        int salary = Integer.parseInt(input.nextLine());
        if(salary<0 || salary>999999){
            System.out.println("Hibás input");
            return null;
        }


        System.out.println("Adja meg a dolgozó városát:");
        String town = input.nextLine();

        System.out.println("Adja meg a dolgozó IQ szintjét:");
        String iq = input.nextLine();

        Employee d = new Employee(kod,nev,date,salary,town,iq);

        return d;
    }






    public static boolean DAteFormatChecker(String SDate, SimpleDateFormat RDF){
        try{
            Date date = RDF.parse(SDate);
            return true;
        } catch(ParseException ef) {return false;}
    }

    public static int kodBeovlas(Scanner input){

        System.out.println("Adjon meg egy kódot:");
        int kod = Integer.parseInt(input.nextLine());
        return kod;

    }



    public static void Kiir(Set<Employee> osztaly,int kivetel){
        try {
            FileOutputStream fos = new FileOutputStream("Employee.dat",false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            int x = osztaly.size();

            if(kivetel != -1){
                x--;
            }

            oos.writeInt(x);
            for(Employee d : osztaly){
                if(d.getCode() != kivetel)
                    oos.writeObject(d);
            }

            oos.flush();
            oos.close();
            fos.flush();
            fos.close();

            System.out.println("Data is written to file!");
        } catch (IOException ioe) {
            System.out.println("File write exception: "+ioe);
        }

    }


    public static Set<Employee> Beolvas(){
        Set<Employee> osztaly = new TreeSet<>(Comparator.comparing(Employee::getCode));
        try {
            FileInputStream fis = new FileInputStream("Employee.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            int db = ois.readInt();
            Object student = null;
            System.out.println("Data from file:");
            for (int i = 0; i < db; i++) {
                student = ois.readObject();
                Employee st = (Employee)student;
                osztaly.add(st);
                //System.out.println(st);

            }

            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("File read exception: "+e);
        }

        return osztaly;
    }
}
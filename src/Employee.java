import java.io.Serializable;
import java.util.Comparator;

public class Employee implements Serializable, Comparator<Employee> {

        private int code;
        private String name;
        private String birthDate;
        private int salary;
        private String town;

        private String iq;

        private static final long serialVersionUID = 6529685098267757690L;

        public Employee(int code, String name, String birthDate, int salary, String town, String iq) {
            this.code = code;
            this.name = name;
            this.birthDate = birthDate;
            this.salary = salary;
            this.town = town;
            this.iq = iq;
        }

    @Override
    public String toString() {
        return "Employee{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", salary=" + salary +
                ", town='" + town + '\'' +
                ", iq='" + iq + '\'' +
                '}';
    }

    public int getCode() {
            return code;
        }
        public String getName() {
            return name;
        }

   

        @Override
        public int hashCode() {
            return this.code;
        }

    @Override
    public int compare(Employee o1, Employee o2) {


        //return (Integer)o1.getCode().compareTo((Integer)o2.getCode());
        return Integer.compare(o1.getCode(),o2.getCode());
    }

    @Override
        public boolean equals(Object obj) {

            // if both the object references are
            // referring to the same object.
            if(this == obj)
                return true;

            // it checks if the argument is of the
            // type Geek by comparing the classes
            // of the passed argument and this object.
            // if(!(obj instanceof Geek)) return false; ---> avoid.
            if(obj == null || obj.getClass()!= this.getClass())
                return false;

            // type casting of the argument.
            Employee st = (Employee) obj;

            // comparing the state of argument with
            // the state of 'this' Object.
            return (st.code == this.code  );

        }


}

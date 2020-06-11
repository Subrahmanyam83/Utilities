package concepts.reflcetions.dynamicproxies;

public class AboutMeImplementation implements AboutMe{

    public AboutMeImplementation(){
    }

    @Override
    public String getName() {
        System.out.println("Calling actual getName() method");
        return "Subrahmanyam";
    }

    @Override
    public int getAge() {
        System.out.println("Calling actual getAge() method");
        return 37;
    }
}

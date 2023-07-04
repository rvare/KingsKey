public class BackendTest {
   public static void main(String[] args) {
      Backend b = Backend.createInstance();
      b.gatherUserData();
      b.printData();
   }
}
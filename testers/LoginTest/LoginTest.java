import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginTest {

    public static void main(String[] args) {
        LoginWindowOld lw = new LoginWindowOld();
        //boolean signal = lw.showLogin();

        LoginWindow2 lw2 = new LoginWindow2();
        lw2.showLogin();
        System.exit(0);
    }
}
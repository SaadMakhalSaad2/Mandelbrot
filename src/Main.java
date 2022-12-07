
import java.awt.Container;
import javax.swing.JFrame;

public class Main extends JFrame {

    private final int CA_WIDTH = 768;
    private final int CA_HEIGHT = 768;
    private final String CA_TITLE = "Mapping";

    public Main() {
        this.configure();
    } // Mapping()

    private void configure() {
        this.setSize(CA_WIDTH, CA_HEIGHT);
        this.setTitle(CA_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        pane.add(new MappingPanel());

        this.setVisible(true);
    } // configure()

    public static void main(String[] args) {
        Main mapping
                = new Main();
    } // main( String [] )

} // Mapping

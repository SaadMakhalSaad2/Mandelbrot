
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Main extends JFrame {

    private final int CA_WIDTH = 512;
    private final int CA_HEIGHT = 512;
    private final String CA_TITLE = "Mapping";

    public Main() {
        this.configure();
    } // Mapping()

    private void configure() {
        this.setSize(CA_WIDTH, CA_HEIGHT);
        this.setTitle(CA_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MappingPanel panel = new MappingPanel();
        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setLayout(new ScrollPaneLayout());

        this.add(scrollPane);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Main mapping
                = new Main();
    } // main( String [] )

} // Mapping

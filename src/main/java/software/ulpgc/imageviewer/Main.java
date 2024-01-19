package software.ulpgc.imageviewer;

import java.io.File;

public class Main {
    public static final String root = "E:/Usuario/Documents/INGENIERÍA INFORMÁRICA/3º Tercero/Ingeniería del Software II/ImageViewer";
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Image image = new FileImageLoader(new File(root)).load();
        frame.imageDisplay().show(image);
        frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
        frame.add(">", new NextImageCommand(frame.imageDisplay()));
        frame.setVisible(true);
    }}

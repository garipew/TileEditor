import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class EditorPanel extends JPanel implements Runnable {

    // Componentes
    Controlador controlador = new Controlador();
    Thread editor;
    TileMap mapa;
    Seletor seletor;
    Observador observador;


    // Propriedades
    public final int maxLinhas;
    public final int maxColunas;
    public final int maxLinhasTela = 20;
    public final int maxColunasTela = 30;
    public final int tamanhoTile = 32;
    public Point mouse, offset;

    public EditorPanel(){

        this.setBackground(Color.GRAY);
        this.setPreferredSize(new Dimension(maxColunasTela*tamanhoTile, maxLinhasTela*tamanhoTile));
        this.setFocusable(true);

        this.addMouseListener(controlador);
        this.addKeyListener(controlador);

        String[] args = readDefaultValues();
        maxColunas = Integer.parseInt(args[1]);
        maxLinhas = Integer.parseInt(args[2]);

        this.mapa = new TileMap(this, args[0]);
        this.seletor = new Seletor(this);
        this.observador = new Observador(this);


    }


    public String[] readDefaultValues(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nome Largura Altura");
        return scanner.nextLine().split(" ");

    }


    public void startPanelThread(){

        editor = new Thread(this);
        editor.start();

    }

    public void run(){


        int FPS = 60;
        double drawInterval = 1000000000.0f/ FPS;
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();


        while(editor != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }


        }


    }


    public void update(){

        mouse = MouseInfo.getPointerInfo().getLocation();
        offset = this.getLocationOnScreen();

        mouse.x -= offset.x;
        mouse.y -= offset.y;

        observador.update();

        if(controlador.salvar){
            mapa.salvar();
        }

    }


    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D pincel = (Graphics2D) g;

        mapa.desenhar(pincel);
        seletor.desenhar(pincel);


    }

}

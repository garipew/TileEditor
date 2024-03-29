import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TileMap {

    // Componentes
    EditorPanel editor;
    File save;

    // Propriedades
    int[][] mapa;
    BufferedImage[] tiles;
    public final int tamanho;
    String nome;


    public TileMap(EditorPanel ed, String nome){

        this.tamanho = 20;

        this.editor = ed;
        this.mapa = new int[editor.maxColunas][editor.maxLinhas];
        this.tiles = new BufferedImage[15];
        this.nome = nome;

        carregarMapa();
        carregarTiles();

    }


    public void carregarMapa() {


        checarSave(nome);

        try{

            FileInputStream inputStream = new FileInputStream(save);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int lin = 0;
            String[] numerosLinha;
            int celulaAtual;


            while(lin < editor.maxLinhas){

                String linha = bufferedReader.readLine();

                linha = linha == null ? "0 ".repeat(editor.maxColunas) : linha;
                numerosLinha = linha.split(" ");

                while(col < editor.maxColunas) {

                    celulaAtual = Integer.parseInt(numerosLinha[col]);

                    mapa[col][lin] = celulaAtual;
                    col++;

                }
                col = 0;
                lin++;

            }

            bufferedReader.close();


        } catch (IOException e){

            e.printStackTrace();

        }


    }


    public void checarSave(String nome){

        try {

            save = new File( "saves/" + nome + ".txt");
            if(save.createNewFile()){
                System.out.println("File created: " + nome + ".txt");
            } else{
                System.out.println("Save file opened");
            };

        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public void carregarTiles(){

        setup(0, "ground");
        setup(1, "ground1");
        setup(2, "hole");
        setup(3, "wall1");
        setup(4, "wall2");
        setup(5, "wall3");
        setup(6, "wall4");
        setup(7, "wall5");
        setup(8, "wall6");
        setup(9, "wall7");
        setup(10, "water");
        setup(11, "gatlingun");
        setup(12, "pistol");
        setup(13, "sniper");

    }


    public void setup(int index, String name){

        tiles[index] = redimensionarImagem(carregarImagem(name), editor.tamanhoTile, editor.tamanhoTile);

    }

    public BufferedImage carregarImagem(String name){

        BufferedImage imagem = null;

        try{
            imagem = ImageIO.read(getClass().getResourceAsStream("/tile/" + name + ".png"));

        }catch(IOException e){
            e.printStackTrace();
        }

        return imagem;
    }

    public BufferedImage redimensionarImagem(BufferedImage imagem, int largura, int altura){

        BufferedImage novaImagem = new BufferedImage(largura, altura, imagem.getType());
        Graphics2D pincel = novaImagem.createGraphics();

        pincel.drawImage(imagem, 0, 0, largura, altura, null);
        pincel.dispose();

        return novaImagem;

    }


    public void desenhar(Graphics2D pincel){

        int colMundo = 0;
        int linMundo = 0;
        int celulaAtual;
        int mundoX;
        int mundoY;
        int telaX;
        int telaY;

        while(linMundo < editor.maxLinhas){

            celulaAtual = mapa[colMundo][linMundo];

            mundoX = colMundo;
            mundoY = linMundo;
            telaX = mundoX - editor.observador.colunaMundo + editor.observador.colunaTela;
            telaY = mundoY - editor.observador.linhaMundo + editor.observador.linhaTela;


            if((mundoX + 1 > editor.observador.colunaMundo - editor.observador.colunaTela) &&
                    (mundoX - 1 < editor.observador.colunaMundo + editor.observador.colunaTela) &&
                    (mundoY + 1 > editor.observador.linhaMundo - editor.observador.linhaTela) &&
                    (mundoY - 1 < editor.observador.linhaMundo + editor.observador.linhaTela)) {

                pincel.drawImage(tiles[celulaAtual], telaX*editor.tamanhoTile, telaY*editor.tamanhoTile, null);
            }
            colMundo++;

            if(colMundo >= editor.maxColunas){

                colMundo = 0;
                linMundo++;

            }


        }


    }


    public void desenharGrid(Graphics2D pincel, int colunaInicial, int linhaInicial, int largura){

        int col = 0;
        int lin = 0;
        int atual = 0;
        int tamanho = editor.tamanhoTile*2;

        while(atual < tiles.length) {

            pincel.drawImage(tiles[atual], (colunaInicial + col) * editor.tamanhoTile, (linhaInicial + lin) * editor.tamanhoTile, tamanho, tamanho, null);
            atual++;
            col+=2;

            if(col > largura){
                col = 0;
                lin+=2;
            }
        }
    }


    public void rabiscar(int tile, Point coordenada){

        int coluna = coordenada.x / editor.tamanhoTile;
        int linha = coordenada.y / editor.tamanhoTile;

        coluna += editor.observador.colunaMundo - editor.observador.colunaTela;
        linha += editor.observador.linhaMundo - editor.observador.linhaTela;

        if(tile != -1) {

            if(coluna < mapa.length &&
            coluna >= 0 &&
            linha < mapa[0].length &&
            linha >= 0) {

                mapa[coluna][linha] = tile;

            }
        }

    }


    public void salvar(){

        int col = 0;
        int lin = 0;

        try {
            FileWriter file = new FileWriter(save);

            while(lin < mapa[0].length){


                file.write(mapa[col][lin] + " ");
                col++;

                if(col > mapa.length - 1){

                    file.write('\n');
                    col = 0;
                    lin++;

                }

            }

            file.close();
            System.out.println("Salvamento Concluido.");

        } catch(IOException e){

        }
    }


}

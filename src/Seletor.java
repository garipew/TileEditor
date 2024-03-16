import java.awt.*;

public class Seletor {

    // Componentes
    EditorPanel editor;
    TileMap mapa;
    Rectangle[] tiles;

    // Propriedades
    public final int tamanho;


    public Seletor(EditorPanel editor){

        this.editor = editor;
        this.mapa = editor.mapa;
        this.tamanho = 11;
        this.tiles = new Rectangle[]{

                new Rectangle(23*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(25*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(23*editor.tamanhoTile, 4*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(25*editor.tamanhoTile, 4*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(23*editor.tamanhoTile, 6*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(25*editor.tamanhoTile, 6*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(23*editor.tamanhoTile, 8*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(25*editor.tamanhoTile, 8*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(23*editor.tamanhoTile, 10*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(25*editor.tamanhoTile, 10*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(23*editor.tamanhoTile, 12*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(25*editor.tamanhoTile, 12*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(23*editor.tamanhoTile, 14*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),
                new Rectangle(25*editor.tamanhoTile, 14*editor.tamanhoTile, 2*editor.tamanhoTile, 2*editor.tamanhoTile),

        };

    }

    public void desenhar(Graphics2D pincel){

        int col = editor.maxColunasTela - tamanho + 4;

        editor.observador.desenhar(pincel, col, 1);
        mapa.desenharGrid(pincel, col, 2, 3);

    }


    public int selecionarTile(Point mouse){

        for(int i =0; i < tiles.length; i++){

            if(tiles[i] != null && tiles[i].contains(mouse)){
                return i;
            }

        }

            return -1;

    }


}

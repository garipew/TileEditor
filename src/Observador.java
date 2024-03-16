public class Observador {

    // Componentes
    EditorPanel editor;
    Controlador controlador;

    // Propriedades
    public int colunaMundo;
    public int linhaMundo;
    public int colunaTela;
    public int linhaTela;
    public int[] direcao;
    public int tileSelecionada = -1;



    public Observador(EditorPanel editor){

        this.editor = editor;
        this.controlador = editor.controlador;

        this.colunaMundo = 10;
        this.linhaMundo = 10;

        this.colunaTela = editor.mapa.tamanho/2;
        this.linhaTela = editor.maxLinhasTela/2;

        this.direcao = new int[]{0, 0};

    }


    public void update(){

        mudarDirecao();
        mover();

        if(controlador.mouseDir) {

            if(editor.mouse.x > 20 * editor.tamanhoTile) {
                tileSelecionada = editor.seletor.selecionarTile(editor.mouse);
            } else{
                editor.mapa.rabiscar(tileSelecionada, editor.mouse);
            }
        }

    }


    public void mudarDirecao(){


        if(controlador.cima){
            direcao[1] = -1;
        }
        if(controlador.esquerda){
            direcao[0] = -1;
        }
        if(controlador.baixo){
            direcao[1] = 1;
        }
        if(controlador.direita){
            direcao[0] = 1;
        }


    }


    public void mover(){

        colunaMundo += direcao[0];
        linhaMundo += direcao[1];

        direcao[0] = 0;
        direcao[1] = 0;

    }

}

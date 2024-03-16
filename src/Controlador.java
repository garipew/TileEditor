import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controlador implements KeyListener, MouseListener {


    public boolean mouseDir = false;
    public boolean mouseEsq = false;
    public boolean cima = false;
    public boolean baixo = false;
    public boolean esquerda = false;
    public boolean direita = false;
    public boolean salvar = false;

    public void setMouseDir(boolean mouseDir) {
        this.mouseDir = mouseDir;
    }

    public void setMouseEsq(boolean mouseEsq) {
        this.mouseEsq = mouseEsq;
    }

    public void setCima(boolean cima) {
        this.cima = cima;
    }

    public void setBaixo(boolean baixo) {
        this.baixo = baixo;
    }

    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(boolean direita) {
        this.direita = direita;
    }

    public void setSalvar(boolean salvar) {
        this.salvar = salvar;
    }

    public void keyTyped(KeyEvent key){
    }


    public void keyPressed(KeyEvent key){

        int keyCode = key.getKeyCode();

        switch(keyCode){
            case KeyEvent.VK_W -> setCima(true);
            case KeyEvent.VK_A -> setEsquerda(true);
            case KeyEvent.VK_S -> setBaixo(true);
            case KeyEvent.VK_D -> setDireita(true);
            case KeyEvent.VK_Q -> setSalvar(true);
        }

    }


    public void keyReleased(KeyEvent key){

        int keyCode = key.getKeyCode();

        switch(keyCode){
            case KeyEvent.VK_W -> setCima(false);
            case KeyEvent.VK_A -> setEsquerda(false);
            case KeyEvent.VK_S -> setBaixo(false);
            case KeyEvent.VK_D -> setDireita(false);
            case KeyEvent.VK_Q -> setSalvar(false);
        }

    }


    public void mouseClicked(MouseEvent e){
    }


    public void mousePressed(MouseEvent e){

        int botao = e.getButton();


        if(botao == MouseEvent.BUTTON1){
            setMouseDir(true);
        } else if (botao == MouseEvent.BUTTON2) {
            setMouseEsq(true);
        }


    }


    public void mouseReleased(MouseEvent e){

        int botao = e.getButton();

        if(botao == MouseEvent.BUTTON1){
            setMouseDir(false);
        } else if (botao == MouseEvent.BUTTON2) {
            setMouseEsq(false);
        }


    }


    public void mouseEntered(MouseEvent e){}


    public void mouseExited(MouseEvent e){}
}

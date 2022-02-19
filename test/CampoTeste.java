import com.matheushcamilo.minesweeper.model.Campo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


//Inicializando o campo de teste localizado no centro
public class CampoTeste {
    private Campo campo;
    //Antes de cada teste, executar método abaixo
    @BeforeEach
    void inicializandoCampo(){
        campo = new Campo(3, 3);
    }
    /*
        Abaixo estão os testes
     */
    @Test
    void testeVizinhoReal1Esquerda(){
        Campo vizinho = new Campo(3,2);
        var resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    void testeVizinhoReal1Direita(){
        Campo vizinho = new Campo(3,4);
        var resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    void testeVizinhoReal1Cima(){
        Campo vizinho = new Campo(4,3);
        var resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    void testeVizinhoReal1Baixo(){
        Campo vizinho = new Campo(2,3);
        var resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoReal1Diagonal(){
        Campo vizinho = new Campo(4,4);
        var resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    @Test
    void testeNaoVizinhoReal1Diagonal(){
        Campo vizinho = new Campo(1,1);
        var resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }
    @Test
    void testeAlternarMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacao2Vezes(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }
    @Test
    void testeAbrirNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    //Para mostrar que, mesmo que o campo esteja minado, não consigo abrir, quando está marcado
    @Test
    void testeAbrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());

    }

    //Campos iniciam não minados e vizinho abre vizinho até que a vizinhança não esteja mais segura
    //Por abrir campo, pela recursividade, campo11 e campo22 precisam estar abertos também
    @Test
    void testeAbrirComVizinhos1(){
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2(){
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);
        campo12.minar();
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);
        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && !campo12.isAberto());
    }



}

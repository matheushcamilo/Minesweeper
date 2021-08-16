import com.matheushcamilo.minesweeper.model.Campo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//Inicializando o campo de teste localizado no centro
public class CampoTeste {
    private Campo campo;
    @BeforeEach
    void inicializandoCampo(){
        campo = new Campo(3, 3);
    }
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
    /*@Test
    void testeNaoVizinhoReal1Diagonal(){
        Campo vizinho = new Campo(1,1);
        var resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }*/

}

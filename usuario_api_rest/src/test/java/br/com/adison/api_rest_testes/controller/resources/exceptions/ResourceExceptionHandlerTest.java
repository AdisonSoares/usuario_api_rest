package br.com.adison.api_rest_testes.controller.resources.exceptions;

import br.com.adison.api_rest_testes.model.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * @Funcionalidade_original_testada: Esse método é para testar o "objectNotFound" que retorna um objeto
     * "ResponseEntity" do tipo "<StandardError>" com status e um body de acordo com os dois parâmetros: a
     * exceção lançada (ObjectNotFoundException exception) e a requisição HTTP (HttpServletRequest request).
     * (objectNotFound)<p>
     *
     * @Nomeação: Quando executar "ObjectNotFound" então retorne um objeto "ResponseEntity" com sucesso,
     * lancando uma exception. (whenObjectNotFoundthenReturnsAResponseEntityThrowingAnException).<p>
     *
     * @Response: O response armazena um objeto "ResponseEntity" no tipo "StandardError", isso é feito chamando
     * o método objectNotFound da classe "ResourceExceptionHandler", em seus parametros sao passados um objeto
     * "ObjectNotFoundException", passando a mensagem de erro, e um objeto "HttpServletRequest" mockado.<p>
     *
     * @Assertions:
     * - PRIMEIRA: afirma que o response nao esta nulo.<p>
     * - SEGUNDA: afirma que o corpo/body do response nao esta nulo.<p>
     * - TERCEIRA: afirma que o status do response corresponde a "HttpStatus.NOT_FOUND".<p>
     * - QUARTA: afirma que a classe do responde corresponde a "ResponseEntity.class".<p>
     * - QUINTA: afirma que a classe do body do responde corresponde a "StandardError.class".<p>
     * - SEXTA: afirma que a mensagem do body do responde corresponde a "Objeto não encontrado".<p>
     * - SETIMA: afirma que o status do body do responde corresponde a 404.<p>
     * - OITAVA: afirma que o caminho do response nao corresponde a "/user/2".<p>
     * - NONA: afirma que o local que ocorreu o erro do response nao corresponde a LocalDateTime.now().<p>
     *
     * @Assertion_notNull: Verifica se o objeto da classe passada não está nulo.<p>
     *
     * @Assertion_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Assertion_notEquals: Na primeira parte o atributo que nao deveria retornar e na segunda
     * o que está retornando.<p>
     *
     */
    @Test
    void whenObjectNotFoundthenReturnsAResponseEntityThrowingAnException() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException("Objeto não encontrado"),
                        new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());
        Assertions.assertEquals("Objeto não encontrado", response.getBody().getError());
        Assertions.assertEquals(404, response.getBody().getStatus());

        Assertions.assertNotEquals("/user/2", response.getBody().getPath());
        Assertions.assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void dataIntegrityViolationException() {
    }
}
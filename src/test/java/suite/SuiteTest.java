package suite;

import dev.matheushbmelo.classes.SaudacaoDiariaTest;
import dev.matheushbmelo.project.domain.service.ContaServiceTest;
import dev.matheushbmelo.project.domain.service.TransacaoServiceTest;
import dev.matheushbmelo.project.domain.service.UsuarioServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite de testes de software")
@SelectClasses(value = {
        SaudacaoDiariaTest.class,
        ContaServiceTest.class,
        TransacaoServiceTest.class,
        UsuarioServiceTest.class
})
public class SuiteTest {
}

import controllers.AuthController;

/**
 * Classe principal do sistema usando arquitetura MVC
 */
public class Main {
    public static void main(String[] args) {
        // Inicializa o sistema usando o padrão MVC
        AuthController authController = new AuthController();
        authController.iniciarSistema();
    }
}

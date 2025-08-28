public abstract class Usuario {
    protected String id;
    protected String nome;
    protected String email;
    protected String senha;
    protected TipoUsuario tipo;
    
    public Usuario(String id, String nome, String email, String senha, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }
    
    public boolean autenticar(String senha) {
        System.out.println("Autenticando usuário: " + nome);
        return this.senha.equals(senha);
    }
    
    public void alterarSenha(String novaSenha) {
        System.out.println("Alterando senha do usuário: " + nome);
        this.senha = novaSenha;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}

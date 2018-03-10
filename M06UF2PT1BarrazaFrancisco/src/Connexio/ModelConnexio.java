package Connexio;

public class ModelConnexio{
    
    private String db_ = "dgt";
    private String login_ = "dgt";
    private String password_ = "tomaya";
    private String url_ = "jdbc:mysql://localhost:3306/"+db_;
    
    public ModelConnexio(){
        
    }
    
    public ModelConnexio(String db, String login, String password, String url){
        this.db_=db;
        this.login_=login;
        this.password_=password;
        this.url_=url;
    }

    public String getDb() {
        return db_;
    }

    public void setDb(String db) {
        this.db_ = db_;
    }

    public String getLogin() {
        return login_;
    }

    public void setLogin(String login) {
        this.login_ = login_;
    }

    public String getPassword() {
        return password_;
    }

    public void setPassword(String password) {
        this.password_ = password_;
    }

    public String getUrl() {
        return url_;
    }

    public void setUrl(String url) {
        this.url_ = url;
    }
    
}
